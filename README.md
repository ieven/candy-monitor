# candy-monitor
agent server web monitor system by java
agent用于采集目标机器上数据（目前agent只是一个框架，具体插件还未实现）
server目的是替代graphite，由于其不能接收字符串，只能通过web接口访问数据库whisper且不能更换数据库，局限性太大。candy-monitor支持任何一种graphite数据源，如：collectd
server用来处理接受到的数据（目前server已经初步完成web界面，并实现了与graphite相同功能的render插件）
![](https://github.com/ieven/candy-monitor/blob/master/mongodb.png)
![](https://github.com/ieven/candy-monitor/blob/master/web.png)
![](https://github.com/ieven/candy-monitor/blob/master/render.png)
虽然实现了render插件，但用户可根据自己情况选择使用或者直接读取mongodb
candy-monitor采用mongo存储数据且可根据具体情况更换，提供灵活接口，方便进行二次开发。
MyEclipse项目，配置简单，startUp为启动入口。
