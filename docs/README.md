# simpleFS初认识

> 如你所见，它是一个小型整合型的工具类，带有整合(阿里云,七牛云,又拍云,腾讯云,华为云,~~百度云~~,本地上传)OSS上传,短信发送(阿里云,腾讯云,七牛云),文件加工类,，它可以让我们脱离繁琐的开发流程，让开发变得**So easy!**.

# 支持站点
-------------------------------

| 站点 | 文件上传 | 分片上传 | 断点续传 | 文件下载 | 文件删除 |
| :--: | :--: | :--: | :--: | :--: | :--: |
| **阿里云OSS**  |✔|✔|✔|✔|✔|
| **FastDfs**   |✔|✖|✖|✔|✔|
| **华为云OBS**  |✔|✖|✖|✔|✔|
| **本地上传**   |✔|✖|✖|✔|✔|
| **腾讯云COS**  |✔|✖|✖|✔|✔|
| **七牛云Kodo** |✔|✖|✖|✔|✔|
| **又拍云**     |✔|✖|✖|✔|✔|

# 快速开始
-  安装方式(1)-引入依赖
```xml
<dependency>
  <groupId>com.zhouzifei</groupId>
  <artifactId>simpleFS</artifactId>
  <version>{latest-version}</version>
</dependency>
```
> **latest-version** 版本为：
> - 稳定版本：![](https://img.shields.io/github/v/release/shengdingbox/simpleFS?style=flat-square)
> - 快照版本：![](https://img.shields.io/maven-metadata/v.svg?label=snapshots&metadataUrl=https://repo1.maven.org/maven2/com/zhouzifei/simpleFS/maven-metadata.xml&style=flat-square)
> > 注意：无法引入可添加中央仓库地址。
>
>
> ```xml
> <repositories>
>     <repository>
>         <id>ossrh-snapshot</id>
>         <url>https://oss.sonatype.org/content/repositories/snapshots</url>
>         <snapshots>
>             <enabled>true</enabled>
>         </snapshots>
>     </repository>
> </repositories>
> ```
>
> 如果你想第一时间获取 simpleFS 的最新快照，可以添加下列代码，每次构建时都检查是否有最新的快照（默认每天检查）。
>
> ```diff
>        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
>         <snapshots>
> +           <updatePolicy>always</updatePolicy>
>             <enabled>true</enabled>
>         </snapshots>
> ```

- 安装方式(2)-使用源码安装到本地仓库
```shell
git clone https://gitee.com/zifeiZhou/simpleFS.git(Gitee)
git clone https://github.com/shengdingbox/simpleFS.git(Github)
mvn clean install
```
## 功能介绍
### 文件上传oss(支持阿里云,七牛云,又拍云,腾讯云,华为云,~~百度云~~,本地上传)

配置服务器信息

- `application.yml`方式
```yaml
tool:
  file:
    storage-type-const: 存储类型(枚举可选择)
    bucket-name: 空间名称
    local-file-path: 本地路径
    path-prefix: 图片文件夹
    domain-url: 图片外网地址
    operator-name: 又拍云账号
    operator-pwd: 又拍云密码
    access-key: 授权AK
    secret-key: 授权SK
    endpoint: 地域
```
- `application.properties`方式
```properties
tool.file.storage-type-const=存储类型(枚举可选择)
tool.file.bucket-name=空间名称
tool.file.local-file-path=本地路径
tool.file.path-prefix=图片文件夹
tool.file.domain-url=图片外网地址
tool.file.operator-name=又拍云账号
tool.file.operator-pwd=又拍云密码
tool.file.access-key=授权AK
tool.file.secret-key=授权SK
tool.file.endpoint=地域
```
获取ApiClient对象
```java
    @Autowired
    FileProperties fileProperties;

    BaseFileUploader uploader = new BaseFileUploader();
    ApiClient apiClient = uploader.getApiClient(fileProperties);
```
- 文件上传
```java
VirtualFile uploadFile(MultipartFile file);
VirtualFile uploadFile(File file);
VirtualFile uploadFile(InputStream is, String fileName);
```
- 文件下载
```java
void downloadFile(String file,  String localFile);
```
- 切片上传
```java
VirtualFile multipartUpload(File file);
```
- 文件删除
```java
boolean removeFile(String key);
```
### m3u8下载类

```java
public class M3u8DownloadUtil {
    public static void main(String[] args) {
        M3u8DTO m3u8Download = M3u8DTO.builder()
                .m3u8Url("下载地址")
                .fileName("下载完的文件名,不带后缀")
                .filePath("下载后的地址")
                .retryCount("重试次数")
                .threadCount("线程数")
                .timeout("超时时间").build();
        M3u8DownloadFactory.M3u8Download instance = M3u8DownloadFactory.getInstance(m3u8Download);
        instance.runDownloadTask();//开始下载
        M3u8DownloadFactory.destroy();//销毁实例
    }
}
```

## 特别鸣谢
- `spring-boot-demo` 深度学习并实战 spring boot 的项目: [https://github.com/xkcoding/spring-boot-demo](https://github.com/xkcoding/spring-boot-demo)
- `腾讯云COS`免费空间50G,免费流量10G/月 [https://cloud.tencent.com/product/cos](https://cloud.tencent.com/product/cos)
- `七牛云`免费空间10G,免费流量10G/月,免费GET100万次/月[https://www.qiniu.com/prices](https://www.qiniu.com/prices)
- `又拍云`免费空间10G,免费流量15G/月(非开通就有,需要额外申请又拍云联盟,限时1年）[https://www.upyun.com/league](https://www.upyun.com/league)
- `网易云`免费空间50G,免费流量20G/月（目前发现最慷慨的一家）[https://www.163yun.com/nos/free](https://www.163yun.com/nos/free)
- `青云`免费空间10G,免费流量1G/月,另外注意没有免费请求额度[https://www.qingcloud.com/pricing-standard](https://www.qingcloud.com/pricing-standard)
- `阿里云`免费空间40G,免费流量10G/月 （限定新用户、限时6个月）[https://www.aliyun.com/product/oss](https://www.aliyun.com/product/oss)
- `FASTDFS`一个开源的轻量级分布式文件系统[https://github.com/happyfish100/fastdfs/wiki/](https://github.com/happyfish100/fastdfs/wiki/)
- `FASTDFS`一个开源的轻量级分布式文件系统[https://github.com/happyfish100/fastdfs/wiki/](https://github.com/happyfish100/fastdfs/wiki/)



## 在线文档
- <a href = "http://docs.zhouzifei.com/">http://docs.zhouzifei.com/</a>
