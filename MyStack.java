import java.util.*;

/**
 * This is an implementation of a stack for:
 * - pop, push, getMin operation in O(1)
 * @author quandoan
 *
 */

public class MyStack {
	static Stack<Integer> myStack;
	static int min;
	
	MyStack() {
		myStack = new Stack<Integer>();
	}
	
	static void push(Integer data) {
		if (myStack.isEmpty()) {
			min = data;
			myStack.push(data);
			System.out.println("Inserted: " + data);
			return;
		}
		
		if (data < min) {
			myStack.push(2 * data - min);
			min = data;
		} else {
			myStack.push(data);
		}
		System.out.println("Inserted: " + data);
	}
	
	static void peek() {
		if (myStack.isEmpty()) {
			System.out.println("Stack empty");
			return;
		}
		Integer tmp = myStack.peek();
		Integer result = null;
		if (tmp > min) {
			result = tmp;
		} else {
			result = min;
		}
		System.out.println("Top element is: " + result);
	}
	
	static void pop() {
		if (myStack.isEmpty()) {
			System.out.println("Stack empty");
			return;
		}
		Integer tmp = myStack.pop();
		Integer result = null;
		if (tmp < min) {
			result = min;
			min = 2 * min - tmp;
		} else {
			result = tmp;
		}
		System.out.println("Pop the top element removed: " + result);
	}
	
	static Integer getMin() {
		if (myStack.isEmpty()) {
			System.out.println("Stack empty");
			return null;
		}
		return min;
	}
	
	public String toString() {
		if (myStack.isEmpty()) {
			System.out.println("Stack empty");
			return null;
		}
		return myStack.toString();
	}
	
	public static void main(String[] args) {
		MyStack my = new MyStack();
		my.push(3);
		my.push(5);
		my.push(2);
		System.out.println("Minimum element: " + my.getMin());
		my.push(4);
		my.push(1);
		System.out.println("Minimum element: " + my.getMin());
		System.out.println("Stack: " + my.toString());
		my.pop();
		my.peek();
		System.out.println("Minimum element: " + my.getMin());
		System.out.println("Stack: " + my.toString());
		my.pop();
		my.peek();
		System.out.println("Minimum element: " + my.getMin());
		System.out.println("Stack: " + my.toString());
		my.pop();
		my.peek();
		System.out.println("Minimum element: " + my.getMin());
		System.out.println("Stack: " + my.toString());
	}
}
