package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class TestPIDVals extends Command {

    public TestPIDVals() {
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
    protected void execute(){
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
    	double[] lifter2 = {RobotMap.p[RobotMap.lifterMotor2num],
    			RobotMap.i[RobotMap.lifterMotor2num],
    			RobotMap.d[RobotMap.lifterMotor2num],
    			RobotMap.f[RobotMap.lifterMotor2num],
    			RobotMap.izone[RobotMap.lifterMotor2num],
    			RobotMap.ramp[RobotMap.lifterMotor2num]};
    	double[] grabber1 = {RobotMap.p[RobotMap.grabberMotor1num],
    			RobotMap.i[RobotMap.grabberMotor1num],
    			RobotMap.d[RobotMap.grabberMotor1num],
    			RobotMap.f[RobotMap.grabberMotor1num],
    			RobotMap.izone[RobotMap.grabberMotor1num],
    			RobotMap.ramp[RobotMap.grabberMotor1num]};
    	double[] grabber2 = {RobotMap.p[RobotMap.grabberMotor2num],
    			RobotMap.i[RobotMap.grabberMotor2num],
    			RobotMap.d[RobotMap.grabberMotor2num],
    			RobotMap.f[RobotMap.grabberMotor2num],
    			RobotMap.izone[RobotMap.grabberMotor2num],
    			RobotMap.ramp[RobotMap.grabberMotor2num]};
    	
    	System.out.println("FrontLeft"
    			+ "\n	p:" + frontLeft[p]
    			+ "\n	i:" + frontLeft[i]
    			+ "\n	d:" + frontLeft[d]
    			+ "\n	f:" + frontLeft[f]
    			+ "\n	izone:" + frontLeft[izone]
    			+ "\n	ramp:" + frontLeft[ramp]);
    	System.out.println("FrontRight"
    			+ "\n	p:" + frontRight[p]
    			+ "\n	i:" + frontRight[i]
    			+ "\n	d:" + frontRight[d]
    			+ "\n	f:" + frontRight[f]
    			+ "\n	izone:" + frontRight[izone]
    			+ "\n	ramp:" + frontRight[ramp]);
    	System.out.println("BackLeft"
    			+ "\n	p:" + backLeft[p]
    			+ "\n	i:" + backLeft[i]
    			+ "\n	d:" + backLeft[d]
    			+ "\n	f:" + backLeft[f]
    			+ "\n	izone:" + backLeft[izone]
    			+ "\n	ramp:" + backLeft[ramp]);
    	System.out.println("BackRight"
    			+ "\n	p:" + backRight[p]
    			+ "\n	i:" + backRight[i]
    			+ "\n	d:" + backRight[d]
    			+ "\n	f:" + backRight[f]
    			+ "\n	izone:" + backRight[izone]
    			+ "\n	ramp:" + backRight[ramp]);
    	System.out.println("Lifter1"
    			+ "\n	p:" + lifter1[p]
    			+ "\n	i:" + lifter1[i]
    			+ "\n	d:" + lifter1[d]
    			+ "\n	f:" + lifter1[f]
    			+ "\n	izone:" + lifter1[izone]
    			+ "\n	ramp:" + lifter1[ramp]);
    	System.out.println("Lifter2"
    			+ "\n	p:" + lifter2[p]
    			+ "\n	i:" + lifter2[i]
    			+ "\n	d:" + lifter2[d]
    			+ "\n	f:" + lifter2[f]
    			+ "\n	izone:" + lifter2[izone]
    			+ "\n	ramp:" + lifter2[ramp]);
    	System.out.println("Grabber1"
    			+ "\n	p:" + grabber1[p]
    			+ "\n	i:" + grabber1[i]
    			+ "\n	d:" + grabber1[d]
    			+ "\n	f:" + grabber1[f]
    			+ "\n	izone:" + grabber1[izone]
    			+ "\n	ramp:" + grabber1[ramp]);
    	System.out.println("Grabber2"
    			+ "\n	p:" + grabber2[p]
    			+ "\n	i:" + grabber2[i]
    			+ "\n	d:" + grabber2[d]
    			+ "\n	f:" + grabber2[f]
    			+ "\n	izone:" + grabber2[izone]
    			+ "\n	ramp:" + grabber2[ramp]);
    	
    	SmartDashboard.putNumber("frontLeft P", frontLeft[p]);
    	SmartDashboard.putNumber("frontLeft I", frontLeft[i]);
    	SmartDashboard.putNumber("frontLeft D", frontLeft[d]);
    	SmartDashboard.putNumber("frontLeft F", frontLeft[f]);
    	SmartDashboard.putNumber("frontLeft IZONE", frontLeft[izone]);
    	SmartDashboard.putNumber("frontLeft RAMP", frontLeft[ramp]);
    	
    	SmartDashboard.putNumber("frontRight P", frontRight[p]);
    	SmartDashboard.putNumber("frontRight I", frontRight[i]);
    	SmartDashboard.putNumber("frontRight D", frontRight[d]);
    	SmartDashboard.putNumber("frontRight F", frontRight[f]);
    	SmartDashboard.putNumber("frontRight IZONE", frontRight[izone]);
    	SmartDashboard.putNumber("frontRight RAMP", frontRight[ramp]);
    	
    	SmartDashboard.putNumber("backRight P", backRight[p]);
    	SmartDashboard.putNumber("backRight I", backRight[i]);
    	SmartDashboard.putNumber("backRight D", backRight[d]);
    	SmartDashboard.putNumber("backRight F", backRight[f]);
    	SmartDashboard.putNumber("backRight IZONE", backRight[izone]);
    	SmartDashboard.putNumber("backRight ramp", backRight[ramp]);
    	
    	SmartDashboard.putNumber("backLeft P", backLeft[p]);
    	SmartDashboard.putNumber("backLeft I", backLeft[i]);
    	SmartDashboard.putNumber("backLeft D", backLeft[d]);
    	SmartDashboard.putNumber("backLeft F", backLeft[f]);
    	SmartDashboard.putNumber("backLeft IZONE", backLeft[izone]);
    	SmartDashboard.putNumber("backLeft RAMP", backLeft[ramp]);
    	
    	SmartDashboard.putNumber("lifter1 P", lifter1[p]);
    	SmartDashboard.putNumber("lifter1 I", lifter1[i]);
    	SmartDashboard.putNumber("lifter1 D", lifter1[d]);
    	SmartDashboard.putNumber("lifter1 F", lifter1[f]);
    	SmartDashboard.putNumber("lifter1 IZONE", lifter1[izone]);
    	SmartDashboard.putNumber("lifter1 RAMP", lifter1[ramp]);

    	SmartDashboard.putNumber("lifter2 P", lifter2[p]);
    	SmartDashboard.putNumber("lifter2 I", lifter2[i]);
    	SmartDashboard.putNumber("lifter2 D", lifter2[d]);
    	SmartDashboard.putNumber("lifter2 F", lifter2[f]);
    	SmartDashboard.putNumber("lifter2 IZONE", lifter2[izone]);
    	SmartDashboard.putNumber("lifter2 RAMP", lifter2[ramp]);
    	
    	SmartDashboard.putNumber("grabber1 P", grabber1[p]);
    	SmartDashboard.putNumber("grabber1 I", grabber1[i]);
    	SmartDashboard.putNumber("grabber1 D", grabber1[d]);
    	SmartDashboard.putNumber("grabber1 F", grabber1[f]);
    	SmartDashboard.putNumber("grabber1 IZONE", grabber1[izone]);
    	SmartDashboard.putNumber("grabber1 RAMP", grabber1[ramp]);

    	SmartDashboard.putNumber("grabber2 P", grabber2[p]);
    	SmartDashboard.putNumber("grabber2 I", grabber2[i]);
    	SmartDashboard.putNumber("grabber2 D", grabber2[d]);
    	SmartDashboard.putNumber("grabber2 F", grabber2[f]);
    	SmartDashboard.putNumber("grabber2 IZONE", grabber2[izone]);
    	SmartDashboard.putNumber("grabber2 RAMP", grabber2[ramp]);
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
