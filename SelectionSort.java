import java.util.Arrays;
public class SelectionSort
{
	public static void main(String ar[])
	{
		int arr[]={18,9,33,4,84,32};
		
		for(int i=0;i<arr.length-1;i++)
		{
			int index=i;
			for(int j=i+1;j<arr.length;j++)
			{
				if(arr[j]<arr[index])
				{
					index=j;
				}
			}
			int temp=arr[index];
			arr[index]=arr[i];
			arr[i]=temp;
		}
		
		System.out.print(Arrays.toString(arr));
	}
}
