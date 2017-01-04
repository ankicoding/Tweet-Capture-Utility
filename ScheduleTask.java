package com.newgen.twittercaptureutility;

import java.util.TimerTask;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
	/**
	 *
	 * @author Dhinakaran P.
	 */
	// Create a class extends with TimerTask
	public class ScheduleTask extends TimerTask {
		public static TwitterCommonFile twCommon = new TwitterCommonFile();
		TweetCapture twtCapture=new TweetCapture();
		Date now; // to display current time

		// Add your task here
		public void run() {
			now = new Date(); // initialize date
			Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    String strDate = formatter.format(now);
		  
				try {
					twCommon.writeToFile("Timer started at:" + now);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  // Display current time
			try {
				twtCapture.load();
				Thread.sleep(20000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void main(String args[]) throws IOException{
	        TimerTask timerTask = new ScheduleTask();
	        //running timer task as daemon thread
	        Timer timer = new Timer(true);
	        timer.scheduleAtFixedRate(timerTask, 0, 10*1000);
	        twCommon.writeToFile("TweetCaptureUtility started");
	        //cancel after sometime
	        try {
	            Thread.sleep(120000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        timer.cancel();
	        twCommon.writeToFile("TweetCaptureUtility cancelled");
	        try {
	            Thread.sleep(30000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
	