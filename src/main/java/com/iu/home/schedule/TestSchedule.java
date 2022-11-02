package com.iu.home.schedule;

import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestSchedule {
	
	@Scheduled(cron = "* * * * * *")
	public void cron() {
		//log.info("Cron 매초 실행");
		//log.info(Calendar.getInstance().getTime().toString());
	}
	
	@Scheduled(fixedRate = 3000,initialDelayString = "1000")
	public void ts1() {
		//log.info("Schudule 실행");
	}

}

