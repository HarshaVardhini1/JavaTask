public class AutomorphicNumber
{
	public static void main(String arg[])
	{
		long num=625;
		long square=(long)Math.pow(num,2);
		System.out.println(square);
		long temp=num;
		int count=0;
		
		while(temp>0)
		{
			temp=temp/10;
			count++;
		}
		int divisor=(int)Math.pow(10,count);
		long remainder=square%divisor;
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
