package com.springboot_test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@ApiModel(value="com.springboot_test.entity.note")
@Table(name = "`note`")
@Data
public class Note implements Serializable {
    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value="id")
    private Integer id;

    @Column(name = "`title`")
    @ApiModelProperty(value="title")
    private String title;

    @Column(name = "`body`")
    @ApiModelProperty(value="body")
    private String body;


}