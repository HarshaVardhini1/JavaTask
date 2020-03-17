import java.util.Scanner;
public class Disarium
{
	public static void main(String ar[])
	{
		Scanner input=new Scanner(System.in);
		
		System.out.println("Enter the number : ");
		int num = input.nextInt();
		//int num=89;
		int sum=0;
		int count=0;
		//int count=Integer.toString(num).length();
		int temp=num;
		
		while(temp>0)
		{
			count++;
			temp=temp/10;
		}
		temp=num;
		while(temp>0)
		{
			int digit=temp%10;
			sum+=Math.pow(digit,count);
			count--;
			temp=temp/10;
		}
		if(num==sum)
		{
			System.out.println("It is a Disarium number!");
		}
		else
		{
			System.out.println("It is not a Disarium number!");
		}
	}
}
