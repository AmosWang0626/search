package com.amos.search.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 模块名称: search
 * 模块描述: Product
 *
 * @author amos.wang
 * @date 2020/9/18 18:03
 */
@Getter
@Setter
@Accessors(chain = true)
@Document(indexName = "product")
public class Product implements Persistable<String> {

    @Id
    private String id;

    private String name;


    @Override
    public boolean isNew() {
        return id == null;
    }
}
