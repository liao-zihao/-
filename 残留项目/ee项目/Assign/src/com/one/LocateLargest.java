package com.one;

import java.util.Scanner;

public class LocateLargest {

	public static void main(String[] args) {
		System.out.println("Enter the number of rows and columns of the array :");
		Scanner scan = new Scanner(System.in);
		int row = scan.nextInt();
		int col = scan.nextInt();
		
		double[][] a = new double[row][col];
		System.out.println("Enter the array :");
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				a[i][j]=scan.nextDouble();
			}
		}
		
		System.out.println("The location of the largest element is at ("+locateLargest(a)[0]+","+locateLargest(a)[1]+")");	
	}
	public static int[] locateLargest(double[][] a) {
		int [] ans = new int [2];
		ans[0] = 0;
		ans[1] = 0;
		double largest = 0;
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[0].length;j++) {
				if(a[i][j]>largest) {
					largest = a[i][j];
					ans[0]=i;
					ans[1]=j;
				}
			}
		}
		
		return ans;
	}
}
