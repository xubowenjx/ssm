package com.xbw.spring.applications.task;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
	private static  Logger log = Logger.getLogger(TestTask.class);
	public void run(){
		log.info("定时任务之：以配置文件来运行:" + new Date());
	}
}
