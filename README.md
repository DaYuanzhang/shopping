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

//**************************************************
版本1.1

1.新增注册界面，注册完毕会直接跳转到登录界面

2.将修改密码界面的账号属性改为只读

//**************************************************
版本“都不知道第几版了”

1、db.sql文件执行有错误，第15行的插入语句中的”phone“更改为”tel“

2、login.html中将22行<div>标签改为<form>标签并增加了action="/loginrequest"
   将63行form.on方法内原语句注释，于66行新增方法admin.req，完成登录。

3、LoginController中，将31行”登录失败“改为”密码错误“，将25、29、31行中code改为0

//**************************************************
版本“都不知道第几版了”

1、db.sql文件执行有错误，第15行的插入语句中的”phone“更改为”tel“

2、login.html中将22行<div>标签改为<form>标签并增加了action="/loginrequest"

   将63行form.on方法内原语句注释，于66行新增方法admin.req，完成登录。

3、LoginController中，将31行”登录失败“改为”密码错误“，将25、29、31行中code改为0

  将22行@RequestBody删除
//**************************************************
版本“更不知道第几版了”

1.新增根据不同角色用户登录到不同页面的功能，目前3个角色的用户登录后都会进入AdminPage页面，如有需要可在login.html中的77,86,95行更改。

//***************************************************
版本“别问我这是第几版”

1、修改LoginController.java文件：删除第43行中的@RequestBody；将第48、50行的code：“200”改为“0”。

2、修改reg.html文件：将第22行中的div标签改为form标签，并在form标签中增加了action="/regrequest"；

   修改了24、25行的class、for、id、lay-verify中的内容，纠正了用户名样式；
    
   在33行中在lay-verify中增加了confirmPass内容，用于检测两次输入密码是否相等；
   
   在77~96行新增form.verify方法，用于用户注册的输入检测，将原$.ajax方法注释掉，新增admin.req方法，并把原来在form.on方法里的“同意用户协议”的if语句增加进admin.req方法，判断“密码输入不一致”的if语句加入form.verify方法；
   
//***************************************************
     版本“别问我这是第几版2.33”

  1.完成了管理员订单列表的全部功能，包括增删查改以及搜索功能。
  
  2.完成了管理员商家列表和客户列表的全部功能以及搜索功能。
  
//******************************************************************************************版本0.35

  1、小改动：login.html文件在101行增加return false;语句，解决不能登录的问题。
  
//***************************************************版本0.5
       
  
  1.订单增加了addr地址属性
  2.增加了商家页面MerchantPage
  
//*****************************************************************************************版本0.55

  1、login.html文件对77、86、96行做了修改，增加跳转页面；99行增加了登陆失败的样式；
  
  2、CommonController.java文件将原来的部分controller注释掉了
    
    
  