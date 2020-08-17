package com.springboot_test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value="com.springboot_test.entity.user")
@Table(name = "`user`")
@Data
public class User implements Serializable {
    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value="id")
    private String id;

    @Column(name = "`user_name`")
    @ApiModelProperty(value="userName")
    private String userName;

    @Column(name = "`user_phone`")
    @ApiModelProperty(value="userPhone")
    private String userPhone;

    @Column(name = "`user_password`")
    @ApiModelProperty(value="userPassword")
    private String userPassword;

    @Column(name = "`create_time`")
    @ApiModelProperty(value="createTime 创建时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}