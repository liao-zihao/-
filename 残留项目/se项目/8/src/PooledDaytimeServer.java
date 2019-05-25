import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PooledDaytimeServer {

	public final static int PORT=13;
	
	public static void main(String[] args) {
		
		ExecutorService pool=Executors.newFixedThreadPool(50);
		
		try (ServerSocket server=new ServerSocket(PORT)){
			while(true) {
				try {
					Socket connection=server.accept();
					Callable<Void> task = new DaytimeTask(connection);
					pool.submit(task);
				}catch(IOException ex) {}
			}
		}catch(IOException ex) {
			System.err.println(ex);
		}
	}
	
	private static class DaytimeTask implements Callable<Void>{
		private Socket connection;
		
		DaytimeTask(Socket connection){
			this.connection=connection;
		}
		
		public Void call() {
			try {
				Writer out=new OutputStreamWriter(connection.getOutputStream());
				Date now = new Date();
				out.write(now.toString()+"\t\n");
				out.flush();
			}catch(IOException ex) {
				System.err.println(ex);
			}finally {
				try {
					connection.close();
				}catch(IOException ex) {}
			}
			return null;
		}
	}

}
