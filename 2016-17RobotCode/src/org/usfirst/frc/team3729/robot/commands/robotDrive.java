package org.usfirst.frc.team3729.robot.commands;

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

	CANTalon RightMotorFront, LeftMotorFront, RightMotorBack, LeftMotorBack;
	PlayStationController playStation;
	DriverStation driverStation;
	ADXRS450_Gyro gyro;
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

		gyro = new ADXRS450_Gyro();
		this.playStation = playStation;
	}

	public void arcadeDrive() {

		double rightTrigger = playStation.RightTrigger();
		double leftTrigger = playStation.LeftTrigger();
		double RightStick = playStation.RightStickXAxis();
		double RightPower;
		double LeftPower;
		double Power;
		double turn = 2 * RightStick;

		// LOGIC
		Power = rightTrigger - leftTrigger;
//		if (RightStick > deadZone) {
//
//			RightPower = Power - (turn * Power);
//			LeftPower = Power;
//		} else if (RightStick < -deadZone) {
//
//			LeftPower = Power + (turn * Power);
//			RightPower = Power;
//		} else {
//			LeftPower = Power;
//			RightPower = Power;
//		}
		// MOTOR SETTING

//		RightMotorFront.set(RightPower);
//		RightMotorBack.set(RightPower);
//		LeftMotorFront.set(-LeftPower);
//		LeftMotorBack.set(-LeftPower);
		
		DriveRobot(Power, RightStick);
	}

	private void DriveRobot(double power, double turnInput) {
		// Only move the robot if there is turn input (spin), power input
		// (straight drive),
		// or both (turning).
		if (turnInput > deadZone || turnInput < -deadZone || power > deadZone || power < -deadZone) {
			if (power > deadZone && turnInput > deadZone) {
				leftMotorInput = power;
				rightMotorInput = power * insideTurnSpeedReduction * -turnInput;
				System.out.println("turn forward right");
				// Turn Forward Right
			} else if (turnInput > deadZone && IsValueInDeadZone(power)) {
				leftMotorInput = turnInput;
				rightMotorInput = -turnInput;
				System.out.println("spin right");
				// Spin Right
			} else if (power > deadZone && IsValueInDeadZone(turnInput)) {
				leftMotorInput = power;
				rightMotorInput = -power;
				System.out.println("Forward");
				// Move Forward
			} else if (power > deadZone && turnInput < -deadZone) {
				// Left input is negative, so it must be negated to move
				// forward.
				leftMotorInput = power * insideTurnSpeedReduction * turnInput;
				rightMotorInput = -power;
				System.out.println("turn forward left");
				// Turn Forwards Left
			} else if (turnInput < deadZone && IsValueInDeadZone(power)) {
				// Left motor should move in reverse, right should move forward.
				// Left turn is a negative input already, so we don't need to
				// negate
				// it again.
				leftMotorInput = -turnInput;
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
				leftMotorInput = -power;
				rightMotorInput = power;
				System.out.println("move backwards");
				// Move Backwards
			}
		}
	}

	private boolean IsValueInDeadZone(double value) {
		return value <= deadZone && value >= -deadZone;
	}

}
