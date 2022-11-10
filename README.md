# dynamic-forms

## 一.介绍
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


## 二. 安装教程
1.只需要引入以下依赖包即可
``` xml
<dependency>
    <groupId>com.houlangmark</groupId>
    <artifactId>dynamic-forms</artifactId>
    <version>1.2.2</version>
</dependency>
```

## 三. 使用说明
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

## 四. 调用示例
### 1.以下调用为举例，以组件库类FormsAssembly举例，其他组件类同理，替换相应的字段属性即可
### 2.[接口文档示例](https://www.showdoc.cc/2123913536001181)
### 3.主要接口展示
#### 3.1. 组件字段属性展示
``` shell
GET /forms-api/build/{assembly}
``` 
|      参数名      |   参数位置   |    参数值    | 参数含义    | 是否必填  |
|:-------------:|:--------:|:---------:|:-------:|:-----:|
|   assembly    |   path   |     FormsAssembly      |  组件类名   | Y     |

2. 组件列表查询
``` shell
POST /forms-api/data/table/{assembly}
``` 
|      参数名      | 参数位置 |            参数值             |    参数含义    | 是否必填 |
|:-------------:|:----:|:--------------------------:|:----------:|:----:|
|   pageIndex    | body |             0              |     页码     |  Y   |
|   pageSize    | body |             10             |     页数     |  Y   |
|   sort    | body |            name            |    列名排序    |  N   |
|   condition    | body | {"key": "id","value": "1"} | 条件查询，k,v格式 |  N   |

3. 组件新增
``` shell
POST /forms-api/data/modify/{assembly}
``` 
|      参数名      | 参数位置 |      参数值      | 参数含义 | 是否必填 |
|:-------------:|:----:|:-------------:|:----:|:----:|
|   assembly    | path | FormsAssembly | 组件名  |  Y   |
|   assemblyName    | body |      地图       | 组件名称 |  Y   |
|   required    | body |     true      | 是否必填 |  Y   |
|   editType    | body |      map      | 组件类型 |  Y   |

4. 组件详情展示
``` shell
GET /forms-api/data/{assembly}/{id}
``` 
|   参数名    | 参数位置 |      参数值      | 参数含义 | 是否必填 |
|:--------:|:----:|:-------------:|:----:|:----:|
| assembly | path | FormsAssembly | 组件名  |  Y   |
|    id    | body |       1       | 组件id |  Y   |

5. 组件修改
``` shell
PUT /forms-api/data/modify/{assembly}
``` 
|     参数名      | 参数位置 |      参数值      | 参数含义 | 是否必填 |
|:------------:|:----:|:-------------:|:----:|:----:|
|   assembly   | path | FormsAssembly | 组件名  |  Y   |
| assemblyName | body |      地图       | 组件名称 |  Y   |
|      id      | body |       1       |  id  |  Y   |
|   required   | body |     true      | 组件类型 |  Y   |
|   editType   | body |      map      | 组件类型 |  Y   |

6. 组件删除
``` shell
DELETE /forms-api/data/modify/{assembly}/{id}
``` 
|     参数名      | 参数位置 |      参数值      | 参数含义 | 是否必填 |
|:------------:|:----:|:-------------:|:----:|:----:|
|   assembly   | path | FormsAssembly | 组件名  |  Y   |
|      id      | path |       1       |  id  |  Y   |

7. 组件批量删除
``` shell
DELETE /forms-api/data/modify/{assembly}
``` 
|   参数名    | 参数位置  |      参数值      |    参数含义     | 是否必填 |
|:--------:|:-----:|:-------------:|:-----------:|:----:|
| assembly | path  | FormsAssembly |     组件名     |  Y   |
|   ids    | param |       1       | id数组，可写多个ID |  Y   |
|   ids    | param |       2       |    id数组     |  Y   |

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


### ⭐️ Forms 使用 MulanPSL2 协议，源代码完全开源，无商业限制。 开源不易如果喜欢请给作者 Star 鼓励 👇

---

<p align="center">
    <a href="https://github.com/zhuoxiaoya/dynamic-forms">Github 仓库</a> &nbsp; | &nbsp; 
    <a href="https://gitee.com/zxy130359/dynamic-forms">码云仓库</a> &nbsp; | &nbsp; 
</p>

---