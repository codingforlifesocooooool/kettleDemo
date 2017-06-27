package kettleDemo;


public class TestDemo {
	
	public static void main(String[] args) {
		
		//File file = new File("C:/Users/hk/Desktop/01_work/kettle/ktrs/uspa2sgp3.xml");
		String transFileName = "C:/Users/hk/Desktop/01_work/kettle/ktrs/u3tosgp3.ktr";
		try {
			KettleUtil.callNativeTrans(transFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
