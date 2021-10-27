

## SpringBoot

## https://dwz.cn/P1N121RT

核心：

1. 配置编写 yaml

2. 自动装配（重要）

3. 继承web开发： 业务的核心

4. 集成 数据库 druid

5. 分布式：Dubbo+zookeeper
6. swagger：接口文档
7. 任务调度
8. Spring Security： shiro

-------

initializr创建

更改端口：

server.port= 8081

---

## 原理



### 自动配置：

pom.xml

1. spring-boot-dependencies： 核心依赖在父工程中
2. 我们在引入一些speingboot依赖时，不需要指定版本，因为就有这些版本的仓库

启动器：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
```

1. 启动器：就是speingboot的启动场景
2. 比如springboot-start-starter-web，就会自动导入所有的web环境
3. springboot会将所有的场景变成启动器，需要什么功能就找对应的启动器

主程序：

```java
@SpringBootApplication: //启动类下的所有资源都会被导入
public class Springboot01HelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01HelloworldApplication.class, args);
    }

}
```

注解： 

```java
@SpringBootConfiguration
springboot的配置
@configuration：spring 配置类
@Component： 说明这也是一个spring的组件
  
  
  
  
@EnableAutoConfiguration： 自动导入配置
	@AutoConfigurationPackage：自动配置包
			@Import(AutoConfigurationPackages.Registrar.class)：导入自动配置包
	@Import(AutoConfigurationImportSelector.class)：自动配置导入选择
//获取所有配置
  List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);


```



获取候选配置

```java
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
   List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
         getBeanClassLoader());
   Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you "
         + "are using a custom packaging, make sure that file is correct.");
   return configurations;
}
```

![image-20210918181746164](/Users/chris/Documents/image-20210918181746164.png)

![image-20210918182050150](/Users/chris/Documents/image-20210918182050150.png)

----

.yaml

```yaml
#普通 key value
name: Chris

#对象：
student1:
  name: Chris
  age: 25

#行内写法
student2: {name:Zhifang,age20}


#数组
pets1:
  - cat
  - dog
  - pig

pets2: [cat,dog,pig]
```

Yaml 可以给实体类赋值

```yaml
person:
  name: wangzhifang
  age: 3
  happy: false
  birth: 2019/02/02
  maps: {k1: v1,k2: v2}
  lists:
    - code
    - music
    - firl
  dog:
    name: 旺财
    age: 2
```



```java
  @Component

//  @ConfigurationProperties(prefix="person")
//yaml 赋值

@PropertySource(value = "classpath:wangzhifang.properties")
 // 配置文件赋值
public class Person {

     @Value("${name}")
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
```

松散绑定：

last-name和lastName一个意思



JSR303校验：

```java
/@PropertySource(value = "classpath:wangzhifang.properties")
  @Validated //数据校验
public class Person {

     @Email(message = "邮箱错误")
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
```

```java
 default message [邮箱错误]; origin class path resource [application.yaml] - 26:9
```

yml文件优先级：

项目目录下的config

![图片](https://mmbiz.qpic.cn/mmbiz_png/uJDAUKrGC7IPEXZtUAUBhnSZvUmrPzbDUoiazZ6ehegLG4doZK0uSJHribIqwVKiaNibSaYZSgjZf4kGzhLdGrkzzw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



```
优先级1：项目路径下的config文件夹配置文件
优先级2：项目路径下配置文件
优先级3：资源路径下的config文件夹配置文件
优先级4：资源路径下配置文件
```

spring的多环境配置：

```properties
# spring的多环境配置,可以选择激活哪个配置环境
spring.profiles.active=dev
```



------

## 配置文件到底写什么

1、SpringBoot启动会加载大量的自动配置类

2、我们看我们需要的功能有没有在SpringBoot默认写好的自动配置类当中；

3、我们再来看这个自动配置类中到底配置了哪些组件；（只要我们要用的组件存在在其中，我们就不需要再手动配置了）

4、给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们只需要在配置文件中指定这些属性的值即可；

**xxxxAutoConfigurartion：自动配置类；**给容器中添加组件

**xxxxProperties:封装配置文件中相关属性；**

----

## Web 开发

--

静态资源：

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
   if (!this.resourceProperties.isAddMappings()) {
      logger.debug("Default resource handling disabled");
      return;
   }
   addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
   addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
      registration.addResourceLocations(this.resourceProperties.getStaticLocations());
      if (this.servletContext != null) {
         ServletContextResource resource = new ServletContextResource(this.servletContext, SERVLET_LOCATION);
         registration.addResourceLocations(resource);
      }
   });
}
```

webjars

相当于mvn依赖

静态资源优先级

```java
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
				"classpath:/resources/", "classpath:/static/", "classpath:/public/" };

		/**
		 * Locations of static resources. Defaults to classpath:[/META-INF/resources/,
		 * /resources/, /static/, /public/].
		 */
		private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;

```

总结：

springboot中，可以使用以下方式来处理静态资源

1. webjars localhost：8080/webjars /**
2. public, static, /**, resources   localhost:8080

resources/, /static(默认)/, /public/].



-----

## 模版引擎

thymleaf

只要导入对应依赖就可以

```xml
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring5</artifactId>
</dependency>

<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-java8time</artifactId>
</dependency>
```

将html放入template就行了

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!--所有h5元素都能被thymleaf接管-->

   <h1 th:text="${msg}"></h1>

<div th:text="${msg}"></div>
<div th:utext="${msg}"></div>

   <hr>

遍历：数组
<h3 th:each="user:${users}" th:text="${user}"></h3>

</body>
</html>
```

-----------

## 任务

异步任务



```java
public class AsyncService {

          // 只需要告诉 spring 这是一个异步方法
            @Async
           public void hello(){
               try {
                   Thread.sleep(3000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               System.out.println("数据正在处理");
           }



}
```

```java
@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @RequestMapping("/hello")
    public String hello(){

        asyncService.hello(); //停止3秒

        return "ok";

    }


}
```

```java
@SpringBootApplication
@EnableAsync     // main 方法开起async
public class Springboot08TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08TaskApplication.class, args);
    }

}
```

定时任务-timer

邮件发送

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

```propertities

```

配置类

```xml
spring.mail.username=164932606@qq.com
spring.mail.password=tmegtevokvnbbghc
spring.mail.host=smtp.qq.com

# 开启加密认证
spring.mail.properties.mail.smpt.enable=true
```

```java
@Autowired
JavaMailSenderImpl mailSender;


@Test
void contextLoads() {

    // 一个简单的邮件发送
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setSubject("Chris 你好");     // 标题
    mailMessage.setText("测试一下使用邮件发送");   //内容
    mailMessage.setTo("zwan686@aucklanduni.ac.nz"); //发送给谁
    mailMessage.setFrom("164932606@qq.com");     // 谁发送的
    mailSender.send(mailMessage);

}
```

```java
@Test
void contextLoads2() throws MessagingException {
    // 在项目中放在controller 中
    // 复杂邮件的邮件发送
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    // 组装
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

    helper.setSubject("复杂邮件发送");
    helper.setText("<p style='color:red'>复杂邮件发送测试</p>",true);   // 正常是无法发送html标签的，如果参数中加入true,则可支持html
    // 附件

    helper.addAttachment("1.jpg",new File("/Users/chris/Desktop/1.jpg"));
    helper.addAttachment("2.jpg",new File("/Users/chris/Desktop/2.jpg"));

    helper.setTo("zwan686@aucklanduni.ac.nz");
    helper.setFrom("164932606@qq.com");

    mailSender.send(mimeMessage);

}
```

