package org.usfirst.frc.team3729.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;

public class modularPeripheries {
	Relay LoadMotor;
	CANTalon ShootyMotor1, ShootyMotor2, EatMotor1, ClimbingKiddo;
	XboxControler _xbox;
	boolean eatIsRunning = false;
	boolean gearIsNommed = false;
	double deadzone = 0.2;

	public modularPeripheries(XboxControler xbox) {
		EatMotor1 = new CANTalon(8);
		LoadMotor = new Relay(0);
		ShootyMotor1 = new CANTalon(5);
		ShootyMotor2 = new CANTalon(6);
		ClimbingKiddo = new CANTalon(7);
		this._xbox = xbox;
	}
	
	//X SHOOOT
	//Y CLIMB
	//B LOAD
	//A EAT
	
	

	
	

	public void shootButton() {
		if (_xbox.GetX() == true) {
			ShootyMotor1.set(-0.5);
			ShootyMotor2.set(-0.5);
		} else {
			ShootyMotor1.set(0);
			ShootyMotor2.set(0);
		}
	}

	public void climbingKiddo() {
		if (_xbox.GetY() == true) {
			ClimbingKiddo.set(-1);
		} else {
			ClimbingKiddo.set(0);
		}

	}

	// public void GearNoms() {
	// if (_xbox.GetB() == true) {
	// GearNom.set(0.7);
	// System.out.println("A");
	//
	// } else if (_xbox.GetB() == true && _xbox.GetLeftTrigger() > deadzone) {
	// GearNom.set(-0.7);
	// System.out.println("Y");
	// }
	//
	// }

	public void LoadingKiddo() {
		// A

		if (_xbox.GetA() == true) {
			eatIsRunning = true;
			System.out.println("Loadg");

		} else {
			eatIsRunning = false;
			System.out.println("IsntRunning");
		}
		// The motor setting thing
		// git good
		if (eatIsRunning == false) {
			LoadMotor.set(Relay.Value.kOff);
		}

		else if (eatIsRunning == true) {
			LoadMotor.set(Relay.Value.kReverse);

		}
	}

	public void Noms() {
		if (_xbox.GetB() == true) {
			eatIsRunning = true;
			System.out.println("Loading");

		} else {
			eatIsRunning = false;
			System.out.println("IsntRunning");
		}
		// The motor setting thing
		// git good
		if (eatIsRunning == false) {
			EatMotor1.set(0);
		}

		else if (eatIsRunning == true) {
			EatMotor1.set(-0.6);

		}
	}

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

}