package com.fry.tetris;

import java.awt.Image;

/**
 * ����
 * ÿһ��С���ӣ��������ڵ��� �� ��ͼƬ 
 */
public class Cell {
	private int row;
	private int col;
	//private int color;
	private Image image;//���ӵ���ͼ
	
	public Cell() {
	}

	public Cell(int row, int col, Image image) {
		super();
		this.row = row;
		this.col = col;
		this.image = image;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void moveRight(){
		col++;
		//System.out.println("Cell moveRight()" + col); 
	}
	
	public void moveLeft(){
		col--;
	}
	
	public void moveDown(){
		row++;
	}
	
	@Override
	public String toString() {
		return "["+row+","+col+"]";
	}
}






