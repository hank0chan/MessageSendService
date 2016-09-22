package alidayu_demo.send;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMsgSendService {

	@Test
	public void test() {
		@SuppressWarnings("resource")
		ApplicationContext xml = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		MsgSendService sendService = (MsgSendService) xml.getBean("msgSendService");
		sendService.sendSms();
		System.out.println("test end..");
	}
}
