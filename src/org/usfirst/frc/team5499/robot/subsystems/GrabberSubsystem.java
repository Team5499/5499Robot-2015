package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GrabberSubsystem extends Subsystem {

	//These are miniCims?
	CANTalon gripperMotor1 = new CANTalon(RobotMap.gripperMotor1id);
	DigitalInput limitSwitchLeft = new DigitalInput(RobotMap.limit1SwitchPort);
	DigitalInput limitSwitchRight = new DigitalInput(RobotMap.limit2SwitchPort);


	public void initDefaultCommand() {
		//Set motor2 as the slave motor to motor1, the master motor. This makes for less coding. motor2 will copy everything motor1 does.
		//gripperMotor2.changeControlMode(ControlMode.Follower);
		//gripperMotor2.set(gripperMotor1.getDeviceID());
		//Set smooth accel and decel
		//gripperMotor1.setVoltageRampRate(17);
		//Set limits to not break the system
		//gripperMotor1.setForwardSoftLimit(750); //the 750 is arbitrary (ie needs calibration). The units are presumably in ticks. 750 is 3 revolutions of the encoder disk (1 rev is 250)
		//gripperMotor1.setReverseSoftLimit(-750); //-the 750 is arbitrary (ie needs calibration). The units are presumably in ticks. 750 is 3 revolutions of the encoder disk (1 rev is 250)
	}

	/**
	 * Close the grabber
	 * TODO check if the values need to be negative. If the sign needs to be changed, do this thru CANTalon.reverseOutput(); to make the logic easier on the programmer
	 * Since the motors are oriented the same way, the values should have the same sign
	 * TODO may want to reduce speed from full speed, especially if these are CIMs, not miniCims
	 */
	public void Close(){
		gripperMotor1.set(-0.5);
	}

	/**
	 * Open the grabber
	 * TODO check if the values need to be positive. If the sign needs to be changed, do this thru CANTalon.reverseOutput(); to make the logic easier on the programmer
	 * Since the motors are oriented the same way, the values should have the same sign
	 * TODO may want to reduce speed from full speed, especially if these are CIMs, not miniCims
	 */
	public void Open(){
		gripperMotor1.set(0.5);
	}

	/**
	 * When breaking is enabled and the throttle is set to 0.0, the motor controllers should start actively applying opposite torque to stop moving.
	 * If this doesn't work, try to use changes in the encoder values (this subsystem is controlled by only one gearbox, therefore there is only one encoder)
	 * and CANTalon.getEncVelocity();, which should be zero when holding
	 * Or set a particular forward/reverse limit, an example of which can be found in initDefaultCommand()
	 */
	public void Hold(){
		gripperMotor1.enableBrakeMode(true);
		gripperMotor1.set(0.0);
	}	

}

