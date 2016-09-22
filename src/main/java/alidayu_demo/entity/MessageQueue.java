package alidayu_demo.entity;

/**
 * 消息队列实体类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 8 Sep 201609:16:16
 * <p>类说明:
 */
public class MessageQueue {

	/** 消息编号（格式：yyyyMMddHHmmssfff） */
	private String messageId;
	
	/** 消息类型（1为短信，2为语音） */
	private int messageType;
	
	/** 消息接收号码,短信类型可以是多个号码，语音类型只能是一个号码（多个号码使用逗号分隔） */
	private String recNum;
	
	/** 消息内容 */
	private String messageContent;
	
	/** 项目编号（暂定swift为1，其他项目依次累加1） */
	private int projectId;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getRecNum() {
		return recNum;
	}

	public void setRecNum(String recNum) {
		this.recNum = recNum;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "MessageQueue [messageId=" + messageId + ", messageType=" + messageType + ", recNum=" + recNum
				+ ", messageContent=" + messageContent + ", projectId=" + projectId + "]";
	}
	
}
