package com.amos.search.dao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * DESCRIPTION: User
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2020/9/17
 */
@Getter
@Setter
@Document(indexName = "person")
public class Person implements Persistable<String> {

    @Id
    private String id;

    private String name;

    private String address;

    private String description;

    @CreatedDate
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDateTime createTime;

    private String createdBy;

    @LastModifiedDate
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDateTime lastModifiedTime;

    private String lastModifiedBy;


    @Override
    public boolean isNew() {
        return id == null || (createTime == null && createdBy == null);
    }
}
