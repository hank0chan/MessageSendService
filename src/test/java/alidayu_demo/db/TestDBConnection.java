package alidayu_demo.db;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import alidayu_demo.dao.mybatis.MessageRepository;
import alidayu_demo.entity.MessageQueue;

public class TestDBConnection {

	@Test
	public void test() {  // ==success
		MessageRepository repository = new MessageRepository();
		repository.init();
//		MessageQueue queue = repository.get("get", MessageQueue.class, 1);
		MessageQueue queue = repository.get_h("get", MessageQueue.class, 1);
		System.out.println(queue);
	}
	
	@Test
	public void testDBCPorC3P0() {  // 无疾而终。。==failure
		@SuppressWarnings("resource")
		ApplicationContext xml = new ClassPathXmlApplicationContext("db2.properties");
		DataSource dataSource = (DataSource) xml.getBean("dataSource");
		System.out.println(dataSource);
	}
}
