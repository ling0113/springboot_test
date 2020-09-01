package com.springboot_test.vo;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import lombok.Data;

/**
 * @ClassName: com.springboot_test.vo.UserAaVo
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/8/26 20:21
 * @Version: 1.0
 */
@Data
public class UserAaVo {
    @ApiModelProperty(value="id")
    private String id;

    @ApiModelProperty(value="userName")
    private String userName;

    @ApiModelProperty(value="userPhone")
    private String userPhone;

    @ApiModelProperty(value="a")
    private String a;

    @ApiModelProperty(value="b")
    private String b;

    @ApiModelProperty(value="c")
    private Byte c;

}
