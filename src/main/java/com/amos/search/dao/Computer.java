package com.amos.search.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * DESCRIPTION: 电脑
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/9/20
 */
@Getter
@Setter
@Accessors(chain = true)
public class Computer {

    /**
     * 名字
     */
    private String name;
    /**
     * 型号
     */
    private String model;
    /**
     * 处理器
     */
    private String processor;
    /**
     * 内存
     */
    private String memory;
    /**
     * 硬盘
     */
    private String hardDisk;
    /**
     * 屏幕尺寸
     */
    private String screenSize;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 描述
     */
    private String description;

    public Computer(String name, String model, String processor, String memory, String hardDisk, String screenSize, BigDecimal price) {
        this.name = name;
        this.model = model;
        this.processor = processor;
        this.memory = memory;
        this.hardDisk = hardDisk;
        this.screenSize = screenSize;
        this.price = price;
    }
}
