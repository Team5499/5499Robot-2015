package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GripperSubsystem extends Subsystem {

	CANTalon gripperMotor1 = new CANTalon(RobotMap.gripperMotor1id);
	CANTalon gripperMotor2 = new CANTalon(RobotMap.gripperMotor2id);
	DigitalInput limit1Switch = new DigitalInput(RobotMap.limit1SwitchPort);
	DigitalInput limit2Switch = new DigitalInput(RobotMap.limit2SwitchPort);

	public void initDefaultCommand() {}

	/*
	 * Close the hands and stops them as the limit switches are activated (ie something has been gripped)
	 */
	public void close(){
		while(limit1Switch.get()){
			gripperMotor1.set(-1.0);
		} while(limit2Switch.get()){
			gripperMotor2.set(-1.0);
		}
	}
	public void open(){
		gripperMotor1.set(1.0);
		gripperMotor2.set(1.0);
	}
	/*
	 * Do not allow the load to separate the grippers, but without damaging the load.
	 * The numbers in this method are arbitrary and will need to be calibrated.
	 * The 0.3 is arbitrary
	 */
	public void hold(){
		gripperMotor1.set(0.3);
		gripperMotor2.set(0.3);
	}
	
	/*
	 * Reset the arms of the gripper to a set position. Useful for making the arms be at the same distance from center (ie after having picked up
	 * something that wasn't center).
	 * Will have to use encoders if want to have this be variable based on current position.
	 */
	public void reset(){
		
	}
}

