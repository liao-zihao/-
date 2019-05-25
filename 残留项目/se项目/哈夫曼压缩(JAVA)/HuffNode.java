
public class HuffNode implements Comparable<HuffNode>{
	public int value;
	public int weight;
	HuffNode left,right;
	HuffNode parent;
	
	
	public HuffNode(int value, int weight, HuffNode left, HuffNode right, HuffNode parent) {
		this.value = value;
		this.weight = weight;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public int compareTo(HuffNode rhs) {
		return weight-rhs.weight;
	}

}
