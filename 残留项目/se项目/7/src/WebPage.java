import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebPage {
	public static void main(String[] args) throws IOException {
		String[] arg= {"http://www.baidu.com"};
		for(int i=0;i<arg.length;i++) {
			try {
				String encoding="utf-8";
				URL u=new URL(arg[i]);
				HttpURLConnection uc=(HttpURLConnection)u.openConnection();
				int code=uc.getResponseCode();
				String response=uc.getResponseMessage();
				
				for(int j=1;;j++) {
					String header=uc.getHeaderField(j);
					String key=uc.getHeaderFieldKey(j);
					if(header==null||key==null)break;
					System.out.println(uc.getHeaderFieldKey(j)+": "+header);
				}
				System.out.println();
				
				System.out.println("HTTP/1.x"+code+" "+response);
				String contentType=uc.getContentType();
				int encodingstart=contentType.indexOf("charset=");
				if(encodingstart!=-1) {
					encoding=contentType.substring(encodingstart+8);
				}
				InputStream in=new BufferedInputStream(uc.getInputStream());
				Reader r=new InputStreamReader(in, encoding);
				int c;
				while((c=r.read())!=-1) {
					System.out.print((char)c);
				}
				r.close();
			}catch(MalformedURLException ex) {
				System.out.println("Not parseable.");
			}catch(UnsupportedEncodingException  ex) {
				System.err.println("Encoding not support."+ex.getMessage());
			}catch(IOException ex) {
				System.err.println(ex);
			}
		}
	}
}
