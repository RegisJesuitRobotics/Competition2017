
package org.usfirst.frc.team3729.robot;

import org.usfirst.frc.team3729.robot.commands.Ck;
import org.usfirst.frc.team3729.robot.commands.NO_TOUCH;
import org.usfirst.frc.team3729.robot.commands.PlayStationController;
import org.usfirst.frc.team3729.robot.commands.modularPeripheries;
import org.usfirst.frc.team3729.robot.commands.robotDrive;

import com.ctre.CANTalon;

import java.util.Date;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3279.vision.GearTargetRangerFinder;
import org.usfirst.frc.team3729.robot.Vision;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	// <<<<<<< HEAD
	Date LastPush = new Date();
	Date now = new Date();

	Compressor c = new Compressor(0);
	// ADXRS450_Gyro gyro;
	AnalogInput sanic;
	// =======
	//
	// >>>>>>> e0656688bd1513c860a962ddaf0e25a606525d90
	// THESE ARE THE AUTONIMOUS THINGIES
	final String defaultAuto = "Default";
	final String autonomousPath1 = "Gay Forward  Gear Floop";
	final String autonomousPath2 = "Yoooo";
	final String autonomousPath3 = "Dank-O's";
	String autoSelected;
	boolean automove;
	double seconds = 10.0;
	boolean thread = false;
	UsbCamera camera;
	VisionThread visionThread;

	// //AutoMethods auto;
	// //robotDrive drive;

	// SendableChooser chooser;
	PlayStationController playStation;
	// //modularPeripheries periphery;
	// // robotDrive drive;
	SendableChooser chooser;

	// Ck ck;
	NO_TOUCH no;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		camera = CameraServer.getInstance().startAutomaticCapture();
		sanic = new AnalogInput(1);
		automove = true;

		playStation = new PlayStationController(0);
		// drive = new robotDrive(playStation);
		// periphery = new modularPeripheries(playStation);
		camera.setResolution(640, 480);

		// ck = new Ck(playStation);
		no = new NO_TOUCH(playStation, camera);

		// gyro.calibrate();
		// gyro.reset();

		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("STR8 M8 8/8", autonomousPath1);
		chooser.addObject("Starting on the Right", autonomousPath2);
		chooser.addObject("Starting on the Left", autonomousPath3);
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
		automove = true;
		autoSelected = (String) chooser.getSelected();

		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	// @Override

	public void autonomousPeriodic() {

		// Need to go 9 feet and 4 inches

		// Inside of robot 5ft From Center Line LEFT
		// turn 65 degrees
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

		switch (autoSelected)
		// low key just like change the numbers in this format. (SPEED, TIME)
		// You're welcome CK. I love you buddy!!!
		// Also you Sean!!!! Good luck!!! I beliv e in you guys!!!!

		{
		// GO FOREWARD
		case autonomousPath1:
			if (automove == true) {
				no.autoraise();
				no.GoForewards(.18, 5);
				no.autodrop();
				// no.GoForewards(.18, 5); // perfect gear (.25,3)
				// automove = false
				// while (sanic.getAverageVoltage())
			}
				
			break;
		// LEFT SIDE
		case autonomousPath2:
			if (automove == true) {

				no.GoForewards(.25, 3.25);
				no.TurnLeft(.8, 1.3);
				if(!visionThreadActive){
				AutonomousGearAlign();
				}

			}
			automove = false;
			// RIGHT SIDE
		case autonomousPath3:
			if (automove == true) {
				no.GoForewards(.25, 3.25);
				no.TurnRight(.8, 1.3);
				if(!visionThreadActive){
					AutonomousGearAlign();
					}
				// no.GoForewards(.25, 1.7);
			}
			automove = false;
			break;
		}

	}
	
	int tryForImage = 0;
	public void AutonomousGearAlign() {
		visionThreadOnce = true;
		System.out.println("I have happened once");
		visionThread = new VisionThread(camera, new GearTargetRangerFinder(), pipeline -> {

			if (pipeline.getDistance() > 6 || tryForImage < 10) {
				tryForImage++;
				if (pipeline.getDistance() == 0) {
					return;
				}
				if (playStation.ButtonSquare() == true) {
					if (visionThreadActive) {
						System.out.println("Square button, stop gear align" + visionThreadActive);
						visionThreadActive = false;
						visionThread.interrupt();
					}
				}
				if (no.isInOperation()) {
					System.out.println("In operation do nothing");
					return;
				}
				
				System.out.println("Vision Sees stuff. Get good son distance = " + pipeline.getDistance() );
				if (pipeline.getDistance() > 3) {
					System.out.println("moving");
					no.auto(pipeline);
				}
				else if(pipeline.getDistance() < 3){
					//no.autodrop();
					System.out.println("drop!");
					no.stop();
					no.autodrop();
					automove = false;
					  
				}
//				if (pipeline.getDirectionToTurn() == GearTargetRangerFinder.DirectionToTurn.Left) {
//					no.TurnRight(.3, .1);
//					System.out.println("It works, turn to right");
//				} else if (pipeline.getDirectionToTurn() == GearTargetRangerFinder.DirectionToTurn.Right) {
//					no.TurnLeft(.3, .1);
//					System.out.println("It also works");
//				}
//
//				else if (pipeline.getDirectionToTurn() == GearTargetRangerFinder.DirectionToTurn.Straight) {
//					System.out.println("It doesnt work... hahahaha jk it actually does");
//
//					if (pipeline.getDistance() > 3) {
//						System.out.println("Go forwards");
//
//						no.GoForewards(.3, .1);
//				
//					}
//				}
				tryForImage=0;
			} else {
				System.out.println("Vision Sees Nothing. Gonna Stahp naohw");
				no.stop();
				no.autodrop();
				visionThread.interrupt();

			}

		});
		visionThread.start();
		visionThreadActive = true;

	}

	boolean visionThreadActive = false;
	boolean visionThreadOnce = false;
	
	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		super.teleopInit();
		visionThreadOnce = false;
		no.PnuematicsIsForward = false;
	}

	@Override
	public void disabledPeriodic() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
		if (visionThread != null) {
			visionThreadActive = false;
			visionThread.interrupt();
			visionThread = null;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		double timeBetweenPresses = 300;
		double oversample = 50;

		//c.setClosedLoopControl(true);
		c.start();
		
		double driver = 1;
		if (driver == 1) {
			if (playStation.ButtonTriangle() == true) {
				now = new Date();
				if (now.getTime() - LastPush.getTime() < oversample) {
					LastPush = now;
				} else if (now.getTime() - LastPush.getTime() > timeBetweenPresses) {
					no.ToggleState();
					LastPush = now;
				}

			}
			if (playStation.ButtonSquare() == true) {
				System.out.println("Square button, start gear align" + visionThreadActive);
				if (!visionThreadActive && !visionThreadOnce) {
					AutonomousGearAlign();
				}
			}
			// drive.arcadeDrive();
			// System.out.println(sanic.getAverageVoltage());
			no.pacMan();
			if (!visionThreadActive) {
				no.CkDrive();
			}
			no.rev();
			no.CkPeripheries();

		}

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		// no.CkTest();
	}
}
