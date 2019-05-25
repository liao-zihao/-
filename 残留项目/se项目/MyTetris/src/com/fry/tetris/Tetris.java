//����
package com.fry.tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * ����˹������Ϸ��� 
 *
 */


public class Tetris extends JPanel{
	
	/** �������䷽�� */
	private Tetromino tetromino;
	/** ��һ�����䷽�� */
	private Tetromino nextOne;
	/** ���� */
	public static final int ROWS = 20;
	/** ���� */
	public static final int COLS = 10;
	/** ǽ */
	private Cell[][] wall = new Cell[ROWS][COLS]; 
	/** ���������� */
	private int lines;
	/** ���� */
	private int score;
	
	public static final int CELL_SIZE = 26;
	
	private static Image background;//����ͼƬ
	public static Image I;
	public static Image J;
	public static Image L;
	public static Image S;
	public static Image Z;
	public static Image O;
	public static Image T;
	static{//���ؾ�̬��Դ�ģ�����ͼƬ
		//���齫ͼƬ�ŵ� Tetris.java ͬ����!
		//�Ӱ��м���ͼƬ����ʹ��Swing APIʵ��
//		Toolkit toolkit = Toolkit.getDefaultToolkit();
//		background = toolkit.getImage(
//				Tetris.class.getResource("tetris.png"));
//		T = toolkit.getImage(Tetris.class.getResource("T.png"));
//		S = toolkit.getImage(Tetris.class.getResource("S.png"));
//		Z = toolkit.getImage(Tetris.class.getResource("Z.png"));
//		L = toolkit.getImage(Tetris.class.getResource("L.png"));
//		J = toolkit.getImage(Tetris.class.getResource("J.png"));
//		I = toolkit.getImage(Tetris.class.getResource("I.png"));
//		O = toolkit.getImage(Tetris.class.getResource("O.png"));
		//import javax.imageio.ImageIO;
		try{
			background = ImageIO.read(
				Tetris.class.getResource("tetris.png"));
			T=ImageIO.read(Tetris.class.getResource("T.png"));
			I=ImageIO.read(Tetris.class.getResource("I.png"));
			S=ImageIO.read(Tetris.class.getResource("S.png"));
			Z=ImageIO.read(Tetris.class.getResource("Z.png"));
			L=ImageIO.read(Tetris.class.getResource("L.png"));
			J=ImageIO.read(Tetris.class.getResource("J.png"));
			O=ImageIO.read(Tetris.class.getResource("O.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void action(){
		//tetromino = Tetromino.randomTetromino();
		//nextOne = Tetromino.randomTetromino();
		//wall[19][2] = new Cell(19,2,Tetris.T);
		startAction();//��ʼ��Ϸ
		repaint();//���»���
		KeyAdapter l = new KeyAdapter() {//���ռ������룬�жϷ�Ӧ
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_Q){
					System.exit(0);//�˳���ǰ��Java����
				}
				if(gameOver){
					if(key==KeyEvent.VK_S){
						startAction();
					}
					return;
				}
				//�����ͣ���Ұ�����[C]�ͼ�������
				if(pause){//pause = false
					if(key==KeyEvent.VK_C){	continueAction();	}
					return;
				}
				//��������������
				switch(key){
				case KeyEvent.VK_RIGHT: moveRightAction(); break;
				case KeyEvent.VK_LEFT: moveLeftAction(); break;
				case KeyEvent.VK_DOWN: softDropAction() ; break;
				case KeyEvent.VK_UP: rotateRightAction() ; break;
				case KeyEvent.VK_Z: rotateLeftAction() ; break;
				case KeyEvent.VK_SPACE: hardDropAction() ; break;
				case KeyEvent.VK_P: pauseAction() ; break;
				}
				repaint();
			}
		};
		this.requestFocus();
		this.addKeyListener(l);
	}
	
	public void paint(Graphics g){
		g.drawImage(background, 0, 0, null);//ʹ��this ��Ϊ�۲���
		g.translate(15, 15);//ƽ�ƻ�ͼ����ϵ
		paintTetromino(g);//������������ķ���
		paintWall(g);//��ǽ
		paintNextOne(g);
		paintScore(g);
	}
	
	
	public static final int FONT_COLOR = 0x667799;
	public static final int FONT_SIZE = 0x20;
	private void paintScore(Graphics g) {
		Font f = getFont();//��ȡ��ǰ�� ���Ĭ������
		Font font = new Font(
				f.getName(), Font.BOLD, FONT_SIZE);
		int x = 290;
		int y = 162;
		g.setColor(new Color(FONT_COLOR));
		g.setFont(font);
		String str = "SCORE:"+this.score;
		g.drawString(str, x, y);
		y+=56;//Ӧ��������
		str = "LINES:"+this.lines;
		g.drawString(str, x, y);
		y+=56;
		str = "[P]Pause";
		if(pause){str = "[C]Continue";}
		if(gameOver){	str = "[S]Start!";}
		g.drawString(str, x, y);
	}

	private void paintNextOne(Graphics g) {
		Cell[] cells = nextOne.getCells();
		for(int i=0; i<cells.length; i++){
			Cell c = cells[i];
			int x = (c.getCol()+10) * CELL_SIZE-1;//����ʮ����λ
			int y = (c.getRow()+1) * CELL_SIZE-1;//Ŀ��������
			g.drawImage(c.getImage(), x, y, null);
		}	
	}
	
	private void paintTetromino(Graphics g) {
		Cell[] cells = tetromino.getCells();
		for(int i=0; i<cells.length; i++){
			Cell c = cells[i];
			int x = c.getCol() * CELL_SIZE-1;
			int y = c.getRow() * CELL_SIZE-1;
			//g.setColor(new Color(c.getColor()));
			//g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
			g.drawImage(c.getImage(), x, y, null);
		}		
	}
	
	
	//�� Tetris �� ����� ���� paintWall
		private void paintWall(Graphics g){
			for(int row=0; row<wall.length; row++){
				//����ÿһ��, i = 0 1 2 ... 19
				Cell[] line = wall[row];
				//line.length = 10
				for(int col=0; col<line.length; col++){
					Cell cell = line[col]; 
					int x = col*CELL_SIZE; 
					int y = row*CELL_SIZE;
					if(cell==null){
						//g.setColor(new Color(0));
						//������ 
						//g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
					}else{
						g.drawImage(cell.getImage(), x-1, y-1, null);
					}
				}
			}
		}
	
	
	
		/**
		 * �� Tetris(����˹����) �������ӷ���
		 * ��������Ĺ����ǣ�������Ķ��� ��������
		 * ��ɹ��ܣ�����ܹ���������䣬�������½��ǽ�ϣ�
		 *   ���µķ�����ֲ���ʼ���¡�
		 */
		public void softDropAction(){
			if(tetrominoCanDrop()){
				tetromino.softDrop();
			}else{
				tetrominoLandToWall();
				destroyLines();//�ƻ�������
				checkGameOver();
				tetromino = nextOne;
				nextOne = Tetromino.randomTetromino();
			}
		}
		
		/** �����Ѿ������У����ҼƷ�
		 * 1������ÿһ�� 
		 * 2���������飩ĳ�����Ǹ����� ���������� 
		 **/
		public void destroyLines(){
			int lines = 0;
			for(int row = 0; row<wall.length; row++){
				if(fullCells(row)){
					deleteRow(row);
					lines++;
				}
			}
			// lines = ?
			this.lines += lines;//0 1 2 3 4
			this.score += SCORE_TABLE[lines];
		}
		private static final int[] SCORE_TABLE={0,1,10,30,200};//�Ʒֹ���
		//   
		
		public boolean fullCells(int row){
			Cell[] line = wall[row];
			for(int i=0; i<line.length; i++){
				if(line[i]==null){//����пո�ʽ�Ͳ�������
					return false;
				}
			}
			return true;
		}
		
		public void deleteRow(int row){
			for(int i=row; i>=1; i--){
				//���� [i-1] -> [i] 
				System.arraycopy(wall[i-1], 0, wall[i], 0, COLS);
			}
			Arrays.fill(wall[0], null);
		}
		
		/** ��鵱ǰ��4�񷽿��ܷ�������� */
		public boolean tetrominoCanDrop(){
			Cell[] cells = tetromino.getCells();
			for(int i = 0; i<cells.length; i++){
				Cell cell = cells[i];
				int row = cell.getRow(); int col = cell.getCol();
				if(row == ROWS-1){return false;}//���׾Ͳ����½���
			}
			for(int i = 0; i<cells.length; i++){
				Cell cell = cells[i];
				int row = cell.getRow(); int col = cell.getCol();
				if(wall[row+1][col] != null){
					return false;//�·�ǽ���з���Ͳ����½���
				}
			}
			return true;
		}
		/** 4�񷽿���½��ǽ�� */
		public void tetrominoLandToWall(){
			Cell[] cells = tetromino.getCells();
			for(int i=0; i<cells.length; i++){
				Cell cell = cells[i];
				int row = cell.getRow();
				int col = cell.getCol();
				wall[row][col] = cell;
			}
		}
		
		public void moveRightAction(){
			tetromino.moveRight();
			if(outOfBound() || coincide()){
				tetromino.moveLeft();
			}
		}
		public void moveLeftAction(){
			tetromino.moveLeft();
			if(outOfBound() || coincide()){
				tetromino.moveRight();
			}
		}
	
		/** ... */
		private boolean outOfBound(){
			Cell[] cells = tetromino.getCells();
			for(int i=0; i<cells.length; i++){
				Cell cell = cells[i];
				int col = cell.getCol();
				if(col<0 || col>=COLS){
					return true;//������
				}
			}
			return false;
		}
		private boolean coincide(){
			Cell[] cells = tetromino.getCells();
			//for each ѭ��������������"���������д"
			for(Cell cell: cells){//Java 5 �Ժ��ṩ��ǿ��forѭ��
				int row = cell.getRow();
				int col = cell.getCol();
				if(row<0 || row>=ROWS || col<0 || col>=COLS || 
						wall[row][col]!=null){
					return true; //ǽ���и��Ӷ��󣬷����غ�
				}
			}
			return false;
		} 
		
		
		/** ������ת���� */
		public void rotateRightAction(){
			//��ת֮ǰ
			//System.out.println(tetromino);
			tetromino.rotateRight();
			//System.out.println(tetromino);
			//��ת֮��
			if(outOfBound() || coincide()){
				tetromino.rotateLeft();
			}
		}
		
		/** Tetris ������ӵķ��� */
		public void rotateLeftAction(){
			tetromino.rotateLeft();
			if(outOfBound() || coincide()){
				tetromino.rotateRight();
			}
		}
		public void hardDropAction(){
			while(tetrominoCanDrop()){
				tetromino.softDrop();
			}
			tetrominoLandToWall();
			destroyLines();
			checkGameOver();
			tetromino = nextOne;
			nextOne = Tetromino.randomTetromino();
		}
		
		private boolean pause;
		private boolean gameOver;
		private Timer timer;
		/** Tetris ������ӵķ���, ����������Ϸ */
		public void startAction(){
			clearWall();
			tetromino = Tetromino.randomTetromino();
			nextOne = Tetromino.randomTetromino();
			lines = 0; score = 0;	pause=false; gameOver=false;
			timer = new Timer();
			timer.schedule(new TimerTask(){
				public void run() {
					softDropAction();
					repaint();
				}
			}, 700, 700);
		}
		
		private void clearWall(){
			//��ǽ��ÿһ�е�ÿ����������Ϊnull
			for(int row=0; row<ROWS; row++){
				Arrays.fill(wall[row], null);
			}
		}
		
		/** ��Tetris ������ӷ���  */
		public void pauseAction(){
			timer.cancel(); //ֹͣ��ʱ��
			pause = true;
			repaint();
		}
		public void continueAction(){
			timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					softDropAction();
					repaint();
				}
			}, 700, 700);
			pause = false;
			repaint();
		}
		/** �� Tetris ������� ���� */
		public void checkGameOver(){
			if(wall[0][4]==null){
				return;
			}
			gameOver = true;
			timer.cancel();
			repaint();
		}
	

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Tetris tetris = new Tetris();
		frame.add(tetris);
		frame.setSize(525, 590);
		frame.setUndecorated(false);//trueȥ�����ڿ�
		frame.setTitle("����˹����");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Location λ�� RelativeTo����� 
		frame.setLocationRelativeTo(null);//ʹ��ǰ���ھ���
		frame.setVisible(true);
		tetris.action();
	}

}
