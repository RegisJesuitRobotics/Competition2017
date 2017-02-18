package org.usfirst.frc.team3729.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Relay;

public class modularPeripheries {
	Relay feedMotor1;
	CANTalon ShootyMotor1, ShootyMotor2, eatMotor , climberMotor;
	XboxControler _xbox;
	boolean feedIsRunning = false;
	boolean	eatIsRunning = false;

	public modularPeripheries(XboxControler xbox) {
		feedMotor1 = new Relay(0);
		 eatMotor = new CANTalon(8);
		 ShootyMotor1 = new CANTalon(5);
		 ShootyMotor2 = new CANTalon(6);
		 climberMotor = new CANTalon(7);
		this._xbox = xbox;
	}

	// EATING DOODLES
	public void onOffEatingFeeding() {
		// Push X to START
		// Push Y to STOP

		if (_xbox.ButtonA() == true) {

			eatIsRunning = true;
			System.out.println("A");

		}
		
		if (_xbox.ButtonY() == true) {

			eatIsRunning = false;
			System.out.println("BA");

		}
		
		if (_xbox.ButtonX() == true) {
			feedIsRunning = true;
			System.out.println("IsRunning");

		}
		if (_xbox.ButtonB() == true) {
			feedIsRunning = false;
			System.out.println("IsntRunning");
		}
		// The motor setting thing
		// git good
		if (feedIsRunning == false) {
			feedMotor1.set(Relay.Value.kOff);
		}

		else if (feedIsRunning == true) {
			feedMotor1.set(Relay.Value.kReverse);

		}
		
		if (eatIsRunning==true) {
			
			eatMotor.set(-.7);
			
		}
		
		else if (eatIsRunning==false) {
			
			eatMotor.set(0);
			
		}
	}
	
	public void climber(){
		
		if (_xbox.LeftBumper() == true) {
			
			climberMotor.set(-1);
		}
		
		else{
			climberMotor.set(0);
		}
	}
	
	public void shooter() {
		boolean leftBumper = _xbox.LeftBumper();
		boolean rightBumper = _xbox.RightBumper();
		double shoot = -1;
		if (rightBumper == true) {
	
			ShootyMotor1.set(-.95);
			ShootyMotor2.set(-.95);
			
		}
		
		else {
			ShootyMotor1.set(0);
			ShootyMotor2.set(0);
		}
		
		/*if (leftBumper == true){
			shoot = shoot + 0.05;
			System.out.println(shoot);*/
		}
		
	}

	// LOADING DOODLES
	// Make a timer so the motor goes 360 deg when you hit B

	// public void conscousLoading() {
	// if (_xbox.GetB() == true) {
	//
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
	
