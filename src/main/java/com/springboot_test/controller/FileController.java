package com.springboot_test.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.springboot_test.service.TestService;
import com.springboot_test.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @ClassName: com.springboot_test.controller.TestController
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/7/30 15:30
 * @Version: 1.0
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件模块")
public class FileController {

    @Autowired
    private TestService testService;
    // endpoint以杭州为例，其它region请按实际情况填写
    private String endpoint = "oss-cn-beijing.aliyuncs.com";
    // accessKey
    private String accessKeyId = "ZZZZLTAIi4DV4Aso36u1AAAAAA";
    private String accessKeySecret = "ZZZZJEdNVS4MnEi0ayllmnWxqP9Dw7tx7FAAAA";
    //空间
    private String bucketName = "lingoss0730";

    @ApiOperation(value = "下载图片")
    @GetMapping("/getStream")
    public void getProjectStream(HttpServletResponse response, @RequestParam("url") String url) {
        System.out.println(accessKeyId);
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        OSSObject ossObject = client.getObject(bucketName, url);
        InputStream content = ossObject.getObjectContent();
        try {
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            String fileName = "a.jpg";
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            //读去Object内容  返回
            BufferedInputStream in = new BufferedInputStream(content);
            byte[] car = new byte[1024];
            int L = 0;
            while ((L = in.read(car)) != -1) {
                out.write(car, 0, L);
            }
            if (out != null) {
                out.flush();
                out.close();
            }
            if (in != null) {
                in.close();
            }
            // 关闭OSSClient。
            client.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @ApiOperation(value = "文件上传")
//    @LogAnnotation(module = "文件上传", recordParam = false)
    @PostMapping
    public ResponseResult upload(@RequestParam("file") MultipartFile file, String fileSource) {
        return testService.uploadOss(file, fileSource);

    }
}
