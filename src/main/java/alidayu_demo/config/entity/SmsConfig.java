package alidayu_demo.config.entity;

/**
 * 短信消息配置信息实体类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 8 Sep 201609:18:54
 * <p>类说明:
 */
public class SmsConfig {

	/** 短信签名 */
	private String signName;
	
	/** 短信模板ID */
	private String templateCode;
	
	/** 短信模板 */
	private String smsParam;

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getSmsParam() {
		return smsParam;
	}

	public void setSmsParam(String smsParam) {
		this.smsParam = smsParam;
	}

	@Override
	public String toString() {
		return "SmsConfig [signName=" + signName + ", templateCode=" + templateCode + ", smsParam=" + smsParam + "]";
	}
	
}
