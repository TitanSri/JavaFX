package application;

/*30.4 (Synchronize threads) Write a program that launches 1,000 threads. Each
thread adds 1 to a variable sum that initially is 0. Define an Integer wrapper
object to hold sum. Run the program with and without synchronization to see
its effect.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise_32_4_Synchronize_Threads implements Runnable {
	private static int counter = 1; // start from 
    private static final int limit = 1000; // count to this number
    private static final int threadPoolSize = 5; // amount of threads used
    private static final Object lock = new Object(); // object for synchronization 
    private static Lock lockSleep = new ReentrantLock(); // required for the delay to use all the threads else it will only use 1 thread

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        for (int i = 0; i < threadPoolSize; i++) { // ensure all threads are used 
            executorService.submit(new Exercise_32_4_Synchronize_Threads()); // use a thread available 
        }
        executorService.shutdown();
    }
    
    // runs the threads loop 
    public void run() {
        while (counter <= limit - threadPoolSize + 1) {
            increaseCounter();
        }
    }
    
    // increase the counter and use a object to synchronize 
    private void increaseCounter() {
    	lockSleep.lock(); // sleep lock
        synchronized (lock) { // sync the object to prevent uncoordinated outcomes 
            System.out.println(Thread.currentThread().getName() + " : " + counter); // thread used in executor service 
            
            try {
            	counter++; // increase the counter
                // This delay is deliberately added to magnify the
                // data-corruption problem and make it easy to see.
                Thread.sleep(5);
              }
              catch (InterruptedException ex) {
              }
              finally {
                lockSleep.unlock(); // Release the lock
              }
        }
    }
}