package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This is for testing purposes only
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
    	double[] GrabberEncVals = {Robot.grabberSubsystem.grabberMotor1.getEncPosition(), Robot.grabberSubsystem.grabberMotor1.getEncVelocity()};
    	double[] LifterEncVals = {Robot.lifterSubsystem.lifterMotor1.getEncPosition(), Robot.lifterSubsystem.lifterMotor1.getEncVelocity()};
    	double[] frontLeftEncVals = {Robot.driveTrainSubsystem.motorFrontLeft.getEncPosition(), Robot.driveTrainSubsystem.motorFrontLeft.getEncVelocity()};
    	double[] backLeftEncVals = {Robot.driveTrainSubsystem.motorBackLeft.getEncPosition(), Robot.driveTrainSubsystem.motorBackLeft.getEncVelocity()};
    	double[] frontRightEncVals = {Robot.driveTrainSubsystem.motorFrontRight.getEncPosition(), Robot.driveTrainSubsystem.motorFrontRight.getEncVelocity()};
    	double[] backRightEncVals = {Robot.driveTrainSubsystem.motorBackRight.getEncPosition(), Robot.driveTrainSubsystem.motorBackRight.getEncVelocity()};
    	
    	//Print encoder values in the riolog
    	System.out.println("Grabber	 	position:"+ GrabberEncVals[0] 		+ "		velocity:" + GrabberEncVals[1]);
    	System.out.println("Lifter  	position:"+ LifterEncVals[0] 		+ "		velocity:" + LifterEncVals[1]);
    	System.out.println("FrontLeft 	position:"+ frontLeftEncVals[0] 	+ "		velocity:" + frontLeftEncVals[1]);
    	System.out.println("BackLeft 	position:"+ backLeftEncVals[0] 		+ "		velocity:" + backLeftEncVals[1]);
    	System.out.println("FrontRight 	position:"+ frontRightEncVals[0]	+ "		velocity:" + frontRightEncVals[1]);
    	System.out.println("BackRight 	position:"+ backRightEncVals[0] 	+ "		velocity:" + backRightEncVals[1]);
    	
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
