package org.usfirst.frc.team3729.robot.commands;

import java.util.Date;

import org.usfirst.frc.team3729.robot.commands.PlaystationController;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;;

public class TShirtControl {
	CANTalon L1, L2, L3, R1, R2, R3, TurningMotor, ElevationMotorLeft, ElevationMotorRight;
	PlaystationController playStation;
	DriverStation driverStation;
	Solenoid[] Barrel;
	int ActiveBarrel;
	double timeBetweenR1Presses = 1;
	Date now = new Date();
	Date LastPush = new Date();

	public TShirtControl(PlaystationController playStation) {
		// Movement Motors. R=Right,L=Left, Number = Distance from front.
		L1 = new CANTalon(4);
		L2 = new CANTalon(3);
		R1 = new CANTalon(2);
		R2 = new CANTalon(1);

		// Cannon Stuff
		TurningMotor = new CANTalon(0);
		ElevationMotorLeft = new CANTalon(0);
		ElevationMotorRight = new CANTalon(0);

		// Solenoid Stuff
		Barrel = new Solenoid[5];
		Barrel[0] = new Solenoid(0);
		Barrel[1] = new Solenoid(0);
		Barrel[2] = new Solenoid(0);
		Barrel[3] = new Solenoid(0);
		Barrel[4] = new Solenoid(0);
		Barrel[5] = new Solenoid(0);
		ActiveBarrel = 0;

		// Code Stuff
		this.playStation = playStation;
	}

	public void TShirtDrive() {
		double RightTrigger = playStation.RightTrigger();
		double LeftTrigger = playStation.LeftTrigger();
		double LeftStick = playStation.LeftStickXAxis();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double turn = 2 * LeftStick;
		Power = RightTrigger - LeftTrigger;
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

		R1.set(-RightPower);
		R2.set(-RightPower);

		L1.set(LeftPower);
		L2.set(LeftPower);

	}

	public void CanonMovement() {
		// Horizontal Movement
		if (playStation.ButtonSquare() == true) {
			TurningMotor.set(0.5);

		} else if (playStation.ButtonTriangle() == true) {
			TurningMotor.set(-0.5);

		} else {
			TurningMotor.set(0);
		}

		// Vertical Movement
		if (playStation.ButtonX() == true) {
			ElevationMotorLeft.set(0.5);
			ElevationMotorRight.set(0.5);

		} else if (playStation.ButtonCircle() == true) {
			ElevationMotorLeft.set(-0.5);
			ElevationMotorRight.set(-0.5);

		} else {
			ElevationMotorLeft.set(0);
			ElevationMotorRight.set(0);
		}
	}

	// THIS CODE MAKES SURE THERE'S ONE SECOND BETWEEN SHOTS AND THEN SHOOTS
	public void ShootTimerAndShoot() {
		if (playStation.ButtonR1() == true) {
			now = new Date();
			if (now.getTime() - LastPush.getTime() > timeBetweenR1Presses) {
				Shoot();
			}
			LastPush = now;
		}
	}

	// DONT PUT THIS CODE IN THE ROBOT.JAVA, ITS ALLREADY CALLED BY
	// SHOOTTIMERANDSHOOT!!!!!! >:(
	public void Shoot() {
		Barrel[ActiveBarrel].set(true);
		ActiveBarrel = ActiveBarrel + 1;

		if (ActiveBarrel == 6) {
			ActiveBarrel = 0;
		}
	}

}
