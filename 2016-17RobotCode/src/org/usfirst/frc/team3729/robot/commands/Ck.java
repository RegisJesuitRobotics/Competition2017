package org.usfirst.frc.team3729.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class Ck {
	Relay clipMotor;
	CANTalon RightFrontMotor, LeftFrontMotor, RightMotorBack, LeftMotorBack, intakeMotor, shooterMotor1, shooterMotor2,
			climberMotor;
	PlayStationController playStation;
	DriverStation driverStation;
	// ADXRS450_Gyro gyro;
	boolean shootSequence = false;
	double deadZone = 0.2;
	double leftMotorInput = 1;
	double rightMotorInput = 1;

	public Ck(PlayStationController playStation) {
		RightMotorBack = new CANTalon(4);
		RightFrontMotor = new CANTalon(3);
		LeftMotorBack = new CANTalon(1);
		LeftFrontMotor = new CANTalon(2);
		clipMotor = new Relay(0);
		intakeMotor = new CANTalon(8);
		shooterMotor1 = new CANTalon(5);
		shooterMotor2 = new CANTalon(6);
		climberMotor = new CANTalon(7);
		// gyro = new ADXRS450_Gyro();
		this.playStation = playStation;

	}

	public void CkDrive() {

		double R2 = playStation.RightTrigger();
		double L2 = playStation.LeftTrigger();
		double LeftStick = playStation.LeftStickXAxis();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double turn = 2 * LeftStick;
		Power = R2 - L2;
		if (LeftStick > Deadzone) {

			RightPower = Power - (turn * Power);
			LeftPower = Power;
		} else if (LeftStick < -Deadzone) {

			LeftPower = Power + (turn * Power);
			RightPower = Power;
		} else {
			LeftPower = Power;
			RightPower = Power;
		}

		RightFrontMotor.set(RightPower);
		RightMotorBack.set(RightPower);
		LeftFrontMotor.set(-LeftPower);
		LeftMotorBack.set(-LeftPower);
	}

	public void CkPeripheries() {
		// intake
		if (playStation.ButtonR1() == true) {
			intakeMotor.set(-0.8);
		} else {
			intakeMotor.set(0);
		}
		// clip
		if (playStation.ButtonSquare() == true) {
			clipMotor.set(Relay.Value.kForward);
		} else {
			clipMotor.set(Relay.Value.kOff);
		}
		// shooter
		if (playStation.ButtonTriangle() == true) {
			shooterMotor1.set(-0.95);
			shooterMotor2.set(-0.95);
		} else {
			shooterMotor1.set(0);
			shooterMotor2.set(0);
		}
		// climber
		if (playStation.ButtonL1() == true) {
			climberMotor.set(-1);
		} else {
			climberMotor.set(0);
		}
		// shooter sequence
		if (playStation.ButtonX() == true) {
			shooterMotor1.set(-0.95);
			shooterMotor2.set(-0.95);
			Thread thread3 = new Thread(new Runnable() {
				@Override
				public void run() {
					// Thread.sleep(2000);
					clipMotor.set(Relay.Value.kReverse);
				}
			}, "Thread 3");
			thread3.start();
		}

	}

	public void GoForewards(double speed, double time) {
		RightMotorBack.set(speed);
		RightFrontMotor.set(speed);
		LeftMotorBack.set(-speed);
		LeftFrontMotor.set(-speed);
		System.out.println("Finished setting motors");
		Timer.delay(time);
		System.out.println("Finshed Delay 1");
		Stop();
		System.out.println("Stopped");
		Timer.delay(15);
	}

	public void GoBackwards(double speed, double time) {
		RightMotorBack.set(-speed);
		RightFrontMotor.set(-speed);
		LeftMotorBack.set(speed);
		LeftFrontMotor.set(speed);
		Timer.delay(time);
		Stop();
	}

	public void TurnLeft(double speed, double time) {
		RightMotorBack.set(speed);
		RightFrontMotor.set(speed);
		LeftMotorBack.set(speed);
		LeftFrontMotor.set(speed);
		Timer.delay(time);
		Stop();
	}

	public void TurnRight(double speed, double time) {
		RightMotorBack.set(-speed);
		RightFrontMotor.set(-speed);
		LeftMotorBack.set(-speed);
		LeftFrontMotor.set(-speed);
		Timer.delay(time);
		Stop();
	}

	public void Stop() {
		RightMotorBack.set(0);
		RightFrontMotor.set(0);
		LeftMotorBack.set(0);
		LeftFrontMotor.set(0);

	}

}
