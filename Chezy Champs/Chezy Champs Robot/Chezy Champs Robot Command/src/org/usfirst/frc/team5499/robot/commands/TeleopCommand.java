
package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopCommand extends Command {

    public TeleopCommand() {
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
    	Robot.drivebaseSubsystem.move();
    	Robot.elevatorSubsystem.move();
    	Robot.grabberSubsystemConveyor.startStopConveyor(Robot.oi.CONTROLLER.getRawButton(Robot.oi.A), 1);
    	Robot.grabberSubsystemConveyor.toggleTabs(Robot.oi.CONTROLLER.getRawButton(Robot.oi.Y));
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
