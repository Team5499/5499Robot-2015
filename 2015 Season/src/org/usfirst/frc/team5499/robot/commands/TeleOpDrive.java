package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
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

	Timer timer;
	PowerDistributionPanel pdp;
	
	public boolean slow;

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.lifterSubsystem.isLifterSlow = false;
		timer = new Timer();
		pdp = new PowerDistributionPanel();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//RoboRio IP: 169.254.36.107
		//RoboRio Web Interface address: http://roborio-5499.local/
		
		
		double X = Robot.oi.stick.getX();
		double Y = Robot.oi.stick.getY();
		double ZRaw;
		double Z; 

//		boolean isZActivated;

		//Make it so that rotation is deliberate thru making the driver press a button to be able to rotate

		if(Robot.oi.stick.getRawButton(Robot.oi.rotateButton)){
			ZRaw = Robot.oi.stick.getTwist();
			if(ZRaw > 0){
				Z = ZRaw*ZRaw;				
			} else {
				Z = -1*ZRaw*ZRaw;
			}
			
//			isZActivated = true;
		}else{
			Z = 0;
//			isZActivated = false;
		}
		//Joystick X,y and Z are backwards, so all of them are multiplied by -1
		X = -1 * X;
		Y = -1 * Y;
		
		if(X > 0){
			X = X*X;
		}else{
			X = -1 * X * X;
		}
		if(Y>0){
			Y=Y*Y;
		}else{
			Y = -1 * Y * Y;
		}

		//slow mode toggle for the lifter
		if (Robot.oi.stick.getRawButton(Robot.oi.slowLifterButton)){
			if(timer.hasPeriodPassed(0.2)){
				Robot.lifterSubsystem.isLifterSlow = !Robot.lifterSubsystem.isLifterSlow; 
//				System.out.println("IsLifterSlow: " + Robot.lifterSubsystem.isLifterSlow);
			}
		}

		//System.out.println(pdp.getCurrent(13));
		Robot.driveTrainSubsystem.move(X, Y, Z, Robot.oi.stick.getThrottle());

		//Lifter binding
		if(Robot.oi.stick.getPOV() == Robot.oi.lifterRaiseDeg){
			Robot.lifterSubsystem.Raise();
		} else if (Robot.oi.stick.getPOV() == Robot.oi.lifterLowerDeg){
			Robot.lifterSubsystem.Lower();   		
//		} else if (Robot.oi.stick.getRawButton(Robot.oi.lowerToFloorButton)){
//			Robot.lifterSubsystem.LowerToFloor();
		} else{
			Robot.lifterSubsystem.Hold();
		}

		//Grabber binding
		if (Robot.oi.stick.getRawButton(Robot.oi.grabberCloseButton)){
			Robot.grabberSubsystem.Close();
		} else if (Robot.oi.stick.getRawButton(Robot.oi.grabberOpenButton)){
			Robot.grabberSubsystem.Open();
		} else if (Robot.oi.stick.getRawButton(Robot.oi.releaseButton)){
			Robot.grabberSubsystem.Release();
		} else{
			Robot.grabberSubsystem.Hold();
		}
		
//		Robot.driveTrainSubsystem.motorBackLeft.changeControlMode(ControlMode.Position);;
//		Robot.driveTrainSubsystem.motorBackLeft.set(-100);

//		Robot.driveTrainSubsystem.motorBackLeft.enableControl();
//		Robot.driveTrainSubsystem.motorBackRight.enableControl();
//		Robot.driveTrainSubsystem.motorFrontLeft.enableControl();
//		Robot.driveTrainSubsystem.motorFrontRight.enableControl();
//		System.out.println(Robot.driveTrainSubsystem.motorBackLeft.getEncVelocity());
//		SmartDashboard.putNumber("EncPos", Robot.driveTrainSubsystem.motorBackLeft.getEncPosition());
//		if(Robot.oi.stick.getRawButton(Robot.oi.slowDriveButton)){
//			
//		}

//		Robot.driveTrainSubsystem.motorBackLeft.getEncVelocity();
		
//		//Inform driver about active modes
//		SmartDashboard.putBoolean("Z is activated", isZActivated); //is translation activated
//		SmartDashboard.putBoolean("Lifter on slow", Robot.lifterSubsystem.isLifterSlow); //is lifter on slow mode    	
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
