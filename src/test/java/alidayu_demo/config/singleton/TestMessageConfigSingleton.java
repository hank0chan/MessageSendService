package alidayu_demo.config.singleton;

import org.junit.Test;

import alidayu_demo.config.entity.MessageConfig;

public class TestMessageConfigSingleton {

	@Test
	public void test() {
		MessageConfigSingleton singleton = MessageConfigSingleton.getInstance();
		MessageConfig config = singleton.getMessageConfig();
		System.out.println(config.toString());
	}
}
