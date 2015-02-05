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
		//TODO test
		lifterMotor2.changeControlMode(ControlMode.Follower);
		lifterMotor2.set(lifterMotor1.getDeviceID());
		//Set smooth accel and decel
		//TODO remove after encoders are connected
		lifterMotor1.setVoltageRampRate(17);
		//Set limits to not break the system
		lifterMotor1.setForwardSoftLimit(750); //FIXME calibrate
		lifterMotor1.setReverseSoftLimit(-750); //FIXME calibrate
	}

	/**
	 * Move the lifter up
	 * TODO check if the values need to be positive. If the sign needs to be changed, do this thru CANTalon.reverseOutput(); to make the logic easier on the programmer
	 * Since the motors are oriented the same way, the values should have the same sign
	 */
	public void Raise(){
		//TODO may want to reduce speed from full speed, especially if these are CIMs, not miniCims
		if(lifterMotor1.getEncPosition() < 750){ //FIXME calibrate
			lifterMotor1.set(1.0);
		}
	}

	/**
	 * Move the lifter down
	 * Since the motors are oriented the same way, the values should have the same sign
	 */
	public void Lower(){
		//TODO may want to reduce speed from full speed, especially if these are CIMs, not miniCims
		if(lifterMotor1.getEncPosition() > -750){ //FIXME calibrate
			lifterMotor1.set(-1.0);
		}
	}
	
	/**
	 * Move the lifter all of the way down to the floor
	 */
	public void LowerToFloor(){
		while(lifterMotor1.getEncPosition() > -750){ //FIXME calibrate
			Lower();
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
	}

	/**
	 * Raise or lower the lifter depending on the current position of the lifter.
	 * The condition is the tentative height of the bin just below the handles.
	 * Default is to hold.
	 */
	public void GetToBin(){
		if(lifterMotor1.getEncPosition() < 0){ //FIXME calibrate
			Raise();
		} else if(lifterMotor1.getEncPosition() > 0){ //FIXME calibrate
			Lower();
		} else {
			Hold();
		}
	}

	/**
	 * Raise or lower the lifter depending on the current position of the lifter.
	 * The condition is the tentative height of a single tote at just below the lid
	 * Default is to hold.
	 */
	public void GetToTote1(){
		if(lifterMotor1.getEncPosition() < -550){ //FIXME calibrate
			Raise();
		} else if(lifterMotor1.getEncPosition() > -550){ //FIXME calibrate
			Lower();
		} else {
			Hold();
		}
	}

	/**
	 * Raise or lower the lifter depending on the current position of the lifter.
	 * The condition is the tentative height of a tote stacked on another tote at just below the lid
	 * Default is to hold.
	 */	
	public void GetToTote2(){
		if(lifterMotor1.getEncPosition() < -350){ //FIXME calibrate
			Raise();
		} else if(lifterMotor1.getEncPosition() > -350){ //FIXME calibrate
			Lower();
		} else {
			Hold();
		}
	}

	/**
	 * Raise or lower the lifter depending on the current position of the lifter.
	 * The condition is the tentative height of a tote stacked on another 2 totes at just below the lid
	 * Default is to hold.
	 */	
	public void GetToTote3(){
		if(lifterMotor1.getEncPosition() < -150){ //FIXME calibrate
			Raise();
		} else if(lifterMotor1.getEncPosition() > -150){ //FIXME calibrate
			Lower();
		} else {
			Hold();
		}
	}

}

