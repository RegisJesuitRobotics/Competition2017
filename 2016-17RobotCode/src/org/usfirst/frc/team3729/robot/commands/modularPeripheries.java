package org.usfirst.frc.team3729.robot.commands;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Relay;

public class modularPeripheries {
	Relay feedMotor1;
	CANTalon ShootyMotor1, ShootyMotor2, eatMotor, climberMotor;
	PlayStationController playStation;
	boolean feedIsRunning = false;
	boolean eatIsRunning = false;

	public modularPeripheries(PlayStationController playStation) {
		feedMotor1 = new Relay(0);
		eatMotor = new CANTalon(8);
		ShootyMotor1 = new CANTalon(5);
		ShootyMotor2 = new CANTalon(6);
		climberMotor = new CANTalon(7);
		this.playStation = playStation;
	}
}