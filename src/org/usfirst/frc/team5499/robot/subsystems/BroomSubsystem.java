package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BroomSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Servo broomServo = new Servo(RobotMap.broomServoChannel);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void close(){
    	broomServo.set(0.0);
    }
    public void open(){
    	broomServo.set(0.5);
    }
}

