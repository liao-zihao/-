package com.lzh.game;

import java.util.Random;

public class Tetromino {
	protected Cell[] cells = new Cell[4];
	
	protected State[] states;
	
	public static Tetromino randomTetromino() {
		Random r = new Random();
		int type = r.nextInt(7);
		switch(type) {
		case 0:return new T();
		case 1:return new I();
		case 2:return new J();
		case 3:return new L();
		case 4:return new O();
		case 5:return new S();
		case 6:return new Z();
		}
		return null;
	}
	
	public Cell[] getCells() {
		return cells;
	}
	
	public void softDrop() {
		for(int i=0;i<cells.length;i++) {
			cells[i].moveDown();
		}
	}
	
	public void moveRight() {
		for(int i=0;i<cells.length;i++) {
			this.cells[i].moveRight();
		}
	}
	
	public void moveLeft() {
		for(int i=0;i<cells.length;i++) {
			cells[i].moveLeft();
		}
	}
	
	private int index=100;
	
	public void rotateRight() {
		index++;
		State s = states[index%states.length];
		Cell o = cells[0];
		cells[1].setRow(o.getRow()+s.row1);//每次都取原点
		cells[1].setCol(o.getCol()+s.col1);
		cells[2].setRow(o.getRow()+s.row2);
		cells[2].setCol(o.getCol()+s.col2);
		cells[3].setRow(o.getRow()+s.row3);
		cells[3].setCol(o.getCol()+s.col3);
	}
	
	public void rotateLeft() {
		index--;//特定次序旋转
		State s = states[index%states.length];
		Cell o = cells[0];//以第一个点为定点
		cells[1].setRow(o.getRow()+s.row1);
		cells[1].setCol(o.getCol()+s.col1);
		cells[2].setRow(o.getRow()+s.row2);
		cells[2].setCol(o.getCol()+s.col2);
		cells[3].setRow(o.getRow()+s.row3);
		cells[3].setCol(o.getCol()+s.col3);
	}
	
	protected class State{//旋转状态
		int row0,col0,row1,col1,row2,col2,row3,col3;

		public State(int row0, int col0, int row1, int col1, int row2, int col2, int row3, int col3) {
			super();
			this.row0 = row0;
			this.col0 = col0;
			this.row1 = row1;
			this.col1 = col1;
			this.row2 = row2;
			this.col2 = col2;
			this.row3 = row3;
			this.col3 = col3;
		}
		
	}
}
class T extends Tetromino{
	public T() {
		cells[0]= new Cell(0,4,Tetris.T);
		cells[1]= new Cell(0,3,Tetris.T);
		cells[2]= new Cell(0,5,Tetris.T);
		cells[3]= new Cell(1,4,Tetris.T);
		states = new State[] {
				new State(0,0,0,-1,0,1,1,0),
				new State(0,0,-1,0,1,0,0,-1),
				new State(0,0,0,1,0,-1,-1,0),
				new State(0,0,1,0,-1,0,0,1)
		};
	}
}
class I extends Tetromino{
	public I() {
		cells[0] = new Cell(0, 4, Tetris.I);
		cells[1] = new Cell(0, 3, Tetris.I);
		cells[2] = new Cell(0, 5, Tetris.I);
		cells[3] = new Cell(0, 6, Tetris.I);
		states = new State[]{
				new State(0,0, 0,1, 0,-1, 0,-2),
				new State(0,0, -1,0, 1,0,2,0)};
	}
}
class L extends Tetromino {
	public L() {
		cells[0] = new Cell(0, 4, Tetris.L);
		cells[1] = new Cell(0, 3, Tetris.L);
		cells[2] = new Cell(0, 5, Tetris.L);
		cells[3] = new Cell(1, 3, Tetris.L);
		states = new State[]{
				new State(0,0, 0,-1, 0,1, 1,-1 ),
				new State(0,0, -1,0, 1,0, -1,-1),
				new State(0,0, 0,1, 0,-1, -1,1),
				new State(0,0, 1,0, -1,0, 1,1)};	
	}
}

class J extends Tetromino {
	public J() {
		cells[0] = new Cell(0, 4, Tetris.J);
		cells[1] = new Cell(0, 3, Tetris.J);
		cells[2] = new Cell(0, 5, Tetris.J);
		cells[3] = new Cell(1, 5, Tetris.J);
		states = new State[]{
				new State(0,0, 0,-1, 0,1, 1,1),
				new State(0,0, -1,0, 1,0, 1,-1),
				new State(0,0, 0,1, 0,-1, -1,-1),
				new State(0,0, 1,0, -1,0, -1,1 )};
	}
}

class S extends Tetromino {
	public S() {
		cells[0] = new Cell(0, 4, Tetris.S);
		cells[1] = new Cell(0, 5, Tetris.S);
		cells[2] = new Cell(1, 3, Tetris.S);
		cells[3] = new Cell(1, 4, Tetris.S);
		states = new State[]{
			new State(0,0, 0,1, 1,-1, 1,0 ),
			new State(0,0, -1,0, 1,1, 0,1 )};
	}
}

class Z extends Tetromino {
	public Z() {
		cells[0] = new Cell(1, 4, Tetris.Z);
		cells[1] = new Cell(0, 3, Tetris.Z);
		cells[2] = new Cell(0, 4, Tetris.Z);
		cells[3] = new Cell(1, 5, Tetris.Z);
		states = new State[]{
				new State(0,0, -1,-1, -1,0, 0,1 ),
				new State(0,0, -1,1, 0,1, 1,0 )};
	}
}

class O extends Tetromino {
	public O() {
		cells[0] = new Cell(0, 4, Tetris.O);
		cells[1] = new Cell(0, 5, Tetris.O);
		cells[2] = new Cell(1, 4, Tetris.O);
		cells[3] = new Cell(1, 5, Tetris.O);
		states = new State[]{
				new State(0,0, 0,1, 1,0, 1,1 ),
				new State(0,0, 0,1, 1,0, 1,1 )};
	}
}