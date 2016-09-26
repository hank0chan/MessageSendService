package alidayu_demo.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import alidayu_demo.dataservice.MessageQueueService;
import alidayu_demo.entity.MessageQueue;
import alidayu_demo.send.MsgSendService;
import alidayu_demo.util.APIResult;
import alidayu_demo.util.TimeUtils;

/**
 * 测试API调用过程。。
 * @author Hank_  
 * <p>Email:hicth_chan@163.com</p>
 * @version 创建时间: 26 Sep 201616:22:07
 * <p>类说明:
 */
@Controller
public class TestAPIs {
	
	@Autowired
	MessageQueueService queueService;
	@Autowired
	MsgSendService msgSendService;
	
	@RequestMapping("/queue/send.json")
	public @ResponseBody APIResult sendMsg(HttpServletRequest request, HttpServletResponse response) {
		APIResult result = APIResult.prepare();
		try {
			// 发送SMS消息
			msgSendService.sendSms();
			return result.ok("send success..");
		} catch (Exception e) {
			return result.error(e.getMessage());
		}
	}
	
	@RequestMapping("/queue/{project}-{type}-insert.json")
	public @ResponseBody APIResult insertQueue(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("type") int msgType,
			@PathVariable("project") int projectId,
			@RequestParam("recNum") String recNum,
			@RequestParam("content") String content) {
		APIResult result = APIResult.prepare();
		MessageQueue queue = new MessageQueue();
		queue.setMessageContent(content);
		queue.setMessageType(msgType);
		queue.setProjectId(projectId);
		queue.setRecNum(recNum);
		queue.setMessageId(TimeUtils.YYYYMMDDHHMMSSFFF.format(new Date()));
		MessageQueue insertResult = queueService.insert(queue);
		if(insertResult != null) {
			return result.ok(insertResult);
		}else {
			return result.error("insert failure..");
		}
	}
	
	
}
