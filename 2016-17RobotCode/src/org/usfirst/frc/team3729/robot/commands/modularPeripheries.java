package org.usfirst.frc.team3729.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;

public class modularPeripheries {
<<<<<<< HEAD
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
=======
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
>>>>>>> 64a81e60820cda08c974c90a257465557e5a1b62
		this._xbox = xbox;
	}
	
	//X SHOOOT
	//Y CLIMB
	//B LOAD
	//A EAT
	
<<<<<<< HEAD
=======
	

<<<<<<< HEAD
	// EATING DOODLES
	public void onOffEatingFeeding() {
		// Push X to START
		// Push Y to STOP

		if (_xbox.ButtonA() == true) {

			eatIsRunning = true;
			System.out.println("A");
=======
	
	

>>>>>>> e3f7b4cb8261247119e8acce3bd75d1f6f46fc24
	public void shootButton() {
		if (_xbox.GetX() == true) {
			ShootyMotor1.set(-0.5);
			ShootyMotor2.set(-0.5);
		} else {
			ShootyMotor1.set(0);
			ShootyMotor2.set(0);
		}
	}
>>>>>>> 64a81e60820cda08c974c90a257465557e5a1b62

	public void climbingKiddo() {
		if (_xbox.GetY() == true) {
			ClimbingKiddo.set(-1);
		} else {
			ClimbingKiddo.set(0);
		}
<<<<<<< HEAD
		
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
=======

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
>>>>>>> 64a81e60820cda08c974c90a257465557e5a1b62
			System.out.println("IsntRunning");
		}
		// The motor setting thing
		// git good
<<<<<<< HEAD
		if (feedIsRunning == false) {
			feedMotor1.set(Relay.Value.kOff);
		}

		else if (feedIsRunning == true) {
			feedMotor1.set(Relay.Value.kReverse);
=======
		if (eatIsRunning == false) {
			EatMotor1.set(0);
		}

		else if (eatIsRunning == true) {
			EatMotor1.set(-0.6);
>>>>>>> 64a81e60820cda08c974c90a257465557e5a1b62

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
		
		if (leftBumper == true){
			shoot = shoot + 0.05;
			System.out.println(shoot);
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