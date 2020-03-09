public class Arithmeticprog
{
	public static void main(String ar[])
	{
		int num=5;
		int start=2;
		int diff=3;
		
		System.out.print(start);
		for(int i=1;i<num;i++)
		{
			System.out.print(",");
			System.out.print(start+(diff*i));
		}
	}
}
