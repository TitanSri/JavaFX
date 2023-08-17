package application;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FlashingLight_Thread extends Circle implements Runnable {
	private Thread thread;
	private boolean suspended = false;
	
	public FlashingLight_Thread(double radius, Color color) {
		super(radius, color);
	}
	
	public void start() {
		if (thread == null) { // if nothing then start
			thread = new Thread(this);
			thread.start();
		}
		else if (suspended == true) { // if suspended
			resume();
		}
		else // do nothing is already started
			return;
	}
	
	public void stop() {
		if (thread != null) { // if the thread is running then interrupt and set the thread to null
			thread.interrupt();
			thread = null;
		}
	}
	
	public void pause() { // change the suspend to true which will out the thread to wait in the run method 
		suspended = true;
	}
	
	public synchronized void resume() { // change the suspend to false to continue the thread in the run method
		suspended = false;
		notifyAll();
	}
	
	@Override
	public void run() {
		Runnable updater = new Runnable() {

			@Override
			public void run() {
				setFill(Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
			}
			
		};
		
		while(true) {
			try {
				Thread.sleep(20);
				
				synchronized(this) {
					while(suspended) { // check the suspend boolean
						wait();
					}
				}	
				
				Platform.runLater(updater);
				
			} catch (InterruptedException e) {
				return;
			}
		}
		
	}
	
	
}