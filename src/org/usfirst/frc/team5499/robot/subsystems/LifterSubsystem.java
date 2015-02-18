package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterSubsystem extends Subsystem {

	public CANTalon lifterMotor1 = new CANTalon(RobotMap.lifterMotor1id);
	public CANTalon lifterMotor2 = new CANTalong(RobotMap.lifterMotor2id);

	public final int TOP_LIMIT = 750; //FIXME calibrate
	public final int BOTTOM_LIMIT = -750; //FiXME calibrate
	public final int BIN_LIMIT = 0; //FIXME calibrate
	public final int TOTE1LIMIT = -550; //FIXME calibrate
	public final int TOTE2LIMIT = -350; //FIXME calibrate
	public final int TOTE3LIMIT = -150; //FIXME calibrate



	public boolean isLifterSlow; //for slow mode

	public void initDefaultCommand() {

		//TODO remove after PID is implemented
		lifterMotor1.setVoltageRampRate(17);
		lifterMotor2.setVoltageRampRate(17);
		//Set limits to not break the system
		//lifterMotor1.setForwardSoftLimit(TOP_LIMIT);
		//lifterMotor1.setReverseSoftLimit(BOTTOM_LIMIT);
		
//		lifterMotor.changeControlMode(ControlMode.Speed);
	}

	/**
	 * Move the lifter up
	 * TODO check if the values need to be positive. If the sign needs to be changed, do this thru CANTalon.reverseOutput(); to make the logic easier on the programmer
	 * Since the motors are oriented the same way, the values should have the same sign
	 */
	public void Raise(){
		//TODO may want to reduce speed from full speed, especially if these are CIMs, not miniCims
		//		if(lifterMotor1.getEncPosition() < TOP_LIMIT){
		if(isLifterSlow){
			lifterMotor1.set(0.3);
		} else{
			lifterMotor1.set(1);
			lifterMotor2.set(1);

		}
		//		}
	}

	/**
	 * Move the lifter down
	 * Since the motors are oriented the same way, the values should have the same sign
	 */
	public void Lower(){
		//TODO may want to reduce speed from full speed, especially if these are CIMs, not miniCims
//		if(lifterMotor1.getEncPosition() > BOTTOM_LIMIT){
				lifterMotor1.set(-0.3);
		}
	//}

	/**
	 * Move the lifter all of the way down to the floor
	 */
	public void LowerToFloor(){
		while(lifterMotor1.getEncPosition() > BOTTOM_LIMIT){
			Lower();
		}
	}

	/**
	 * This doesn't work. Could this be implemented with PID?
	 */
	public void Hold(){
		lifterMotor1.enableBrakeMode(true);
		lifterMotor1.set(0.0);
	}

	/**
	 * Raise or lower the lifter depending on the current position of the lifter.
	 * The condition is the tentative height of the bin just below the handles.
	 * Default is to hold.
	 */
	public void GetToBin(){
		if(lifterMotor1.getEncPosition() < BIN_LIMIT){
			Raise();
		} else if(lifterMotor1.getEncPosition() > BIN_LIMIT){
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
		if(lifterMotor1.getEncPosition() < TOTE1LIMIT){
			Raise();
		} else if(lifterMotor1.getEncPosition() > TOTE1LIMIT){
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
		if(lifterMotor1.getEncPosition() < TOTE2LIMIT){
			Raise();
		} else if(lifterMotor1.getEncPosition() > TOTE2LIMIT){
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
		if(lifterMotor1.getEncPosition() < TOTE3LIMIT){
			Raise();
		} else if(lifterMotor1.getEncPosition() > TOTE3LIMIT){
			Lower();
		} else {
			Hold();
		}
	}

}

