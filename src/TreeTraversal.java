
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
	/*
	 * PreOrder Tree Traversal -  Depth First Search
	 */
	public static void preorder(Node root){
		if (root == null) return;
		
		System.out.print(root.value + "-");
		preorder(root.left);
		preorder(root.right);
	}
	/*
	 * InOrder Tree Traversal -  Depth First Search
	 */
	public static void inorder(Node root){
		if (root == null) return;
		
		inorder(root.left);
		System.out.print(root.value + "-");
		inorder(root.right);
	}
	/*
	 * PostOrder Tree Traversal -  Depth First Search
	 */
	public static void postorder(Node root){
		if (root == null) return;
		
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.value + "-");
	}
	
	/*
	 * Level Order Tree Traversal -  Breadth First Search
	 */
	public static void BFS(Node root){
		if (root == null) return;
		
		Queue<Node> queue = new Queue<>();
		queue.enqueue(root);
		
		while (!queue.isEmpty()){
			Node temp = queue.dequeue();
			System.out.print(temp.value +"-");
			if (temp.left != null) queue.enqueue(temp.left);
			if (temp.right!= null) queue.enqueue(temp.right);
		}
		
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
		
		// Tree Traversals
		System.out.println("###### Preorder -  Depth First #######");
		preorder(root);
		
		System.out.println("");
		System.out.println("###### Inorder -  Depth First #######");
		inorder(root);
		
		System.out.println("");
		System.out.println("###### Postorder -  Depth First #######");
		postorder(root);
		
		
		System.out.println("");
		System.out.println("###### Level Order Traversal - Breadth First #######");
		BFS(root);
	}

}
