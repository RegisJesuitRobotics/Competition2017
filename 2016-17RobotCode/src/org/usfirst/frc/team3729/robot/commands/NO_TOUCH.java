package org.usfirst.frc.team3729.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NO_TOUCH {

	Relay clipMotor, brady;
	CANTalon RightFrontMotor, LeftFrontMotor, RightBackMotor, LeftBackMotor, intakeMotor, shooterMotor1, shooterMotor2,
			climberMotor;
	Talon test, test2, test3;

	PlayStationController playStation;
	DriverStation driverStation;

	NetworkTable networkTable;

	public NO_TOUCH(PlayStationController playStation) {

		networkTable = NetworkTable.getTable("GRIP/DOEET");

		RightFrontMotor = new CANTalon(4);
		RightBackMotor = new CANTalon(3);
		LeftBackMotor = new CANTalon(1);
		LeftFrontMotor = new CANTalon(2);
		clipMotor = new Relay(0);
		brady = new Relay(1);
		intakeMotor = new CANTalon(8);
		shooterMotor1 = new CANTalon(5);
		shooterMotor2 = new CANTalon(6);
		climberMotor = new CANTalon(7);
		test = new Talon(1);
		test2 = new Talon(2);
		test3 = new Talon(3);
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
		RightBackMotor.set(RightPower);
		LeftFrontMotor.set(-LeftPower);
		LeftBackMotor.set(-LeftPower);
	}

	public void rev() {
		if (playStation.ButtonX() == true) {
			shooterMotor1.set(-0.6799999999999997);
			shooterMotor2.set(-0.6799999999999997);
			brady.set(Relay.Value.kForward);
			test.set(1);
			test3.set(1);
			// System.out.println("RUUUUUUUUUUUUUUUUUU");
		} else {
			shooterMotor1.set(0);
			shooterMotor2.set(0);
			brady.set(Relay.Value.kOff);
			test.set(0);
			test3.set(0);
			// System.out.print("..");
		}
	}

	public void CkPeripheries() {
		// intake
		if (playStation.ButtonR1() == true) {
			intakeMotor.set(-0.7);
		} else {
			intakeMotor.set(0);
		}
		// shooter
		if (playStation.ButtonTriangle() == true) {
			shooterMotor1.set(-0.6799999999999997);
			shooterMotor2.set(-0.6799999999999997);
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
		// shoot sequence manual
		if (playStation.ButtonX() == true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			clipMotor.set(Relay.Value.kReverse);
			test2.set(1);
		} else {
			test2.set(0);
			shooterMotor1.set(0);
			shooterMotor2.set(0);
			clipMotor.set(Relay.Value.kOff);
		}

		// auto line-up vision
		if (playStation.ButtonSquare() == true) {
//			double[] taco;
//			taco = networkTable.getNumberArray("area");

			/*
			 * if (currentLocationX < desiredLocationX){
			 * RightFrontMotor.set(-1); RightBackMotor.set(-1);
			 * LeftFrontMotor.set(-1); LeftBackMotor.set(-1); } else if
			 * (currentLocationX > desiredLocationX) { RightFrontMotor.set(1);
			 * RightBackMotor.set(1); LeftFrontMotor.set(1);
			 * LeftBackMotor.set(1); } if (currentLocationY < desiredLocationY)
			 * { RightFrontMotor.set(1); RightBackMotor.set(-1);
			 * LeftFrontMotor.set(-1); LeftBackMotor.set(1); } else if
			 * (currentLocationY > desiredLocationY) { RightFrontMotor.set(-1);
			 * RightBackMotor.set(1); LeftFrontMotor.set(1);
			 * LeftBackMotor.set(-1); }
			 */
		}
	}

	public void GoForewards(double speed, double time) {
		RightBackMotor.set(speed);
		RightFrontMotor.set(speed);
		LeftBackMotor.set(-speed);
		LeftFrontMotor.set(-speed);
		System.out.println("Finished setting motors");
		Timer.delay(time);
		System.out.println("Finshed Delay 1");
		Stop();
		System.out.println("Stopped");
	}

	public void GoBackwards(double speed, double time) {
		RightBackMotor.set(-speed);
		RightFrontMotor.set(-speed);
		LeftBackMotor.set(speed);
		LeftFrontMotor.set(speed);
		Timer.delay(time);
		Stop();
	}

	public void TurnLeft(double speed, double time) {
		RightBackMotor.set(speed);
		RightFrontMotor.set(speed);
		LeftBackMotor.set(speed);
		LeftFrontMotor.set(speed);
		Timer.delay(time);
		Stop();
	}

	public void TurnRight(double speed, double time) {
		RightBackMotor.set(-speed);
		RightFrontMotor.set(-speed);
		LeftBackMotor.set(-speed);
		LeftFrontMotor.set(-speed);
		Timer.delay(time);
		Stop();
	}

	public void Stop() {
		RightBackMotor.set(0);
		RightFrontMotor.set(0);
		LeftBackMotor.set(0);
		LeftFrontMotor.set(0);
	}
}