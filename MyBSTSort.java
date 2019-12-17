import java.util.Arrays;

public class MyBSTSort {
	
	Node root;
	MyBSTSort() {
		root = null;
	}

	class Node {
		int key;
		Node left, right;
		public Node(int key) {
			this.key = key;
			left = right = null;
		}
	}
	
	void insert(int key) {
		root = insertRecursive(root, key);
	}
	
	Node insertRecursive(Node root, int key) {
		if (null == root) {
			root = new Node(key);
			return root;
		}
		if (key < root.key) 
			root.left = insertRecursive(root.left, key);
		else
			root.right = insertRecursive(root.right, key);
		return root;
	}
	
	void inOrderTraversal(Node root) { // Left-Visit-Right
		if (null != root) {
			inOrderTraversal(root.left);
			System.out.print(root.key + " ");
			inOrderTraversal(root.right);
		}
	}
	
	public static void main(String[] args) {
		MyBSTSort my = new MyBSTSort();
		int arr[] = {5, 4, 7, 2, 11};
		System.out.println("In array: " + Arrays.toString(arr));
		for (int i : arr) {
			my.insert(i);
		}
		System.out.print("Sorted array: ");
		my.inOrderTraversal(my.root);
	}
}
