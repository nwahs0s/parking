package com.park.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.park.domain.CarParkingRecord;
import com.park.domain.ParkingSpaces;
import com.park.mapper.CarParkingRecordMapper;
import com.park.mapper.ParkingSpacesMapper;

@Service
public class ParkingService {

    static Logger logger = LoggerFactory.getLogger(ParkingService.class);

    @Autowired
    private ParkingSpacesMapper parkingSpacesMapper;
    
    @Autowired
    private CarParkingRecordMapper carParkingRecordMapper;
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private final int carCharge =30;
    
    private final int busCharge =40;

    /**
     * 進入停車場
     * @param carParkingRecord
     * @return
     * @throws Exception
     */
    public Integer enterPark(CarParkingRecord carParkingRecord) throws Exception{
    	Date nowtime = new Date();
    	Thread.sleep(1000);
    	List<ParkingSpaces> list = parkingSpacesMapper.query(carParkingRecord.getType(), 0);
    	
    	//if no vacancy, throw message.
    	if(list.size()==0) {
    		logger.info(String.format("A %s has entered at %s, but left because there are no vacant %s parking spaces"
    				,carParkingRecord.getType(),sdf.format(nowtime),carParkingRecord.getType()));
    		return 0;
    	}
    	ParkingSpaces parkingSpaces = list.get((int) Math.floor(Math.random()*(list.size())));//隨機選停車位
    	
    	carParkingRecord.setParkingSpacesId(parkingSpaces.getId());
    	carParkingRecord.setStartTime(nowtime);
    	carParkingRecordMapper.insertSelective(carParkingRecord);
    	parkingSpaces.setStatus(true);
    	parkingSpacesMapper.updateByPrimaryKey(parkingSpaces);
    	logger.info(String.format("A %s has entered at %s and parked in parking space <%d>. Remaining parking spaces for %s is <%d>"
				,carParkingRecord.getType(),sdf.format(nowtime),1,carParkingRecord.getType(),list.size()-1));
    	return carParkingRecord.getId();
    }
    
    /**
     * 離開停車場
     * @param carParkingRecord
     * @return
     * @throws Exception
     */
    public String exitPark(CarParkingRecord carParkingRecord) throws Exception{
    	int charge = 10;
    	Date nowtime = new Date();
    	long parkingTime =  (nowtime.getTime() - carParkingRecord.getStartTime().getTime())/1000;
    	int parkingUnit = (int) (Math.ceil((double) parkingTime/60)); 
    	String total = this.calcuteTime(parkingTime);
    	
    	if("car".equals(carParkingRecord.getType())) {
    		charge = carCharge;
    	}else if("small bus".equals(carParkingRecord.getType())) {
    		charge = busCharge;
    	}
    	int chargeFee = charge* parkingUnit;
    	carParkingRecord.setCharge(chargeFee);
    	carParkingRecord.setEndTime(nowtime);
    	carParkingRecordMapper.updateByPrimaryKeySelective(carParkingRecord);
    	
    	ParkingSpaces parkingSpaces= parkingSpacesMapper.selectByPrimaryKey(carParkingRecord.getParkingSpacesId());
    	parkingSpaces.setStatus(false);
    	parkingSpacesMapper.updateByPrimaryKeySelective(parkingSpaces);
    	
    	List<ParkingSpaces> list = parkingSpacesMapper.query(carParkingRecord.getType(), 0);
    	
    	logger.info(String.format("The %s parked at parking space <%d> has left at %s. Total time parked is %s, for a total parking fee of <%d>. Available parking spaces for %s is <%d>"
				,carParkingRecord.getType(),carParkingRecord.getParkingSpacesId(),sdf.format(nowtime),total,chargeFee,carParkingRecord.getType(),list.size()));
    	
    	return "success";
    }
    
    private String calcuteTime(long parkingTime) {
		long hours = parkingTime/3600;
		long minutes = parkingTime%3600/60;
		long second = parkingTime%60;
		return String.format("%02d:%02d:%02d ", hours, minutes, second );
	}

	/**
     * 紀錄目前停車格狀況
     * @return
     * @throws Exception
     */
    public String checkParkingSpace() throws Exception{
    	int motorVacancy =0;
    	int carVacancy =0;
    	int busVacancy =0;
    	List<ParkingSpaces> list = parkingSpacesMapper.query(null, 0);
    	for(ParkingSpaces model : list) {
    		switch(model.getType()) {
	    		case "car":
	    			carVacancy++;
	    			break;
	    		case "motorcycle":
	    			motorVacancy++;
	    			break;
	    		case "small bus":
	    			busVacancy++;
	    			break;
	    		default:
	    			break;
    		
    		}
    	}
    	
        logger.info(String.format("Available parking slots for motorcycles: <%d>, for cars: <%d>, for small buses: <%d>",
        		motorVacancy,carVacancy,busVacancy));
        return "";
    }
    
    public List<CarParkingRecord> getParkingInfo() throws Exception{
    	return carParkingRecordMapper.getParingRecord(1);
    }
    
    
    
}
