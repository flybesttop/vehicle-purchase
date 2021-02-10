package com.vp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
class VpApplicationTests {

    @Test
    void contextLoads() {
        LocalDate nowLocalDate=LocalDate.now();
        LocalDate localDate1=nowLocalDate.minusMonths(1);
        System.out.println(nowLocalDate);
        System.out.println(localDate1);
    }

}
