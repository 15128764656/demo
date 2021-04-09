package text.ceshi.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
 /**
 * <p>Description:MynewFixedThreadPool.java</p>
 * @author jzy
 * @version v1
 * @date 2021年4月2日 下午2:21:27
 */
public class MynewFixedThreadPool {

	public static void main(String[] args) {
		int threads=3;
		
		CountDownLatch countdownlatch=new CountDownLatch(threads);
		
		CyclicBarrier barrier=new CyclicBarrier(threads);
		
		MyTarsks mytarsks=new MyTarsks(countdownlatch, barrier);
		
		ExecutorService newfixedthreadpool=Executors.newFixedThreadPool(threads);
		Future<ConcurrentMap<Integer, Integer>> result=null;
		for (int i = 1; i <=threads; i++) {
			result=newfixedthreadpool.submit(mytarsks);
		}
		try {
			countdownlatch.await();
			
			ConcurrentMap<Integer, Integer> concurrentMap=result.get();
			
			Set<Entry<Integer, Integer>> entrySet=concurrentMap.entrySet();
			
			Iterator<Entry<Integer, Integer>> iterator=entrySet.iterator();
			System.out.println("总记录数="+concurrentMap.get(-1));
			while(iterator.hasNext()){
				Entry<Integer, Integer> entry=iterator.next();
				int key=entry.getKey();
				int value=entry.getValue();
				
				System.out.println("文件"+key+".txt  记录数=="+value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class MyTarsks implements Callable<ConcurrentMap<Integer, Integer>>{
	
	CountDownLatch countdownlatch;
	CyclicBarrier barrier;
	
	public MyTarsks(CountDownLatch countdownlatch,CyclicBarrier barrier){
		this.countdownlatch=countdownlatch;
		this.barrier=barrier;
	}
	ConcurrentMap<Integer, Integer> map=new ConcurrentHashMap<Integer, Integer>();
	
	AtomicInteger sum=new AtomicInteger(0);
	@Override
	public ConcurrentMap<Integer, Integer> call() throws Exception {
		String name=Thread.currentThread().getName();
		System.out.println(name+"到达.....");
		barrier.await();
		if("pool-1-thread-1".equals(name)){
			System.out.println("-------------所有线程就绪，开始计算文件-------------");
		}
		if("pool-1-thread-1".equals(name)){
			for (int i = 1; i <= 2; i++) {
				int k=readFile("e:\\"+i+".txt");
				sum.getAndAdd(k);
				map.put(i, k);
			}
		}else if("pool-1-thread-2".equals(name)){
			for (int i = 3; i <= 4; i++) {
				int k=readFile("e:\\"+i+".txt");
				sum.getAndAdd(k);
				map.put(i, k);
			}
		}else if("pool-1-thread-3".equals(name)){
			for (int i = 5; i <= 6; i++) {
				int k=readFile("e:\\"+i+".txt");
				sum.getAndAdd(k);
				map.put(i, k);
			}
		}
		map.put(-1, sum.get());
		countdownlatch.countDown();
		return map;
	}
	public int readFile(String filePath) throws Exception{
		BufferedReader bf=null;
		String str=null;
		int k=0;
		try {
			bf=new BufferedReader(new FileReader(filePath));
			while((str=bf.readLine())!=null){
				k++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bf!=null){
				bf.close();
			}
		}
		return k;
	}
	
}