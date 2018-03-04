import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
	private int[][] pizzaMap;

	public static void main(String args[])
	{
		String[] arguments = new String[4];
//		arguments[0] = "example.out";
		arguments[1] = "small.out";
		arguments[2] = "medium.out";
		arguments[3] = "big.out";

		new Main(arguments);
	}

	private Main(String[] args)
	{
		int totalScore = 0;
		for (int i = 0; i < args.length; i++)
		{
			if (args[i] != null)
			{
				totalScore += checkFile(args[i]);
			}
		}
		System.out.println("Total Score: " + totalScore);
	}

	private double checkFile(String fileLoc)
	{
		double score = 0;
		System.out.println("Processing file: " + fileLoc);
		switch (fileLoc)
		{
			case "big.out":
				pizzaMap = new int[1000][1000];
				score = readFile(fileLoc, 14);
				break;
			case "medium.out":
				pizzaMap = new int[200][250];
				score = readFile(fileLoc, 12);
				break;
			case "small.out":
				pizzaMap = new int[6][7];
				score = readFile(fileLoc, 5);
				break;
			case "example.out":
				pizzaMap = new int[3][5];
				score = readFile(fileLoc, 6);
				break;
			default:
				System.out.println(fileLoc + ":\tThis file was not recognised and won't be counted");
				break;
		}
		return score;
	}

	private double readFile(String file, int maxSliceSize)
	{
		boolean flag = false;
		double score = 0;
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(new File(file));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		if(scanner != null)
		{
			for (int j = 0; j < pizzaMap.length; j++)
			{
				for (int k = 0; k < pizzaMap[j].length; k++)
				{
					pizzaMap[j][k] = 0;
				}
			}

			int lines = Integer.parseInt(scanner.nextLine());
			for (int i = 1; i <= lines; i++)
			{
				String[] data = scanner.nextLine().split(" ");
				int[] locations = new int[4];
				for (int j = 0; j < 4; j++)
					locations[j] = Integer.parseInt(data[j]);

				for (int a = locations[0]; a <= locations[2]; a++)
				{
					for (int b = locations[1]; b <= locations[3]; b++)
					{
						if(pizzaMap[a][b] == 0)
						{
							pizzaMap[a][b] = 1;
						}
						else
						{
							System.out.println("Collision detected at: " + b + ", " + a);
							flag = true;
							break;
						}
					}
				}
				if(!flag)
					score += Math.abs(locations[2] - locations[0] + 1) * Math.abs(locations[3] - locations[1] + 1);
				flag = false;
			}
		}

		return score;
	}

}
