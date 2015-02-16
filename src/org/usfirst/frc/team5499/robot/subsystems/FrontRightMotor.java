package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class FrontRightMotor extends PIDSubsystem {

    // Initialize your subsystem here
    public FrontRightMotor() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("MotorFrontRight", 1.0, 0.0, 0.02, 1.0);
    	getPIDController().enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return Robot.driveTrainSubsystem.motorFrontRight.getEncVelocity();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Robot.driveTrainSubsystem.motorFrontRight.pidWrite(output);
    }
}
