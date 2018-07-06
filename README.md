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
![])(https://github.com/hongBUG/java_practice/raw/master/image/verify.png) 