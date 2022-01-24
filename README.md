

## TODO

- JDK自带的Manage后期封装成endpoint
- loggers endpoint也允许你在运行时改变应用的日志等级。

### 监控部分
- jvm
- 线程

图标
服务注册
心跳检测

服务端向admin注册，admin注册成功之后，根据配置，向线程池中提交任务

1、admin
2、rest 接口


正常关闭之后删除容器中的结点
非正常关闭的结点通过心跳机制 清楚结点


心跳端口添加ip白名单

9013 memory: Error request, response status: 502

### 静态变量



### 查看已经加载的类 
类似于sc的功能


### 查看已经加载的方法
类似与sm的方法


### 反汇编代码



### 动态加载









## 参考文档

admin系统可以参照下面这个链接
https://zhuanlan.zhihu.com/p/448849473


Springboot 神器之 Actuator
https://blog.csdn.net/weixin_34080951/article/details/86255102

Springboot Actuator 官方文档
https://blog.csdn.net/alinyua/article/details/80009435


服务断的心跳检测
如果有很多客户端，10s没不会发生读超时事件，但是可能会有的客户端已经失联了 这种问题如何处理？