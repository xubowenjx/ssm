package com.xbw.spring.applications.task;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ComponentTask {
	private static  Logger log = Logger.getLogger(ComponentTask.class);
	/**
	 * 每20秒中跑一次，cron参考本包下的cron.txt
	 */
	@Scheduled(cron = "0/30 * * * * ? ")
	public void runTask1() {
		log.info("定时任务之:cron表达式定义时间：每30秒 执行 " + new Date());
	}

	/**
	 * 任务执行完成，18秒后再次执行
	 */
	@Scheduled(fixedDelay = 18000)
	void doSomethingWithDelay() {
		log.info("定时任务之：1.1、固定延时18s执行:" + new Date());
	}

	/**
	 * 每隔17秒执行任务
	 */
	@Scheduled(fixedRate = 17000)
	void doSomethingWithRate() {
		log.info("定时任务之：1.2、固定频率17s执行:" + new Date());
	}

}
