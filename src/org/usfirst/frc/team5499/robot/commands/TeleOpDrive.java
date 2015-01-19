package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleOpDrive extends Command {

    public TeleOpDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double X = Robot.oi.stick.getX();
    	double Y = Robot.oi.stick.getY();
    	double Z = Robot.oi.stick.getZ();
    	if(Robot.oi.stick.getRawButton(RobotMap.rotateButton)){
    		Z = -1 * Z;
    	}else{
    		Z = 0;
    	}
    	X = -1 * X;
    	Y = -1 * Y;

    	Robot.driveTrainSubsystem.move(X, Y, Z);
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
