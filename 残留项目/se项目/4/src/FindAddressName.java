import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class FindAddressName {
	public static void main(String[] args) {
		try {
//			InetAddress add=InetAddress.getByName("www.baidu.com");
//			System.out.println(add);
//			add=Inet4Address.getByName("45.113.192.101");
//			System.out.println(add.getHostName());
//			
//			InetAddress[] addr=InetAddress.getAllByName("www.baidu.com");
//			for(InetAddress ad:addr) {//找所有的ip
//				System.out.println(ad);
//			}
			
			InetAddress me=InetAddress.getLocalHost();
			System.out.println(me.getAddress());
		}catch(UnknownHostException ex) {
			System.out.println("Can't find.");
		}
	}
}
