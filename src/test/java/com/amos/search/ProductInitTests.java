package com.amos.search;

import com.amos.search.dao.Product;
import com.amos.search.dao.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模块名称: search
 * 模块描述: PhoneInit
 *
 * @author amos.wang
 * @date 2020/9/18 18:06
 */
@SpringBootTest
public class ProductInitTests {

    @Resource
    private ProductRepository productRepository;


    @Test
    public void init() {
        String products = "HUAWEI P40 Pro,HUAWEI P40,HUAWEI P40 Pro+,HUAWEI nova 7 Pro 5G,HUAWEI nova 7 5G,HUAWEI nova 7 SE 5G,HUAWEI Mate 30 Pro 5G,HUAWEI Mate 30 5G,HUAWEI Mate 30 Pro,HUAWEI Mate 30,HUAWEI Mate Xs,HUAWEI Mate 30 RS 保时捷设计,HUAWEI Mate 20 X (5G),HUAWEI P30 Pro,HUAWEI P30, HUAWEI nova 6 5G, HUAWEI nova 6 SE, HUAWEI nova 5 Pro, HUAWEI nova 5, HUAWEI nova 5z, HUAWEI nova 5i Pro,HUAWEI nova 5i,HUAWEI Mate 20 Pro,HUAWEI Mate 20,华为畅享 20 Plus 5G,华为畅享20 5G,华为畅享20 Pro,华为畅享 Z 5G,华为麦芒9, HUAWEI 麦芒8,华为畅享10 Plus,华为畅享10,华为畅享10S,华为畅享 10e,华为畅享 9S,华为畅享 9e,华为畅享9,华为畅享9 Plus,HUAWEI WATCH GT 2,HUAWEI WATCH GT 2e,华为手环 B6,华为儿童手表 4X,华为儿童手表 3 Pro 超能版,华为手环 4 Pro,HUAWEI VR Glass,HUAWEI X Gentle Monster Eyewear II,HUAWEI WATCH GT,华为手环 4,华为手环 3 Pro,华为手环B5,华为手环 B3 青春版,华为手环 4e,华为手环 3e,华为儿童手表 3x,华为儿童手表 3s,华为儿童手表 3,华为儿童手表3 Pro,HUAWEI MatePad Pro 5G,HUAWEI MatePad Pro,HUAWEI MatePad 10.8,HUAWEI MatePad 5G ,HUAWEI MatePad,华为畅享平板 2,华为平板 M6 10.8英寸,华为平板 M6 高能版,华为平板 M6 8.4 英寸,华为平板 M5 青春版 8.0英寸,HUAWEI MateBook X 2020款,HUAWEI MateBook X Pro 2020款,HUAWEI MateBook 13 锐龙版 2020,HUAWEI MateBook 14 锐龙版 2020,HUAWEI MateBook 13 2020款,HUAWEI MateBook 13 锐龙版,HUAWEI MateBook 14 2020款, HUAWEI MateBook D 14 锐龙版 2020,HUAWEI MateBook D 15 锐龙版 2020,HUAWEI MateBook X Pro 2019款,HUAWEI MateBook D 14,HUAWEI MateBook D 15,HUAWEI MateBook X Pro 2019款 Linux版,HUAWEI MateBook D 14 锐龙版,HUAWEI MateBook D 15 锐龙版,华为智慧屏V系列,HUAWEI FreeBuds Pro 无线耳机,HUAWEI FreeLace Pro 无线耳机,HUAWEI Sound X,HUAWEI FreeBuds 3 无线耳机,HUAWEI FreeLace 无线耳机,HUAWEI FreeGO 便携蓝牙音箱,HUAWEI FreeBuds 悦享版 无线耳机,华为 AI 音箱 2,华为AI音箱mini,华为AI音箱";
        List<String> productNameList = Arrays.stream(products.split(",")).collect(Collectors.toList());
        List<Product> productList = new ArrayList<>();
        productNameList.forEach(s -> productList.add(new Product().setName(s)));

        productRepository.saveAll(productList);
    }

    @Test
    public void getAll() {
        productRepository.findAll().forEach(product -> System.out.println("[" + product.getId() + "]" + product.getName()));
    }

}
