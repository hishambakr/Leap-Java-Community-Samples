package com.egymasters.leapmousecontrol;
import java.io.IOException;

import com.leapmotion.leap.Controller;

/* Remember to set java.library.path= path to Leap lib folder (ex: /Applications/LeapSDK/lib/)
 in (Run Configurations --> VM Arguments)*/
public class Main {
	public static void main(String[] args) {
		LeapListener listener = new LeapListener();
		Controller controller = new Controller();

		controller.addListener(listener);

		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done
		controller.removeListener(listener);
	}
}
