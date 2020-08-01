//每次新增信息请用************************做分割线
    Mall Version 0.0
  1.此版本是最初版本，没有实现任何功能
  
  2.已经使用generator生成器自动生成admin adminExample adminMapper adminMapper.xml
  
  3.各个文件夹都已创建完毕
  
//**************************************************

1、新增登入和注册页面，并且二者可以实现相互跳转

2、为了实现跳转功能，对controller中的CommonController.java进行了一些修改

3、在static\layuiadmin\layui\css中的layui.css文件添加了✉的css特殊符号

4、数据库用户表新增phone和email属性

//**************************************************
第一版本/版本1.0

1.重写了User，UserExample和UserMapper的内容，新增了各种Extend里的代码

2.对Controller里面的大部分进行了整合，可能有更改返回值，会对页面造成一定影响

3.新增了几个页面与个人信息的功能有关，为了联系更顺畅，略微修改了几个相关页面，不会影响使用

4.需要使用账号或密码等数据的时候建议使用session，使用方法类比Infocontroller，如果有需要更改个人信息后，记得更新session里的数据
