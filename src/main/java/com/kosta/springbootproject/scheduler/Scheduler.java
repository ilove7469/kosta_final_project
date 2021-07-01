package com.kosta.springbootproject.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kosta.springbootproject.adminservice.ClassHistroyService;
import com.kosta.springbootproject.adminservice.ClassesService;

@Component
public class Scheduler {
	
	@Autowired
	ClassesService classesService;
	
	@Autowired
	ClassHistroyService classHistroyService; 

	//  "0 0 0 * * *" : 0초 0분 0시 매일 매월 매요일
    @Scheduled(cron = "0 0 0 * * *") 
    public void ClassOpen() {
    	classHistroyService.commitToComplete();
    	classesService.manageClassState();
    }

}
