

package assignment2_q1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
public class Assignment2_Q1 {
    public static void validate(String s) {
        String pattern1="^[0-9]";
        String pattern2="^[A-Za-z][A-Za-z0-9_-]*+";
	    String pattern3="for\\(.*;.*;.*\\)";
	    Pattern a=Pattern.compile(pattern1); 
	    Pattern b=Pattern.compile(pattern2); 
        Pattern c=Pattern.compile(pattern3);
        String[] String=s.split("[ \\n]");
        for(int i=0;i<String.length;i++) {
        System.out.println(String[i]);
		Matcher matcher=c.matcher(String[i]);
		if(matcher.find()){  
		System.out.println("<"+matcher.group()+">"+" is for loop");  
                continue;
	        }
		matcher = b.matcher(String[i]);  
		if(matcher.find()){  
		System.out.println("<"+matcher.group()+">"+" is identifier");       
		}
        matcher = a.matcher(String[i]);  
		if(matcher.find()){  
		System.out.println("<"+matcher.group()+">"+" is variable"); 
		} 
		
                }
}
public static void main(String[] args){
	String filepath="C:\\Users\\LZH\\Desktop\\abc.txt";
	try {
	        FileReader reader = new FileReader(filepath);
		BufferedReader br = new BufferedReader(reader);
		String String=null;
		while((String=br.readLine())!=null) 
                {
		    validate(String);
		}
		} 
        catch (IOException e) 
        {
		e.printStackTrace();
	}
	}
}
