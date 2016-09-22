package alidayu_demo.db;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import alidayu_demo.dao.mybatis.MessageRepository;
import alidayu_demo.entity.Message;
import alidayu_demo.entity.MessageQueue;
import alidayu_demo.util.Criteria;
import alidayu_demo.util.TimeUtils;

public class TestMybatis {

	@Test
	public void testSendService() {
		MessageRepository repository = new MessageRepository();
		repository.init();
		Message msg = new Message();
		msg.setCreateTime(TimeUtils.ISO8601.format(new Date()));
		msg.setMessageContent("测试插入");
		msg.setMessageId("20160922113200111");
		msg.setMessageType(1);
		msg.setProjectId(1);
		msg.setRecNum("13099999999");
		repository.create_h(msg);
		Map<String, String> map = new HashMap<>();
		map.put("messageId", "123");
		//int result = repository.delete_h(MessageQueue.class, map);
		//System.out.println(result);
		
//		// 读取短信队列消息
//		MessageQueue smsQueue = repository.get_h("get", MessageQueue.class, 1); 
//		System.out.println("短信消息：" + smsQueue);
//		// 读取语音队列消息
//		List<MessageQueue> ttsQueues = repository.query_h("query", MessageQueue.class, 2);
//		if(ttsQueues != null) {
//			for(MessageQueue ttsQueue : ttsQueues)
//			System.out.println("语音消息：" + ttsQueue);
//		}
		
	}
	
	@Test
	public void test() {
		MessageRepository repository = new MessageRepository();
		repository.init();
		
		Message message2 = new Message();
		message2.setMessageId("101");
		message2.setCreateTime("19970101120000777");
		message2.setMessageContent("Update测试2");
		message2.setMessageType(0);
//		message2.setRecNum("18900001234,18900002345,18900003456,18900004567");
		message2.setRecNum("18900002345");
//		Message result = repository.create_h(message2);
//		System.out.println(result);
		System.out.println("============");
//		MessageQueue insertQueue = new MessageQueue();
//		insertQueue.setMessageId("20160918103800118");
//		insertQueue.setMessageType(1);
//		insertQueue.setMessageContent("sql 插入测试");
//		insertQueue.setProjectId(1);
//		insertQueue.setRecNum("15920121585");
//		repository.create_h(insertQueue);
		
		System.out.println(repository.update_h(message2));
		
		/*// 测试多参数传入
		Map<String, Integer> map = new HashMap<>();
		map.put("id", 1);
		map.put("projectId", 1);
		List<MessageQueue> results = repository.query("queryByParams", MessageQueue.class, map);
		if(results != null) {
			for(MessageQueue result0 : results) {
				System.out.println(result0);
			}
		}*/
		
//		MessageQueue result = repository.get("insert", MessageQueue.class, insertQueue);
//		System.out.println(result);
//		Message message2 = new Message();
//		message2.setMessageId("20160918101400111");
//		message2.setCreateTime("19970101120000777");
//		message2.setMessageContent("Update测试2");
//		message2.setMessageType("1");
//		message2.setRecNum("18900001234,18900002345,18900003456,18900004567");
//		Message result = (Message) repository.update_h(message2);
//		System.out.println(result);
//		Message update = repository.get_h("get", Message.class, "20160918101400111");
//		System.out.println("updateTest: " + update);
		System.out.println("-----------------------------------");
//		Message message3 = new Message();
//		message3.setMessageId("100001");
//		message3.setCreateTime("19960101120000777");
//		message3.setMessageContent("Update测试2");
//		message3.setMessageType("1");
//		message3.setRecNum("18900001234,18900002345,18900003456,18900004567");
//		Message result3 = (Message) repository.insert_h(message3);
//		System.out.println(result3);
//		Message update3 = repository.get_h("get", Message.class, "100001");
//		System.out.println("insertTest: " + update3);
		
		/*
		MessageQueue queue = repository.get("get", MessageQueue.class, 1);
		System.out.println(queue);
		MessageQueue queue2 = repository.get_h("get", MessageQueue.class, 1);
		System.out.println(queue2);
		List<MessageQueue> queues = repository.query_h("query", MessageQueue.class, 0);
		if(queues != null) {
			System.out.println(queues.get(0));
			System.out.println(queues.get(1));
		}*/
		
	}
}
