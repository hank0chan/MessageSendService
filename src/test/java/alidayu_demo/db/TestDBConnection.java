package alidayu_demo.db;

import org.junit.Test;

import alidayu_demo.dao.mybatis.MessageRepository;
import alidayu_demo.entity.MessageQueue;

public class TestDBConnection {

	@Test
	public void test() {
		MessageRepository repository = new MessageRepository();
		repository.init();
		MessageQueue queue = repository.get("get", MessageQueue.class, "");
		System.out.println(queue);
	}
}
