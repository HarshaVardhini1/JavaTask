public class chess{
	public static void main(String ar[]){
	int num;
		for(int i=1;i<=4;i++){
			for(int j=1;j<=4;j++){			
				if(i%2!=0&&j%2==0 || i%2==0&&j%2!=0){
					num=0;	
				}
				else{
					num=1;
				}
				System.out.print(num+" ");	
			}
			System.out.println();
		}
	}
}	
