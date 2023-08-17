package application;

public class Message implements Runnable {

	private String theMessage;
	
	public Message(String theMessage) {
		this.theMessage = theMessage;
	}
	
	@Override
	public void run() {
		for (int i=0; i < 5; i++) {
			System.out.println(theMessage + " : loop number: " + i);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // reset the status flag
			}
		}
	}
}
