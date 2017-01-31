package org.usfirst.frc.team3729.robot.subsystems;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;

// This code came from https://github.com/WHS-FRC-3467/Skip-5.5/blob/master/src/org/usfirst/frc3467/subsystems/LIDAR/LIDAR.java
// It is starter code added to the codebase by Garrison.
// We still have to figure out how to hook the LIDAR up to the RoboRIO

public class Lidar {

	private I2C i2c;

	private static byte[] distance;

	private java.util.Timer updater;

	private LIDARUpdater task;

	private final int LIDAR_ADDR = 0x62;

	private final int LIDAR_CONFIG_REGISTER = 0x00;

	private final int LIDAR_DISTANCE_REGISTER = 0x8f;

	public Lidar(Port port) {

		i2c = new I2C(Port.kMXP, LIDAR_ADDR);

		distance = new byte[2];

		task = new LIDARUpdater();

		updater = new java.util.Timer();

	}

	// Distance in cm

	public static int getDistance() {

		return (int) Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);

	}

	public double pidGet() {

		return getDistance();

	}

	// Start 10Hz polling

	public void start() {

		updater.scheduleAtFixedRate(task, 0, 20);

	}

	// Start polling for period in milliseconds

	public void start(int period) {

		updater.scheduleAtFixedRate(task, 0, period);

	}

	public void stop() {

		updater.cancel();

	}

	// Update distance variable

	public void update() {

		i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement

		Timer.delay(0.04); // Delay for measurement to be taken

		i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement

		Timer.delay(0.01); // Delay to prevent over polling

	}

	// Timer task to keep distance updated

	private class LIDARUpdater extends TimerTask {

		public void run() {

			while (true) {

				update();

				if (getDistance() < 90 && getDistance() > 84) {

					// SmartDashboard.putBoolean("Correct distance from human
					// feeder", true);
					System.out.println("The distance is correct.");

				}

				else {

					// SmartDashboard.putBoolean("Correct distance from human
					// feeder", false);
					System.out.println("The distance is incorrect.");

				}

				// if(getDistance() < 80 && getDistance() > 70){
				//
				// SmartDashboard.putBoolean("Correct distance to stacks",
				// true);
				//
				// }
				//
				// else{
				//
				// SmartDashboard.putBoolean("Correct distance to stacks",
				// false);
				//
				// }

				// SmartDashboard.putNumber("LIDAR distance Inches",
				// (getDistance() / 2.54));
				System.out.println("LIDAR distance in inches: " + getDistance() / 2.54);

				try {

					Thread.sleep(10);

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

			}

		}

	}

	// @Override

	// protected void initDefaultCommand() {
	//
	// // TODO Auto-generated method stub
	//
	//
	//
	// }

}
