package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This is the backup to the main autonomous strategy
 */
public class AltAuto extends Command {

    public AltAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSubsystem);
    }
    double startEncVal;
    final int ENC_LIMIT = 350; //TODO calibrate
    // Called just before this Command runs the first time
    protected void initialize() {
    	startEncVal = Robot.driveTrainSubsystem.motorFrontRight.getEncPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currEncVal = Robot.driveTrainSubsystem.motorFrontRight.getEncPosition();
    	while(currEncVal - startEncVal < ENC_LIMIT){
    		Robot.driveTrainSubsystem.move_polar(180, 0.8, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
