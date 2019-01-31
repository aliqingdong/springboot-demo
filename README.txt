技术说明：
    1、项目使用springboot、spring-security、mybatis-plus、thymeleaf模版引擎、前段采用Vue及基于Vue的ElementUI

    2、权限控制使用RBAC配合security进行url权限动态细粒度控制

    3、
约定配置：
    1、需要安全访问的URL配置规则如下(至多三级)
    页面访问:/模块/子模块.shtml
    接口调用:/模块/子模块/操作.do
    eg:
        /sys-menu/index.shtml /sys-menu/edit.shtml
        /sys-menu/index/save.do /sys-menu/update.do

    2、"/rest/**"无论页面访问还是接口调用一律放行，由于不经框架处理，可不用遵守上述URL规则。
    系统提供的外部接口URL可采用此方式。接口鉴权通过统一的拦截器处理或接口单独处理。

    3、