import java.io.IOException;
import java.io.InputStream;


public class CharCounter {
	private int theCounts[] = new int[BitUtils.DIEF_BYTES];
	public CharCounter() {
	}
	
	public CharCounter(InputStream input) throws IOException{
		int ch;
		while((ch=input.read())!=-1) {
			theCounts[ch]++;
		}
	}
	
	public int getCount(int ch) {
		return theCounts[ch&0xff];//得到低八位
	}
	
	public void setCount(int ch,int count) {
		theCounts[ch&0xff]= count;
	}
}
