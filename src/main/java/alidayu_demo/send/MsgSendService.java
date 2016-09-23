package alidayu_demo.send;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import alidayu_demo.config.entity.MessageProject;
import alidayu_demo.config.entity.SmsConfig;
import alidayu_demo.config.singleton.MessageConfigSingleton;
//import alidayu_demo.dao.MessageQueueDao;
import alidayu_demo.dao.mybatis.MessageRepository;
import alidayu_demo.entity.Message;
import alidayu_demo.entity.MessageDetail;
import alidayu_demo.entity.MessageQueue;
import alidayu_demo.util.Criteria;
import alidayu_demo.util.TimeUtils;

/**
 * 消息发送服务
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 14 Sep 201610:36:14
 * <p>类说明:
 */
public class MsgSendService {

	//private MessageConfig config = new MessageConfig();
	/** 配置文件信息 */
	private Map<Integer, MessageProject> messageProject = new HashMap<>();
	
	// 在初始化构造函数中加载读取配置信息
	public MsgSendService() {
		MessageConfigSingleton configSingleton = MessageConfigSingleton.getInstance();
		//config = configSingleton.getMessageConfig();
		messageProject = configSingleton.getMessageConfig().getProject();
	}
	
	MessageRepository repository;
	public void setMessageRepository(MessageRepository repository) {
		this.repository = repository;
	}
	
//	MessageQueueDao dao;
//	public void setMessageQueueDao(MessageQueueDao dao) {
//		this.dao = dao;
//	}
	
	/** 发送短信消息 */
	public void sendSms() {
		try {
			// 获取指定项目ID(ProjectId)的配置信息 ==success
			MessageProject projectConfig = messageProject.get(1);		
			SmsConfig smsConfig = projectConfig.getSmsConfig();

			// 读取队列消息 ==success
			//MessageQueue queue = dao.getSmsMsg();
			MessageQueue queue = repository.get_h("get", MessageQueue.class, 1);
			
			if(queue != null) {
				// 发起API请求
				TaobaoClient client = new DefaultTaobaoClient(projectConfig.getUrl(), projectConfig.getAppKey(), projectConfig.getSecret());
				AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
				req.setExtend("123456");
				req.setSmsType("normal");
				req.setSmsFreeSignName(smsConfig.getSignName());
				
				String param = smsConfig.getSmsParam();
				
				// 处理接收号码及消息内容
				req.setSmsParamString(param.replaceAll("value", queue.getMessageContent()));
				req.setRecNum(queue.getRecNum());
				
				req.setSmsTemplateCode(smsConfig.getTemplateCode());
				AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
				
				if(rsp.isSuccess() == true) {			// 调用API成功
					// 执行数据库操作，需要作为一个事务进行处理
					afterCalledSendApi(queue, rsp.getResult().getModel());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 发送语音消息 */
	public void sendTts() {	}
	
	/** 
	 * 调用API成功后的事务管理（SMS和TTS通用）
	 * @param queue：队列消息
	 * @param model：消息唯一标识BizID
	 */
	@Transactional(value="afterCalledSendApi",propagation=Propagation.REQUIRED)
	public void afterCalledSendApi(MessageQueue queue, String model) {
		
		// 事务1：消息存入Message主表(MessageId) ==success
		Message message = new Message();
		message.setMessageId(queue.getMessageId());
		message.setMessageType(queue.getMessageType());
		message.setProjectId(queue.getProjectId());
		message.setMessageContent(queue.getMessageContent());
		message.setRecNum(queue.getRecNum());
		message.setCreateTime(TimeUtils.ISO8601.format(new Date()));
		repository.create_h(message);
		
		// 事务2：从队列表中删除该MessageId的消息 ==success
		repository.delete_h(Message.class, new Criteria().with("messageId", queue.getMessageId()));
		
		// 事务3：每个消息分别存入消息明细表(MessageId,RecNum) ==success
		List<String> recNums = Arrays.asList(queue.getRecNum().split(","));
		for(String recNum : recNums) { // 根据每个接收号码存入明细表
			MessageDetail detail = new MessageDetail();
			detail.setMessageId(queue.getMessageId());
			detail.setRecNum(recNum);
			detail.setMsgId(model);  // 消息唯一标识BizID（任务主键）
			detail.setCreateTime(TimeUtils.ISO8601.format(new Date()));
			detail.setMaxSendTimes(5);
			detail.setMessageType(queue.getMessageType());
			detail.setRecTime("");
			detail.setSendTime(TimeUtils.ISO8601.format(new Date()));
			detail.setSendTimes(1);
			detail.setState(1);
			detail.setUpdateTime(TimeUtils.YYYYMMDDHHMMSS.format(new Date()));
			repository.create_h(detail);
		}
	}

}



