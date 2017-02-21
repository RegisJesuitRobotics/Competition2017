package org.usfirst.frc.team3729.robot.commands;

import java.util.Date;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Timer;

public class AutoMethods {
	Relay feedMotor1;
	ADXRS450_Gyro gyro;
	CANTalon ShootyMotor1, ShootyMotor2, SweeperMotor, climberMotor, RightMotorFront, LeftMotorFront, RightMotorBack,
			LeftMotorBack;
	boolean ClimbingIsRunning = false;
	boolean SweeperIsRunning = false;
	Date now = new Date();
	Date LastPush = new Date();

	public AutoMethods() {
		feedMotor1 = new Relay(0);
		SweeperMotor = new CANTalon(8);
		ShootyMotor1 = new CANTalon(5);
		ShootyMotor2 = new CANTalon(6);
		climberMotor = new CANTalon(7);
		RightMotorBack = new CANTalon(4);
		RightMotorFront = new CANTalon(3);
		LeftMotorBack = new CANTalon(1);
		LeftMotorFront = new CANTalon(2);

		gyro = new ADXRS450_Gyro();

	}

	public void GoForewards(double speed, double time) {
		RightMotorBack.set(speed);
		RightMotorFront.set(speed);
		LeftMotorBack.set(-speed);
		LeftMotorFront.set(-speed);
		Timer.delay(time);
		Stop();
	}

	public void GoBackwards(double speed, double time) {
		RightMotorBack.set(-speed);
		RightMotorFront.set(-speed);
		LeftMotorBack.set(speed);
		LeftMotorFront.set(speed);
		Timer.delay(time);
		Stop();
	}

	public void TurnLeft(double speed, double time) {
		RightMotorBack.set(speed);
		RightMotorFront.set(speed);
		LeftMotorBack.set(speed);
		LeftMotorFront.set(speed);
		Timer.delay(time);
		Stop();
	}

	public void TurnRight(double speed, double time) {
		RightMotorBack.set(-speed);
		RightMotorFront.set(-speed);
		LeftMotorBack.set(-speed);
		LeftMotorFront.set(-speed);
		Timer.delay(time);
		Stop();
	}

	public void Stop() {
		RightMotorBack.set(0);
		RightMotorFront.set(0);
		LeftMotorBack.set(0);
		LeftMotorFront.set(0);

	}

}
