public class MyLinkedList {
	Node head;
	static class Node {
		Integer data;
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
	
	Node reverse(Node node) {
		Node prev = null;
		Node current = node;
		Node temp = null;
		while (null != current) {
			temp = current.next;
			System.out.println(printNodes("temp=current.next", temp, current, prev));
			current.next = prev;
			System.out.println(printNodes("current.next=prev", temp, current, prev));
			prev = current;
			System.out.println(printNodes("prev=current", temp, current, prev));
			current = temp;
			System.out.println(printNodes("current=temp", temp, current, prev));
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
		mll.head = mll.reverse(mll.head);
		mll.toString(mll.head);
	}
	
	static String printNodes(String str, Node temp, Node current, Node prev) {
		StringBuilder sb = new StringBuilder();
		sb.append(str).append(": ");
		sb.append("temp=").append(temp==null?"null":temp.data).append(" >> ");
		sb.append("current=").append(current==null?"null":current.data).append(" >> ");
		sb.append("current.next=").append(current.next==null?"null":current.next.data).append(" >> ");
		sb.append("prev=").append(prev==null?"null":prev.data);
		return sb.toString();
	}
}
