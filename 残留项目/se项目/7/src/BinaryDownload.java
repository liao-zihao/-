import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
//不得不说，很慢
public class BinaryDownload {
	public static void main(String[] args) {
		String[] arg= {""};
		for(int i=0;i<arg.length;i++) {
			try {
				URL u=new URL(arg[i]);
				savaFile(u);
			}catch(MalformedURLException ex) {
				System.err.println("Not a url");
			}catch(IOException ex) {
				System.err.println(ex);
			}
		}
	}
	public static void savaFile(URL u)throws IOException{
		URLConnection uc = u.openConnection();
		String contentType=uc.getContentType();
		int contentLength = uc.getContentLength();
		try(InputStream raw=uc.getInputStream()) {
			InputStream in = new BufferedInputStream(raw);
			byte[] data=new byte[contentLength];
			int offset=0;
			int per=0;
			while(offset<contentLength) {
				int bytes=in.read(data);
				if(bytes==-1) break;
				offset+=bytes;
				if(offset*100/contentLength>per) {
					System.out.println("Now is "+offset*100/contentLength+"%");
					per=offset*100/contentLength;
				}
			}
			
			if(offset!=contentLength) {
				throw new IOException("Expected more bytes");
			}
			
			String filename=u.getFile();
			filename=filename.substring(filename.lastIndexOf('/')+1);
			try(FileOutputStream o = new FileOutputStream(filename)){
				o.write(data);
				o.flush();
			}
		}
	}
}
