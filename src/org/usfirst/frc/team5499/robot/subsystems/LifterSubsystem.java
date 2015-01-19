package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterSubsystem extends Subsystem {
	
	CANTalon lifterMotor1 = new CANTalon(RobotMap.lifterMotor1id);
	CANTalon lifterMotor2 = new CANTalon(RobotMap.lifterMotor2id);

    public void initDefaultCommand() {}

    public void up(){
    	lifterMotor1.set(1.0);
    	lifterMotor2.set(1.0);
    }
    public void down(){
    	lifterMotor1.set(-1.0);
    	lifterMotor2.set(-1.0);
    }
    public void hold(){}

}

