package alidayu_demo.config.entity;

import java.util.Arrays;

/**
 * 语音消息配置信息实体类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 8 Sep 201609:19:16
 * <p>类说明:
 */
public class TtsConfig {

	/** 语音模板ID */
	private String templateCode;
	
	/** 语音模板 */
	private String ttsParam;
	
	/** 被叫显示号码 */
	private String[] CalledShowNums;

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTtsParam() {
		return ttsParam;
	}

	public void setTtsParam(String ttsParam) {
		this.ttsParam = ttsParam;
	}

	public String[] getCalledShowNums() {
		return CalledShowNums;
	}

	public void setCalledShowNums(String[] calledShowNums) {
		CalledShowNums = calledShowNums;
	}

	@Override
	public String toString() {
		return "TtsConfig [templateCode=" + templateCode + ", ttsParam=" + ttsParam + ", CalledShowNums="
				+ Arrays.toString(CalledShowNums) + "]";
	}
	
}
