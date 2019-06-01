package li;
import java.util.Scanner;

public class li
{
	public static void main(String[] args)
	{
		String[][] people={{"L","12"},{"W","34"},{"H","56"}};
		String str;
		int i,j;
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("¿é¤JL,W,H¬d¸ß");
		str=scanner.nextLine();
		
		for(i=0;i<people.length;i++)
		{
			if(str.equals(people[i][0]))
			{
				System.out.println(people[i][1]);
			}
		}
	}
}