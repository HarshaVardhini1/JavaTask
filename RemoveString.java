import java.util.Arrays;
import java.util.Scanner;
public class RemoveString
{
	public static void main(String ar[])
	{
		Scanner input=new Scanner(System.in);
		
		System.out.println("Enter the sentance : ");
		String sentance = input.nextLine();
		
		System.out.println("Enter the word to be removed : ");
		String remove = input.next();
		
		String result="";
		
		for(int i=0;i<sentance.length();i++)
		{
			if(sentance.substring(i,i+remove.length()).equals(remove) && (i+remove.length()<=sentance.length()))
			{
				i=i+remove.length()-1;
			}
			else
			{
				result+=sentance.substring(i,i+1);
			}	
		}
		System.out.println(result);

		/*String sentance="Hello This is Sandhiya doss";
		String remove="is";
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
		}*/
	}
}

/*String result=sentance.replace("e","");
		System.out.println(result);*/
