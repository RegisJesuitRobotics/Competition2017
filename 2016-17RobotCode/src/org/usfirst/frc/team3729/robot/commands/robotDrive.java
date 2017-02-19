package org.usfirst.frc.team3729.robot.commands;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;

public class robotDrive {

	CANTalon RightMotorFront, LeftMotorFront, RightMotorBack, LeftMotorBack;
	PlayStationController playStation;
	DriverStation driverStation;
	ADXRS450_Gyro gyro;
	boolean isRight;
	double motorLimiterRatioinital = 0.8;
	double motorLimiterRatio = motorLimiterRatioinital;
	double deadZone = 0.2;
	double leftMotorInput = 1;
	double rightMotorInput = 1;

	public robotDrive(PlayStationController playStation) {
		RightMotorBack = new CANTalon(4);
		RightMotorFront = new CANTalon(3);
		LeftMotorBack = new CANTalon(1);
		LeftMotorFront = new CANTalon(2);

		gyro = new ADXRS450_Gyro();
		this.playStation = playStation;

	}

	public void arcadeDrive() {

		double R2 = playStation.L2Axis();
		double L2 = playStation.L2Axis();
		double RightStick = playStation.RightStickYAxis();
		double Deadzone = 0.1;
		double RightPower;
		double LeftPower;
		double Power;
		double turn = 2 * RightStick;

		// LOGIC
		Power = R2 - L2;
		if (RightStick > Deadzone) {

			RightPower = Power - (turn * Power);
			LeftPower = Power;
		} else if (RightStick < -Deadzone) {

			LeftPower = Power + (turn * Power);
			RightPower = Power;
		} else {
			LeftPower = Power;
			RightPower = Power;
		}
		// MOTOR SETTING

		RightMotorFront.set(RightPower);
		RightMotorBack.set(RightPower);
		LeftMotorFront.set(-LeftPower);
		LeftMotorBack.set(-LeftPower);

	}

}
