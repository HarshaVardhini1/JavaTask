public class square{
	public static void main(String ar[]){
		int num=1;
		int row=4;
		int diff=0;
		for(int i=1;i<=row;i++)
		{
			if(i%2==0)
			{
				num+=1;
				diff=-1;
			}
			else
			{
				num+=-1;
				diff=1;
			}	
			for(int j=1;j<=row;j++)
			{
				num+=diff;
				System.out.print(num+" ");
			}
			num+=row;
			System.out.println();
		}
	}
}	
