package text.ceshi.day1;
 /**
 * <p>Description:User.java</p>
 * @author jzy
 * @version v1
 * @date 2021年3月26日 下午4:10:57
 */
public class User {
	private String name;
	private int age;
	
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public User() {
		super();
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
		return "name=" + name + ", age=" + age;
	}
}
