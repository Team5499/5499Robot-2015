//package org.usfirst.frc.team5499.robot.commands;
//
//import org.usfirst.frc.team5499.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class GetToTote1 extends Command {
//
//	public GetToTote1() {
//		// Use requires() here to declare subsystem dependencies
//		// eg. requires(chassis);
//	}
//
//	// Called just before this Command runs the first time
//	protected void initialize() {
//	}
//
//	// Called repeatedly when this Command is scheduled to run
//	protected void execute() {
//		Robot.lifterSubsystem.GetToTote1();
//	}
//
//	// Make this return true when this Command no longer needs to run execute()
//	protected boolean isFinished() {
//		if(Robot.lifterSubsystem.lifterMotor1.getEncPosition() == Robot.lifterSubsystem.TOTE1LIMIT){
//			return true;
//		}else {
//			return false;
//		}
//	}
//
//	// Called once after isFinished returns true
//	protected void end() {
//	}
//
//	// Called when another command which requires one or more of the same
//	// subsystems is scheduled to run
//	protected void interrupted() {
//	}
//}
