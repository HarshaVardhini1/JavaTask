import java.util.Arrays;
import java.util.Scanner;

public class SelectionSort
{
	public static void main(String ar[])
	{
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the total number of elements in array : ");
		int length=input.nextInt();
		int arr[]=new int[length];
		System.out.println("Enter the numbers : ");	
		for(int i=0;i<length;i++)
		{
			arr[i]=input.nextInt();
		}
		//int arr[]={18,9,33,4,84,32};
		
		for(int i=0;i<arr.length-1;i++)
		{
			for(int j=i+1;j<arr.length;j++)
			{
				if(arr[j]<arr[i])
				{
					int temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
			}
			
		}
		
		System.out.print(Arrays.toString(arr));
	}
}
