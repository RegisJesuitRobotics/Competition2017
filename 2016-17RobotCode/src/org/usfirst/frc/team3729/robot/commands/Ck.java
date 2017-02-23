package org.usfirst.frc.team3729.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class Ck {
	Relay clipMotor, brady;
	CANTalon RightFrontMotor, LeftFrontMotor, RightBackMotor, LeftBackMotor, intakeMotor, shooterMotor1, shooterMotor2,
			climberMotor;
	PlayStationController playStation;
	DriverStation driverStation;
//<<<<<<< HEAD
//<<<<<<< HEAD
	ADXRS450_Gyro gyro;
//=======
	//ADXRS450_Gyro gyro;
//=======
	// ADXRS450_Gyro gyro;
//>>>>>>> 5e196497e2dae7eb8124cb389dfbdca1e56da8a4
	boolean shootSequence = false;
//>>>>>>> d8f2cae2e2a0f7fe0f60d520b7c9ed4236c9caab
	double deadZone = 0.2;
	double leftMotorInput = 1;
	double rightMotorInput = 1;

	public Ck(PlayStationController playStation) {
		RightBackMotor = new CANTalon(4);
		RightFrontMotor = new CANTalon(3);
		LeftBackMotor = new CANTalon(1);
		LeftFrontMotor = new CANTalon(2);
		clipMotor = new Relay(0);
		brady = new Relay(1);
		intakeMotor = new CANTalon(8);
		shooterMotor1 = new CANTalon(5);
		shooterMotor2 = new CANTalon(6);
		climberMotor = new CANTalon(7);
//<<<<<<< HEAD
		//gyro = new ADXRS450_Gyro();
//=======
		// gyro = new ADXRS450_Gyro();
//>>>>>>> 5e196497e2dae7eb8124cb389dfbdca1e56da8a4
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
	double x = 1;
	public void CkPeripheries() {
		// intake
		
		if (playStation.ButtonR1() == true) {
			intakeMotor.set(-0.7);
		} else {
			intakeMotor.set(0);
		}
		// clip
		if (playStation.ButtonSquare() == true && x>0) {
			x= x-.01;
			System.out.println(x);
		}
		// shooter
		if (playStation.ButtonTriangle() == true && x<1) {
			x= x+.01;
			System.out.println(x);
		}
		// climber
		if (playStation.ButtonL1() == true) {
			climberMotor.set(-1);
		} else {
			climberMotor.set(0);
		}
		// shooter sequence
		if (playStation.ButtonX() == true) {
			
			boolean shootSequence = false;

			shooterMotor1.set(-0.6799999999999997);
			shooterMotor2.set(-0.6799999999999997);
//<<<<<<< HEADv 
			clipMotor.set(Relay.Value.kReverse);
			brady.set(Relay.Value.kForward);
			System.out.println("brady on");
		}
//			Thread thread3 = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					//Thread.sleep(2000);
//					clipMotor.set(Relay.Value.kReverse);
//				}
//			}, "Thread 3");
//			thread3.start();
			
		
		else {
			shooterMotor1.set(0);
			shooterMotor2.set(0);
			clipMotor.set(Relay.Value.kOff);
			brady.set(Relay.Value.kOff);
			System.out.println("brady off");
//=======
//			Thread thread3 = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					// Thread.sleep(2000);
//					clipMotor.set(Relay.Value.kReverse);
//				}
//			}, "Thread 3");
//			thread3.start();
//>>>>>>> 5e196497e2dae7eb8124cb389dfbdca1e56da8a4
		}
		
	}
}
