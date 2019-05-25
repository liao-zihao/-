import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
//历史遗留问题
public class PortScanner {

	public static void main(String[] args) throws UnknownHostException {
		String host="localhost";
		InetAddress add=InetAddress.getByName("127.0.0.1"); 
		for(int i=1;i<=1024;i++) {
			try {
				Socket s = new Socket();
				s.connect(new InetSocketAddress("localhost",i));
				System.out.println("Port on "+i);
				s.close();
			}catch(UnknownHostException ex) {
				System.err.println(ex);
				break;
			}catch(IOException ex) {
				System.err.println(ex);
				//break;
			}
		}
	}

}
