import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Binary_to_Decimal {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input the binary string,input q to quit the program:");
		String s =br.readLine();
		while(s.charAt(0)!='q') {
			System.out.println("The decimal is \n"+toDecimal(s));
			
			System.out.println("\nInput the binary string,input q to quit the program:");
			s =br.readLine();
		}
	}
	
	public static double toDecimal(String s) {
		String[] ss = s.split("\\.");
		String pre = ss[0];
		String after="";
		if(ss.length>1) {
			after =ss[1];
		}

		double big=0,small=0;
		
		for(int i=0;i<pre.length();i++) {
			big=big*2+Double.parseDouble(""+pre.charAt(i));
		}
		
		for(int i=after.length()-1;i>=0;i--) {
			small=small/2+Double.parseDouble(""+after.charAt(i))/2;
		}
		return big+small;
	}
}
