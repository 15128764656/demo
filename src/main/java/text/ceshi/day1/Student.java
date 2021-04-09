package text.ceshi.day1;
 /**
 * <p>Description:Student.java</p>
 * @author jzy
 * @version v1
 * @date 2021年3月26日 下午4:55:57
 */
public class Student implements Comparable{
	private String name;
	private int cscore;
	private int mscore;
	private int escore;
	private int sum;
	public Student(String name, int cscore, int mscore, int escore, int sum) {
		super();
		this.name = name;
		this.cscore = cscore;
		this.mscore = mscore;
		this.escore = escore;
		this.sum = sum;
	}
	public Student() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCscore() {
		return cscore;
	}
	public void setCscore(int cscore) {
		this.cscore = cscore;
	}
	public int getMscore() {
		return mscore;
	}
	public void setMscore(int mscore) {
		this.mscore = mscore;
	}
	public int getEscore() {
		return escore;
	}
	public void setEscore(int escore) {
		this.escore = escore;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", cscore=" + cscore + ", mscore="
				+ mscore + ", escore=" + escore + ", sum=" + sum + "]";
	}
	@Override
	public int compareTo(Object o) {
		return this.sum - ((Student) o).getSum();
	}
	
}
