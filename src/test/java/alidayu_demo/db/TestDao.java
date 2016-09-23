package alidayu_demo.db;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import alidayu_demo.dao.MessageQueueDao;
import alidayu_demo.entity.MessageQueue;

@SuppressWarnings("deprecation")
public class TestDao {

	@Test
	public void test() {
		
	}
	
	@Test
	public void testMessageQueue() {
		@SuppressWarnings("resource")
		ApplicationContext xml = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		MessageQueueDao queueDao = (MessageQueueDao) xml.getBean("messageQueueDao");

//		int result = queueDao.deleteMsg("101");
//		System.out.println(result);
		MessageQueue queue = queueDao.getSmsMsg();
		System.out.println(queue);
		List<MessageQueue> lists = queueDao.getSmsMsgs();
		System.out.println(lists);
	}
	
}
