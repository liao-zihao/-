 
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.DataInputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
  
public class HZIP {  
    /** 
     * 压缩文件的方法，此方法需要传入正确的绝对路径名 
     * @param inFile 需要被压缩的文件 
     * @param outFile 压缩之后的输出文件 
     * @throws IOException IO异常 
     */  
    public static void compress(String inFile,String outFile) throws IOException{  
        String compressFile=null;//创建压缩文件  
        String extension=inFile.substring(inFile.length()-4);//获取源文件的后缀名  
        File file=new File(outFile);  
        //如果文件已经存在  
        if(file.exists()){  
            System.out.println("文件已经存在");  
        }else{  
            //自动补齐后缀名  
            if(!outFile.endsWith(".hfm")){  
                compressFile=outFile+".hfm";    
            }  
            else{  
                compressFile=outFile+extension;  
            }  
            //创建文件输入的缓冲流  
            InputStream in=new BufferedInputStream(new FileInputStream(inFile));  
            //创建文件输出的缓冲流  
            OutputStream out=new BufferedOutputStream(new FileOutputStream(compressFile));  
            int ch;  
            //创建哈弗曼压缩的输入流  
            HZIPOutputStream hzout=new HZIPOutputStream(out);  
            while((ch=in.read())!=-1){  
                hzout.write(ch);  
            }  
            
            //关闭流  
            in.close();  
            hzout.close();  
        }  
    }  
    /** 
     * 解压文件的方法，此方法需要填入正确的绝对路径名 
     * @param compressedFile  需要被解压的文件 
     * @param outFile 解压之后的输出文件 
     * @throws IOException IO异常 
     */  
    public static void uncompress(String compressedFile,String outFile) throws IOException{  
        String extension;//文件的后缀名  
        extension =compressedFile.substring(compressedFile.length()-4);  
        //得到压缩前的文件的后缀名  
        //String suffix=compressedFile.substring(compressedFile.length()-8,compressedFile.length()-4);  
        //如果文件不合法则不执行解压操作  
        if(!extension.equals(".hfm")){  
            System.out.println("文件格式错误或者不是有效的压缩文件");  
            return;  
        }  
        File file=new File(outFile);  
        //如果已经存在同名文件  
        if(file.exists()){  
            System.out.println("该文件已经存在，请重新命名解压后的文件");  
        }  
        else{  
            //outFile+=(suffix+".uc");//输出文件的格式统一为uc格式  
            //创建文件输入的缓冲流  
            InputStream fin=new BufferedInputStream(new FileInputStream(compressedFile));  
            //创建数据读入流  
            DataInputStream in=new DataInputStream(fin);  
            //创建哈弗曼压缩输入流  
            HZIPInputStream hzin=new HZIPInputStream(in);  
            //创建文件输出的缓冲流  
            OutputStream fout=new BufferedOutputStream(new FileOutputStream(outFile));  
            int ch;  
            //解压并输出文件  
            while((ch=hzin.read())!=-1){  
                fout.write(ch);  
                //System.out.println(ch);
            }  
            //关闭流  
            hzin.close();  
            fout.close();     
        }  
      
    }  
    public static void main(String args[]) throws IOException{  
        System.out.println("开始压缩");   
        long start=System.currentTimeMillis();  
        compress("第一行代码.pdf","des"); 
        System.out.println("压缩结束,用时:"+(System.currentTimeMillis()-start)); 
        
        System.out.println("开始解压");  
        start=System.currentTimeMillis();  
        uncompress("des.hfm","第一行代码2.pdf");  
        System.out.println("解压结束,用时:"+(System.currentTimeMillis()-start));
    }  
}