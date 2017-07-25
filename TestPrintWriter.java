

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;


public class TestPrintWriter
{

	public static String fileName = "myBankData.txt";
	public static void getStatus() throws FileNotFoundException
	{
		File file = new File( fileName );

		System.out.println("File exists: " + file.exists());
		System.out.println("File isFile: " + file.isFile());
		if (file.exists())
		{
			Date date = new Date (file.lastModified() );
			System.out.println("last modified: "+ date);
			System.out.println("length: " + file.length() );
		}

	}
	public static void readFile() throws FileNotFoundException
	{
		File file = new File( fileName );

		if (file.exists() && file.isFile())
		{
			Scanner file_input = new Scanner (file);
			while (file_input.hasNext())
			{
				String str = file_input.nextLine();
				System.out.println("String: " + str);

				//create a Scanner instance to parse the String
				Scanner temp = new Scanner (str);
				String name = temp.next();  			//first
				name = name + " " + temp.next();		//last name
				int cnt = temp.nextInt();				//count
				double balance = temp.nextDouble();	//balance
				long dateCreated = temp.nextLong();
				Date date = new Date(dateCreated);
				System.out.printf("%s, %4d, %8.2f, %s\n", name, cnt, balance,  date);
			}
			file_input.close();
		}
		else 
			System.out.println("Error: exists=" + file.exists() + ", isFile: " + file.isFile());
		
	}
	public static void saveFile(Date dCreated)throws FileNotFoundException
	{
		File file = new File(fileName);
		PrintWriter outFile = new PrintWriter ( file );

		long dateCreated = dCreated.getTime();
		System.out.println("date Created: " + dCreated);
		double balance = 1234.56;
		String name = "Fred Johnson";
		int count = 5;
		outFile.printf("%-30s  %8d %8.2f %8d\r\n", name, count, balance, dateCreated);

		outFile.close();
	}

	public static void main(String[] args) throws Exception
	{ 
		Scanner input = new Scanner(System.in);
		Date dateCreated = new Date();
		System.out.println("current time is: " + dateCreated);
		while (true)
		{
			System.out.println("Test File I/O");
			System.out.println("1..Read file");
			System.out.println("2..Save file");
			System.out.println("3..Get status of file");
			System.out.println("4..Exit");

			int choice = input.nextInt();

			switch (choice) {
			case 1:  readFile();
			break;
			case 2:  saveFile(dateCreated);
			break;
			case 3: getStatus();
			break;
			case 4:  System.exit(0);
			default:
				System.out.println("invalid choice");
			}
		}
	}
}


