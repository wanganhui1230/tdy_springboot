package com.example.common.file;

import com.aliyun.oss.OSSClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 上传视频/图片 到阿里oss 2.5
 *
 */
public class AliyunOSSUpload {


    public static Map uploadVideo(MultipartFile file) throws IOException {
        Map map = new HashMap();
        InputStream input = file.getInputStream();
        Random random = new Random();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
        String num = df.format(new Date()) + random.nextInt(1000000);// new Date()为获取当前系统时间，也可使用当前时间戳
        // 对文文件的全名进行截取然后在后缀名进行删选。
        int begin = file.getOriginalFilename().indexOf(".");
        int last = file.getOriginalFilename().length();
        // 获得文件后缀名
        String a = file.getOriginalFilename().substring(begin, last);
        // 把文件读入到内存中
        String beforeFileName = file.getOriginalFilename();
        String afterFileName = num + a;
        String floder = AliyunOSSClientUtil.FOLDER;
        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();

        // 上传图片
        /*
         * System.out.println(AliyunOSSClientUtil.uploadObject2OSS(ossClient, bufImg,
         * AliyunOSSClientUtil.BACKET_NAME, floder, beforeFileName, afterFileName));
         */
        // 获取图片路径
        /* System.out.println(AliyunOSSClientUtil.getUrl(floder+afterFileName)); */
        map.put("key", AliyunOSSClientUtil.uploadObject2OSS(ossClient, input, AliyunOSSClientUtil.BACKET_NAME, floder,
                beforeFileName, afterFileName));
        map.put("url", AliyunOSSClientUtil.getUrl(floder + afterFileName));
        return map;
    }


    public static void deleteFile(String key) throws IOException {
        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        AliyunOSSClientUtil.deleteFile(ossClient, AliyunOSSClientUtil.BACKET_NAME, AliyunOSSClientUtil.FOLDER, key);
    }
}
