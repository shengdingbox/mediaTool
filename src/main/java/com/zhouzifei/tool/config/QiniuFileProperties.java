package com.zhouzifei.tool.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 周子斐 (17600004572@163.com)
 * @remark 2021/1/26
 * @Description
 */
@Component
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "simple-fs.qiniu")
public class QiniuFileProperties extends FileProperties{
    private String domainUrl;
    private String bucketName;
    private String accessKey;
    private String secretKey;
    private String region;
}
