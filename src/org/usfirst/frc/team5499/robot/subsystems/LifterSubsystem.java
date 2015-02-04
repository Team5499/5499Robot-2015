package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterSubsystem extends Subsystem {

	//These are miniCims?
	public CANTalon lifterMotor1 = new CANTalon(RobotMap.lifterMotor1id);
	public CANTalon lifterMotor2 = new CANTalon(RobotMap.lifterMotor2id);



	public void initDefaultCommand() {
		//Set motor2 as the slave motor to motor1, the master motor. This makes for less coding. motor2 will copy everything motor1 does.
		lifterMotor2.changeControlMode(ControlMode.Follower);
		lifterMotor2.set(lifterMotor1.getDeviceID());
		//Set smooth accel and decel
		//TODO remove after encoders are connected
		lifterMotor1.setVoltageRampRate(17);
		//Set limits to not break the system
		lifterMotor1.setForwardSoftLimit(750); //1750 is arbitrary (ie needs calibration). The units are presumably in ticks. 1750 is 7 revolutions of the encoder disk (1 rev is 250)
		lifterMotor1.setReverseSoftLimit(-750); //-1750 is arbitrary (ie needs calibration). The units are presumably in ticks. 1750 is 7 revolutions of the encoder disk (1 rev is 250)
	}

	/**
	 * Move the lifter up
	 * TODO check if the values need to be positive. If the sign needs to be changed, do this thru CANTalon.reverseOutput(); to make the logic easier on the programmer
	 * Since the motors are oriented the same way, the values should have the same sign
	 * TODO may want to reduce speed from full speed, especially if these are CIMs, not miniCims
	 */
	public void Raise(){
		if(lifterMotor1.getEncPosition() < 750){
			lifterMotor1.set(1.0);
			lifterMotor2.set(1.0);
		}
	}

	/**
	 * Move the lifter down
	 * TODO check if the values need to be negative. If the sign needs to be changed, do this thru CANTalon.reverseOutput(); to make the logic easier on the programmer
	 * Since the motors are oriented the same way, the values should have the same sign
	 * TODO may want to reduce speed from full speed, especially if these are CIMs, not miniCims
	 */
	public void Lower(){
		if(lifterMotor1.getEncPosition() > -750){
			lifterMotor1.set(-1.0);
			lifterMotor2.set(-1.0);
		}
	}

	/**
	 * When breaking is enabled, the motor controllers should start actively applying opposite torque to stop moving.
	 * If this doesn't work, try to use changes in the encoder values (this subsystem is controlled by only one gearbox, therefore there is only one encoder)
	 * and CANTalon.getEncVelocity();, which should be zero when holding
	 */
	public void Hold(){
		lifterMotor1.enableBrakeMode(true);
		lifterMotor2.enableBrakeMode(true);
		lifterMotor1.set(0.0);
		lifterMotor2.set(0.0);
	}

	public void GetToBin(){

	}

	public void LowerToFloor(){

	}

	public void GetToTote1(){

	}

	public void GetToTote2(){

	}

	public void GetToTote3(){

	}

}

