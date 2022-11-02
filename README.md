# dynamic-forms

#### 介绍
动态表单服务端实现，可自动创建虚拟组件表，通过规则请求api自动完成增删查改，接口风格遵守resultfull风格


## 🤔 为什么要做 Forms ?
随着现在低代码的时代来临，越来越多功能可以通过较低的成本快速引入，大家可以花最小的代价完成一项较有意义的功能模块。

#### 软件架构
## 🛸 模块说明 | Module
软件架构说明
```lua
dynamic-forms
├── annotation -- 核心注解声明
├── config -- 核心配置中心
├── constant -- 常量类
├── controller -- 通用控制器入口，包含增删查改，表格查询
├── data -- 多数据源情况增删查改实现类
    ├── elasticSearch -- elasticSearch查询实现(暂未实现)
    ├── jpa -- jpa查询实现(默认实现)
    └── mongodb -- mongodb查询实现(暂未实现) 
├── enums -- 枚举模块
├── exception -- 异常捕获类，包含全局异常捕获统一实体返回
├── invoke -- 数据源管理控制器，动态选择数据查询源
├── model -- 数据实体类，对应数据表，自动生成
    ├── api -- 统一接口返回实体
    ├── base -- 数据表统一定义父类，包含自动创建主键
    ├── query -- 查询相关返回对象，参数对象
    └── vo -- 视图对象，虚拟对象
├── prop -- 数据源相关参数设置
├── repository -- 数据服务层jpa实现
├── runner -- 服务启动后执行函数类
├── service -- 业务服务层
├── toolkit -- 时间工具模块
└── util -- 工具类

    
```


#### 安装教程
1.只需要引入以下依赖包即可
``` xml
<dependency>
    <groupId>com.houlangmark</groupId>
    <artifactId>dynamic-forms</artifactId>
    <version>1.2.1</version>
</dependency>
```

#### 使用说明
1.只需要在启动类上加上@EnableDynamicForms注解即可轻松实现
``` java
@EnableDynamicForms
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
``` 

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
