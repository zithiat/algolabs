
public class MyLinkedList {
	static Node head;
	static class Node {
		int data;
		Node next;
		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	void toString(Node node) {
		while (null != node) {
			System.out.print(node.data + " > ");
			node = node.next;
		}
		if (null == node)
			System.out.println(node);
	}
	
	static Node reverse(Node node) {
		Node prev = null;
		Node current = node;
		Node temp = null;
		while (null != current) {
			temp = current.next;
			current.next = prev;
			prev = current;
			current = temp;
		}
		node = prev;
		return node;
	}
	
	public static void main(String[] args) {
		MyLinkedList mll = new MyLinkedList();
		mll.head = new Node(2);
		mll.head.next = new Node(7);
		mll.head.next.next = new Node(9);
		mll.head.next.next.next = new Node(18);
		mll.toString(mll.head);
		head = mll.reverse(head);
		mll.toString(mll.head);
	}
}
