import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AllHeaders {
	public static void main(String[] args) {
		String url="http://www.taobao.com";
		try {
			URL u = new URL(url);
			URLConnection uc=u.openConnection();
			for(int j=1;;j++) {
				String header=uc.getHeaderField(j);
				if(header==null) break;
				System.out.println(uc.getHeaderFieldKey(j)+": "+header);
			}
			System.out.println(3&17);
			
		}catch(MalformedURLException EX) {
			System.err.println("Not a url");
		}catch(IOException ex) {
			System.err.println(ex);
		}
	}
}
