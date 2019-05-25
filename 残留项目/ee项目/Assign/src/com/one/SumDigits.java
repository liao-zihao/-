package com.one;

import java.util.Scanner;

public class SumDigits {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		long num = scan.nextLong();
		System.out.println(num);
	}
	public static int sumDigits(long n) {
		int ans=0;
		while(n!=0) {
			ans = (int) (ans+n%10);
			n=n/10;
		}
		return ans;
	}
}
