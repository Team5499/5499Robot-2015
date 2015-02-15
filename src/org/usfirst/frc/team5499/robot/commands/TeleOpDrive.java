package org.usfirst.frc.team5499.robot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

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
    	Robot.lifterSubsystem.isLifterSlow = true;    	
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
        
        Robot.driveTrainSubsystem.motorBackLeft.set(1);
        Robot.driveTrainSubsystem.motorBackRight.set(1);
        Robot.driveTrainSubsystem.motorFrontLeft.set(1);
        Robot.driveTrainSubsystem.motorFrontRight.set(1);
        
    	
    	
    	//slow mode toggle for the lifter
    	if (Robot.oi.stick.getRawButton(Robot.oi.slowLifterButton)){
    		Robot.lifterSubsystem.isLifterSlow ^= true; //XOR bitwise operation <a>http://en.wikipedia.org/wiki/Exclusive_or</a>
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
    	} else if (Robot.oi.stick.getPOV() == Robot.oi.grabberOpenDeg1){
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
    	
//    	if (Robot.oi.stick.getRawButton(5)) Robot.lifterSubsystem.Raise();
//    	else if (Robot.oi.stick.getRawButton(6)) Robot.lifterSubsystem.Lower();
    	
    	
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
//    	
    	
    	try{
    		File file = new File("/usr/PIDVals.txt");
    		String backLeftString = "BackLeft\n"
    				+ "\n	P: " + String.valueOf(Robot.driveTrainSubsystem.motorBackLeft.getP())
    				+ "\n	I: " + String.valueOf(Robot.driveTrainSubsystem.motorBackLeft.getI())
    				+ "\n	D: " + String.valueOf(Robot.driveTrainSubsystem.motorBackLeft.getD())
    				+ "\n	F: " + String.valueOf(Robot.driveTrainSubsystem.motorBackLeft.getF())
    				+ "\n	Izone: " + String.valueOf(Robot.driveTrainSubsystem.motorBackLeft.getIZone())
    				+ "\n	EncVel: "+ String.valueOf(Robot.driveTrainSubsystem.motorBackLeft.getEncVelocity())
    				+ "\n	EncPos: " + String.valueOf(Robot.driveTrainSubsystem.motorBackLeft.getEncPosition());
    		String backRightString = "\nBackRight"
    				+ "\n	P: " + String.valueOf(Robot.driveTrainSubsystem.motorBackRight.getP())
    				+ "\n	I: " + String.valueOf(Robot.driveTrainSubsystem.motorBackRight.getI())
    				+ "\n	D: " + String.valueOf(Robot.driveTrainSubsystem.motorBackRight.getD())
    				+ "\n	F: " + String.valueOf(Robot.driveTrainSubsystem.motorBackRight.getF())
    				+ "\n	Izone: " + String.valueOf(Robot.driveTrainSubsystem.motorBackRight.getIZone())
    				+ "\n	EncVel: "+ String.valueOf(Robot.driveTrainSubsystem.motorBackRight.getEncVelocity())
    				+ "\n	EncPos: " + String.valueOf(Robot.driveTrainSubsystem.motorBackRight.getEncPosition());
    		String frontLeftString = "\nFrontLeft"
    				+ "\n	P: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontLeft.getP())
    				+ "\n	I: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontLeft.getI())
    				+ "\n	D: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontLeft.getD())
    				+ "\n	F: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontLeft.getF())
    				+ "\n	Izone: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontLeft.getIZone())
    				+ "\n	EncVel: "+ String.valueOf(Robot.driveTrainSubsystem.motorFrontLeft.getEncVelocity())
    				+ "\n	EncPos: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontLeft.getEncPosition());
    		String frontRightString = "\nFrontRight\n"
    				+ "\n	P: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontRight.getP())
    				+ "\n	I: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontRight.getI())
    				+ "\n	D: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontRight.getD())
    				+ "\n	F: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontRight.getF())
    				+ "\n	Izone: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontRight.getIZone())
    				+ "\n	EncVel: "+ String.valueOf(Robot.driveTrainSubsystem.motorFrontRight.getEncVelocity())
    				+ "\n	EncPos: " + String.valueOf(Robot.driveTrainSubsystem.motorFrontRight.getEncPosition());
    		
    		FileWriter fw = new FileWriter(file.getAbsoluteFile());
    		BufferedWriter bw = new BufferedWriter(fw);
    		bw.write(backLeftString);
    		bw.write(backRightString);
    		bw.write(frontLeftString);
    		bw.write(frontRightString);
    		bw.close();
    		
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    	
    	
    	int p = 0;
    	int i = 1;
    	int d = 2;
    	int f = 3;
    	int izone = 4;
    	int ramp = 5;
    	
    	double[] frontLeft = {RobotMap.p[RobotMap.frontLeftWheelnum],
    			RobotMap.i[RobotMap.frontLeftWheelnum],
    			RobotMap.d[RobotMap.frontLeftWheelnum],
    			RobotMap.f[RobotMap.frontLeftWheelnum],
    			RobotMap.izone[RobotMap.frontLeftWheelnum],
    			RobotMap.ramp[RobotMap.frontLeftWheelnum]};
    	double[] frontRight = {RobotMap.p[RobotMap.frontRightWheelnum],
    			RobotMap.i[RobotMap.frontRightWheelnum],
    			RobotMap.d[RobotMap.frontRightWheelnum],
    			RobotMap.f[RobotMap.frontRightWheelnum],
    			RobotMap.izone[RobotMap.frontRightWheelnum],
    			RobotMap.ramp[RobotMap.frontRightWheelnum]};
    	double[] backLeft = {RobotMap.p[RobotMap.backLeftWheelnum],
    			RobotMap.i[RobotMap.backLeftWheelnum],
    			RobotMap.d[RobotMap.backLeftWheelnum],
    			RobotMap.f[RobotMap.backLeftWheelnum],
    			RobotMap.izone[RobotMap.backLeftWheelnum],
    			RobotMap.ramp[RobotMap.backLeftWheelnum]};
    	double[] backRight = {RobotMap.p[RobotMap.backRightWheelnum],
    			RobotMap.i[RobotMap.backRightWheelnum],
    			RobotMap.d[RobotMap.backRightWheelnum],
    			RobotMap.f[RobotMap.backRightWheelnum],
    			RobotMap.izone[RobotMap.backRightWheelnum],
    			RobotMap.ramp[RobotMap.backRightWheelnum]};
    	double[] lifter1 = {RobotMap.p[RobotMap.lifterMotor1num],
    			RobotMap.i[RobotMap.lifterMotor1num],
    			RobotMap.d[RobotMap.lifterMotor1num],
    			RobotMap.f[RobotMap.lifterMotor1num],
    			RobotMap.izone[RobotMap.lifterMotor1num],
    			RobotMap.ramp[RobotMap.lifterMotor1num]};
    	double[] grabber1 = {RobotMap.p[RobotMap.grabberMotor1num],
    			RobotMap.i[RobotMap.grabberMotor1num],
    			RobotMap.d[RobotMap.grabberMotor1num],
    			RobotMap.f[RobotMap.grabberMotor1num],
    			RobotMap.izone[RobotMap.grabberMotor1num],
    			RobotMap.ramp[RobotMap.grabberMotor1num]};
    	
    	System.out.println("FrontLeft"
    			+ "\n	p: " + frontLeft[p]
    			+ "\n	i: " + frontLeft[i]
    			+ "\n	d: " + frontLeft[d]
    			+ "\n	f: " + frontLeft[f]
    			+ "\n	izone: " + frontLeft[izone]
    			+ "\n	ramp: " + frontLeft[ramp]);
    	System.out.println("FrontRight"
    			+ "\n	p: " + frontRight[p]
    			+ "\n	i: " + frontRight[i]
    			+ "\n	d: " + frontRight[d]
    			+ "\n	f: " + frontRight[f]
    			+ "\n	izone: " + frontRight[izone]
    			+ "\n	ramp: " + frontRight[ramp]);
    	System.out.println("BackLeft"
    			+ "\n	p: " + backLeft[p]
    			+ "\n	i: " + backLeft[i]
    			+ "\n	d: " + backLeft[d]
    			+ "\n	f: " + backLeft[f]
    			+ "\n	izone: " + backLeft[izone]
    			+ "\n	ramp: " + backLeft[ramp]);
    	System.out.println("BackRight"
    			+ "\n	p: " + backRight[p]
    			+ "\n	i: " + backRight[i]
    			+ "\n	d: " + backRight[d]
    			+ "\n	f: " + backRight[f]
    			+ "\n	izone: " + backRight[izone]
    			+ "\n	ramp: " + backRight[ramp]);
    	System.out.println("Lifter1"
    			+ "\n	p: " + lifter1[p]
    			+ "\n	i: " + lifter1[i]
    			+ "\n	d: " + lifter1[d]
    			+ "\n	f: " + lifter1[f]
    			+ "\n	izone: " + lifter1[izone]
    			+ "\n	ramp: " + lifter1[ramp]);
    	System.out.println("Grabber1"
    			+ "\n	p: " + grabber1[p]
    			+ "\n	i: " + grabber1[i]
    			+ "\n	d: " + grabber1[d]
    			+ "\n	f: " + grabber1[f]
    			+ "\n	izone: " + grabber1[izone]
    			+ "\n	ramp: " + grabber1[ramp]);
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
