public class TriangularPattern
{
	public static void main(String ar[])
	{
		int num=7;
		int j;
		for(int i=1;i<=num;i++)
		{
			for(j=1;j<i;j++)
			{
				System.out.print(j+" ");
			}
			for(int k=j;k>=1;k--)
			{
				System.out.print(k+" ");
			}
			System.out.println();
		}
	}
}
