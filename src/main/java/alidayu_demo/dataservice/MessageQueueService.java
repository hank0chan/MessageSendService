package alidayu_demo.dataservice;

import alidayu_demo.dao.mybatis.MessageRepository;
import alidayu_demo.entity.MessageQueue;

public class MessageQueueService {

	MessageRepository repository;
	public void setMessageRepository(MessageRepository repository) {
		this.repository = repository;
	}
	
	public MessageQueue insert(MessageQueue queue) {
		MessageQueue result = repository.create_h(queue);
		return result != null ? result : null;
	}
}
