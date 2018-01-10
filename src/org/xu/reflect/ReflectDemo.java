package org.xu.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.xu.verify.VerifyCode;

public class ReflectDemo {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		
		// demo1(): 通过java反射机制得到类的包名和类名
		Demo1();
		System.out.println("================================================================");
		
		// demo2(): 验证所有的类都是class类的实例对象
		Demo2();
		System.out.println("================================================================");
		
		// demo3(): 通过java反射机制，用class。instance创建对象（设施反射存在的意义所在） 无参构造函数
		Demo3();
		System.out.println("================================================================");

		// demo4(): 通过java反射机制得到一个类的所有构造函数，并实现构造带参数的实例对象
		Demo4();
		System.out.println("================================================================");

		// demo5(): 通过java反射机制使用get set方法操作成员变量
		Demo5();
		System.out.println("================================================================");

		// demo6(): 通过java反射机制得到类的一些属性 ：继承的接口 父类 函数信息 成员信息 类型 等等
		Demo6();
		System.out.println("================================================================");

		// demo7(): 通过java反射机制调用类中的方法  有参 无参
		Demo7();
		System.out.println("================================================================");

		// demo8(): 通过java反射机制获得类加载器
		Demo8();
		System.out.println("================================================================");
	
		
	}
	
	public static void Demo1() {
		VerifyCode verify = new VerifyCode();
		System.out.println("demo1:");
		System.out.println("包名：" + verify.getClass().getPackage().getName());
		System.out.println("完整类名：" + verify.getClass().getName());
	}
	
	public static void Demo2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// 定义俩个类型都未知的class，看如何给他们赋值成实体类
		Class<?> class1 = null;
		Class<?> class2 = null;
		
		// 方法1 可能抛出ClassNotFoundException 推荐使用
		class1 = Class.forName("org.xu.verify.VerifyCode");
		System.out.println("demo2(方法1):");
		System.out.println("包名：" + class1.getClass().getPackage().getName());
		System.out.println("完整类名：" + class1.getClass().getName());
		
		// 方法2
		class2 = VerifyCode.class;
		System.out.println("demo2(方法2):");
		System.out.println("包名：" + class2.getClass().getPackage().getName());
		System.out.println("完整类名：" + class2.getClass().getName());
		
	}
	
	public static void Demo3() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> class1 = null;
		class1 = Class.forName("org.xu.reflect.Person");
		// 这里不能带参数  所有实例化的类要有无参的构造函数
		Person person = (Person) class1.newInstance();
		person.setAge(20);
		person.setName("HongKucha");
		System.out.println("demo3:");
		System.out.println("年龄：" + person.getAge());
		System.out.println("姓名：" + person.getName());
	}
	
	/**
	 */
	public static void Demo4() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<?> class1 = null;
		Person person1 = null;
		Person person2 = null;
		
		class1 = Class.forName("org.xu.reflect.Person");
		// 得到该类的构造函数
		// 这里获取到的第一个构造方法为含参构造方法 第二个构造方法为无参构造方法  顺序与定义时的顺序无关
		Constructor<?> [] constructors = class1.getConstructors();
		person1 = (Person) constructors[0].newInstance(19, "HongGe");
		
		person2 = (Person) constructors[1].newInstance();
		person2.setAge(12);
		person2.setName("HongKucha");
		System.out.println("demo4:");
		System.out.println("年龄：" + person1.getAge() + ", 姓名：" + person1.getName());
		System.out.println("年龄：" + person2.getAge() + ", 姓名：" + person2.getName());
	}
	
	/**
	 * 通过java反射机制操作成员变量 get、set方法
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void Demo5() throws ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		Class<?> class1 = null;
		class1 = Class.forName("org.xu.reflect.Person");
		Object obj = class1.newInstance();
		Field personField = class1.getDeclaredField("name");
		personField.setAccessible(true);
		personField.set(obj, "HongKucha");
		
		System.out.println("Demo5: \n 修改属性之后得到属性变量的值：" + personField.get(obj));
	}
	
	/**
	 * 通过java反射机制得到类的一些属性： 继承的接口，父类，函数信息， 成员信息， 类型。。。
	 * @throws ClassNotFoundException
	 */
	public static void Demo6() throws ClassNotFoundException {
		Class<?> class1 = null;
		class1 = Class.forName("org.xu.reflect.SuperMan");
		
		// 取得父类名称
		Class<?> superClass = class1.getSuperclass();
		System.out.println("Demo6:\n SuperMan类的父类名：" + superClass.getName());
		System.out.println("================================================================");
		
		// 获取属性
		Field[] fields = class1.getDeclaredFields();
		System.out.println(class1.getName() + "类中的成员：");
		for (Field f: fields) {
			System.out.println(f + ", ");
		}
		System.out.println("================================================================");
		
		// 获取类的方法
		Method[] methods = class1.getDeclaredMethods();
		System.out.println(class1.getName() + "类中的方法为：");
		for (Method m: methods) {
			System.out.println("函数名：" + m.getName());
			System.out.println("函数返回类型：" + m.getReturnType());
			System.out.println("函数访问修饰符：" + Modifier.toString(m.getModifiers()));
			System.out.println("函数代码写法：" + m);
			System.out.println("函数参数个数：" + m.getParameterCount());
			System.out.println("函数参数声明：" + m.getParameterAnnotations().toString());
			System.out.println("函数参数类型：" + m.getParameterTypes().toString());
		}
		System.out.println("================================================================");
		
		// 取得类实现的接口因为类接口也属于Class 所以取得接口中的方法和上述一样
		Class<?> interfaces[] = class1.getInterfaces();
		for (Class c: interfaces) {
			System.out.println("实现的接口类名" + c.getName());
		}
	}
	
	/**
	 * 通过java反射机制调用类的方法  有参 无参
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static void Demo7() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class<?> class1 = null;
		class1 = Class.forName("org.xu.reflect.SuperMan");
		
		System.out.println("Demo7:");
		System.out.println("调用无参方法fly()");
		Method method = class1.getMethod("fly");
		method.invoke(class1.newInstance());
		
		System.out.println("调用有参方法walk(int m)：");
		method = class1.getMethod("walk", int.class);
		method.invoke(class1.newInstance(), 300);
	}
	
	
	public static void Demo8() throws ClassNotFoundException {
		Class<?> class1 = null;
		class1 = Class.forName("org.xu.reflect.SuperMan");
		String nameString = class1.getClassLoader().getClass().getName();
		System.out.println("Demo8: 类加载器名：" + nameString);
	}
	
	
	
}
