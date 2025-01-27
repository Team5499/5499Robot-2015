package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GrabberSubsystem extends Subsystem {

	public CANTalon grabberMotor = new CANTalon(RobotMap.grabberMotorid);
	public DigitalInput limitSwitchLeft = new DigitalInput(RobotMap.limitSwitch1Port);
	public DigitalInput limitSwitchRight = new DigitalInput(RobotMap.limitSwitch2Port);
	
	public final int OPEN_LIMIT = 500; //FIXME calibrate
	public final int CLOSE_LIMIT = 0; //FIXME calibrate
	public final int BIN_LIMIT = 125; // FIXME calibrate
	public final int TOTE_LIMIT = 200; //FIXME calibrate


	public void initDefaultCommand() {
		
		//Set motor2 as the slave motor to motor1, the master motor. This makes for less coding. motor2 will copy everything motor1 does.
//		grabberMotor2.changeControlMode(ControlMode.Follower);
//		grabberMotor2.set(grabberMotor1.getDeviceID());
		//Set smooth accel and decel
//		grabberMotor1.setVoltageRampRate(17); //FIXME calibrate
		//Set limits to not break the system
//		grabberMotor1.setForwardSoftLimit(OPEN_LIMIT); 
//		grabberMotor1.setReverseSoftLimit(CLOSE_LIMIT);
	}

	/**
	 * Close the grabber
	 * Since the motors are oriented the same way, the values should have the same sign
	 */
	public void Close(){
		//TODO may want to reduce speed from full speed, especially if these are CIMs, not miniCims
		//TODO check if these values need to be negative
//		if(grabberMotor.getEncPosition() > OPEN_LIMIT){
			grabberMotor.set(1.0);
		//}
	}

	/**
	 * Open the grabber
	 * TODO check if the values need to be positive. If the sign needs to be changed, do this thru CANTalon.reverseOutput(); to make the logic easier on the programmer
	 * Since the motors are oriented the same way, the values should have the same sign
	 */
	public void Open(){
		//TODO may want to reduce speed from full speed, especially if these are CIMs, not miniCims
		//TODO chek if these values need to be positive
//		if(grabberMotor.getEncPosition() < CLOSE_LIMIT){
			grabberMotor.set(-1.0);
		//}
	}

	/**
	 * When breaking is enabled and the throttle is set to 0.0, the motor controllers should start actively applying opposite torque to stop moving.
	 * If this doesn't work, try to use changes in the encoder values (this subsystem is controlled by only one gearbox, therefore there is only one encoder)
	 * and CANTalon.getEncVelocity();, which should be zero when holding, and PID
	 * Or set a particular forward/reverse limit, an example of which can be found in initDefaultCommand()
	 */
	public void Hold(){
		grabberMotor.set(0.0);
	}

	/**
	 * Grab the bin by opening the or closing the bin until at least one of the conditions is met.
	 * The first condition is the tentative width of the bin in encoder ticks.
	 * The second condition is the activation of both of the limit switches on the grabber 
	 * Default is to hold.
	 */
	public void GrabBin(){
		if(grabberMotor.getEncPosition() < BIN_LIMIT || (!limitSwitchLeft.get() && !limitSwitchRight.get())){
			Open();		
		} else if(grabberMotor.getEncPosition() > BIN_LIMIT || (!limitSwitchLeft.get() && !limitSwitchRight.get())){
			Close();
		} else{
			Hold();
		}
	}
	
	/**
	 * Grab the tote by opening the or closing the tote until at least one of the conditions is met.
	 * The first condition is the tentative width of the tote in encoder ticks.
	 * The second condition is the activation of both of the limit switches on the grabber 
	 * Default is to hold
	 */
	public void GrabTote(){
		if(grabberMotor.getEncPosition() < TOTE_LIMIT || (!limitSwitchLeft.get() && !limitSwitchRight.get())){
			Open();		
		} else if(grabberMotor.getEncPosition() > TOTE_LIMIT || (!limitSwitchLeft.get() && !limitSwitchRight.get())){
			Close();
		} else{
			Hold();
		}
	}
	
	/**
	 * Open the grabber until both of the limit switches have been released.
	 */
	public void Release(){
		if(limitSwitchLeft.get() && limitSwitchRight.get()){
			Open();
		} else{
			Hold();
		}
	}

}

