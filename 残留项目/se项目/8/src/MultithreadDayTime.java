import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MultithreadDayTime {
	public final static int PORT =13;
	
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(PORT)){
			while(true) {
				try {
					Socket connection = server.accept();
					Thread task = new DayTimeThread(connection);
					task.start();
				}catch(IOException ex) {}
			}
		}catch(IOException ex) {
			System.err.println(ex);
		}
	}
	
	public static class DayTimeThread extends Thread{
		private Socket connection;
		
		DayTimeThread(Socket connection){
			this.connection=connection;
		}
	}
	
	public void run(){
		try {
			Writer out= new OutputStreamWriter(connection.getOutPutStream());
			Date now=new Date();
			out.flush();
		}catch(IOException ex) {
			System.err.println(ex);
		}finally {
			try {
				connection.close();
			}catch (IOException ex) {}
		}
	}
}
