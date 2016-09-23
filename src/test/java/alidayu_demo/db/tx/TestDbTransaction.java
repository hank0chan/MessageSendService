package alidayu_demo.db.tx;

import java.util.Date;

import org.junit.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import alidayu_demo.dao.mybatis.MessageRepository;
import alidayu_demo.entity.Message;
import alidayu_demo.entity.MessageDetail;
import alidayu_demo.entity.MessageQueue;
import alidayu_demo.util.Criteria;
import alidayu_demo.util.TimeUtils;

public class TestDbTransaction {

	/**
	 * 事务管理测试
	 */
	@Test
	public void testTransaction() {
		MessageRepository repository = new MessageRepository();
		repository.init();		
		MessageQueue queue = repository.get_h("get", MessageQueue.class, 1);
		
		/** 将下方三个操作作为一个事务处理 */
		// 消息存入Message主表(MessageId)
		Message message = new Message();
		message.setMessageId(queue.getMessageId());
		message.setMessageType(queue.getMessageType());
		message.setProjectId(queue.getProjectId());
		message.setMessageContent(queue.getMessageContent());
		message.setRecNum(queue.getRecNum());
		message.setCreateTime(TimeUtils.ISO8601.format(new Date()));
		repository.create_h(message);
		
		// 从队列表中删除该MessageId的消息
		repository.delete_h(Message.class, new Criteria().with("messageId", queue.getMessageId()));
		
		// 每个消息分别存入消息明细表(MessageId,RecNum)
		MessageDetail detail = new MessageDetail();
		detail.setMessageId(queue.getMessageId());
		//detail.setMessageId(TimeUtils.YYYYMMDDHHMMSSFFF.format(new Date()));
		detail.setRecNum("18887650000");
		detail.setCreateTime(TimeUtils.ISO8601.format(new Date()));
		detail.setMaxSendTimes(3);
		detail.setMessageType(1);
		detail.setMsgId("hhhhhaaahhh^0");
		detail.setRecTime("null");
		detail.setSendTime(TimeUtils.ISO8601.format(new Date()));
		detail.setSendTimes(1);
		detail.setState(1);
		detail.setUpdateTime(TimeUtils.YYYYMMDDHHMMSS.format(new Date()));
		repository.create_h(detail);
		
		TransactionDefinition transactionDefinition = null;
		DataSourceTransactionManager dataSourceTransactionManager = null;
		System.out.println(transactionDefinition + "......" + dataSourceTransactionManager);
		
	}

	
}
