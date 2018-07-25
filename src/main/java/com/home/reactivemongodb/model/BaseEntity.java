package com.home.reactivemongodb.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by marcin.bracisiewicz
 */
@Setter
@Getter
public abstract class BaseEntity implements Serializable {

    @Id
    private String id;

    @CreatedDate
    private Date createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private Date lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @Version
    private Long version;

    private Boolean delete = Boolean.FALSE;
}
