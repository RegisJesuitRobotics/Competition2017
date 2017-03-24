package org.usfirst.frc.team3729.robot.commands;

import org.usfirst.frc.team3279.vision.GearTargetRangerFinder;

import com.ctre.CANTalon;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class NO_TOUCH {
	
	DoubleSolenoid gearActuator;
	Relay clipMotor, brady;
	CANTalon RightFrontMotor, LeftFrontMotor, RightBackMotor, LeftBackMotor, intakeMotor, shooterMotor1, shooterMotor2,
			climberMotor;
	AnalogInput gearIntake;
	
	PlayStationController playStation;
	DriverStation driverStation;
	// ADXRS450_Gyro gyro;
	NetworkTable networkTable;
	public boolean PnuematicsIsForward = false;
	double angle = 0;
	double front = 2;
	double back = 2;
	double deltamin = 0.25;
	double toggles = 0;
	double gearSpeed = 0;
	boolean gearUp = false;
	boolean inOperation = false;
	boolean feed = true;

	public NO_TOUCH(PlayStationController playStation, UsbCamera camera) {
	
		// gyro = new ADXRS450_Gyro();
		RightFrontMotor = new CANTalon(4);
		RightBackMotor = new CANTalon(3);
		LeftBackMotor = new CANTalon(1);
		LeftFrontMotor = new CANTalon(2);
		clipMotor = new Relay(0);
		brady = new Relay(1);
		intakeMotor = new CANTalon(8);
		shooterMotor1 = new CANTalon(5);
		shooterMotor2 = new CANTalon(6);
		climberMotor = new CANTalon(7);
		gearActuator = new DoubleSolenoid(0, 1);
		gearIntake = new AnalogInput(0);
		this.playStation = playStation;

	}

	public void CkDrive() {
		double R2 = playStation.RightTrigger();
		double L2 = playStation.LeftTrigger();
		double RightStick = playStation.RightStickXAxis();
		double LeftStick = playStation.LeftStickXAxis();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double turn = 2 * LeftStick;
		Power = R2 - L2;
		if (RightStick > Deadzone && R2 < Deadzone) {
			RightFrontMotor.set(RightStick / 1.7);
			RightBackMotor.set(-RightStick / 2.3);
			LeftFrontMotor.set(-RightStick / 1.7);
			LeftBackMotor.set(RightStick / 2.3);
		} else if (RightStick < -Deadzone && R2 < Deadzone) {
			RightFrontMotor.set(RightStick / 1.7);
			RightBackMotor.set(-RightStick / 2.3);
			LeftFrontMotor.set(-RightStick / 1.7);
			LeftBackMotor.set(RightStick / 2.3);
		} else if (RightStick > Deadzone && R2 > Deadzone) {
			RightFrontMotor.set(RightStick);
			RightBackMotor.set(-RightStick * .74);
			LeftFrontMotor.set(-RightStick);
			LeftBackMotor.set(RightStick * .74);
		} else if (RightStick < -Deadzone && R2 > Deadzone) {
			RightFrontMotor.set(-RightStick);
			RightBackMotor.set(RightStick * .74);
			LeftFrontMotor.set(RightStick);
			LeftBackMotor.set(-RightStick * .74);
		} else {
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
			RightFrontMotor.set(RightPower);
			RightBackMotor.set(RightPower);
			LeftFrontMotor.set(-LeftPower);
			LeftBackMotor.set(-LeftPower);
		}
	}

	/*
	 * public void pacMan() { if (gearIntake.getVoltage() > 1){ if
	 * (PnuematicsIsForward == false){ intakeMotor.set(0); } } else if
	 * (gearIntake.getVoltage() < 1){ if (PnuematicsIsForward == false){
	 * intakeMotor.set(1); } } }
	 */
	public void auto(GearTargetRangerFinder range) {
		double right = range.rightPower;
		System.out.println("right");
		double left = range.leftPower;
		System.out.println("left");
		RightFrontMotor.set(right);
		RightBackMotor.set(right);
		LeftFrontMotor.set(-left);
		LeftBackMotor.set(-left);
	}
	
	public void stop(){
		RightFrontMotor.set(0);
		RightBackMotor.set(0);
		LeftFrontMotor.set(0);
		LeftBackMotor.set(0);	
	}
	public void autoraise(){
		gearActuator.set(DoubleSolenoid.Value.kForward);
		PnuematicsIsForward = true;
	}
	public void autodrop() {
		intakeMotor.set(1);
		Timer.delay(.5);
		gearActuator.set(DoubleSolenoid.Value.kReverse);
		PnuematicsIsForward = false;	}
	public void rev() {
		if (playStation.ButtonX() == true) {
			shooterMotor1.set(-.74); // -.74 test bot value
			shooterMotor2.set(-.74); // -0.6799999999999997 value for comp bot
			if (playStation.ButtonCircle() == true) {
				brady.set(Relay.Value.kForward);
			} else {
				brady.set(Relay.Value.kReverse);
			}
		} else {
			shooterMotor1.set(0);
			shooterMotor2.set(0);
			brady.set(Relay.Value.kOff);
		}
	}

	public void CkPeripheries() {
		// climber
		if (playStation.ButtonL1() == true) {
			climberMotor.set(-1);
		} else {
			climberMotor.set(0);
		}
		// front gear wheel
		if(playStation.ButtonCircle()==true && feed ==false){
			feed=true;
			System.out.println("true");
		}
		else if(playStation.ButtonCircle()==true && feed ==true){
			feed=false;
			System.out.println("false");
			
		}
		// System.out.println(PnuematicsIsForward);
		if (gearIntake.getAverageVoltage() < 1 ) {
			if (PnuematicsIsForward == false && feed == true) {
				intakeMotor.set(-1);
			} 
			else {
				intakeMotor.set(0);
			}
		}
		else {
			intakeMotor.set(0);
		}
		// shoot sequence manual
		if (playStation.ButtonX() == true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			clipMotor.set(Relay.Value.kForward);
		} else {
			clipMotor.set(Relay.Value.kOff);
		}
		// auto line-up vision
//		if (playStation.ButtonSquare() == true) {
//			double centerX;
//			centerX = networkTable.getNumber("Name of the table and key the value will be in", 0);
//			double centerY;
//			centerY = networkTable.getNumber("", 0);
//			/*
//			 * if (centerX < desiredcenterX){ RightFrontMotor.set(-1);
//			 * RightBackMotor.set(-1); LeftFrontMotor.set(-1);
//			 * LeftBackMotor.set(-1); } else if(centerX > desiredLocationX) {
//			 * RightFrontMotor.set(1); RightBackMotor.set(1);
//			 * LeftFrontMotor.set(1); LeftBackMotor.set(1); } if (centerY <
//			 * desiredLocationY) { RightFrontMotor.set(1);
//			 * RightBackMotor.set(-1); LeftFrontMotor.set(-1);
//			 * LeftBackMotor.set(1); } else if (centerY > desiredLocationY) {
//			 * RightFrontMotor.set(-1); RightBackMotor.set(1);
//			 * LeftFrontMotor.set(1); LeftBackMotor.set(-1); }
//			 */
//		}
	}

	public void pacMan() {
		
	}

	
	public void TogglePnuematics() {
		/*
		 * try { Thread.sleep(500); } catch (InterruptedException ex) {
		 * Thread.currentThread().interrupt(); }
		 */
		gearActuator.set(DoubleSolenoid.Value.kReverse);
		PnuematicsIsForward = false;
	}

	public void ToggleState() {
		if (PnuematicsIsForward == true && gearIntake.getVoltage() > 1) {
			intakeMotor.set(1);
			Timer.delay(.5);
			gearActuator.set(DoubleSolenoid.Value.kReverse);
			PnuematicsIsForward = false;
		} else if (PnuematicsIsForward == true && gearIntake.getVoltage() < 1) {
			gearActuator.set(DoubleSolenoid.Value.kReverse);
			PnuematicsIsForward = false;
		} else if (PnuematicsIsForward == false) {
			gearActuator.set(DoubleSolenoid.Value.kForward);
			PnuematicsIsForward = true;
		}
	}

	public void CkTest() {
		// double newangle = gyro.getAngle();
		System.out.println("old" + angle);
		// System.out.println("new" + newangle);

		// if (newangle > angle+deltamin) {
		front = front + 0.001;
		back = back - 0.001;
		// }
		// if (newangle < angle-deltamin) {
		front = front - 0.001;
		back = back + 0.001;
		// }
		// System.out.println("front=" + front);
		// System.out.println("back=" + back);
		double R2 = playStation.RightTrigger();
		double L2 = playStation.LeftTrigger();
		double RightStick = playStation.RightStickXAxis();
		double LeftStick = playStation.LeftStickXAxis();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double turn = 2 * LeftStick;
		Power = R2 - L2;
		if (RightStick > Deadzone && R2 < Deadzone) {
			RightFrontMotor.set(RightStick / front);
			RightBackMotor.set(-RightStick / back);
			LeftFrontMotor.set(RightStick / front);
			LeftBackMotor.set(-RightStick / back);
		} else if (RightStick < -Deadzone && R2 < Deadzone) {
			RightFrontMotor.set(RightStick / front);
			RightBackMotor.set(-RightStick / back);
			LeftFrontMotor.set(RightStick / front);
			LeftBackMotor.set(-RightStick / back);
		} else if (RightStick > Deadzone && R2 > Deadzone) {
			RightFrontMotor.set(RightStick / front);
			RightBackMotor.set(-RightStick / back);
			LeftFrontMotor.set(RightStick / front);
			LeftBackMotor.set(-RightStick / back);
		} else if (RightStick < -Deadzone && R2 > Deadzone) {
			RightFrontMotor.set(RightStick / front);
			RightBackMotor.set(-RightStick / back);
			LeftFrontMotor.set(RightStick / front);
			LeftBackMotor.set(-RightStick / back);
		} else {
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
			RightFrontMotor.set(RightPower);
			RightBackMotor.set(RightPower);
			LeftFrontMotor.set(-LeftPower);
			LeftBackMotor.set(-LeftPower);
		}
		// angle = newangle;
		Timer.delay(0.004);

	}

	public void GoForewards(double speed, double time) {
		setInOperation(true);
		RightBackMotor.set(speed);
		RightFrontMotor.set(speed);
		LeftBackMotor.set(-speed);
		LeftFrontMotor.set(-speed);
		System.out.println("Finished setting motors");
		Timer.delay(time);
		System.out.println("Finshed Delay 1");
		Stop();
		System.out.println("Stopped");
		setInOperation(false);
	}

	
	public boolean isInOperation() {
		return inOperation;
	}

	public synchronized void setInOperation(boolean inOperation) {
		this.inOperation = inOperation;
	}

	public void GoBackwards(double speed, double time) {
		setInOperation(true);
		RightBackMotor.set(-speed);
		RightFrontMotor.set(-speed);
		LeftBackMotor.set(speed);
		LeftFrontMotor.set(speed);
		Timer.delay(time);
		Stop();
		setInOperation(false);
	}

	public void TurnLeft(double speed, double time) {
		setInOperation(true);
		RightBackMotor.set(speed);
		RightFrontMotor.set(speed);
		LeftBackMotor.set(speed);
		LeftFrontMotor.set(speed);
		Timer.delay(time);
		Stop();
		setInOperation(false);
	}

	public void TurnRight(double speed, double time) {
		setInOperation(true);
		RightBackMotor.set(-speed);
		RightFrontMotor.set(-speed);
		LeftBackMotor.set(-speed);
		LeftFrontMotor.set(-speed);
		Timer.delay(time);
		Stop();
		setInOperation(false);
	}

	public void Stop() {
		RightBackMotor.set(0);
		RightFrontMotor.set(0);
		LeftBackMotor.set(0);
		LeftFrontMotor.set(0);
	}

	public void MoveUp(double speed) {
		RightBackMotor.set(speed);
		RightFrontMotor.set(speed);
		LeftBackMotor.set(speed);
		LeftFrontMotor.set(speed);
	}



}