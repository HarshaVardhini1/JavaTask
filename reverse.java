public class reverse
{
	public static void main(String ar[])
	{
		int num=123;
		int temp=num;
		int reverse=0;
		while(temp>0)
		{
			int rem=temp%10;
			temp=temp/10;
			reverse=reverse*10+rem;	
		}
		System.out.println(reverse);
	}
}
