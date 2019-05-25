import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.PriorityQueue;

public class HuffmanTree {
	private CharCounter theCounts;
	private HuffNode root;
	private HuffNode[] theNodes= new HuffNode[BitUtils.DIEF_BYTES+1];
	public static final int ERROR=-3;
	public static final int INCOMPLETE_CODE=-2;
	public static final int END=BitUtils.DIEF_BYTES;
	
	public HuffmanTree() {
		theCounts = new CharCounter();
		root = null;
	}
	
	public HuffmanTree(CharCounter cc) {
		theCounts = cc;
		root = null;
		createTree();
	}

	public int[] getCode(int ch) {
		HuffNode current = theNodes[ch];
		
		if(current==null)
			return null;
		String v="";
		HuffNode parent=current.parent;
		
		while(parent!=null) {
			if(parent.left==current)
				v="0"+v;
			else
				v="1"+v;
			current= current.parent;
			parent= current.parent;
		}
		//System.out.println("---"+v+' '+(char)ch+" "+current.weight);
		int len = v.length();
		int[] result = new int[len];
		for(int i=0;i<len;i++)
			result[i]=v.charAt(i)=='0'?0:1;
		return result;
	}
	
	public int getChar(String code) {
		HuffNode leaf=root;
		//System.out.println("root ="+root.weight+"  value= "+root.value);
		int len = code.length();
		//System.out.println("len = "+len);
		
		for(int i=0;leaf!=null&&i<len;i++)
			if(code.charAt(i)=='0') {
				//System.out.println("leaf= "+leaf.weight);
				leaf=leaf.left;
			}
			else
				leaf=leaf.right;
		
		if(leaf ==null)
			return ERROR;
		return leaf.value;
	}
	
	public void writeEncodingTable(DataOutputStream out)throws IOException{
		for(int i=0;i<BitUtils.DIEF_BYTES;i++) {
			if(theCounts.getCount(i)>0) {
				out.writeByte(i);
				//System.out.print((char)i+" ");
				out.writeInt(theCounts.getCount(i));
				//System.out.print(theCounts.getCount(i));
			}
		}
		out.writeByte(0);
		out.writeInt(0);
	}
	
	public void readEncodingTable(DataInputStream in)throws IOException{
		for(int i=0;i<BitUtils.DIEF_BYTES;i++) {
			theCounts.setCount(i, 0);
		}
		byte ch;
		int num;
		for(;;) {
			ch=in.readByte();
			num=in.readInt();
			//System.out.println("ch = "+ch+" "+"num= "+num);
			if(num==0)
				break;
			theCounts.setCount(ch, num);
		}
		createTree();
	}
	
	private void createTree() {
		PriorityQueue<HuffNode> pq = new PriorityQueue<HuffNode>();
		
		for(int i=0;i<BitUtils.DIEF_BYTES;i++) {
			if(theCounts.getCount(i)>0) {
				HuffNode newNode = new HuffNode(i,theCounts.getCount(i),null,null,null);
				char c= (char) (newNode.value);
				//System.out.println(c+" "+newNode.weight);
				theNodes[i]=newNode;
				pq.add(newNode);
			}
		}
		
		theNodes[END]=new HuffNode(END,1,null,null,null);
		pq.add(theNodes[END]);
		//System.out.println("--------------");
		while(pq.size()>1) {
			HuffNode n1=pq.remove();
			//System.out.println((char)n1.value+" "+n1.weight);
			HuffNode n2=pq.remove();
			//System.out.println((char)n2.value+" "+n2.weight);
			HuffNode result = new HuffNode(INCOMPLETE_CODE,n1.weight+n2.weight,n1,n2,null);
			n1.parent = n2.parent = result;
			
			pq.add(result);
			//System.out.println("pq weight :" +result.weight);
		}
		root = pq.poll();
		//System.out.println("root weight :" +root.weight);
	}
}
