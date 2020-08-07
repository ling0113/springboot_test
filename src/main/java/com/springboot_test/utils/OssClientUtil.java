package com.springboot_test.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

@Service
public class OssClientUtil {
    public static Logger logger = LoggerFactory.getLogger(OssClientUtil.class);
    @Value("${oss.endpoint}")
    private String endpoint;// "oss-cn-hangzhou.aliyuncs.com";
    @Value("${oss.accessKeyId}")
    private String accessKeyId;// "LTAIgVMd1E5jODQ";
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;// "va7pTIrOWDOFYaxCD5M06TuOQ";
    @Value("${oss.bucketName}")
    private String bucketName;//
    // 文件存储目录
    private String filedir = "test01/";
    @Autowired
    private OSSClient ossClient;

    @Bean("ossClient")
    public OSSClient getOssClient() {
        @SuppressWarnings("deprecation")
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }

    public String uploadByByteToOSS(byte[] bcyte, String fileName) {
        String resultStr = null;
        Long fileSize = (long) bcyte.length;
        // 创建上传Object的Metadata
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileSize);
        // 指定该Object被下载时的网页的缓存行为
        metadata.setCacheControl("no-cache");
        // 指定该Object下设置Header
        metadata.setHeader("Pragma", "no-cache");
        // 指定该Object被下载时的内容编码格式
        metadata.setContentEncoding("utf-8");
        // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
        // 如果没有扩展名则填默认值application/octet-stream
        metadata.setContentType(getContentType(fileName));
        // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
        String filePath = filedir + fileName;
        // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
        metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
        ossClient.putObject(bucketName, filePath, new ByteArrayInputStream(bcyte),
                metadata);
        // -------------------
        StringBuilder sb = new StringBuilder(endpoint + "/" + filePath);// 构造一个StringBuilder对象
        sb.insert(7, bucketName + ".");// 在指定的位置插入指定的字符串
        resultStr = sb.toString();
        // -------------------

        return resultStr;
    }
    /**
     * 上传图片至OSS
     * @return String 返回的唯一MD5数字签名
     */
    public String uploadObject2OSS(InputStream is, String fileName) {
        String resultStr = null;
        try {
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            String filePath = filedir + fileName;
            metadata.setContentDisposition("filename/filesize=" + (filePath));
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, filePath, is, metadata);
            // 解析结果
            // PutObjectResult putResult = ossClient.putObject(bucketName, filePath, is, metadata);
            resultStr = putResult.getETag();
            StringBuilder sb = new StringBuilder(endpoint + "/" + filePath);// 构造一个StringBuilder对象
            sb.insert(7, bucketName + ".");// 在指定的位置插入指定的字符串
            resultStr = sb.toString();
            // 关闭OSSClient。
            //
        } catch (Exception e) {
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }
    /**
     * 上传到OSS服务器 如果同名文件会覆盖服务器上的
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        try {
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            String filePath = filedir + fileName;
            // 上传文件
            ossClient.putObject(bucketName, filePath, instream, objectMetadata);
            ret = endpoint + "/" + filePath;
            // ret = putResult.getETag();
            // 关闭OSSClient。

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
    /**
     * @Title: uploadFileToOSS
     * @Description: 以文件的形式上传文件到OSS
     * @param file
     * @return
     * @return: String
     * @throws IOException
     */
    public String uploadFileToOSS(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String fileName = random.nextInt(10000) + System.currentTimeMillis() + substring;
        InputStream inputStream = null;
        inputStream = file.getInputStream();
        this.uploadFile2OSS(inputStream, fileName);
        System.out.println("fileName {}"+fileName);
        return fileName;
    }
    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String FilenameExtension) {
        logger.info("FilenameExtension:" + FilenameExtension);
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") || FilenameExtension.equalsIgnoreCase(".jpg")
                || FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") || FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") || FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (FilenameExtension.equalsIgnoreCase(".pdf")) {
            return "application/pdf";
        }
        return "image/jpg";
    }
    /**
     * 获得图片路径
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        System.out.println(fileUrl);
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUrl(this.filedir + split[split.length - 1]);
        }
        return null;
    }
    /**
     * 获得url链接
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    private static String getContentType(String fileName) {
        logger.info("getContentType:" + fileName);
        // 文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension)
            || ".jpg".equalsIgnoreCase(fileExtension)
            || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        if (".pdf".equalsIgnoreCase(fileExtension)) {
            return "application/pdf";
        }
        // 默认返回类型
        return "image/jpg";
    }
    /**
     * @Title: getByteByFileUrl
     * @Description: 根据文件路径获取Byte流
     * @param fileName
     * @return
     * @return: byte[]
     */
    public byte[] getByteByFileUrl(String fileName) {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, filedir + fileName);
        InputStream inputStream = ossObject.getObjectContent();
        byte[] input2byte = null;
        try {
            //input2byte = IOUtil.input2byte(inputStream);
            input2byte = input2byte(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input2byte;
    }
    /**
     * @Title: getInputStreamByFileUrl
     * @Description: 根据文件路径获取InputStream流
     * @param fileName
     * @return
     * @return: InputStream
     */
    public InputStream getInputStreamByFileUrl(String fileName) {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, filedir + fileName);
        return ossObject.getObjectContent();
    }
    public static final byte[] input2byte(InputStream inStream)
        throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    public InputStream getInputStreamByFileUrls(String fileName) {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, fileName);
        return ossObject.getObjectContent();
    }
}