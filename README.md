## SpringBoot

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

