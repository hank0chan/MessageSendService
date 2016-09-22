package alidayu_demo.config.entity;

/**
 * 项目配置实体类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 8 Sep 201609:18:11
 * <p>类说明:
 */
public class MessageProject {

	/** 公共请求参数URL */
	private String url;
	
	/** 公共请求参数应用AppKey */
	private String appKey;
	
	/** 公共请求参数应用密码Secret */
	private String secret;
	
	/** 短信消息请求参数配置信息 */
	private SmsConfig smsConfig;
	
	/** 语音消息请求参数配置信息 */
	private TtsConfig ttsConfig;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public SmsConfig getSmsConfig() {
		return smsConfig;
	}
	public void setSmsConfig(SmsConfig smsConfig) {
		this.smsConfig = smsConfig;
	}
	public TtsConfig getTtsConfig() {
		return ttsConfig;
	}
	public void setTtsConfig(TtsConfig ttsConfig) {
		this.ttsConfig = ttsConfig;
	}
	@Override
	public String toString() {
		return "MessageProject [url=" + url + ", appKey=" + appKey + ", secret=" + secret + ", smsConfig=" + smsConfig
				+ ", ttsConfig=" + ttsConfig + "]";
	}
	
}
