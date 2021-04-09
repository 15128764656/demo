package text.ceshi.day6;
 /**
 * <p>Description:StaticProxy.java</p>
 * @author jzy
 * @version v1
 * @date 2021年3月31日 下午2:18:58
 */
public class StaticProxy {
	public static void main(String[] args) {
		ISearchHose searchHose=new MyProxy(new Master());
		searchHose.searchhose();
	}
}

interface ISearchHose{
	
	public void searchhose();
	
}
class Master implements ISearchHose{

	@Override
	public void searchhose() {
		System.out.println("客户找房子...");
	}
	
}

class MyProxy implements ISearchHose{
	
	private ISearchHose target;
	
	public MyProxy(ISearchHose target){
		this.target=target;
	}

	@Override
	public void searchhose() {
		System.out.println("中介找房子..");
		target.searchhose();
		System.out.println("中介退房..");
	}
	
}
