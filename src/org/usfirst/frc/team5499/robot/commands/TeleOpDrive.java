package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.OI;
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
    	double Z;
    	
    	//Make it so that rotation is deliberate thru making the driver press a button to be able to rotate
    	if(Robot.oi.stick.getRawButton(Robot.oi.rotateButton)){
    		Z = Robot.oi.stick.getZ();
    	}else{
    		Z = 0;
    	}

    	//Call the move method to actually set the motors' speeds
    	Robot.driveTrainSubsystem.mecanumDrive.mecanumDrive_Cartesian(X, Y, Z, 0.0);
    	
    	//Lifter binding
    	if(Robot.oi.stick.getPOV() == Robot.oi.lifterRaiseDeg){
    		Robot.lifterSubsystem.Raise();
    	} else if (Robot.oi.stick.getPOV() == Robot.oi.lifterLowerDeg){
    		Robot.lifterSubsystem.Lower();
    	} else if (Robot.oi.stick.getRawButton(Robot.oi.getToBinButton)){
    		Robot.lifterSubsystem.GetToBin();
    	} else if (Robot.oi.stick.getRawButton(Robot.oi.getToTote1Button)){
    		Robot.lifterSubsystem.GetToTote1();    		
    	} else if (Robot.oi.stick.getRawButton(Robot.oi.getToTote2Button)){
    		Robot.lifterSubsystem.GetToTote2();
    	} else if (Robot.oi.stick.getRawButton(Robot.oi.getToTote3Button)){
    		Robot.lifterSubsystem.GetToTote3();    		
    	} else if (Robot.oi.stick.getRawButton(Robot.oi.lowerToFloorButton)){
    		Robot.lifterSubsystem.LowerToFloor();
    	} else{
    		Robot.lifterSubsystem.Hold();
    	}
    	
    	//Grabber binding
    	if (Robot.oi.stick.getRawButton(Robot.oi.grabberCloseButton)){
    		Robot.grabberSubsystem.Close();
    	} else if (Robot.oi.stick.getPOV() == Robot.oi.grabberOpenDeg1 || Robot.oi.stick.getPOV() == Robot.oi.grabberOpenDeg2){
    		Robot.grabberSubsystem.Open();
    	} else if (Robot.oi.stick.getRawButton(Robot.oi.grabBinButton)){
    		Robot.grabberSubsystem.GrabBin();
    	} else if (Robot.oi.stick.getRawButton(Robot.oi.grabToteButton)){
    		Robot.grabberSubsystem.GrabTote();
    	} else if (Robot.oi.stick.getRawButton(Robot.oi.releaseButton)){
    		Robot.grabberSubsystem.Release();
    	} else{
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
