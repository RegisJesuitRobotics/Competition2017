
package org.usfirst.frc.team3729.robot;

import org.usfirst.frc.team3729.robot.commands.Ck;
import org.usfirst.frc.team3729.robot.commands.PlayStationController;
import org.usfirst.frc.team3729.robot.commands.modularPeripheries;
import org.usfirst.frc.team3729.robot.commands.robotDrive;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// THESE ARE THE AUTONIMOUS THINGIES
	final String defaultAuto = "Default";
	final String autonomousPath1 = "1";
	final String autonomousPath2 = "2";
	final String autonomousPath3 = "3";
	String autoSelected;
	boolean automove;
	double seconds = 10.0;

	// robotDrive drive;
	SendableChooser chooser;
	PlayStationController playStation;
	// modularPeripheries periphery;
	Ck ck;

	UsbCamera cam;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		automove = true;
		playStation = new PlayStationController(0);
		// drive = new robotDrive(playStation);
		// periphery = new modularPeripheries(playStation);
		ck = new Ck(playStation);
		// cam = CameraServer.getInstance().startAutomaticCapture();

		// chooser = new SendableChooser();
		// chooser.addDefault("Default Auto", defaultAuto);
		// chooser.addObject("Autonomous Path High Center Goal",
		// autonomousPath1);
		// chooser.addObject("Autonomous Path High Left Goal", autonomousPath2);
		// chooser.addObject("Autonomous Path Defense Driveover",
		// autonomousPath3);
		// SmartDashboard.putData("Auto choices", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		// autoSelected = (String) chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		// System.out.println("Auto selected: " + autoSelected);
		// autoSelected = (String) chooser.getSelected();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	// @Override

	public void autonomousPeriodic() {
		// AUTO STR8
		if (automove == true) {
			ck.GoForewards(.25, 2.7);
			automove = false;
		}

		// Need to go 9 feet and 4 inches

		// // Inside of robot 5ft From Center Line LEFT
		// // turn 65 degrees
		// auto.GoForewards(0.6, 2);
		// auto.TurnRight(0.5, 2);
		// auto.GoForewards(0.6, 2);
		//
		// // Inside of robot 5ft From Center Line RIGHT
		// //Go 103.5 inches
		//
		// // Drive 86 inches
		// // turn 65 degrees
		// // drive 37.5 inches
		// auto.GoForewards(0.6, 2);
		// auto.TurnLeft(0.5, 2);
		// auto.GoForewards(0.6, 2);
		//
		// switch (autoSelected)
		//
		// {
		//
		// case autonomousPath1:
		// if (automove == true) {
		// automove = false;
		// }
		// break;
		// case autonomousPath2:
		// if (automove == true) {
		//
		// }
		// }
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		// drive.arcadeDrive();
		ck.CkDrive();
		ck.CkPeripheries();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}

}
