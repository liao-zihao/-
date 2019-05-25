import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Validate {
	public static void main(String[] args) {
		String path="D://code";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String temp="";
			while((temp=br.readLine())!=null) detect(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	public static void detect(String s) {
		String id_regex = "^[A-Za-z][A-Za-z1-9_-]*+";
		String for_loop_regex="for(.*:.*:.*)";
		Pattern pattern1 = Pattern.compile(id_regex); 
		Pattern pattern2 = Pattern.compile(for_loop_regex); 
		String[] ss=s.split("[ \\+\\-\\*\\/\\=]");
		for(int i=0;i<ss.length;i++) {
			Matcher matcher=pattern2.matcher(ss[i]);
			if(matcher.find()){  
		        System.out.println("<"+matcher.group()+">"+" is a for loop");  
		        continue;
			}
			matcher = pattern1.matcher(ss[i]);  
			if(matcher.find()){  
			        System.out.println("<"+matcher.group()+">"+" is a variable");   
			}
		}
	}
}
