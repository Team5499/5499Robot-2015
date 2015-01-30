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
    	requires(Robot.lifterSubsystem);
    	requires(Robot.grabberSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/**
    	 * Bind buttons to actions
    	 */
    	//Set axes using the stick
    	double X = Robot.oi.stick.getX();
    	double Y = Robot.oi.stick.getY();
    	double Z = Robot.oi.stick.getZ();
    	
    	//Make it so that rotation is deliberate thru making the driver press a button to be able to rotate
    	if(Robot.oi.stick.getRawButton(RobotMap.rotateButton)){
    		Z = -1 * Z;
    	}else{
    		Z = 0;
    	}
    	//Joystick X,y and Z are backwards, so all of them are multiplied by -1
    	X = -1 * X;
    	Y = -1 * Y;

    	//Call the move method to actually set the motors' speeds
    	Robot.driveTrainSubsystem.move(X, Y, Z);
    	
    	//Lifter binding
    	if(Robot.oi.stick.getRawButton(RobotMap.lifterRaiseButton)){
    		Robot.lifterSubsystem.Raise();
    	} else if (Robot.oi.stick.getRawButton(RobotMap.liferLowerButton)){
    		Robot.lifterSubsystem.Raise();
    	} else if (Robot.oi.stick.getRawButton(RobotMap.lifterHoldButton)){
    		Robot.lifterSubsystem.Hold();
    	}
    	
    	//Grabber binding
    	if (Robot.oi.stick.getRawButton(RobotMap.grabberCloseButton)){
    		Robot.grabberSubsystem.Close();
    	} else if (Robot.oi.stick.getRawButton(RobotMap.grabberOpenButton)){
    		Robot.grabberSubsystem.Open();
    	} else if (Robot.oi.stick.getRawButton(RobotMap.grabberHoldButton)){
    		Robot.grabberSubsystem.Hold();
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
