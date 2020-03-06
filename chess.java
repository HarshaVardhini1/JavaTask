public class chess{
	public static void main(String ar[]){
	int num=1;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){			
				 if(num == 1)
			    	 {
					System.out.print("1");
				 }
			    	 else
			    	 {
					System.out.print("0");
				 }
            			num*=-1;	
			}
			num*=-1;
			System.out.println();
		}
	}
}	
