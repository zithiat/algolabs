import java.util.*;
/**
 * MyBST is the lab for Binary Search Tree
 * including AVL Tree and Red-Black Tree
 * @author quadoan
 *
 */

public class MyBST {
	
	Node<Integer> root = null;

	public static void main(String[] args) {
		MyBST my = new MyBST();
		int[] arr1 = {1, 9, 3, 8, 12, 4, 2};
		for (int i : arr1) {
			insert(my, i);
		}
		
		System.out.println("Level-order");
		levelOrder(my.root);
		System.out.println("\nTree in visual");
		BTreePrinter.printNode(my.root);
	}
	
	public static void insert(MyBST my, int n) {
		if (my.root == null) {
			my.root = new Node<Integer>(n);
			my.root.left = my.root.right = null;
			return;
		}
		Node<Integer> node = my.root;
		boolean inserted = false;
		while (!inserted) {
			if (node.value.compareTo(n) > 0) {
				if (null == node.left) {
					node.left = new Node<Integer>(n);
					inserted = true;
				} else {
					node = node.left;
				}
			} else if (node.value.compareTo(n) < 0) {
				if (null == node.right) {
					node.right = new Node<Integer>(n);
					inserted = true;
				} else {
					node = node.right;
				}
			}
		}
	}
	
	/**
	 * Pre-order:
	 * - Visit the current
	 * - Recurse on left child
	 * - Then recurse on right child 
	 */ 
	public static void vlr(Node<Integer> node) {
		System.out.print(node.value + " ");
		if (null != node.left) {
			vlr(node.left);
		}
		if (null != node.right) {
			vlr(node.right);
		}
		
	}
	
	/**
	 * In-order
	 * - Recurse left child
	 * - Visit the current
	 * - Then recurse right child
	 */
	public static void lvr(Node<Integer> node) {
		if (null != node.left) {
			lvr(node.left);
		}
		System.out.print(node.value + " ");
		if (null != node.right) {
			lvr(node.right);
		}		
	}
	
	/**
	 * Post-order
	 * - Recurse left child
	 * - Recurse right child
	 * - Then visit the current
	 */
	public static void lrv(Node<Integer> node) {
		if (null != node.left) {
			lrv(node.left);
		}
		if (null != node.right) {
			lrv(node.right);
		}
		System.out.print(node.value + " ");
	}
	
	/**
	 * Level-order
	 * - Visit all node in the same level
	 * - Going thru all levels from top the the farthest leaf
	 */
	public static void levelOrder(Node<Integer> root) {
		int height = getHeightOfTree(root);
		for (int i = 1; i <= height; i++) {
			givenLevel(root, i);
		}
	}
	
	/**
	 * Print all nodes at the same level
	 * Then recurse the next level, left and right nodes
	 */
	private static void givenLevel(Node<Integer> node, int level) {
		if (null == node) return;
		else {
			if (level == 1)
				System.out.print(node.value + " ");
			else if (level > 1){
				givenLevel(node.left, level - 1);
				givenLevel(node.right, level - 1);
			}
		}
	}
	
	/**
	 * Get the height of the tree, based on the counting
	 * - Left nodes
	 * - Right nodes
	 * - Return the largest value
	 */
	private static int getHeightOfTree(Node<Integer> root) {
		if (null == root) return 0;
		else {
			int lH = getHeightOfTree(root.left);
			int rH = getHeightOfTree(root.right);
			if (lH > rH) return lH + 1;
			else return rH + 1;
		}
	}
	
	static class Node<T extends Comparable<?>> {
	    Node<T> left, right;
	    T value;

	    public Node(T value) {
	        this.value = value;
	    }
	}

	static class BTreePrinter {
		public static <T extends Comparable<?>> void printNode(Node<T> root) {
			int maxLevel = BTreePrinter.maxLevel(root);
			printNodeInternal(Collections.singletonList(root), 1, maxLevel);
		}

		private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
			if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
				return;

			int floor = maxLevel - level;
			int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
			int firstSpaces = (int) Math.pow(2, (floor)) - 1;
			int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

			BTreePrinter.printWhitespaces(firstSpaces);

			List<Node<T>> newNodes = new ArrayList<Node<T>>();
			for (Node<T> node : nodes) {
				if (node != null) {
					System.out.print(node.value);
					newNodes.add(node.left);
					newNodes.add(node.right);
				} else {
					newNodes.add(null);
					newNodes.add(null);
					System.out.print(" ");
				}
				BTreePrinter.printWhitespaces(betweenSpaces);
			}
			System.out.println("");

			for (int i = 1; i <= endgeLines; i++) {
				for (int j = 0; j < nodes.size(); j++) {
					BTreePrinter.printWhitespaces(firstSpaces - i);
					if (nodes.get(j) == null) {
						BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
						continue;
					}

					if (nodes.get(j).left != null)
						System.out.print("/");
					else
						BTreePrinter.printWhitespaces(1);

					BTreePrinter.printWhitespaces(i + i - 1);

					if (nodes.get(j).right != null)
						System.out.print("\\");
					else
						BTreePrinter.printWhitespaces(1);

					BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
				}
				System.out.println("");
			}
			printNodeInternal(newNodes, level + 1, maxLevel);
		}

		private static void printWhitespaces(int count) {
			for (int i = 0; i < count; i++)
				System.out.print(" ");
		}

		private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
			if (node == null)
				return 0;

			return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
		}

		private static <T> boolean isAllElementsNull(List<T> list) {
			for (Object object : list) {
				if (object != null)
					return false;
			}
			return true;
		}
	}
}


