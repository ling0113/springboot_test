package com.springboot_test.service;

import com.springboot_test.util.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: com.springboot_test.service.TestService
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/7/30 15:32
 * @Version: 1.0
 */
public interface TestService {


    ResponseResult uploadOss(MultipartFile file, String fileSource);

}
