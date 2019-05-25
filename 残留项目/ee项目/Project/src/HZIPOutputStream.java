import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class HZIPOutputStream extends OutputStream{
	private ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
	private DataOutputStream dout;
	
	public HZIPOutputStream(OutputStream out)throws IOException{
		dout = new DataOutputStream(out);
	}
	
	public void write(int ch)throws IOException{
		byteOut.write(ch);
	}
	
	public void close() throws IOException{
		byte[] theInput = byteOut.toByteArray();
		ByteArrayInputStream byteIn = new ByteArrayInputStream(theInput);
		
		CharCounter countObj = new CharCounter(byteIn);
		byteIn.close();
		
		HuffmanTree codeTree = new HuffmanTree(countObj);
		codeTree.writeEncodingTable(dout);
		
		BitOutputStream bout = new BitOutputStream(dout);
		
		int len = theInput.length;
		for(int i=0;i<len;i++)
			bout.writeBits(codeTree.getCode(theInput[i]&0xff));
		bout.writeBits(codeTree.getCode(BitUtils.EOF));
		
		bout.close();
		byteOut.close();
	}
}
