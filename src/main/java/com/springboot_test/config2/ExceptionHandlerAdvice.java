package com.springboot_test.config2;


import lombok.extern.slf4j.Slf4j;

/**
 * 全局封装返回
 *
 * @author Tanwei
 * @date 2019-06-15 09:46
 * @return
 */
//@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    /*@Autowired
    private MessageSource messageSource;

    *//**
     * 处理异常返回
     *
     * @author Tanwei
     * @date 2019-06-15 09:46
     *//*
    @ExceptionHandler({Exception.class})
    public ResponseResult badRequestException(Exception exception) {
        String message = exception.getMessage();
        String code = null;
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException subException = ((ConstraintViolationException) exception);
            AtomicReference<String> codeString = new AtomicReference<>();
            subException.getConstraintViolations().forEach((violation) -> {
                codeString.set(violation.getMessage());
            });
            code = codeString.get();
            try {
                message = messageSource.getMessage(code, null, Locale.CHINA);
            } catch (Exception ex) {
            }
        } else if (exception instanceof BindException) {
            BindException subException = ((BindException) exception);
            code = subException.getBindingResult()
                .getAllErrors().get(0).getDefaultMessage();
            try {
                message = messageSource.getMessage(code, null, Locale.CHINA);
            } catch (Exception ex) {
            }
        } else if (exception instanceof MultipartException) {
            MultipartException subException = ((MultipartException) exception);
            code = StatusCode.FILE_UPLOAD_ERROR.getCode();
            message = StatusCode.FILE_UPLOAD_ERROR.getMessage();
        } else if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException subException = ((MethodArgumentNotValidException) exception);
            code = subException.getBindingResult()
                .getAllErrors().get(0).getDefaultMessage();
            try {
                message = messageSource.getMessage(code, null, Locale.CHINA);
            } catch (Exception ex) {
            }
        } else if (exception instanceof GlobalException) {
            GlobalException globalException = ((GlobalException) exception);
            code = globalException.getCode();
            if (globalException.getMessage() == null) {
                try {
                    message = messageSource.getMessage(code, null, Locale.CHINA);
                } catch (Exception ex) {
                    log.error("globalException 获取message失败,{}",ex.getMessage());
                }
            } else {
                message = globalException.getMessage();
            }
        } else if (exception instanceof AccessDeniedException) {
            code = StatusCode.UNAUTHORIZED.getCode();
            message = StatusCode.UNAUTHORIZED.getMessage();
        } else if (exception instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException missingParamException = (MissingServletRequestParameterException) exception;
            code = StatusCode.BAD_REQUEST.getCode();
            message = "参数缺失：" + missingParamException.getParameterName();
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException methodNotSupportedException = (HttpRequestMethodNotSupportedException) exception;
            code = StatusCode.METHOD_NOT_ALLOWED.getCode();
            message = "请求方法不支持 ：" + methodNotSupportedException.getMethod();
        } else if (exception instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
            code = StatusCode.METHOD_NOT_ALLOWED.getCode();
            message = "oAuth2Exception ：" + oAuth2Exception.getSummary();
        } else {
//            log.error("error message:{}",exception.getMessage());
            exception.printStackTrace();
            code = StatusCode.SYSTEM_ERROR.getCode();
            message = StatusCode.SYSTEM_ERROR.getMessage();
        }

        return new ResponseResult(code, message);
    }*/
}
