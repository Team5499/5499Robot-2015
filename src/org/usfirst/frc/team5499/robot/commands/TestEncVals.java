package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * For testing purposes only
 */
public class TestEncVals extends Command {

	//TODO run with encoders
    public TestEncVals() {
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
    	//Store the encoder values
    	double[] frontLeftEncVals = {Robot.driveTrainSubsystem.motorFrontLeft.getEncPosition(),
    			Robot.driveTrainSubsystem.motorFrontLeft.getEncVelocity()};
    	double[] frontRightEncVals = {Robot.driveTrainSubsystem.motorFrontRight.getEncPosition(),
    			Robot.driveTrainSubsystem.motorFrontRight.getEncVelocity()};
    	double[] backLeftEncVals = {Robot.driveTrainSubsystem.motorBackLeft.getEncPosition(),
    			Robot.driveTrainSubsystem.motorBackLeft.getEncVelocity()};
    	double[] backRightEncVals = {Robot.driveTrainSubsystem.motorBackRight.getEncPosition(),
    			Robot.driveTrainSubsystem.motorBackRight.getEncVelocity()};
    	double[] lifterEncVals = {Robot.lifterSubsystem.lifterMotor1.getEncPosition(),
    			Robot.lifterSubsystem.lifterMotor1.getEncVelocity()};
    	double[] grabberEncVals = {Robot.grabberSubsystem.grabberMotor1.getEncPosition(),
    			Robot.grabberSubsystem.grabberMotor1.getEncVelocity()};
    	
    	
    	//Print encoder values in the riolog
    	System.out.println("FrontLeft"
    			+ "\n	position:"+ frontLeftEncVals[0]
    			+ "\n	velocity:" + frontLeftEncVals[1]);
    	System.out.println("FrontRight"
    			+ "\n	position:"+ frontRightEncVals[0]
    			+ "\n	velocity:" + frontRightEncVals[1]);
    	System.out.println("BackLeft"
    			+ "\n	position:"+ backLeftEncVals[0]
    			+ "\n	velocity:" + backLeftEncVals[1]);
    	System.out.println("BackRight"
    			+ "\n	position:"+ backRightEncVals[0]
    			+ "\n	velocity:" + backRightEncVals[1]);
    	System.out.println("Lifter"
    			+ "\n	position:"+ lifterEncVals[0]
    			+ "\n	velocity:" + lifterEncVals[1]);
    	System.out.println("Grabber"
    			+ "\n	position:"+ grabberEncVals[0]
    			+ "\n	velocity:" + grabberEncVals[1]);
    	
    	//print encoder values in the smartDashboard
    	SmartDashboard.putNumber("FrontLeftPos", frontLeftEncVals[0]);
    	SmartDashboard.putNumber("FrontRightPos", frontRightEncVals[0]);
    	SmartDashboard.putNumber("BackLeftPos", backLeftEncVals[0]);
    	SmartDashboard.putNumber("BackRightPos", backRightEncVals[0]);
    	SmartDashboard.putNumber("LifterPos", lifterEncVals[0]);
    	SmartDashboard.putNumber("GrabberPos", grabberEncVals[0]);
    	
    	
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
