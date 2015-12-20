# candy-monitor
agent server web monitor system by java
agent用于采集目标机器上数据
server用来处理接受到的数据
server目的是替代graphite，由于其只能通过web接口访问数据库whisper且不能更换数据库，局限性太大。
candy-monitor采用mongo存储数据且可根据具体情况更换，提供灵活接口，方便进行二次开发。
eclipse项目，配置简单，startUp为启动入口。
