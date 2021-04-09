package text.ceshi.day5;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
 /**
 * <p>Description:MyReflect.java</p>
 * @author jzy
 * @version v1
 * @date 2021年3月30日 下午3:05:32
 */
public class MyReflect {

	public static void main(String[] args) {
		MyReflect ref=new MyReflect();
		ref.test04();
	}
	
	/**
	 * 如何获取Class类型的对象
	 */
	public void test01(){
		//1
//		try {
//			Class clazz=Class.forName("text.ceshi.day5.Student");
//			Student st=(Student)clazz.newInstance();
//			System.out.println(st);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//2
//		Class clazz=Student.class;
//		Student st;
//		try {
//			st=(Student)clazz.newInstance();
//			System.out.println(st);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//3
//		Student st=new Student();
//		try {
//			Class clazz=st.getClass();
//			Student stt=(Student)clazz.newInstance();
//			System.out.println(stt);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 反射获取构造方法
	 */
	public void test02(){
		try {
			Class clazz=Class.forName("text.ceshi.day5.Student");
			
			//取public的构造方法
//			Constructor<?>[] constructor=clazz.getConstructors();
			
			//取任意修饰符构造方法
			Constructor<?>[] constructor=clazz.getDeclaredConstructors();
//			for (Constructor<?> c : constructor) {
//				System.out.println("构造方法名称==="+c.getName()+"参数个数=="+c.getParameterCount());
//			}
			
			//通过构造方法创建对象
//			constructor[0].setAccessible(true);
//			Student st=(Student)constructor[0].newInstance();
			
			//调用有参的构造方法
			Student st=(Student)constructor[1].newInstance("张一豪",20);
			System.out.println(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过反射获取类的变量
	 */
	public void test03(){
		try {
			Class<?> clazz = Class.forName("text.ceshi.day5.Student");
			Constructor<?> c=clazz.getDeclaredConstructor();
			c.setAccessible(true);
			Student st=(Student)c.newInstance();
			//获取public的变量
//			Field[] fileds=clazz.getFields();
			
			//获取所有的修饰符的变量
			Field[] fileds=clazz.getDeclaredFields();
			for (Field filed : fileds) {
				String name=filed.getName();
				System.out.println("修饰符=="+Modifier.toString(filed.getModifiers())+",变量名称=="+filed.getName()+",数据类型=="+filed.getType());
				if("name".equals(name)){
					filed.set(st, "张一豪");
				}else if("age".equals(name)){
					filed.setAccessible(true);
					filed.set(st, 20);
				}
			}
			System.out.println(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过反射获取方法
	 */
	public void test04(){
		try {
			Class clazz=Class.forName("text.ceshi.day5.Student");
			//有参数的构造方法
			Constructor constructor=clazz.getConstructor(String.class,int.class);
			Student st=(Student)constructor.newInstance("张一豪",20);
			//获取所有过早方法
//			Method[] methods=clazz.getDeclaredMethods();
//			for (Method method : methods) {
//				System.out.println("修饰符=="+Modifier.toString(method.getModifiers())+","
//						+ "方法名称=="+method.getName()+",返回值类型=="+method.getReturnType()+","
//								+ "参数个数=="+method.getParameterCount());
//				String name=method.getName();
//				if("setName".equals(name)){
//					method.invoke(st, "徐伟");
//				}
//			}
			//指定调用某个方法
			Method method=clazz.getMethod("setAge", int.class);
			method.invoke(st, 30);
			System.out.println(st);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Student{
	public String name;
	private int age;
	private Student(){
		
	}
	public Student(String name,int age){
		this.name=name;
		this.age=age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
}
