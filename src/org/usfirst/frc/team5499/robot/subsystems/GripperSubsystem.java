package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GripperSubsystem extends Subsystem {
    
	TalonSRX motor1 = new TalonSRX(RobotMap.gripperMotor1Channel);
	TalonSRX motor2 = new TalonSRX(RobotMap.gripperMotor2Channel);
	
    public void initDefaultCommand() {}
    
    public void close(){
    	motor1.set(-1.0);
    	motor2.set(-1.0);
    }
    public void open(){
    	motor1.set(1.0);
    	motor2.set(1.0);
    }
    public void hold(){}
}

