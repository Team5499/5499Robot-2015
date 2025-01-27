
package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GrabberSubsystemConveyor extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final CANTalon RIGHT_MOTOR = new CANTalon(RobotMap.RIGHT_INTAKE_MOTOR_IND);
	private final CANTalon LEFT_MOTOR = new CANTalon(RobotMap.LEFT_INTAKE_MOTOR_IND);
	private boolean isConveyorActive = false;
	private boolean areTabsExtended = false;
	
	private final Servo RIGHT_SERVO_F = new Servo(RobotMap.RIGHT_SERVO_F_PWM_CH);
	private final Servo RIGHT_SERVO_B = new Servo(RobotMap.RIGHT_SERVO_B_PWM_CH);
	private final Servo LEFT_SERVO_F = new Servo(RobotMap.LEFT_SERVO_F_PWM_CH);
	private final Servo LEFT_SERVO_B = new Servo(RobotMap.LEFT_SERVO_B_PWM_CH);
	
	private final int EXTENDED = 70;
	private final int RETRACTED = 0;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * 
     * @param buttonIn the raw output from the button to control the intake start/stop
     * @param forward 1 is forward, -1 if backward
     */
    public void startStopConveyor(boolean buttonIn, int forward){
    	if(buttonIn){
    		isConveyorActive ^= true; //toggle bool
    	}
    	
    	while(isConveyorActive){
    		RIGHT_MOTOR.set(70 * forward);
    		LEFT_MOTOR.set(70 * forward);
    	}
    }
    
    /**
     * 
     * @param buttonIn the raw output from the button to control the retract/extend of tabs
     */
    public void toggleTabs(boolean buttonIn){
    	if(buttonIn){
    		areTabsExtended ^= true; //toggle bool
    	}
    	if(areTabsExtended){
	    	RIGHT_SERVO_F.set(EXTENDED);
	    	RIGHT_SERVO_B.set(EXTENDED);
	    	LEFT_SERVO_F.set(EXTENDED);
	    	LEFT_SERVO_B.set(EXTENDED);
    	} else {
    		RIGHT_SERVO_F.set(RETRACTED);
	    	RIGHT_SERVO_B.set(RETRACTED);
	    	LEFT_SERVO_F.set(RETRACTED);
	    	LEFT_SERVO_B.set(RETRACTED);
    	}
    }
}

