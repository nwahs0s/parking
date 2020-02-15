package com.park;

import com.park.domain.SysParking;
import com.park.mapper.SysParkingMapper;
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
    private SysParkingMapper sysParkingMapper;

    @Test
    public void testInsert() {
        SysParking sysParking = new SysParking();
        sysParking.setParkingNum(1);
        sysParking.setCarId("12124545");

        assertThat(sysParkingMapper.insert(sysParking)).isGreaterThan(0);
        // 成功直接拿会写的 ID
        assertThat(sysParking.getId()).isNotNull();
    }
}
