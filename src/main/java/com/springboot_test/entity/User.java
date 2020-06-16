package com.springboot_test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@ApiModel(value="com.springboot_test.entity.user")
@Table(name = "`user`")
@Data
public class User implements Serializable {
    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value="id")
    private Integer id;

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
    private Date createTime;

}