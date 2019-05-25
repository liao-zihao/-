import java.util.Scanner;

public class UseException {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int rate = input.nextInt();
		if(rate<0) throw new IllegalArgumentException("rate can't smaller than 0");
	}

}
