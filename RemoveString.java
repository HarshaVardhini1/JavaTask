import java.util.Arrays;
public class RemoveString
{
	public static void main(String ar[])
	{
		String sentance="Hello This is Hello";
		String remove="He";
		String result="";
		String resultArr[];

		if(sentance.contains(remove))
		{
			resultArr=sentance.split(remove);
			for(int i=0;i<resultArr.length;i++)
			{
				result+=resultArr[i];
			}
			System.out.println(result);
		}
	}
}

/*String result=sentance.replace("e","");
		System.out.println(result);*/
