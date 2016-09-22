package alidayu_demo.entity;

/**
 * 消息主表实体类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 8 Sep 201609:15:21
 * <p>类说明:
 */
public class Message {

	/** 消息编号（格式：yyyyMMddHHmmssfff） */
	private String messageId;
	
	/** 消息类型（1为短信，2为语音） */
	private int messageType;
	
	/** 消息内容 */
	private String messageContent;
	
	/** 短信接收号码（可存放多个号码，由逗号分隔开，最多200个） */
	private String recNum;
	
	/** 项目编号（暂定swift为1，其他后续项目依次累加1） */
	private int projectId;
	
	/** 创建时间（格式：yyyy-MM-dd HH:mm:ss） */
	private String createTime;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int i) {
		this.messageType = i;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getRecNum() {
		return recNum;
	}

	public void setRecNum(String recNum) {
		this.recNum = recNum;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageType=" + messageType + ", messageContent=" + messageContent
				+ ", recNum=" + recNum + ", projectId=" + projectId + ", createTime=" + createTime + "]";
	}
	
}
