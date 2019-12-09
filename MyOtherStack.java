import java.util.*;

public class MyOtherStack {
	
	Stack<Integer> myStack;
	Stack<Integer> minStack;
	int min;
	
	MyOtherStack() {
		myStack = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}
	
	void push(Integer data) {
		if (myStack.isEmpty() || data < min) {
			myStack.push(data);
			minStack.push(data);
			min = data;
		} else if (data > min) {
			myStack.push(data);
			minStack.push(min);
		}
		System.out.println("Actual stack: " + myStack.toString());
		System.out.println("Min stack: " + minStack.toString());
	}
	
	Integer getMin() {
		if (myStack.isEmpty()) {
			return null;
		}
		return min;
	}
	
	Integer pop() {
		if (myStack.isEmpty()) {
			return null;
		}
		minStack.pop();
		min = minStack.peek();
		return myStack.pop();
	}
	
	public String toString() {
		if (myStack.isEmpty()) {
			System.out.println("Stack empty");
			return null;
		}
		return myStack.toString();
	}

	public static void main(String[] args) {
		MyOtherStack mos = new MyOtherStack();
		mos.push(3);
		mos.push(5);
		mos.push(2);
		System.out.println("Minum element: " + mos.getMin());
		mos.push(4);
		mos.push(1);
		System.out.println("Minum element: " + mos.getMin());
		System.out.println("Pop top element: " + mos.pop() + "--stack: " + mos.toString());
		System.out.println("Minum element: " + mos.getMin());
		System.out.println("Pop top element: " + mos.pop() + "--stack: " + mos.toString());
		System.out.println("Minum element: " + mos.getMin());
		System.out.println("Pop top element: " + mos.pop() + "--stack: " + mos.toString());
		System.out.println("Minum element: " + mos.getMin());
	}
}
