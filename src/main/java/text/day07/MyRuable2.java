package demo.day07;

/**
 * 线程死锁问题
 * @author Administrator
 *
 */
public class MyRuable2{
	
	static String  s1="A";
	static String  s2="B";
	
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				synchronized (s1) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (s2) {
						System.out.println("线程一执行...");
					}
				}
			}
		},"A").start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (s2) {
					synchronized (s1) {
						System.out.println("线程2执行...");
					}
				}
			}
		},"B").start();
	}

}
