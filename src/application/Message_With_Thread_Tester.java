package application;

public class Message_With_Thread_Tester {
public static void main(String[] args) throws InterruptedException {
		
		Message m1 = new Message("Message 1");
		Message m2 = new Message("Message 2");
		Message m3 = new Message("Message 3");
		
		Thread t1 = new Thread(m1);
		Thread t2 = new Thread(m2);
		Thread t3 = new Thread(m3);
		
		t1.start();
		t2.start();
		t3.start();
		
		t1.join(); // waits until t1 in finished before stating t1 again
		t2.join();
		t3.join();
		
		System.out.println("*** Program has finished ***");
	}
}