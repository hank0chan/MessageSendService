package alidayu_demo.send;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
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
	
	// 发送短信消息
	public void sendSms() {
		try {
			// 获取指定项目ID(ProjectId)的配置信息 ==success
			MessageProject projectConfig = messageProject.get(1);		
			SmsConfig smsConfig = projectConfig.getSmsConfig();

			// 读取队列消息 ==success
			//MessageQueue queue = dao.getSmsMsg();
			MessageQueue queue = repository.get_h("get", MessageQueue.class, 1);
			
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
				
				// 消息存入Message主表(MessageId) ==success
				Message message = new Message();
				message.setMessageId(queue.getMessageId());
				message.setMessageType(queue.getMessageType());
				message.setProjectId(queue.getProjectId());
				message.setMessageContent(queue.getMessageContent());
				message.setRecNum(queue.getRecNum());
				message.setCreateTime(TimeUtils.ISO8601.format(new Date()));
				repository.create_h(message);
				
				// 从队列表中删除该MessageId的消息 ==success
				repository.delete_h(Message.class, new Criteria().with("messageId", queue.getMessageId()));
				
				// 每个消息分别存入消息明细表(MessageId,RecNum) ==success
				MessageDetail detail = new MessageDetail();
				detail.setMessageId(queue.getMessageId());
				//TODO ...补齐
				repository.create_h(detail);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	// 发送语音消息
	public void sendTts() {
		
	}
	
}




