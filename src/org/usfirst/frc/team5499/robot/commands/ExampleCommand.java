
package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.OI;
import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExampleCommand extends Command {
	
	
    public ExampleCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.exampleSubsystem);
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
    	
    	double volts = Robot.pdp.getVoltage();
    	double current = Robot.pdp.getCurrent(1);
    	System.out.println(current);
    	System.out.println(volts);
    		
    	Robot.driveTrainSubsystem.motorBackLeft.set(1);
    	
    	//Robot.driveTrainSubsystem.motorFrontRight.set(1);
    	//Robot.driveTrainSubsystem.motorBackRight.set(1);

    	//
    	//Robot.driveTrainSubsystem.motorBackRight.set(0.1);
    	//Robot.driveTrainSubsystem.move(-.5,-.5,0);
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
