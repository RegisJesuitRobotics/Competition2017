
package org.usfirst.frc.team3729.robot;

import org.usfirst.frc.team3729.robot.commands.Ck;
import org.usfirst.frc.team3729.robot.commands.PlayStationController;
import org.usfirst.frc.team3729.robot.commands.modularPeripheries;
import org.usfirst.frc.team3729.robot.commands.robotDrive;
//<<<<<<< HEAD
//import org.usfirst.frc.team3729.robot.commands.AutoMethods;
//=======
//import org.usfirst.frc.team3729.robot.commands.AutoMethods;
//>>>>>>> 5e196497e2dae7eb8124cb389dfbdca1e56da8a4

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
	
	
	ADXRS450_Gyro gyro;
	// THESE ARE THE AUTONIMOUS THINGIES
	final String defaultAuto = "Default";
	final String autonomousPath1 = "1";
	final String autonomousPath2 = "2";
	final String autonomousPath3 = "3";
	String autoSelected;
	boolean automove;
	double seconds = 10.0;

//<<<<<<< HEAD
//<<<<<<< HEAD
	//robotDrive drive;
//=======
//	AutoMethods auto;
//	robotDrive drive;
//>>>>>>> d8f2cae2e2a0f7fe0f60d520b7c9ed4236c9caab
//=======
	//AutoMethods auto;
	//robotDrive drive;
//>>>>>>> 5e196497e2dae7eb8124cb389dfbdca1e56da8a4
	SendableChooser chooser;
	PlayStationController playStation;
	//modularPeripheries periphery;
	Ck ck;

	UsbCamera cam;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		playStation = new PlayStationController(0);
		// drive = new robotDrive(playStation);
		// periphery = new modularPeripheries(playStation);
		// gyro = new ADXRS450_Gyro();
		ck = new Ck(playStation);
//<<<<<<< HEAD
		// cam = new USBCamera();
//=======
		cam = CameraServer.getInstance().startAutomaticCapture();
//>>>>>>> 5e196497e2dae7eb8124cb389dfbdca1e56da8a4
		// gyro.calibrate();
		// gyro.reset();

		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("Autonomous Path High Center Goal", autonomousPath1);
		chooser.addObject("Autonomous Path High Left Goal", autonomousPath2);
		chooser.addObject("Autonomous Path Defense Driveover", autonomousPath3);
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
		autoSelected = (String) chooser.getSelected();
		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		autoSelected = (String) chooser.getSelected();

		// gyro.calibrate();
		// gyro.reset();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	// @Override

	public void autonomousPeriodic() {
		switch (autoSelected) {
		case autonomousPath1:
			if (automove == true) {
				automove = false;
			}
			break;
		case autonomousPath2:
			if (automove == true) {
//<<<<<<< HEAD
				//drive.RightMotorFront.set(.3);
				//drive.RightMotorBack.set(.3);
				//drive.LeftMotorFront.set(-.3);
				//drive.LeftMotorBack.set(-.3);
//=======
//>>>>>>> 5e196497e2dae7eb8124cb389dfbdca1e56da8a4

			}
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		double driver = 1;
		if(driver == 1){
		ck.CkDrive();
		ck.CkPeripheries();
		}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	
	
	}

}
