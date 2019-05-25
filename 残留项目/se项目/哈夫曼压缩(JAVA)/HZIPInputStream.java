import java.io.DataInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
  
public class HZIPInputStream extends InputStream{  
    private BitInputStream bin;//位输入流  
    private HuffmanTree codeTree;//编码的HuffmanTree对象  
    /** 
     * 封装InputStream对象,实例化HuffmanTree对象与BitInputStream对象,并读入哈弗曼编码 
     * @param in 
     * @throws IOException 
     */  
    public HZIPInputStream(InputStream in) throws IOException{  
        //数据输入流  
        DataInputStream din=new DataInputStream(in);  
          
        codeTree=new HuffmanTree();  
        codeTree.readEncodingTable(din);  
          
        bin=new BitInputStream(in);  
    }  
    /** 
     * 读取文件的方法 
     */  
    public int read() throws IOException{  
        String bits="";//哈弗曼编码的字符串  
        int bit;//位  
        int decode;//解码后的字符  
        while(true){  
            bit=bin.readBit();  
            if(bit == -1)  
                throw new IOException("Unexpected EOF");//意外的资源结束  
              
            bits+=bit;  
            //System.out.println("bits = "+bits);
            decode=codeTree.getChar(bits);//获取编码对应的字符  
            if(decode==HuffmanTree.INCOMPLETE_CODE)//向下搜索到叶子结点  
                continue;  
            else if(decode==HuffmanTree.ERROR)//编码出错  
                throw new IOException("Unexpected error");  
            else if(decode==HuffmanTree.END)//编码溢出  
                return -1;  
            else   
                return decode;  
        }  
    }  
    /** 
     * 关闭输入流 
     */  
    public void close() throws IOException{  
        bin.close();  
    }  
}