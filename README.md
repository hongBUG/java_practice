# java_practice
daily practice

## java生成验证码，html表单验证登录
		由VerifyCode类生成验证码 ，
		并提供静态output(BufferedImage, OutputStream)方法进行输出验证码图片,
		同时提供getText()方法返回验证码的内容 由后台提供验证
## 18.7.6 修正
> 1.更改了请求路径，路径中需要加上项目名称
> 2.更正了getimage方法中对于drawString方法的使用
		g.drawString(sb.toString() + "", i*width/4, height - 5);改为
		g.drawString(sb.charAt(i) + "", i*width/4, height - 5);
		因为sb.toString()是当前的文本 相当于第一个字符画了四遍， 第二个字符画了三遍...
## 添加了运行效果截图
![](https://github.com/hongBUG/java_practice/raw/master/image/verify.png) 

## 18.7.7 java反射复习
> demo1(): 通过java反射机制得到类的包名和类名
> demo2(): 验证所有的类都是class类的实例对象
> demo3(): 通过java反射机制，用class。instance创建对象（设施反射存在的意义所在） 无参构造函数
> demo4(): 通过java反射机制得到一个类的所有构造函数，并实现构造带参数的实例对象
> demo5(): 通过java反射机制使用get set方法操作成员变量
> demo6(): 通过java反射机制得到类的一些属性 ：继承的接口 父类 函数信息 成员信息 类型 等等
> demo7(): 通过java反射机制调用类中的方法  有参 无参
> demo8(): 通过java反射机制获得类加载器
![](https://github.com/hongBUG/java_practice/raw/master/image/reflect.png) 
