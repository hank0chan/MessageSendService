package alidayu_demo.util;

import org.junit.Test;

import alidayu_demo.dao.mybatis.MessageRepository;
import alidayu_demo.entity.Message;

public class TestCriteria {

	@Test
	public void testCriteria() {
		MessageRepository repository = new MessageRepository();
		repository.init();
		repository.delete_h(Message.class, new Criteria().with("messageId", "101"));
		//repository.delete_h(Message.class, new HashMap<>().put("messageId", "12345")); // 代入占位符无效。全删除了。。
	}
}
