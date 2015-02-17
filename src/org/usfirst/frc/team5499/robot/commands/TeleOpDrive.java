package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	Robot.lifterSubsystem.isLifterSlow = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double X = Robot.oi.stick.getX();
    	double Y = Robot.oi.stick.getY();
        double Z; 
        
        boolean isZActivated;
    	
    	//Make it so that rotation is deliberate thru making the driver press a button to be able to rotate
    	if(Robot.oi.stick.getRawButton(Robot.oi.rotateButton)){
    		Z = Robot.oi.stick.getTwist();
    		isZActivated = true;
    	}else{
    		Z = 0;
    		isZActivated = false;
    	}
    	
    	
    	//slow mode toggle for the lifter
    	if (Robot.oi.stick.getRawButton(Robot.oi.slowLifterButton)){
    		Robot.lifterSubsystem.isLifterSlow = !Robot.lifterSubsystem.isLifterSlow; 
    		System.out.println("IsLifterSlow: " + Robot.lifterSubsystem.isLifterSlow);
    	}

    	//Call the move method to actually set the motors' speeds
//    	Robot.driveTrainSubsystem.mecanumDrive.mecanumDrive_Cartesian(X, Y, Z, 0.0);
    	
    	
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
    	} else if (Robot.oi.stick.getRawButton(Robot.oi.grabberOpenButton)){
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
    		
    	
    	
    	//Inform driver about active modes
    	SmartDashboard.putBoolean("Z is activated", isZActivated); //is translation activated
    	SmartDashboard.putBoolean("Lifter on slow", Robot.lifterSubsystem.isLifterSlow); //is lifter on slow mode
    	
    	
/*TESTING TESTING TESTING TESTING*/
    	//Store the encoder values
//    	double[] frontLeftEncVals = {Robot.driveTrainSubsystem.motorFrontLeft.getEncPosition(),
//    			Robot.driveTrainSubsystem.motorFrontLeft.getEncVelocity()};
//    	double[] frontRightEncVals = {Robot.driveTrainSubsystem.motorFrontRight.getEncPosition(),
//    			Robot.driveTrainSubsystem.motorFrontRight.getEncVelocity()};
//    	double[] backLeftEncVals = {Robot.driveTrainSubsystem.motorBackLeft.getEncPosition(),
//    			Robot.driveTrainSubsystem.motorBackLeft.getEncVelocity()};
//    	double[] backRightEncVals = {Robot.driveTrainSubsystem.motorBackRight.getEncPosition(),
//    			Robot.driveTrainSubsystem.motorBackRight.getEncVelocity()};
//    	double[] lifterEncVals = {Robot.lifterSubsystem.lifterMotor1.getEncPosition(),
//    			Robot.lifterSubsystem.lifterMotor1.getEncVelocity()};
//    	double[] grabberEncVals = {Robot.grabberSubsystem.grabberMotor1.getEncPosition(),
//    			Robot.grabberSubsystem.grabberMotor1.getEncVelocity()};
//    	
//    	
//    	//Print encoder values in the riolog
//    	System.out.println("FrontLeft"
//    			+ "\n	position: "+ frontLeftEncVals[0]
//    			+ "\n	velocity: " + frontLeftEncVals[1]);
//    	System.out.println("FrontRight"
//    			+ "\n	position: "+ frontRightEncVals[0]
//    			+ "\n	velocity: " + frontRightEncVals[1]);
//    	System.out.println("BackLeft"
//    			+ "\n	position: "+ backLeftEncVals[0]
//    			+ "\n	velocity: " + backLeftEncVals[1]);
//    	System.out.println("BackRight"
//    			+ "\n	position: "+ backRightEncVals[0]
//    			+ "\n	velocity: " + backRightEncVals[1]);
//    	System.out.println("Lifter"
//    			+ "\n	position: "+ lifterEncVals[0]
//    			+ "\n	velocity: " + lifterEncVals[1]);
//    	System.out.println("Grabber"
//    			+ "\n	position: "+ grabberEncVals[0]
//    			+ "\n	velocity: " + grabberEncVals[1]);
    	
//    	System.out.println("FrontLeft"
//    			+ "\n	p: " + Robot.driveTrainSubsystem.motorFrontLeft.getP()
//    			+ "\n	i: " + Robot.driveTrainSubsystem.motorFrontLeft.getI()
//    			+ "\n	d: " + Robot.driveTrainSubsystem.motorFrontLeft.getD()
//    			+ "\n	f: " + Robot.driveTrainSubsystem.motorFrontLeft.getF()
//    			+ "\n	izone: " + Robot.driveTrainSubsystem.motorFrontLeft.getIZone()
//    			+ "\n	ramp: " + Robot.driveTrainSubsystem.motorFrontLeft.getCloseLoopRampRate()
//    			+ "\n	speed: " + Robot.driveTrainSubsystem.motorFrontLeft.get());
//    	System.out.println("FrontRight"
//    			+ "\n	p: " + Robot.driveTrainSubsystem.motorFrontRight.getP()
//    			+ "\n	i: " + Robot.driveTrainSubsystem.motorFrontRight.getI()
//    			+ "\n	d: " + Robot.driveTrainSubsystem.motorFrontRight.getD()
//    			+ "\n	f: " + Robot.driveTrainSubsystem.motorFrontRight.getF()
//    			+ "\n	izone: " + Robot.driveTrainSubsystem.motorFrontRight.getIZone()
//    			+ "\n	ramp: " + Robot.driveTrainSubsystem.motorFrontRight.getCloseLoopRampRate()
//    			+ "\n	speed: " + Robot.driveTrainSubsystem.motorFrontRight.get());
//    	System.out.println("BackLeft"
//    			+ "\n	p: " + Robot.driveTrainSubsystem.motorBackLeft.getP()
//    			+ "\n	i: " + Robot.driveTrainSubsystem.motorBackLeft.getI()
//    			+ "\n	d: " + Robot.driveTrainSubsystem.motorBackLeft.getD()
//    			+ "\n	f: " + Robot.driveTrainSubsystem.motorBackLeft.getF()
//    			+ "\n	izone: " + Robot.driveTrainSubsystem.motorBackLeft.getIZone()
//    			+ "\n	ramp: " + Robot.driveTrainSubsystem.motorBackLeft.getCloseLoopRampRate()
//    			+ "\n	speed: " + Robot.driveTrainSubsystem.motorBackLeft.get());
//    	System.out.println("BackRight"
//    			+ "\n	p: " + Robot.driveTrainSubsystem.motorBackRight.getP()
//    			+ "\n	i: " + Robot.driveTrainSubsystem.motorBackRight.getI()
//    			+ "\n	d: " + Robot.driveTrainSubsystem.motorBackRight.getD()
//    			+ "\n	f: " + Robot.driveTrainSubsystem.motorBackRight.getF()
//    			+ "\n	izone: " + Robot.driveTrainSubsystem.motorBackRight.getIZone()
//    			+ "\n	ramp: " + Robot.driveTrainSubsystem.motorBackRight.getCloseLoopRampRate()
//    			+ "\n	speed: " + Robot.driveTrainSubsystem.motorBackRight.get());
//    	System.out.println("Lifter"
//    			+ "\n	p: " + Robot.lifterSubsystem.lifterMotor.getP()
//    			+ "\n	i: " + Robot.lifterSubsystem.lifterMotor.getI()
//    			+ "\n	d: " + Robot.lifterSubsystem.lifterMotor.getD()
//    			+ "\n	f: " + Robot.lifterSubsystem.lifterMotor.getF()
//    			+ "\n	izone: " + Robot.lifterSubsystem.lifterMotor.getIZone()
//    			+ "\n	ramp: " + Robot.lifterSubsystem.lifterMotor.getCloseLoopRampRate()
//    			+ "\n	speed: " + Robot.lifterSubsystem.lifterMotor.get());
//    	System.out.println("Grabber"
//    			+ "\n	p: " + Robot.grabberSubsystem.grabberMotor.getP()
//    			+ "\n	i: " + Robot.grabberSubsystem.grabberMotor.getI()
//    			+ "\n	d: " + Robot.grabberSubsystem.grabberMotor.getD()
//    			+ "\n	f: " + Robot.grabberSubsystem.grabberMotor.getF()
//    			+ "\n	izone: " + Robot.grabberSubsystem.grabberMotor.getIZone()
//    			+ "\n	ramp: " + Robot.grabberSubsystem.grabberMotor.getCloseLoopRampRate()
//    			+ "\n	speed: " + Robot.grabberSubsystem.grabberMotor.get());
/*TESTING TESTING TESTING TESTING*/
    	
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
