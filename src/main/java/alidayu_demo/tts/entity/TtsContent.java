package alidayu_demo.tts.entity;

/**
 * 消息订阅返回语音呼叫结果实体类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 8 Sep 201609:14:37
 * <p>类说明: 用于解析返回的Json字符串为一个实体类
 */
public class TtsContent {

	/** 错误码 */
	private String status_code;
	
	/** 通话时间（未接通为0） */
	private String duration;
	
	/** 消息唯一标识BizID */
	private String biz_id;
	
	/** 通话结束时间（未接通为空） */
	private String end_time;
	
	/** 消息状态描述 */
	private String status_msg;

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getBiz_id() {
		return biz_id;
	}

	public void setBiz_id(String biz_id) {
		this.biz_id = biz_id;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getStatus_msg() {
		return status_msg;
	}

	public void setStatus_msg(String status_msg) {
		this.status_msg = status_msg;
	}

	@Override
	public String toString() {
		return "TtsContent [status_code=" + status_code + ", duration=" + duration + ", biz_id=" + biz_id
				+ ", end_time=" + end_time + ", status_msg=" + status_msg + "]";
	}
	
}
