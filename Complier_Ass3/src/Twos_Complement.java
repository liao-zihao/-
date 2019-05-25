import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Twos_Complement {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input the binary string,input q to quit the program:");
		String s =br.readLine();
		while(s.charAt(0)!='q') {
			System.out.println("The two's complement is \n"+toTwos(s));
			
			System.out.println("\nInput the binary string,input q to quit the program:");
			s =br.readLine();
		}
	}
	
	public static String toTwos(String s) {
		String tows="";
		String carry="0";
		if(s.charAt(s.length()-1)=='0') carry="1";
		if(s.charAt(s.length()-1)=='0') {
			tows="0";
		}else {
			tows="1";
		}
		
		for(int i=s.length()-2;i>=0;i--) {
			if(s.charAt(i)=='0') {
				if(carry=="1") {
					tows="0"+tows;
					carry="1";
				}else {
					tows="1"+tows;
					carry="0";
				}
			}else {
				if(carry=="1") {
					tows="1"+tows;
					carry="0";
				}else {
					tows="0"+tows;
					carry="0";
				}
			}
		}
		
		return tows;
	}
	
}
