package com.park.schedule;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.park.domain.CarParkingRecord;
import com.park.service.ParkingService;

@Component
public class PackingSchedule {
	
	static Logger logger = LoggerFactory.getLogger(PackingSchedule.class);
	
	@Autowired
	ParkingService parkingService;
	
	@Scheduled(cron = "0/15 * * * * ?")
	@Async
	public void checkParkSpace() throws Exception {
		parkingService.checkParkingSpace();
	};
	
	@Scheduled(cron = "0/10 * * * * ?")
	@Async
	public void enterPark() throws Exception {
		List<String> list = Arrays.asList("car","motorcycle","small bus");
		int num =(int) Math.floor(Math.random()*3) ;
		while(num>0) {
			int index = (int) Math.floor(Math.random()*3);
			String type =list.get(index);
			CarParkingRecord carParkingRecord =new CarParkingRecord();
			carParkingRecord.setType(type);
			carParkingRecord.setCarId(String.format("%s-%d", type, (int) Math.floor(Math.random()*9999)));
			parkingService.enterPark(carParkingRecord);
			num--;
		};
	};
	
	@Scheduled(cron = "0/20 * * * * ?")
	@Async
	public void exitPark() throws Exception {
		int num = (int) Math.floor(Math.random()*3) ;
		
		while(num>0) {
			List<CarParkingRecord> list = parkingService.getParkingInfo();
			if(list.size()!=0) {
				int index = (int) Math.floor(Math.random()*(list.size())) ;
				CarParkingRecord carParkingRecord = list.get(index);
				parkingService.exitPark(carParkingRecord);
			}
			num--;
		}
	};

}
