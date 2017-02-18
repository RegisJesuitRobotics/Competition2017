package org.usfirst.frc.team3729.robot.commands;

import edu.wpi.first.wpilibj.Joystick;

public class XboxControler extends Joystick {
	public XboxControler(int port) {
		super(port);
	}

	public double RightStickX() {
		return super.getRawAxis(4);
	}

	/**
	 * Returns left or right. Left is negative, right is positive.
	 * 
	 * @return
	 */
	public double LeftTrigger() {
		return super.getRawAxis(2);
	}
	
	public double RightTrigger() {

		return super.getRawAxis(3);
	}
	
	public double LeftStick() {
		return super.getRawAxis(4);
	}

	

	public boolean ButtonA() {
		return super.getRawButton(2);
	}
	
	public boolean ButtonY() {
		return super.getRawButton(4);
	}
	
	public boolean ButtonB() {
		return super.getRawButton(3);
	}
	
	public boolean ButtonX() {
		return super.getRawButton(1);
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

