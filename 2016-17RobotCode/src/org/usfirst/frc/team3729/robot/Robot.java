
package org.usfirst.frc.team3729.robot;

import org.usfirst.frc.team3729.robot.commands.XboxControler;
import org.usfirst.frc.team3729.robot.commands.modularPeripheries;
import org.usfirst.frc.team3729.robot.commands.robotDrive;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	ADXRS450_Gyro gyro;
	// THESE ARE THE AUTONIMOUS THINGIES
	final String defaultAuto = "Default";
	final String autonomousPath1 = "Just go straight then stop.";
//	final String autonomousPath2 = "BLUE - MIDDLE";
//	final String autonomousPath3 = "BLUE - RIGHT";
//	final String autonomousPath4 = "RED - LEFT";
//	final String autonomousPath5 = "RED - MIDDLE";
//	final String autonomousPath6 = "RED - RIGHT";
	String autoSelected;
	boolean automove;
	int seconds;

	robotDrive drive;
	SendableChooser chooser;
	XboxControler xbox;
	modularPeripheries periphery;

	// UsbCamera cam;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void robotInit() {

		xbox = new XboxControler(0);
		drive = new robotDrive(xbox);
		periphery = new modularPeripheries(xbox);

		gyro = new ADXRS450_Gyro();

		// cam = CameraServer.getInstance().startAutomaticCapture();

		gyro.calibrate();
		gyro.reset();

		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("Autonomous Path High Center Goal", autonomousPath1);
//		chooser.addObject("Autonomous Path High Left Goal", autonomousPath2);
//		chooser.addObject("Autonomous Path Defense Driveover", autonomousPath3);
//		chooser.addObject("Autonomous Path High Center Goal", autonomousPath4);
//		chooser.addObject("Autonomous Path High Left Goal", autonomousPath5);
//		chooser.addObject("Autonomous Path Defense Driveover", autonomousPath6);
		SmartDashboard.putData("Auto choices", chooser);

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
		seconds = 0;
		// gyro.calibrate();
		// gyro.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		automove = true;
		switch (autoSelected) {

		case autonomousPath1: // BLUE - LEFT
			if (automove == true) {

				while (seconds >= 10) {
					drive.autoDrive(0.7);

					seconds++;
					Timer.delay(1);
				}
			}

			automove = false;
		
		break;
		// case autonomousPath2: // BLUE - MIDDLE
		// if(automove==true)
		//
		// {
		// automove = false;
		// }break;
		//
		// case autonomousPath3: // BLUE - RIGHT
		// if(automove==true)
		// {
		//
		// automove = false;
		// }case autonomousPath4: // RED - LEFT
		// if(automove==true)
		// {
		//
		// automove = false;
		// }case autonomousPath5:// RED - MIDDLE
		// if(automove==true)
		// {
		//
		// automove = false;
		// }case autonomousPath6: // RED - RIGHT
		// if(automove==true)
		// {
		//
		// automove = false;
		// }break;case defaultAuto:default:if(automove==true)
		// {
		//
		// automove = false;
		// }break;
	}

	// switch (autoSelected) {
	// case customAuto:
	// Put custom auto code here
	// break;
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
		// drive.mechenumDrive();

		// EATING DOODLE

		periphery.climbingKiddo();
		periphery.shootButton();
		periphery.LoadingKiddo();
		periphery.Noms();

		// table = NetworkTable.getTable("");
		// stuff = table.getSubTable("SmartDashboard");
		// double area = stuff.getNumber("COG_AREA",0.0);
		// System.out.println("Area "+ area);

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}

}
