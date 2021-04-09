package text.ceshi.day1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

 /**
 * <p>Description:LianXi.java</p>
 * @author jzy
 * @version v1
 * @date 2021年3月26日 下午3:24:14
 */
public class LianXi {

	public static void main(String[] args) {
		//txt("E:\\a.txt","E:\\b.txt");
		//image("E:\\1.jpg","E:\\2.jpg");
		//wlist("E:\\user.txt");
		//rlist("E:\\user.txt");
		a("E:\\stu.txt");
	}
	//
	public static void txt(String srcFile,String descFile){
		InputStreamReader reader=null;
		OutputStreamWriter writer=null;
		char[] c=new char[1024];
		int k=0;
		try {
			reader=new FileReader(new File(srcFile));
			writer=new FileWriter(new File(descFile));
			while((k=reader.read(c))!=-1){
				writer.write(c, 0, k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(reader, writer);
		}
	}
	//
	public static void image(String srcFile,String descFile){
		BufferedInputStream in=null;
		BufferedOutputStream out=null;
		byte[] b=new byte[1024];
		int k=0;
		try {
			in=new BufferedInputStream(new FileInputStream(new File(srcFile)));
			out=new BufferedOutputStream(new FileOutputStream(new File(descFile)));
			while((k=in.read(b))!=-1){
				out.write(b,0,k);
			}
			System.out.println("复制完成");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(out, in);
		}
	}
	//
	public static void wlist(String descFile){
		List<User> list=new ArrayList<User>();
		BufferedWriter writer=null;
		try {
			writer=new BufferedWriter(new FileWriter(new File(descFile)));
			for(int i=0;i<5;i++){
				User user=new User("用户"+i,i);
				list.add(user);
				writer.write(user.toString());
				writer.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(null,writer);
		}
	}
	//
	public static void rlist(String srcFile){
		List<User> list=new ArrayList<User>();
		String str=null;
		BufferedReader reader=null;
		try {
			reader=new BufferedReader(new FileReader(new File(srcFile)));
			while((str=reader.readLine())!=null){
				String name=str.substring(str.indexOf("=")+1, str.indexOf(","));
				String age=str.substring(str.lastIndexOf("=")+1, str.length());
				User u=new User(name,Integer.parseInt(age));
				list.add(u);
			}
			for (User user : list) {
				System.out.println(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(reader, null);
		}
	}
	//
	public static void a(String descFile){
		Scanner scanner=new Scanner(System.in);
		Student stu=null;
		List<Student> list=new ArrayList<Student>();
		BufferedWriter writer=null;
		/*int i=1;*/
		for(int i=1;i<=5;i++){
			System.out.println("请输入第"+i+"名学生的姓名：");
			String name=scanner.next();
			System.out.println("请输入第"+i+"名学生的语文成绩：");
			int cscore=scanner.nextInt();
			System.out.println("请输入第"+i+"名学生的数学成绩：");
			int mscore=scanner.nextInt();
			System.out.println("请输入第"+i+"名学生的英语成绩：");
			int escore=scanner.nextInt();
			int sum=cscore+mscore+escore;
			stu=new Student(name,cscore,mscore,escore,sum);
			list.add(stu);
		}
		sortIntMethod(list);
		try {
			writer=new BufferedWriter(new FileWriter(new File(descFile)));
			for (Student student : list) {
				writer.write(student.toString());
				writer.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(null, writer);
			scanner.close();
		}
	}
	//
	@SuppressWarnings("unchecked")
	public static void sortIntMethod(List list){
	    Collections.sort(list, new Comparator(){
			@Override
			public int compare(Object o1, Object o2) {
				Student stu1=(Student)o1;
				Student stu2=(Student)o2;
				if(stu1.getSum()<stu2.getSum()){
					return 1;
				}else if(stu1.getSum()==stu2.getSum()){
					return 0;
				}else{
					return -1;
				}
			}	    	
	    });
	}
	//关闭
	public static void close(Reader reader,Writer writer){
		try {
			if(writer!=null){
				writer.close();
			}
			if(reader!=null){
				reader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(OutputStream out,InputStream in){
		try {
			if(out!=null){
				out.close();
			}
			if(in!=null){
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
