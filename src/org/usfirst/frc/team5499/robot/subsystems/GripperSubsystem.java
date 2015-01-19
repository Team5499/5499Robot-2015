package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GripperSubsystem extends Subsystem {
    
	CANTalon gripperMotor1 = new CANTalon(RobotMap.gripperMotor1id);
	CANTalon gripperMotor2 = new CANTalon(RobotMap.gripperMotor2id);
	
    public void initDefaultCommand() {}
    
    public void close(){
    	gripperMotor1.set(-1.0);
    	gripperMotor2.set(-1.0);
    }
    public void open(){
    	gripperMotor1.set(1.0);
    	gripperMotor2.set(1.0);
    }
    public void hold(){}
}

