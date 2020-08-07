package com.springboot_test.util;

import com.springboot_test.util.Constants.StatusCode;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 返回的结果实体类
 *
 * @author Tanwei
 * @JsonInclude(JsonInclude.Include.NON_NULL)
 * @date 2019-06-06 14:19
 */
@Data
public class ResponseResult<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8246163217408706455L;

    /**
     * 状态码
     */
    @ApiModelProperty("状态码 0：成功 其他：失败")
    private String code;
    /**
     * 错误消息
     */
    @ApiModelProperty("返回信息")
    private String message;
    /**
     * 返回的数据
     */
    @ApiModelProperty("返回数据")
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(String code, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult success() {
        return new ResponseResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage());
    }

    public static <T> ResponseResult fail() {
        return new ResponseResult(StatusCode.REMOTE_CALL_FAILED.getCode(),
            StatusCode.REMOTE_CALL_FAILED.getMessage());
    }

    public static <T> ResponseResult success(T data) {
        return new ResponseResult(StatusCode.SUCCESS.getCode(), data);
    }

}
