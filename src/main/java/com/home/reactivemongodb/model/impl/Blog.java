package com.home.reactivemongodb.model.impl;

import com.home.reactivemongodb.model.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by marcin.bracisiewicz
 */
@Data
@Document(collection = "blog")
public class Blog extends BaseEntity {

    @Indexed
    private String author;

    @TextIndexed
    private String title;

    private String content;
}
