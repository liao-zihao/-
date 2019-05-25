import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream {
	private OutputStream out;
	private int buffer;
	private int bufferPos;
	
	public BitOutputStream(OutputStream os) {
		bufferPos=0;
		buffer=0;
		out=os;
	}
	
	public void writeBits(int [] val)throws IOException{
		int len=val.length;
		for(int i=0;i<len;i++) {
			writeBit(val[i]);
		}
	}

	public void writeBit(int val) throws IOException{
		buffer = setBit(buffer,bufferPos++,val);
		if(bufferPos==BitUtils.BITS_PER_BYTES)
			flush();
	}

	public void flush() throws IOException{
		if(bufferPos==0)
			return ;
		out.write(buffer);
		bufferPos=0;
		buffer=0;
	}

	private int setBit(int pack, int pos, int val) {
		if(val==1)
			pack|=(val<<pos);
		return pack;
	}
	
	public void close() throws IOException{
		flush();
		out.close();
	}
}
