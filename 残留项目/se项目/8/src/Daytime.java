import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Daytime {

	public static void main(String[] args) {
		Daytime t = new Daytime();
		try {
			System.out.println(0X28&0X0F);
			System.out.println(t.getDateFromNetwork());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Date getDateFromNetwork() throws IOException,ParseException{
		try (Socket socket = new Socket("time.nist.gov",37)){//字符时间用13端口
			socket.setSoTimeout(10000);
			InputStream in = socket.getInputStream();
			
			long diff=2208988800L;//转换东部时间
			long seconds1900=0;
			for(int i=0;i<4;i++) {
				seconds1900=(seconds1900<<8)|in.read();
			}
			long seconds1970=seconds1900-diff;
			long mills1970=seconds1970*1000;
			
			Date time = new Date(mills1970);
			return time;
			/*StringBuilder time = new StringBuilder();
			InputStreamReader reader=new InputStreamReader(in,"ASCII");
			for(int c=reader.read();c!=-1;c=reader.read()) {
				time.append((char)c);
			}
			System.out.println(time.toString());
			return parseDate(time.toString());*/
		}
	}
	
	static Date parseDate(String s)throws ParseException{
		String[]pieces=s.split(" ");
		String datetime=pieces[1]+" "+pieces[2]+" UTC";
		SimpleDateFormat format=new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
		return format.parse(datetime);
	}

}
