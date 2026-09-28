package files_exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/*Aiden Murphy/Colton McWilliams
 * When trying run the program without having a numbers.txt file with each of the different functions, none of them run.
 *
 * The first function throws a specific error where it states it cannot invoke 'java.util.Scanner.hasNext()" because "in"
 * is null. The other two simply say that numbers.txt cannot be found.
 * 
 * While numbers.txt if full of numbers, 1 and 2 run without issue, along with 3 properly
 * outputting the correct sum. Wile the file is empty, 3 outputs a sum of 0 while the other
 * functions do not output anything. 
 * 
 * When numbers.txt has lines that are not numeric, function one throws a complete error stating
 * there is in exception in thread "main" for the input string in the file. function 2 prints out
 * its catch and states there is a non numeric value and completely kills the function, meaning if there
 * were numbers after the function they would not count those up for the sum. With function 3, it reads
 * through the whole file and counts every instance of a non-numeric value while also still counting the sum
 * of the numeric values in the file. 
 * */

public class FileTotal {
	private int sum;
	private String inFile;
	public FileTotal(String inFile) {
		this.inFile = inFile;
	}
	public void sumFileValues1() {
		FileReader reader = null;
		Scanner in = null;
		String strNum = null;
		try {
			reader = new FileReader(inFile);
			
			// This line needs to be in try, don't want to make
			// a new Scanner with a null reader!
			in = new Scanner(reader);
			
			while (in.hasNext()) {
				strNum = in.next();
				int num = Integer.parseInt(strNum);
				sum += num;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void sumFileValues2() {
		String strNum = null;
		try {
			FileReader reader = new FileReader(inFile);
			Scanner in = new Scanner(reader);
			while (in.hasNext()) {
				strNum = in.next();
				int num = Integer.parseInt(strNum);
				sum += num;
			}
			in.close();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		catch (NumberFormatException e) {
			System.out.println("Error, non-numeric value " + strNum);
		}
	}	
	
	public void sumFileValues3() {
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader(inFile);
			in = new Scanner(reader);		
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}
		String strNum = null;
		while (in.hasNext()) {
			try {
				strNum = in.next();
				int num = Integer.parseInt(strNum);
				sum += num;
			} catch (NumberFormatException e) {
				System.out.println("Error, non-numeric value " + strNum);
			}
		}
		System.out.println(sum);
		in.close();
	}
	
	
	public static void main(String[] args) {
		FileTotal ft = new FileTotal("numbers.txt");
		ft.sumFileValues2();
	}

}
