package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveForward extends Command {

    public MoveForward() {
        // Use requires() here to declare subsystem dependencies
    	// eg. requires(chassis);
    	requires(Robot.driveTrainSubsystem);
    }
    
    private double initEncVal;
    // Called just before this Command runs the first time
    protected void initialize() {
    	initEncVal = Robot.driveTrainSubsystem.motorBackLeft.getEncPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSubsystem.MoveForward();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.driveTrainSubsystem.motorBackLeft.getEncPosition() - initEncVal >= 500){//FIXME calibrate
    		return true;
    	}else {
    		return false;
    	}
    }
    
    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
