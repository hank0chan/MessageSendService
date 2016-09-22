package alidayu_demo.dao;

import java.util.List;

import alidayu_demo.dao.mybatis.MessageRepository;
import alidayu_demo.entity.MessageQueue;
import alidayu_demo.util.Criteria;

/**
 * 队列消息Dao类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 14 Sep 201611:05:24
 * <p>类说明:
 */
@Deprecated
public class MessageQueueDao {

	MessageRepository repository;
	public void setMessageRepository(MessageRepository repository) {
		this.repository = repository;
	}
	
	/** 返回短信消息 */
	public MessageQueue getSmsMsg() {
		return repository.get_h("get", MessageQueue.class, 1);
	}
	
	/** 返回语音消息 */
	public MessageQueue getTtsMsg() {
		return repository.get_h("get", MessageQueue.class, 2);
	}
	
	/** 根据消息MessageID删除队列消息 */
	public int deleteMsg(String messageId) {
		/*Map<String, String> map = new HashMap<>();
		map.put("messageId", messageId);
		return repository.delete_h(MessageQueue.class, map);*/
		return repository.delete_h(MessageQueue.class, new Criteria().with("messageId", messageId));
	}
	
	/** 返回短信消息 */
	public List<MessageQueue> getSmsMsgs() {
		return repository.query_h("query", MessageQueue.class, 1);
	}
	
	/** 返回语音消息 */
	public List<MessageQueue> getTtsMsgs() {
		return repository.query_h("query", MessageQueue.class, 2);
	}
	
}
