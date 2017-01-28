package org.usfirst.frc.team3729.robot.commands;

import edu.wpi.first.wpilibj.Talon;

public class modularPeripheries {
	Talon EatMotor1, EatMotor2, ShootyMotor1,ShootyMotor2;
	XboxControler _xbox;

	public modularPeripheries(XboxControler xbox) {
		EatMotor1 = new Talon(5);
		EatMotor2 = new Talon(6);
		ShootyMotor1 = new Talon(7);
		ShootyMotor2 = new Talon(8);
		this._xbox = xbox;
	}

	
	//EATING DOODLES
	public void mindlessEating() {
		// Constant eat motor running
		EatMotor1.set(0.5);
		EatMotor2.set(0.5);
	}

	public void consciousEating() {
		// Eat motor on button press
		double deadZone = 0.2;
		if (_xbox.GetLeftTrigger() > deadZone) {

		}
	}

	public void onOffEating() {
		boolean running = true;
		if (_xbox.GetY() == true && running == true) {
			running = false;
		}
		if (_xbox.GetY() == true && running == false) {
			running = true;
		}
		// The motor setting thing
		//git good
		if (running == true) {
			EatMotor1.set(0.5);
			EatMotor2.set(0.5);
		} else if (running == false) {
			EatMotor1.set(0.5);
			EatMotor2.set(0.5);
		}

	}
	
	
	
	
	//SHOOTING DOODLES
	public void shootButton() {
		if (_xbox.GetA() == true) {
			ShootyMotor1.set(0.5);
			ShootyMotor2.set(0.5);
		} else {
			//System.out.println("elf");
		}
	}
	

}