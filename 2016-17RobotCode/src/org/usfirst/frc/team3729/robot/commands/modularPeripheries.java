package org.usfirst.frc.team3729.robot.commands;

import java.util.Date;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Relay;

public class modularPeripheries {
	Relay feedMotor1;
	CANTalon ShootyMotor1, ShootyMotor2, SweeperMotor, climberMotor;
	PlayStationController playStation;
	boolean ClimbingIsRunning = false;
	boolean SweeperIsRunning = false;
	double timeBetweenR1Presses = 1;
	Date now = new Date();
	Date LastPush = new Date();

	public modularPeripheries(PlayStationController playStation) {
		feedMotor1 = new Relay(0);
		SweeperMotor = new CANTalon(8);
		ShootyMotor1 = new CANTalon(5);
		ShootyMotor2 = new CANTalon(6);
		climberMotor = new CANTalon(7);
		this.playStation = playStation;
	}

	public void ShootCycle() {
		if (playStation.ButtonX() == true) {

		}
	}

	public void Climber() {
		if (playStation.ButtonL1() == true) {
			climberMotor.set(0.8);
		} else {
			climberMotor.set(0);
		}
	}

	public void SweeperPushAndHold() {
		if (playStation.ButtonR1() == true) {
			SweeperMotor.set(0.7);
		} else {
			SweeperMotor.set(0);
		}
	}

	public void SweeperOnOff() {
		if (playStation.ButtonR1() == true) {
			now = new Date();
			if (now.getTime() - LastPush.getTime() > timeBetweenR1Presses) {
				ToggleSweeperState();
			}
			LastPush = now;
		}
	}

	public void ToggleSweeperState() {
		if (SweeperIsRunning == true) {
			SweeperIsRunning = false;
			SweeperMotor.set(0);
		} else {
			SweeperIsRunning = true;
			SweeperMotor.set(0.7);
		}
	}

}