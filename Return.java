
public class Return {

	
	int test() {
		int k=10;
		try {
			
			
			int l=10/0;
			System.out.println("1"+k);
			return k;
		}catch(Exception e) {
			System.out.println("2"+k);
			return k;
		}finally {
			System.out.println("3"+k);
			return k;
		}
		//return 0;
		
		
	}
	
	public static void main(String[] args) {
		Return r=new Return();
		r.test();
	}
	
	
	
	
	
}
