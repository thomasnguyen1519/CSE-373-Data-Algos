

public class Test {

	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack();
		ListStack stack1 = new ListStack();
		DoubleQueue queue = new DoubleQueue();
		DoubleQueue queue1 = new DoubleQueue();
		for (int i = 0; i <= 20; i++) {
			stack.push(i);
			stack1.push(i);
			System.out.println(stack + " " + stack.size());
			System.out.println(stack1 + " " + stack1.size());
		}
		while(!stack.isEmpty()) {
			queue.add(stack.pop());
			queue1.add(stack1.pop());
			System.out.println(stack + " " + stack.size());
			System.out.println(stack1 + " " + stack1.size());
			System.out.println(queue);
			System.out.println(queue1);
		}
		while (!queue.isEmpty()) {
			stack.push(queue.remove());
			stack1.push(queue1.remove());
		}
	}

}
