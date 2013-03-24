package com.egymasters.leapmousecontrol;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Listener;

public class LeapListener extends Listener {
	private static final int SPEED = 3;
	private Finger finger;
	private float x, y;
	private int difX, difY;
	private Robot robot;
	private boolean isOutOfRange;
	private Dimension screenSize ;

	public void onInit(Controller controller) {
		System.out.println("Initialized");

	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		 screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		try {
			robot = new Robot();
			
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}

	public void onExit(Controller controller) {
		System.out.println("Exited");
	}

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();

		if (!frame.fingers().empty()) {
			isOutOfRange = false;
			finger = frame.fingers().get(0);

			difX = (int) (SPEED * (finger.tipPosition().getX() - x));
			difY = (int) (SPEED * (y - finger.tipPosition().getY()));

			int mouseX = MouseInfo.getPointerInfo().getLocation().x + difX;
			int mouseY = MouseInfo.getPointerInfo().getLocation().y + difY;

			if(mouseX>0&&mouseX<screenSize.width&&mouseY>0&&mouseY<screenSize.height){
				robot.mouseMove(mouseX, mouseY);
				robot.delay(1);

				x = finger.tipPosition().getX();
				y = finger.tipPosition().getY();
			}
			

		} else {
			if (!isOutOfRange) {
				System.out.println("Finger is out of Leap range.");
				isOutOfRange = true;
			}
		}
	}

}
