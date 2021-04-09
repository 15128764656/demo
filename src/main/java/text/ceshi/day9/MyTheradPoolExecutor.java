package text.ceshi.day9;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
 /**
 * <p>Description:MyTheradPoolExecutor.java</p>
 * @author jzy
 * @version v1
 * @date 2021年4月2日 下午1:46:23
 */
public class MyTheradPoolExecutor {

	public static void main(String[] args) {
		ThreadPoolExecutor executor=
				new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue(5), new ThreadPoolExecutor.DiscardPolicy());
		/*ThreadPoolExecutor executor=
				new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue(5), new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println("有任务被拒绝.....");
						if(!executor.isShutdown()){
							r.run();
						}
					}
				});*/
		int n=20;
		for (int i = 0; i < n; i++) {
			final int index=i;
			myRunnable myrunnable=new myRunnable(index);
			executor.execute(myrunnable);
			System.out.println("线程池中的数量=="+executor.getPoolSize()+","
					+ "队列中等待执行的任务数目=="+executor.getQueue().size()+","
					+ "已执行完毕任务=="+executor.getCompletedTaskCount());
		}
		executor.shutdown();													
	}
}

class myRunnable implements Runnable{
	int index;
	
	public myRunnable(int index){
		this.index=index;
	}
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+"正在执行任务"+index);
			Thread.currentThread().sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("任务"+index+"执行完毕");
	}
}
