package text.ceshi.day8;

import java.util.concurrent.atomic.AtomicInteger;
 /**
 * <p>Description:MyThreadTx.java</p>
 * @author jzy
 * @version v1
 * @date 2021年4月1日 下午2:51:17
 */
public class MyThreadTx {
	//锁
	private static final Object obj=new Object();
	
	private static volatile AtomicInteger k=new AtomicInteger(1);
	
	public static void main(String[] args) {
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					for (int i = 0; i < 10; i++) {
						try {
							while(k.get()!=1){
								obj.wait();
							}
							System.out.println("A");
							k.set(2);
							Thread.sleep(100);
						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							//obj.notify();
							obj.notifyAll();
						}
					}
				}
			}
		});
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					for (int i = 0; i < 10; i++) {
						try {
							while(k.get()!=2){
								obj.wait();
							}
							System.out.println("B");
							k.set(3);
							Thread.sleep(100);
						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							//obj.notify();
							obj.notifyAll();
						}
					}
				}
			}
		});
		Thread t3=new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					for (int i = 0; i < 10; i++) {
						try {
							while(k.get()!=3){
								obj.wait();
							}
							System.out.println("C");
							k.set(1);
							Thread.sleep(100);
						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							//obj.notify();
							obj.notifyAll();
						}
					}
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();
	}

}
