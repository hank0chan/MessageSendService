package alidayu_demo.subscribe;

import java.util.HashMap;
import java.util.Map;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TmcMessage;
import com.taobao.api.request.TmcMessagesConfirmRequest;
import com.taobao.api.request.TmcMessagesConsumeRequest;
import com.taobao.api.response.TmcMessagesConfirmResponse;
import com.taobao.api.response.TmcMessagesConsumeResponse;

import alidayu_demo.config.entity.MessageProject;
import alidayu_demo.config.singleton.MessageConfigSingleton;

/**
 * 消息订阅服务
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 14 Sep 201610:36:41
 * <p>类说明:
 */
public class MsgSubscribeService {
	
	private MessageProject messageProject = new MessageProject();
	
	public MsgSubscribeService() {
		MessageConfigSingleton configSingleton = MessageConfigSingleton.getInstance();
		messageProject = configSingleton.getMessageConfig().getProject().get(1);
	}
	
	// 消息订阅
	public void msgSendChecking() throws InterruptedException {
		
		String appKey = messageProject.getAppKey();
		String secret = messageProject.getSecret();
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", appKey, secret, "json");
		do {
		    long quantity = 100L;
		    TmcMessagesConsumeResponse rsp = null;
		    do {
		    	try {
			        TmcMessagesConsumeRequest req = new TmcMessagesConsumeRequest();
			        req.setQuantity(quantity);
			        req.setGroupName("default");
					rsp = client.execute(req);
				} catch (ApiException e) {
					e.printStackTrace();
				}
		        if (rsp.isSuccess() && rsp.getMessages() != null) {
		            for (TmcMessage msg : rsp.getMessages()) {

		            	//TODO 
		                // handle message
		                System.out.println(msg.getContent());
		                System.out.println(msg.getTopic());
		                // 获取消息发送结果，处理消息。。
		                // ... ... 
		                
		                try {
		                // confirm message
		                TmcMessagesConfirmRequest cReq = new TmcMessagesConfirmRequest();
		                cReq.setGroupName("default");
		                cReq.setsMessageIds(String.valueOf(msg.getId()));
		                TmcMessagesConfirmResponse cRsp;
						
							cRsp = client.execute(cReq);
							System.out.println(cRsp.getBody());
						} catch (ApiException e) {
							e.printStackTrace();
						}
		            }
		        }
		        System.out.println(rsp.getBody());
		    } while (rsp != null && rsp.isSuccess() && rsp.getMessages() != null && rsp.getMessages().size() == quantity);
		    Thread.sleep(1000L);
		} while (true);
	}
}
