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

	CANTalon RightMotor1, LeftMotor1, RightMotor2, LeftMotor2;
	XboxControler _xbox;
	DriverStation driverStation;
	ADXRS450_Gyro gyro;
	boolean isRight;
	double motorLimiterRatioinital = 0.8;
	double motorLimiterRatio = motorLimiterRatioinital;
	double deadZone = 0.2;
	double leftMotorInput = 1;
	double rightMotorInput = 1;

	public robotDrive(XboxControler xbox) {
		RightMotor2 = new CANTalon(4);
		RightMotor1 = new CANTalon(3);
		LeftMotor2 = new CANTalon(1);
		LeftMotor1 = new CANTalon(2);
		gyro = new ADXRS450_Gyro();
		this._xbox = xbox;

	}

	public void arcadeDrive() {

		// This limits the power of the motor, it is a percentage
		// This SHOULD NOT go above 1.0, not should it be negative
		double motorLimiterRatioinital = 0.7; // change to
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
		RightMotor1.set(rightMotorInput * motorLimiterRatio);
		LeftMotor1.set(-leftMotorInput * motorLimiterRatio);
		RightMotor2.set(rightMotorInput * motorLimiterRatio);
		LeftMotor2.set(-leftMotorInput * motorLimiterRatio);
		// System.out.println(leftMotorInput + "left");
		// System.out.println(rightMotorInput + "right");

	}

	public void mechenumDrive() {
		boolean leftBumper = _xbox.GetLeftBumper();
		boolean rightBumper = _xbox.GetRightBumper();
		double angle;

		// Maybe lower this
		double motorLimiterRatioinital = 0.8;

		// speed button
		if (_xbox.GetRightTrigger() > deadZone) {
			motorLimiterRatio = 0.4;
		} else {
			motorLimiterRatio = motorLimiterRatioinital;
		}

		if (leftBumper == true) {
			// currentHeading = gyro.getAngle();
			isRight = false;
			strafeStraight(gyro.getAngle(), isRight);

		} else if (rightBumper == true) {
			// currentHeading = gyro.getAngle();
			isRight = true;
			strafeStraight(gyro.getAngle(), isRight);
		}

	}

	private void strafeStraight(double currentHeading, boolean isRight) {
		double direction;
		double angle = gyro.getAngle();
		if (isRight == true) {
			direction = 1;
		} else {
			direction = -1;
		}

		if (angle >= currentHeading + .05) {
			RightMotor1.set(-rightMotorInput * motorLimiterRatio * direction);
			LeftMotor1.set(-leftMotorInput * motorLimiterRatio * .75 * direction);
			RightMotor2.set(rightMotorInput * motorLimiterRatio * direction);
			LeftMotor2.set(leftMotorInput * motorLimiterRatio * .75 * direction);
			System.out.println("right");
		} else if (angle <= currentHeading - .05) {
			System.out.println("left");
			RightMotor1.set(-rightMotorInput * motorLimiterRatio * .75 * direction);
			LeftMotor1.set(-leftMotorInput * motorLimiterRatio * direction);
			RightMotor2.set(rightMotorInput * motorLimiterRatio * .75 * direction);
			LeftMotor2.set(leftMotorInput * motorLimiterRatio * direction);
		} else {
			System.out.println("straight");
			RightMotor1.set(-rightMotorInput * motorLimiterRatio * direction);
			LeftMotor1.set(-leftMotorInput * motorLimiterRatio * direction);
			RightMotor2.set(rightMotorInput * motorLimiterRatio * direction);
			LeftMotor2.set(leftMotorInput * motorLimiterRatio * direction);
		}
	}

	private void driveStraight(double speed, double currentHeading) {

		double angle = gyro.getAngle();

		if (angle >= currentHeading + .05) {

			RightMotor1.set(speed);
			LeftMotor1.set(-speed * .75);
			RightMotor2.set(speed);
			LeftMotor2.set(-speed * .75);
			System.out.println("right");

		} else if (angle <= currentHeading - .05) {
			System.out.println("left");
			RightMotor1.set(speed * .75);
			LeftMotor1.set(-speed);
			RightMotor2.set(speed * .75);
			LeftMotor2.set(-speed);

		} else {
			System.out.println("straight");
			RightMotor1.set(speed);
			LeftMotor1.set(-speed);
			RightMotor2.set(speed);
			LeftMotor2.set(-speed);
		}
	}

	// AUTONOMOUS STUFF
	// IMPORTANT
	// WORDS
	// TEXT

	public void TurnAround() {
		gyro.reset();
		do {
			LeftMotor1.set(.5);
			LeftMotor2.set(.5);
			RightMotor1.set(-.5);
			RightMotor2.set(.5);
		} while (gyro.getAngle() <= 180 && driverStation.isAutonomous() == true);
		// leftMotorInput = turnInput;
		// rightMotorInput = -turnInput;
		// System.out.println("spin right")
	}

	public void StopAutonomous() {
		if (driverStation.isAutonomous()) {
			this.Stop();
		}
	}

	public void Stop() {
		LeftMotor1.set(-.2);
		LeftMotor2.set(-.2);
		RightMotor1.set(-.2);
		RightMotor2.set(-.2);
		Timer.delay(.1);
		LeftMotor1.set(0);
		LeftMotor2.set(0);
		RightMotor1.set(0);
		RightMotor2.set(0);
	}

}
