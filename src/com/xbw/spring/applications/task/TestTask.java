package com.xbw.spring.applications.task;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TestTask {
	public void run(){
		System.out.println("定时任务之：以配置文件来运行:" + new Date());
	}
}
