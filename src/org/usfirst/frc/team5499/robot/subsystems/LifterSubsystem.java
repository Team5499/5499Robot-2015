package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterSubsystem extends Subsystem {
	
	TalonSRX motor = new TalonSRX(RobotMap.lifterMotorChannel);

    public void initDefaultCommand() {}

    public void up(){
    	motor.set(1.0);
    }
    public void down(){
    	motor.set(-1.0);
    }
    public void hold(){}

}

