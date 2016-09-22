package alidayu_demo.dao.mybatis;

import alidayu_demo.entity.Message;
import alidayu_demo.entity.MessageDetail;
import alidayu_demo.entity.MessageQueue;

public class MessageRepository extends MybatisRepository {

	@Override
	public void init() {
		init(Message.class, MessageDetail.class, MessageQueue.class);
	}
	
}
