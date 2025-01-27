
package org.usfirst.frc.team5499.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5499.robot.Robot;

/**
 *
 */
public class FullAutoCommand extends Command {

    public FullAutoCommand() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivebaseSubsystem);
    	requires(Robot.elevatorSubsystem);
    	requires(Robot.grabberSubsystemConveyor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drivebaseSubsystem.timedMove(50, 0, 70);
    	Robot.grabberSubsystemConveyor.startStopConveyor(true, -1);
    	Robot.elevatorSubsystem.moveSetAmt(5, 30);
    	Robot.grabberSubsystemConveyor.startStopConveyor(true, 1);
    	Robot.elevatorSubsystem.moveSetAmt(5, -30);
    	Robot.elevatorSubsystem.moveSetAmt(5, 30);
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
