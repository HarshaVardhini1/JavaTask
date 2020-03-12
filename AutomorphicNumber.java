public class AutomorphicNumber
{
	public static void main(String arg[])
	{
		int num=25;
		int square=(int)Math.pow(num,2);
		System.out.println(square);
		int temp=num;
		int count=0;
		
		while(temp>0)
		{
			temp=temp/10;
			count++;
		}
		int divisor=(int)Math.pow(10,count);
		int remainder=square%divisor;
		if(remainder==num)
		{
			System.out.println("It is an Automorphic number!");
		}
		else
		{
			System.out.println("It is not an Automorphic number!");
		}
	}
}
