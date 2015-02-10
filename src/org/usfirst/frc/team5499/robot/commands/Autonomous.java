package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {

	public Autonomous() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.cameraSubsystem);
		requires(Robot.driveTrainSubsystem);
		requires(Robot.grabberSubsystem);
		requires(Robot.lifterSubsystem);
	}

	private DigitalInput startSideSwitch;
	private int totesPickedUp;
	// Called just before this Command runs the first time
	protected void initialize() {
		startSideSwitch = new DigitalInput(RobotMap.startSideSwitchPort);
		totesPickedUp = 0;
		Robot.lifterSubsystem.isLifterSlow = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//take and analyze a picture
		Robot.cameraSubsystem.Execute();

		//starting from the left
		if (startSideSwitch.get()){
			if (Robot.cameraSubsystem.isTote){ //check if a tote has been found
				if (Robot.cameraSubsystem.horizCenterOfTote == 0){//FIXME calibrate
					while(Robot.cameraSubsystem.distToTote < Robot.cameraSubsystem.AUTO_FORWARD_LIMIT){
						addParallel(Robot.moveForward);
					}

					if (totesPickedUp == 0){
						addSequential(Robot.getToTote1);
					} else if (totesPickedUp == 1){
						addSequential(Robot.getToTote2);
						addSequential(Robot.release);
					} else if (totesPickedUp == 2){
						addSequential(Robot.getToTote2);
						addSequential(Robot.release);
					} 


					if (Robot.cameraSubsystem.distToTote >= Robot.cameraSubsystem.AUTO_FORWARD_LIMIT){
						addSequential(Robot.getToTote1);
						addSequential(Robot.grabTote);
						addParallel(Robot.getToTote2);
						addParallel(Robot.moveBackward);
						if (totesPickedUp <= 2)	addParallel(Robot.moveRightward);
					}
				}
				if (totesPickedUp == 3){
					addSequential(Robot.turnAroundLeft);
					while(Robot.driveTrainSubsystem.motorBackRight.getEncPosition() < 250 * 2.5 + 250 * 0.7){ //FIXME calibrate
						addSequential(Robot.moveForward);
					}
					addSequential(Robot.lowerToFloor);
					addSequential(Robot.release);
					while(Robot.driveTrainSubsystem.motorBackRight.getEncPosition() < 250 * 2.5){ //FIXME calibrate
						addSequential(Robot.moveBackward);
					}
					while(Robot.driveTrainSubsystem.motorBackRight.getEncPosition() < 250 * 2.5 + 250){ //FIXME calibrate
						addSequential(Robot.moveRightward);
					}
					while(Robot.driveTrainSubsystem.motorBackRight.getEncPosition() < 250 * 2.5 + 250 * 0.9){ //FIXME calirate
						addSequential(Robot.moveForward);
					}
				}

			} else{
				addSequential(Robot.moveRightward);
			}
			//starting from the right
		} else {
			if (Robot.cameraSubsystem.isTote){ //check if a tote has been found
				if (Robot.cameraSubsystem.horizCenterOfTote == 0){//FIXME calibrate
					while(Robot.cameraSubsystem.distToTote < Robot.cameraSubsystem.AUTO_FORWARD_LIMIT){
						addParallel(Robot.moveForward);
					}

					if (totesPickedUp == 0){
						addSequential(Robot.getToTote1);
					} else if (totesPickedUp == 1){
						addSequential(Robot.getToTote2);
						addSequential(Robot.release);
					} else if (totesPickedUp == 2){
						addSequential(Robot.getToTote2);
						addSequential(Robot.release);
					} 


					if (Robot.cameraSubsystem.distToTote >= Robot.cameraSubsystem.AUTO_FORWARD_LIMIT){
						addSequential(Robot.getToTote1);
						addSequential(Robot.grabTote);
						addParallel(Robot.getToTote2);
						addParallel(Robot.moveBackward);
						if (totesPickedUp <= 2)	addParallel(Robot.moveLeftward);
					}
				}
				if (totesPickedUp == 3){
					addSequential(Robot.turnAroundRight);
					while(Robot.driveTrainSubsystem.motorBackLeft.getEncPosition() < 250 * 2.5 + 250 * 0.7){ //FIXME calibrate
						addSequential(Robot.moveForward);
					}
					addSequential(Robot.lowerToFloor);
					addSequential(Robot.release);
					while(Robot.driveTrainSubsystem.motorBackLeft.getEncPosition() < 250 * 2.5){ //FIXME calibrate
						addSequential(Robot.moveBackward);
					}
					while(Robot.driveTrainSubsystem.motorBackLeft.getEncPosition() < 250 * 2.5 + 250){ //FIXME calibrate
						addSequential(Robot.moveLeftward);
					}
					while(Robot.driveTrainSubsystem.motorBackLeft.getEncPosition() < 250 * 2.5 + 250 * 0.9){ //FIXME calirate
						addSequential(Robot.moveForward);
					}
				}

			} else{
				addSequential(Robot.moveLeftward);
			}

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
