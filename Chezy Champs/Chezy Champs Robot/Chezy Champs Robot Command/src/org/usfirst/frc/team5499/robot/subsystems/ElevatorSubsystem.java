
package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final CANTalon ELEVATOR_MOTOR_F = new CANTalon(RobotMap.ELEVATOR_MOTOR_F_IND);
	private final CANTalon ELEVATOR_MOTOR_B = new CANTalon(RobotMap.ELEVATOR_MOTOR_B_IND);

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void move(){
		ELEVATOR_MOTOR_F.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.TRIGGERS));
		ELEVATOR_MOTOR_B.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.TRIGGERS));
	}
	/**
	 * 
	 * @param time amt of time to move the elevator for in seconds
	 * @param speed speed at which to move the elevator in percent, with positive being up, negative being down
	 */
	public void moveSetAmt(double time, double speed){
		ELEVATOR_MOTOR_F.set(speed);
		ELEVATOR_MOTOR_B.set(speed);
	}
}

