package alidayu_demo.config.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import alidayu_demo.config.entity.MessageConfig;
import alidayu_demo.config.entity.MessageProject;
import alidayu_demo.config.entity.SmsConfig;
import alidayu_demo.config.entity.TtsConfig;

/**
 * 配置文件信息读取
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 8 Sep 201609:57:56
 * <p>类说明:
 */
public class MessageConfigSingleton {
	
	/** 消息配置信息 */
	private List<MessageConfig> configResults = null;
	
	public MessageConfig getMessageConfig() {
		MessageConfig config = new MessageConfig();
		if(configResults != null) {
		config = configResults.get(0);
		}
		return config;
	}
	
	/*
	// 消息配置信息 
	private MessageConfig configResult = null;
	
	public MessageConfig getConfigResult() {
		return configResult;
	}

	public void setConfigResult(MessageConfig configResult) {
		this.configResult = configResult;
	}*/

	// 单例模式
	private static class MessageConfigSingletonHolder {
		private static MessageConfigSingleton instance = new MessageConfigSingleton();		
	}
	
	public static MessageConfigSingleton getInstance() {
		return MessageConfigSingletonHolder.instance;
	}
	
	/** 私有构造函数 */
	private MessageConfigSingleton() {
		configResults = new ArrayList<>();
		loadData();
	}
	
	/** 重新装载数据 */
	public void reloadData() {
		configResults.clear();
		loadData();
	}
	
	/** 读取配置文件，获取信息 */
	@SuppressWarnings("unchecked")
	public void loadData() {
		try {
			
			MessageConfig messageConfig = new MessageConfig();
			
			// 1. 根据读取的xml路径，传递给SAXReader之后 返回一个Document文档对象
			SAXReader reader = new SAXReader();
			Document doc = reader.read(this.getClass().getResourceAsStream("/requestConfig/MessageConfig.xml"));  
			
			// 2. 然后操作这个Document对象，获取下面的节点以及子节点的信息
			Element rootElement = doc.getRootElement();
			//Element projectEl = rootElement.element("Project");
			
			// 获取子project节点的集合
			List<Element> projectEls = rootElement.elements("Project");
			Map<Integer, MessageProject> projectMap = new HashMap<>();
			for(Element projectEl : projectEls) {
				
				MessageProject messageProject = new MessageProject();				
				// 读取project节点属性
				String projectId = projectEl.attributeValue("id");
				String projectAppkey = projectEl.attributeValue("AppKey");
				String projectSecret = projectEl.attributeValue("Secret");
				String projectUrl = projectEl.attributeValue("Url");
				
				messageProject.setAppKey(projectAppkey);
				messageProject.setSecret(projectSecret);
				messageProject.setUrl(projectUrl);
				
				// 从project节点中获取所有的Message节点
				List<Element> messageEls = projectEl.elements("Message");
				for(Element messageEl : messageEls) {
					// 判断Message节点类型
					String msgType = messageEl.attributeValue("Type");
					if(msgType.equals("1")) {			// 短信类型
						SmsConfig smsConfig = new SmsConfig();
						smsConfig.setSignName(messageEl.element("SignName").getText());
						smsConfig.setTemplateCode(messageEl.element("TemplateCode").getText());
						smsConfig.setSmsParam(messageEl.element("SMSParam").getText());
						
						messageProject.setSmsConfig(smsConfig);
					}
					if(msgType.equals("2")) {			// 语音类型
						TtsConfig ttsConfig = new TtsConfig();
						ttsConfig.setTemplateCode(messageEl.element("TemplateCode").getText());
						ttsConfig.setTtsParam(messageEl.element("TTSParam").getText());
						ttsConfig.setCalledShowNums(messageEl.element("CalledShowNums").getText().split(","));
						
						messageProject.setTtsConfig(ttsConfig);
					}
				}
				projectMap.put(Integer.parseInt(projectId), messageProject);
			}
			messageConfig.setProject(projectMap);
			configResults.add(messageConfig);
			
			/*// 测试
			System.out.println("---------------------");
			@SuppressWarnings("unchecked")
			List<Element> msgElements = projectEl.elements("Message"); 
			if(msgElements != null) {
				for(Element msgEl : msgElements) {
					System.out.println(msgEl.element("TemplateCode").getText());
				}
			}
			System.out.println("---------------------");
			Element messageEl = projectEl.element("Message");
			System.out.println("message节点为：" + messageEl);
			System.out.println(messageEl.element("SMSParam").getText());
			String messageElText = projectEl.getText();
			System.out.println(messageElText);
			System.out.println("id = " + projectEl.attributeValue("id"));
			System.out.println("appkey = " + projectEl.attributeValue("AppKey"));
			System.out.println("secret = " + projectEl.attributeValue("Secret"));
			System.out.println("url = " + projectEl.attributeValue("Url"));*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
