
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RemoveWord {

	public static void main(String[] args) {
		if(args.length!=2) {
			System.out.println("Invalid arguments");
			for(String s:args) System.out.println(s);
			System.exit(1);
		}
		File filename=new File(args[1]);
		if(!filename.exists()) {
			System.out.println("file does not exist");
		}
		
		String s="";
		StringBuilder sb = new StringBuilder();
		try {
			Scanner input = new Scanner(filename);
			while(input.hasNext()) {
				s=input.nextLine().replaceAll(args[0], "");
				sb.append(s);
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter output = new PrintWriter(filename);
			System.out.println(sb);
			s=sb.toString();
			output.write(s);
			output.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("complete");
	}
	

}
