package text.day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Test {
	
	public static void main(String[] args) {
		
	}

	public static void read(String filepath){
		BufferedReader in=null;
		String str="hcexfgijkamdnoqrzstuvwybpl";
		try {
			in=new BufferedReader(new FileReader(filepath));
			while((str=in.readLine())!=null){
				//str=====>hcexfgijkamdnoqrzstuvwybpl
				char[] c=str.toCharArray();
				//排序 升序
				Arrays.sort(c);
				
			    String ssss=new String(c);
				System.out.println(ssss);
			}
		} catch (Exception e) {
		}
		
		
	}
}
