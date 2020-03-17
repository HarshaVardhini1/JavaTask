import java.util.Scanner;
public class StrongNumber
{
	public static void main(String ar[])
	{
		Scanner input=new Scanner(System.in);
		System.out.println("Enter a number : ");
		int num=input.nextInt();
		//int num=145;
		int temp=num;
		int sum=0;
		
		while(temp>0)
		{
			int digit=temp%10;
			temp=temp/10;
			int factorial=1;
			for(int i=1;i<=digit;i++)
			{
				factorial=factorial*i;
			}
			sum+=factorial;
		}
		if(sum==num)
		{
			System.out.println("It is a Strong number!");
		}
		else
		{
			System.out.println("It is not a Strong number!");
		}
	}
}
