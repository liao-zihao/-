package assign2;

import java.util.Scanner;

public class FindMaxFee {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of array :");
		int arrNum=input.nextInt();
		
		int b[]= new int [arrNum];
		int a[]= new int [arrNum];
		
		System.out.print("b :");
		for(int i=0;i<arrNum;i++) b[i]=input.nextInt();
		System.out.print("a :");
		for(int i=0;i<arrNum;i++) a[i]=input.nextInt();
		
		int T[]= new int [arrNum+1];
		int choose[] = new int [arrNum];
		T[0]=0;
		T[1]=Math.max(a[0], b[0]);
		for(int i=2;i<arrNum+1;i++) {
			T[i]=Math.max(T[i-1]+b[i-1], T[i-2]+a[i-1]);
		}
		int i;
		for(i=arrNum;i>1;) {
			if(T[i]==T[i-1]+b[i-1]) {
				choose[i-1]=0;
				i--;
			}
			else {
				choose[i-1]=1;
				choose[i-2]=-1;
				i-=2;
			}
		}
		if(i==1) {
			choose[0]=1;
		}
		System.out.println("Maximum fee is "+T[arrNum]);
		System.out.println("choose is :");
		for(int j=0;j<arrNum;j++) {
			switch(choose[j]) {
			case -1:System.out.print("none ");
					break;
			case 0 :System.out.print("base ");
					break;
			case 1 :System.out.print("advanced ");
					break;
			default :break;
			}
		}
	}

}
