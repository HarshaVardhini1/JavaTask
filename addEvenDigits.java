public class addEvenDigits
{
	public static void main(String ar[])
	{
		int num=2345;
		int temp=num;
		int sum=0;
		while(temp>0)
		{
			int rem=temp%10;
			temp=temp/10;
			if(rem%2==0)
			{
				sum=sum+rem;
			}
		}
		System.out.println(sum);
	}
}
