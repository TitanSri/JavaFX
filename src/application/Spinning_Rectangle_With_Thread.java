package application;

import javafx.application.Platform;
import javafx.scene.shape.Rectangle;

public class Spinning_Rectangle_With_Thread extends Rectangle implements Runnable {
	private Thread t;
	private int rotation = 0;
	private boolean running = false;
	
	public Spinning_Rectangle_With_Thread(float width, float height) {
		super(width, height);
	}
	
	public void start() {
		if (running == false) {
			t = new Thread(this);
			t.start();
			running = true;
		}
		else {
			return; // already running
		}
	}
	
	public void stop() {
		running = false;
		if (t != null) { // don't try to interrupt if thread not yet created
			t.interrupt(); // sets the interrupted flag
		}
	}

	@Override
	public void run() {
		
		Runnable updater = new Runnable() {
			@Override
			public void run() {
				setRotate(rotation += 25);
			}
		};
		
		try {
			while (!Thread.currentThread().isInterrupted()) { // checks for the interrupted flag	
				Thread.sleep(10);
				Platform.runLater(updater);
			}
		}
		catch (InterruptedException ie) {
		}
	}
}