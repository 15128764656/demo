package text.ceshi.day5;

import java.io.FileInputStream;
import java.util.Properties;

 /**
 * <p>Description:Text.java</p>
 * @author jzy
 * @version v1
 * @date 2021年3月30日 下午2:33:54
 */
public class Test {

	public static void main(String[] args) {
		Properties pro=new Properties();
		try {
			pro.load(new FileInputStream("C:\\Users\\Administrator\\workspace\\demo\\src\\main\\java\\text\\ceshi\\day5\\bean.properties"));
			//读取文件属性值
			String classname=pro.getProperty("classname");
			//通过类名称构建字节码文件对象 Class类型的对象
			Class<?> clazz=Class.forName(classname);
			//反射创建对象
			IDao dao=(IDao)clazz.newInstance();
			dao.getCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
