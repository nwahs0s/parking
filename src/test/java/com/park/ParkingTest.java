package com.park;

import com.park.domain.CarParkingRecord;
import com.park.service.ParkingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingTest {

    @Autowired
    private ParkingService parkingService;

    @Test
    public void testQueryParkingSpaces() throws Exception {
       

        assertThat(parkingService.checkParkingSpace());
        
        CarParkingRecord carParkingRecord= new CarParkingRecord();
        carParkingRecord.setCarId("test123");
        carParkingRecord.setType("motorcycle");
        assertThat(parkingService.enterPark(carParkingRecord)).isGreaterThan(0);
        assertThat(parkingService.exitPark(carParkingRecord)).isEqualTo("success");
        // 成功直接拿会写的 ID
//        assertThat(sysParking.getId()).isNotNull();
    }
}
