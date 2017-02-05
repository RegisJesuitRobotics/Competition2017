
package org.usfirst.frc.team3729.robot;

import org.usfirst.frc.team3729.robot.commands.XboxControler;
import org.usfirst.frc.team3729.robot.commands.robotDrive;
import org.usfirst.frc.team3729.robot.commands.modularPeripheries;
import edu.wpi.first.wpilibj.AnalogGyro;

import edu.wpi.first.wpilibj.IterativeRobot;
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
	final String autonomousPath1 = "Autonomous Path High Center Goal";
	final String autonomousPath2 = "Autonomous Path High Left Goal";
	final String autonomousPath3 = "Autonomous Path Defense Driveover";
	String autoSelected;
	boolean automove;
	robotDrive drive;
	SendableChooser chooser;
	XboxControler xbox;
	modularPeripheries periphery;
	AnalogGyro gyro;
	// USBCamera cam;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// chooser = new SendableChooser();
		// chooser.addDefault("Default Auto", defaultAuto);
		// chooser.addObject("My Auto", customAuto);
		// SmartDashboard.putData("Auto choices", chooser);
		xbox = new XboxControler(0);
		drive = new robotDrive(xbox);
		periphery = new modularPeripheries(xbox);
		gyro = new AnalogGyro(0);
		// cam = new USBCamera();

		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("Autonomous Path High Center Goal", autonomousPath1);
		chooser.addObject("Autonomous Path High Left Goal", autonomousPath2);
		chooser.addObject("Autonomous Path Defense Driveover", autonomousPath3);
		SmartDashboard.putData("Auto choices", chooser);

		gyro.initGyro();
		gyro.calibrate();
		gyro.reset();
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
		autoSelected = (String) chooser.getSelected();

		gyro.initGyro();
		gyro.calibrate();
		gyro.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

		switch (autoSelected) {
		case autonomousPath1:
			if (automove == true) {
				automove = false;
			}
			break;

		case autonomousPath2:
			if (automove == true) {
				automove = false;
			}
			break;

		case autonomousPath3:
			if (automove == true) {

				automove = false;
			}
			break;
		case defaultAuto:
		default:
			if (automove == true) {

				automove = false;
			}
			break;
		}

		// switch (autoSelected) {
		// case customAuto:
		// Put custom auto code here
		// break;+
		// case defaultAuto:
		// default:
		// Talon RightMotor, LeftMotor;
		//
		// RightMotor = new Talon(1);
		// LeftMotor = new Talon(2);
		//
		// RightMotor.set(.5);
		// LeftMotor.set(.5);
		// break;
		// }
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		// DRIVING DOODLE
		drive.arcadeDrive();
		drive.mechenumDrive();

		// EATING DOODLE
		periphery.onOffEating();

		// LOADING DOODLE
		// periphery.conscousLoading();

		// SHOOTING DOODLE
		// periphery.shootButton();
		// SEEING DOODLE

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}

}
