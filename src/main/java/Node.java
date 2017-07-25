/**
 * @filename      : Node.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年6月13日 上午11:12:38   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class Node {

	public int value;//当前值
	public Node left;//左节点
	public Node right;//右节点
	
	public void store(int value)
	{
		if(value<this.value)
		{
			if(left == null)
			{
				left = new Node();
				left.value=value;
			}
			else
			{
				left.store(value);
			}
		}
		else if(value>this.value)
		{
			if(right == null)
			{
				right = new Node();
				right.value=value;
			}
			else
			{
				right.store(value);
			}			
		}
	}
	
	public boolean find(int value)
	{	
		System.out.println("happen " + this.value);
		if(value == this.value)
		{
			return true;
		}
		else if(value>this.value)
		{
			if(right == null) return false;
			return right.find(value);
		}else
		{
			if(left == null) return false;
			return left.find(value);
		}

	}
	
	public  void preList()
	{
		System.out.print(this.value + ",");
		if(left!=null) left.preList();
		if(right!=null) right.preList();
	}
	
	public void middleList()
	{
		if(left!=null) left.preList();
		System.out.print(this.value + ",");
		if(right!=null) right.preList();		
	}
	public void afterList()
	{
		if(left!=null) left.preList();
		if(right!=null) right.preList();
		System.out.print(this.value + ",");		
	}	
//	public static void main(String [] args)
//	{
//		int [] data = new int[20];
//		for(int i=0;i<data.length;i++)
//		{
//			data[i] = (int)(Math.random()*100) + 1;
//			System.out.print(data[i] + ",");
//		}
//		System.out.println();
//		
//		Node root = new Node();
//		root.value = data[0];
//		for(int i=1;i<data.length;i++)
//		{
//			root.store(data[i]);
//		}
//		
//		root.find(data[19]);
//		
//		root.preList();
//		System.out.println();
//		root.middleList();
//		System.out.println();		
//		root.afterList();
//	
//}
}
