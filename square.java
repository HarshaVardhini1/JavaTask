public class square{
	public static void main(String ar[]){
		int num=0;
		for(int i=1;i<=4;i++){
			for(int j=4;j>=1;j--){
				if(j==4 && i!=1){
					num+=j;
				}
				if(i%2==0){
					System.out.print(num+" ");
					num--;
				}
				else{	
					num++;	
					System.out.print(num+" ");	
				}
			}
			System.out.println();
		}
	}
}	
