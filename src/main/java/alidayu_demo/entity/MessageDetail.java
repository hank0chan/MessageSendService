package alidayu_demo.entity;

/**
 * 消息明细表实体类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 8 Sep 201609:15:37
 * <p>类说明:
 */
public class MessageDetail {

	/** 消息编号（格式：yyyyMMddHHmmssfff） */
	private String messageId;
	
	/** 短信接收号码（只有一个号码，11位数） */
	private String recNum;
	
	/** 消息唯一标识符，即任务主键BizID */
	private String msgId;
	
	/** 消息类型（1为短信，2为语音） */
	private int messageType;
	
	/** 发送时间（该消息每次发送的时间，多个则使用逗号分隔） */
	private String sendTime;
	
	/** 短信接收时间 */
	private String recTime;
	
	/** 发送状态，默认为1（1为等待回执，2为发送失败，3为发送成功） */
	private int state;
	
	/** 发送次数 */
	private int sendTimes;
	
	/** 最大发送次数 */
	private int maxSendTimes;
	
	/** 更新时间（格式：yyyyMMddHHmmss） */
	private String updateTime;
	
	/** 创建时间（格式：yyyy-MM-dd HH:mm:ss） */
	private String createTime;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getRecNum() {
		return recNum;
	}

	public void setRecNum(String recNum) {
		this.recNum = recNum;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getRecTime() {
		return recTime;
	}

	public void setRecTime(String recTime) {
		this.recTime = recTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getSendTimes() {
		return sendTimes;
	}

	public void setSendTimes(int sendTimes) {
		this.sendTimes = sendTimes;
	}

	public int getMaxSendTimes() {
		return maxSendTimes;
	}

	public void setMaxSendTimes(int maxSendTimes) {
		this.maxSendTimes = maxSendTimes;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "MessageDetail [messageId=" + messageId + ", recNum=" + recNum + ", msgId=" + msgId + ", messageType="
				+ messageType + ", sendTime=" + sendTime + ", recTime=" + recTime + ", state=" + state + ", sendTimes="
				+ sendTimes + ", maxSendTimes=" + maxSendTimes + ", updateTime=" + updateTime + ", createTime="
				+ createTime + "]";
	}
	
}
