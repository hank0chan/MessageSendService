package alidayu_demo.sms.entity;

/**
 * 消息订阅返回短信报告实体类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 8 Sep 201609:12:36
 * <p>类说明: 用于解析返回的Json字符串为一个实体类
 */
public class SmsContent {

	/** 发送时间 */
	private String send_time;
	
	/** 错误代码 */
	private String err_code;
	
	/** 消息唯一标识BizID */
	private String biz_id;
	
	/** 短信接收号码 */
	private String receiver;
	
	/** 回调值 */
	private String extend;
	
	/** 发送状态（1表示成功，2表示失败） */
	private String state;

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getBiz_id() {
		return biz_id;
	}

	public void setBiz_id(String biz_id) {
		this.biz_id = biz_id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "SmsContent [send_time=" + send_time + ", err_code=" + err_code + ", biz_id=" + biz_id + ", receiver="
				+ receiver + ", extend=" + extend + ", state=" + state + "]";
	}
	
}
