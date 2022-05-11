package main.notes.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class runTaskEverySecond {
	// This is Java 7 and up, version
	public static void main(String[] args) {
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run() {
	            myTask();
	        }
	    }, 0, 1, TimeUnit.SECONDS);
	}
	
	private static void myTask() {
	    System.out.println("Running");
	}
}
