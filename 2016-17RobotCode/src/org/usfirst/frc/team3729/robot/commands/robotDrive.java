package org.usfirst.frc.team3729.robot.commands;

<<<<<<< HEAD
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;

public class robotDrive {

	public CANTalon RightMotorFront, LeftMotorFront, RightMotorBack, LeftMotorBack;
	PlayStationController playStation;
	DriverStation driverStation;
	// ADXRS450_Gyro gyro;
	boolean isRight;
	double motorLimiterRatioinital = 0.8;
	double motorLimiterRatio = motorLimiterRatioinital;
	double deadZone = 0.2;
	double leftMotorInput = 1;
	double rightMotorInput = 1;
	double insideTurnSpeedReduction = .5;// This slows the motors on the inside
											// of a turn.

	public robotDrive(PlayStationController playStation) {
		RightMotorBack = new CANTalon(4);
		RightMotorFront = new CANTalon(3);
		LeftMotorBack = new CANTalon(1);
		LeftMotorFront = new CANTalon(2);

		// gyro = new ADXRS450_Gyro();
		this.playStation = playStation;
	}

	public void arcadeDrive() {

		System.out.println("Garrison Test");
		double rightTrigger = playStation.RightTrigger();
		double leftTrigger = playStation.LeftTrigger();
		double RightStick = playStation.RightStickXAxis();
		double RightPower;
		double LeftPower;
		double Power;
		double turn = 2 * RightStick;

		// LOGIC
		Power = rightTrigger - leftTrigger;
		System.out.println("power:" + Power);
		// if (RightStick > deadZone) {
		//
		// RightPower = Power - (turn * Power);
		// LeftPower = Power;
		// } else if (RightStick < -deadZone) {
		//
		// LeftPower = Power + (turn * Power);
		// RightPower = Power;
		// } else {
		// LeftPower = Power;
		// RightPower = Power;
		// }
		// MOTOR SETTING

		// RightMotorFront.set(RightPower);
		// RightMotorBack.set(RightPower);
		// LeftMotorFront.set(-LeftPower);
		// LeftMotorBack.set(-LeftPower);

		DriveRobot(Power, RightStick);
	}

	private void DriveRobot(double power, double turnInput) {
		// Only move the robot if there is turn input (spin), power input
		// (straight drive),
		// or both (turning).
		if (turnInput > deadZone || turnInput < -deadZone || power > deadZone || power < -deadZone) {
			if (power > deadZone && turnInput > deadZone) {
				leftMotorInput = power;
				rightMotorInput = power * insideTurnSpeedReduction * turnInput;
				System.out.println("turn forward right");
				// Turn Forward Right
			} else if (turnInput > deadZone && IsValueInDeadZone(power)) {
				leftMotorInput = turnInput;
				rightMotorInput = turnInput;
				System.out.println("spin right");
				// Spin Right
			} else if (power > deadZone && IsValueInDeadZone(turnInput)) {
				leftMotorInput = power;
				rightMotorInput = power;
				System.out.println("Forward");
				// Move Forward
			} else if (power > deadZone && turnInput < -deadZone) {
				// Left input is negative, so it must be negated to move
				// forward.
				leftMotorInput = power * insideTurnSpeedReduction * turnInput;
				rightMotorInput = power;
				System.out.println("turn forward left");
				// Turn Forwards Left
			} else if (turnInput < deadZone && IsValueInDeadZone(power)) {
				// Left motor should move in reverse, right should move forward.
				// Left turn is a negative input already, so we don't need to
				// negate
				// it again.
				leftMotorInput = turnInput;
				rightMotorInput = turnInput;
				System.out.println("spin left");
				// Spin Left
				// } else if (forwardInput < -deadZone && turnInput < -deadZone)
				// {
				// leftMotorInput = -turnInput * .1;
				// rightMotorInput = forwardInput * 1.25;
				// System.out.println("turn backwards left");
				// // Turn Backwards Left
				// } else if (forwardInput < -deadZone && turnInput > deadZone)
				// {
				// leftMotorInput = forwardInput * 1.25;
				// rightMotorInput = -turnInput * .1;
				// System.out.println("turn backwards right");
				// // Turn Backwards Right
			} else if (power < -deadZone && IsValueInDeadZone(turnInput)) {
				leftMotorInput = power;
				rightMotorInput = power;
				System.out.println("move backwards");
				// Move Backwards
			}

			RightMotorFront.set(rightMotorInput);
			RightMotorBack.set(rightMotorInput);
			LeftMotorFront.set(-leftMotorInput);
			LeftMotorBack.set(-leftMotorInput);
		}
	}

	private boolean IsValueInDeadZone(double value) {
		return value <= deadZone && value >= -deadZone;
	}

=======
import edu.wpi.first.wpilibj.Talon;

public class robotDrive {
	Talon RightMotor1, LeftMotor1, RightMotor2, LeftMotor2;
	XboxControler _xbox;

	public robotDrive(XboxControler xbox) {
		RightMotor1 = new Talon(1);
		RightMotor2 = new Talon(0);
		LeftMotor1 = new Talon(2);
		LeftMotor2 = new Talon(3);
		this._xbox = xbox;
	}

	public void arcadeDrive() {

		// This limits the power of the motor, it is a percentage
		// This SHOULD NOT go above 1.0, not should it be negative
		double motorLimiterRatioinital = 1; // change to
		double motorLimiterRatio = motorLimiterRatioinital;
		double forwardInput = _xbox.GetForwardInput();
		double turnInput = _xbox.GetTurnInput();
		double leftMotorInput = 0;
		double rightMotorInput = 0;
		double deadZone = 0.2;

		// System.out.println(forwardInput);
		// System.out.println(turnInput + "turn");

		if (forwardInput > deadZone && turnInput > deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = turnInput * .25;
			System.out.println("turn forward right");
			// Turn Forward Right
		} else if (forwardInput <= deadZone && forwardInput >= -deadZone && turnInput > deadZone) {
			leftMotorInput = turnInput;
			rightMotorInput = -turnInput;
			System.out.println("spin right");
			// Spin Right
		} else if (forwardInput > deadZone && turnInput <= deadZone && turnInput >= -deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = forwardInput;
			System.out.println("Forward");
			// Move Forward
		} else if (forwardInput > deadZone && turnInput < deadZone) {
			// Left input is negative, so it must be negated to move forward.
			leftMotorInput = -turnInput * .25;
			rightMotorInput = forwardInput;
			System.out.println("turn forward left");
			// Turn Forwards Left
		} else if (forwardInput <= deadZone && forwardInput >= -deadZone && turnInput < -deadZone) {
			// Left motor should move in reverse, right should move forward.
			// Left turn is a negative input already, so we don't need to negate
			// it again.
			leftMotorInput = turnInput;
			rightMotorInput = -turnInput;
			System.out.println("spin left");
			// Spin Left
		} else if (forwardInput < -deadZone && turnInput < -deadZone) {
			leftMotorInput = turnInput * .25;
			rightMotorInput = forwardInput;
			System.out.println("turn backwards left");
			// Turn Backwards Left
		} else if (forwardInput < -deadZone && turnInput > deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = -turnInput * .25;
			System.out.println("turn backwards right");
			// Turn Backwards Right
		} else if (forwardInput < -deadZone) {
			leftMotorInput = forwardInput;
			rightMotorInput = forwardInput;
			System.out.println("move backwards");
			// Move Backwards
		}
		// Speed Switch
		if (_xbox.GetRightTrigger() > deadZone) {
			motorLimiterRatio = (_xbox.GetRightTrigger() * 0.5);
		} else {
			motorLimiterRatio = motorLimiterRatioinital;
		}
		RightMotor1.set(-rightMotorInput * motorLimiterRatio);
		LeftMotor1.set(leftMotorInput * motorLimiterRatio);
		RightMotor2.set(-rightMotorInput * motorLimiterRatio);
		LeftMotor2.set(leftMotorInput * motorLimiterRatio);
		// System.out.println(leftMotorInput + "left");
		// System.out.println(rightMotorInput + "right");
	}
	
	
	
	
	
	
	

	public void mechenumDrive() {
		boolean leftInput = _xbox.GetLeftBumper();
		boolean rightInput = _xbox.GetRightBumper();

		// Maybe lower this
		double motorLimiterRatioinital = 0.8;

		double motorLimiterRatio = motorLimiterRatioinital;
		double deadZone = 0.2;
		double leftMotorInput = 1;
		double rightMotorInput = 1;

		// speed button
		if (_xbox.GetRightTrigger() > deadZone) {
			motorLimiterRatio = 0.4;
		} else {
			motorLimiterRatio = motorLimiterRatioinital;
		}

		if (leftInput == true) {
			RightMotor1.set(rightMotorInput * motorLimiterRatio);
			LeftMotor1.set(leftMotorInput * motorLimiterRatio);
			RightMotor2.set(-rightMotorInput * motorLimiterRatio);
			LeftMotor2.set(-leftMotorInput * motorLimiterRatio);

		} else if (rightInput == true) {

			RightMotor1.set(-rightMotorInput * motorLimiterRatio);
			LeftMotor1.set(-leftMotorInput * motorLimiterRatio);
			RightMotor2.set(rightMotorInput * motorLimiterRatio);
			LeftMotor2.set(leftMotorInput * motorLimiterRatio);
		}

	}
>>>>>>> branch 'master' of https://github.com/RegisJesuitRobotics/Competition2017.git
}
