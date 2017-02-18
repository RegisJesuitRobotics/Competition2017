package org.usfirst.frc.team3729.robot.commands;

import edu.wpi.first.wpilibj.Joystick;

public class XboxControler extends Joystick {
	public XboxControler(int port) {
		super(port);
	}

<<<<<<< HEAD
	public double RightStickX() {
		return super.getRawAxis(4);
=======
	public double GetLeftStick() {
		return -super.getRawAxis(1);
>>>>>>> 64a81e60820cda08c974c90a257465557e5a1b62
	}
	/**
	 * Returns left or right. Left is negative, right is positive.
	 * 
	 * @return
	 */
<<<<<<< HEAD
	public double LeftTrigger() {
		return super.getRawAxis(2);
=======
	public double GetRightStick() {
		return super.getRawAxis(4);
>>>>>>> 64a81e60820cda08c974c90a257465557e5a1b62
	}
	
	public double RightTrigger() {

		return super.getRawAxis(3);
	}
<<<<<<< HEAD
	
	public double LeftStick() {
		return super.getRawAxis(4);
	}

	
=======

	public boolean GetA() {
		return super.getRawButton(1);
	}
>>>>>>> 64a81e60820cda08c974c90a257465557e5a1b62

	public boolean ButtonA() {
		return super.getRawButton(2);
	}
<<<<<<< HEAD
	
	public boolean ButtonY() {
		return super.getRawButton(4);
	}
	
	public boolean ButtonB() {
		return super.getRawButton(3);
	}
	
	public boolean ButtonX() {
		return super.getRawButton(1);
=======

	public boolean GetX() {
		return super.getRawButton(3);
	}

	public boolean GetY() {
		return super.getRawButton(4);
>>>>>>> 64a81e60820cda08c974c90a257465557e5a1b62
	}

	public boolean LeftBumper() {
		return super.getRawButton(5);
	}

	public boolean RightBumper() {
		return super.getRawButton(6);
	}

	public boolean ButtonBack() {
		return super.getRawButton(7);
	}

	public boolean ButtonStart() {
		return super.getRawButton(8);
	}

	

	public double GetPOV() {
		if ((super.getPOV(0) > 0) && (super.getPOV(0) < 180)) {
			return 1;
		}
		if ((super.getPOV(0) > 180) && (super.getPOV(0) < 360)) {
			return 0;
		} else {
			return -1;
		}
	}
}
