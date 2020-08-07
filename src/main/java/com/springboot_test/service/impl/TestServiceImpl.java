package com.springboot_test.service.impl;

import com.springboot_test.service.TestService;
import com.springboot_test.util.ResponseResult;
import com.springboot_test.utils.OssClientUtil;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: com.springboot_test.service.impl.TestServiceImpl
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/7/30 15:32
 * @Version: 1.0
 */
@Service
@Slf4j
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private OssClientUtil ossClient3;



    @Override
    public ResponseResult uploadOss(MultipartFile file, String fileSource)  {
        String s = null;
        try {
            s = ossClient3.uploadFileToOSS(file);
        } catch (IOException e) {
            System.out.println(e);
        }
        String imgUrl = ossClient3.getImgUrl(s);
        return ResponseResult.success(imgUrl);
    }



}
