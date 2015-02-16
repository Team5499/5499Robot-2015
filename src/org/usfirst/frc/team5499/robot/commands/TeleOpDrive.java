package org.usfirst.frc.team5499.robot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleOpDrive extends Command {

	
    public TeleOpDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSubsystem);
    	//requires(Robot.lifterSubsystem);
    	//requires(Robot.grabberSubsystem);
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
    	double Z = Robot.oi.stick.getRawAxis(2);
    	
    	//Make it so that rotation is deliberate thru making the driver press a button to be able to rotate
    	if(!Robot.oi.stick.getRawButton(RobotMap.rotateButton)){
    		Z = 0;
    	}else{
    		Z = -1*Z;
    	}
    	//Joystick X,y and Z are backwards, so all of them are multiplied by -1
    	X = -1 * X;
    	Y = -1 * Y;
    	
    	

    	//Call the move method to actually set the motors' speeds
    	Robot.driveTrainSubsystem.move(0, .4, 0);
    	//Robot.driveTrainSubsystem.move(X, Y, Z);
    	int pov = Robot.oi.stick.getPOV();
    	//Lifter binding
    	if(pov==0){//RobotMap.lifterRaiseButton)){
    		Robot.lifterSubsystem.Raise();
    	} else if (pov==180){
    		Robot.lifterSubsystem.Lower();
    	} else{
    		Robot.lifterSubsystem.Hold();
    	}
    	
    	//Grabber binding
    	if (Robot.oi.stick.getRawButton(RobotMap.grabberCloseButton)){
    		Robot.grabberSubsystem.Close();
    	} else if (Robot.oi.stick.getRawButton(RobotMap.grabberOpenButton)){
    		Robot.grabberSubsystem.Open();
    	} else{
    		Robot.grabberSubsystem.Hold();
    	}
    	//System.out.println(Robot.driveTrainSubsystem.motorFrontLeft.getEncVelocity());
    	System.out.println(Robot.driveTrainSubsystem.motorFrontRight.getEncVelocity());
    	//System.out.println(Robot.driveTrainSubsystem.motorBackLeft.getEncVelocity());
    	//System.out.println(Robot.driveTrainSubsystem.motorBackRight.getEncVelocity());
    	
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
    	end();
    }
}
