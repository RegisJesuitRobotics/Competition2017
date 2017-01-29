package org.usfirst.frc.team3729.robot.commands;

import com.ctre.*;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.*;

public class modularPeripheries {
	Relay EatMotor1, EatMotor2;
	CANTalon ShootyMotor1, ShootyMotor2, LoadMotor;
	XboxControler _xbox;

	public modularPeripheries(XboxControler xbox) {
		EatMotor1 = new Relay(0);
		EatMotor2 = new Relay(1);
		//LoadMotor = new CANTalon(9);
		//ShootyMotor1 = new CANTalon(7);
		//ShootyMotor2 = new CANTalon(8);
		this._xbox = xbox;
	}

	// EATING DOODLES
	public void onOffEating() {
		boolean isRunning = false;
		if (_xbox.GetY() == true && isRunning == true) {
			isRunning = false;
		}
		if (_xbox.GetA() == true && isRunning == false) {
			isRunning = true;
		}
		// The motor setting thing
		// git good
		if (isRunning == true) {
			EatMotor1.set(Relay.Value.kOff);
			EatMotor2.set(Relay.Value.kOff);
		} else if (isRunning == false) {
			EatMotor1.set(Relay.Value.kReverse);
			EatMotor2.set(Relay.Value.kReverse);
		}
	}

	// LOADING DOODLES
	// Make a timer so the motor goes 360 deg when you hit B
	public void conscousLoading() {
		if (_xbox.GetB() == true) {

			Thread thread2 = new Thread() {
				public void run() {
					int timer = 0;
					while (timer < 100) {
						LoadMotor.set(0.5);
						timer++;
						Timer.delay(1);
					}
				}
			};

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