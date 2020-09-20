package com.amos.search;

import com.amos.search.dao.Computer;
import com.amos.search.utils.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class SearchApplicationTests {

    @Test
    void contextLoads() {
        Computer computer = new Computer("ThinkPad S2", "20R7A018CD", "i5-10210U",
                "16G", "512G", "13.3英寸", new BigDecimal("5499.00"));
        System.out.println(JSON.toJSONString(computer));

        computer = new Computer("ThinkBook 14", "20SLA028CD", "i7-1065G7",
                "8G", "512G", "14英寸", new BigDecimal("5699.00"));
        System.out.println(JSON.toJSONString(computer));

        computer = new Computer("ThinkPad X13", "20T2A005CD", "i7-10510U",
                "8G", "512G", "13.3英寸", new BigDecimal("7999.00"));
        System.out.println(JSON.toJSONString(computer));

        computer = new Computer("ThinkPad T14", "20S0A007CD", "i5-10210U",
                "16G", "512G", "14英寸", new BigDecimal("8999.00"));
        System.out.println(JSON.toJSONString(computer));

        computer = new Computer("ThinkPad P15s", "20T4A000CD", "i7-10510U",
                "16G", "512G", "15.6英寸", new BigDecimal("10499.00"));
        System.out.println(JSON.toJSONString(computer));

        computer = new Computer("ThinkPad X1 Carbon", "20U90039CD", "i7-10710U",
                "16G", "1T", "14英寸", new BigDecimal("16999.00"));
        System.out.println(JSON.toJSONString(computer));
    }

}
