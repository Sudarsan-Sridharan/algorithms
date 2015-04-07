
public class TreeTraversal {

	static class Node {
		private int value;
		private Node left;
		private Node right;
		
		public Node (int value){
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
	
	public static Node generateBST(int [] arr){
		
		return new Node(0);
	}

	public static void preorder(Node root){
		if (root == null) return;
		
		System.out.print(root.value + "-");
		preorder(root.left);
		preorder(root.right);
	}
	
	public static void inorder(Node root){
		if (root == null) return;
		
		inorder(root.left);
		System.out.print(root.value + "-");
		inorder(root.right);
	}
	
	public static void postorder(Node root){
		if (root == null) return;
		
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.value + "-");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
        			  8
       				/   \
      			   3     10
    			  / \     \ 
   				 1   6     14
    				/ \	   /
    			   4   7  13
		 */	
		Node root = new Node(8);				// Level 0
		
		root.left = new Node(3);				// Level 1
		root.right =  new Node (10);			// Level 1
		
		root.left.left = new Node(1);			// Level 2
		root.left.right = new Node(6);			// Level 2
		root.right.right = new Node(14); 		// Level 2
		
		root.left.right.left = new Node(4);			// Level 2
		root.left.right.right = new Node(7);		// Level 2
		root.right.right.left = new Node(13); 		// Level 2
		
		System.out.println("###### Preorder #######");
		preorder(root);
		System.out.println("");
		System.out.println("###### Inorder #######");
		inorder(root);
		System.out.println("");
		System.out.println("###### Postorder #######");
		postorder(root);
	}

}
