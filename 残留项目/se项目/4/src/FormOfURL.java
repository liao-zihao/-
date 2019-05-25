import java.net.MalformedURLException;
import java.net.URL;

public class FormOfURL {
	public static void main(String[] args) {
		String[] arg = {"http://www.baidu.com","http://www.baidu.com/index.html"};
		for(int i=0;i<arg.length;i++) {
			try {
				URL u=new URL(arg[i]);
				System.out.println("The url is "+u);
				System.out.println("The scheme is "+u.getProtocol());
				System.out.println("The user info is "+u.getUserInfo());
				
				String host=u.getHost();
				if(host!=null) {
					int atSign=host.indexOf('@');
					if(atSign!=-1) host=host.substring(atSign+1);
					System.out.println("The host is "+host);
				}else {
					System.out.println("The host is null");
				}
				
				System.out.println("The port is "+u.getPort());
				System.out.println("The path is "+u.getPath());
				System.out.println("The ref is "+u.getRef());
				System.out.println("The query is "+u.getQuery());
			}catch(MalformedURLException ex) {
				System.out.println(arg[i]+" is not a url.");
			}
			System.out.println();
		}
	}
}
