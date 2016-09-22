package alidayu_demo.config.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息配置文件实体类
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 8 Sep 201609:16:46
 * <p>类说明:
 */
public class MessageConfig {

	private Map<Integer, MessageProject> project;
	
	public MessageConfig() {
		project = new HashMap<>();
	}

	public Map<Integer, MessageProject> getProject() {
		return project;
	}

	public void setProject(Map<Integer, MessageProject> project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "MessageConfig [project=" + project + "]";
	}
	
}
