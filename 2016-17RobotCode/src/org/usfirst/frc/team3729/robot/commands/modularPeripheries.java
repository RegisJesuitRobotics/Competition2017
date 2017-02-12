package org.usfirst.frc.team3729.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;

public class modularPeripheries {
	Relay EatMotor1;
	CANTalon ShootyMotor1, ShootyMotor2, LoadMotor;
	XboxControler _xbox;
	Servo GearNom;
	boolean eatIsRunning = false;
	boolean gearIsNommed = false;
	double deadzone = 0.2;

	public modularPeripheries(XboxControler xbox) {
		EatMotor1 = new Relay(0);
		GearNom = new Servo(0);
		// LoadMotor = new CANTalon(9);
		// ShootyMotor1 = new CANTalon(7);
		// ShootyMotor2 = new CANTalon(8);
		this._xbox = xbox;
	}

	public void climbingKiddo() {
		if (_xbox.GetY() == true) {

		} else if (_xbox.GetY() == true && _xbox.GetLeftTrigger() > deadzone) {

		}

	}

	public void GearNoms() {
		if (_xbox.GetB() == true) {
			GearNom.set(0.7);
			System.out.println("A");

		} else if (_xbox.GetB() == true && _xbox.GetLeftTrigger() > deadzone) {
			GearNom.set(-0.7);
			System.out.println("Y");
		}

	}

	public void onOffEating() {
		// A

		if (_xbox.GetA() == true) {
			eatIsRunning = true;
			System.out.println("IsRunning");

		} else if (_xbox.GetA() == true && _xbox.GetLeftTrigger() > deadzone) {
			eatIsRunning = false;
			System.out.println("IsntRunning");
		}
		// The motor setting thing
		// git good
		if (eatIsRunning == false) {
			EatMotor1.set(Relay.Value.kOff);
		}

		else if (eatIsRunning == true) {
			EatMotor1.set(Relay.Value.kReverse);

		}
	}

	// EATING DOODLES
	// public void onOffEating() {
	// // Push X to START
	// // Push Y to STOP
	//
	// if (_xbox.GetA() == true) {
	// eatIsRunning = true;
	// System.out.println("IsRunning");
	//
	// } else if (_xbox.GetA() == true) {
	// eatIsRunning = false;
	// System.out.println("IsntRunning");
	// }
	// // The motor setting thing
	// // git good
	// if (eatIsRunning == false) {
	// EatMotor1.set(Relay.Value.kOff);
	// }
	//
	// else if (eatIsRunning == true) {
	// EatMotor1.set(Relay.Value.kReverse);
	//
	// }
	// }

	// LOADING DOODLES
	// Make a timer so the motor goes 360 deg when you hit B

	// public void conscousLoading() {
	// if (_xbox.GetB() == true) {
	// //
	// Thread thread2 = new Thread() {
	// public void run() {
	// int timer = 0;
	// while (timer < 100) {
	// LoadMotor.set(0.5);
	// timer++;
	// Timer.delay(1);
	// }
	// }
	// };
	//
	// }
	// }

	// SHOOTING DOODLES
	// public void shootButton() {
	// if (_xbox.GetA() == true) {
	// ShootyMotor1.set(0.5);
	// ShootyMotor2.set(0.5);
	// } else {
	// // System.out.println("elf");
	// }
	// }

}