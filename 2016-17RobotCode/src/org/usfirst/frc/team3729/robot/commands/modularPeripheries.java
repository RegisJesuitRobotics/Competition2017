package org.usfirst.frc.team3729.robot.commands;

import com.ctre.*;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.*;

public class modularPeripheries {
	Relay EatMotor1, EatMotor2, LoadMotor;
	CANTalon ShootyMotor1, ShootyMotor2;
	XboxControler _xbox;

	public modularPeripheries(XboxControler xbox) {
		EatMotor1 = new Relay(5);
		EatMotor2 = new Relay(6);
		LoadMotor = new Relay (9);
		ShootyMotor1 = new CANTalon(7);
		ShootyMotor2 = new CANTalon(8);
		this._xbox = xbox;
	}

	// EATING DOODLES
	public void mindlessEating() {
		// Constant eat motor running
		EatMotor1.set(Relay.Value.kOn);
		EatMotor2.set(Relay.Value.kOn);
	}

	public void consciousEating() {
		// Eat motor on button press
		double deadZone = 0.2;
		if (_xbox.GetLeftTrigger() > deadZone) {
			EatMotor1.set(Relay.Value.kOn);
			EatMotor2.set(Relay.Value.kOn);
		} else {
			EatMotor1.set(Relay.Value.kOff);
			EatMotor2.set(Relay.Value.kOff);
		}
	}

	public void onOffEating() {
		boolean isRunning = true;
		if (_xbox.GetY() == true && isRunning == true) {
			isRunning = false;
		}
		if (_xbox.GetY() == true && isRunning == false) {
			isRunning = true;
		}
		// The motor setting thing
		// git good
		if (isRunning == true) {
			EatMotor1.set(Relay.Value.kOn);
			EatMotor2.set(Relay.Value.kOn);
		} else if (isRunning == false) {
			EatMotor1.set(Relay.Value.kOff);
			EatMotor2.set(Relay.Value.kOff);
		}
	}
	
	
	
	//LOADING DOODLES
		public void onOffLoading() {
			boolean isRunning = true;
			if (_xbox.GetX() == true && isRunning == true) {
				isRunning = false;
			}
			if (_xbox.GetX() == true && isRunning == false) {
				isRunning = true;
			}
			if (isRunning == true) {
				LoadMotor.set(Relay.Value.kOn);
			} else if (isRunning == false) {
				LoadMotor.set(Relay.Value.kOff);
			}
		}
		
		public void conscousLoading(){
			if(_xbox.GetX() == true){
				LoadMotor.set(Relay.Value.kOn);
			} else {
				LoadMotor.set(Relay.Value.kOff);
			}
		}

	
	// SHOOTING DOODLES
	public void shootButton() {
		if (_xbox.GetA() == true) {
			ShootyMotor1.set(0.5);
			ShootyMotor2.set(0.5);
		} else {
			// System.out.println("elf");
		}
	}

}