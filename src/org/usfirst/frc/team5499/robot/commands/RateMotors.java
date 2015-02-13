
package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * For testing purposes only
 */
public class RateMotors extends Command {
	// counter for counting the number of times the Riolog has been updated
	int counter;

	public RateMotors() {
		// use requires() here to declare subsystem dependencies
		requires(Robot.driveTrainSubsystem);

	}

	// called just before this Command runs the first time
	protected void initialize() {
		counter = 0;
	}

	// called repeatedly when this Command is scheduled to run
	protected void execute() {
		//the channel should be the channel on the pdp, not the can					
		double current0 = Robot.pdp.getCurrent(RobotMap.motorBackLeftPDPChannel);
		double current1 = Robot.pdp.getCurrent(RobotMap.motorBackRightPDPChannel);
		double current2 = Robot.pdp.getCurrent(RobotMap.motorFrontLeftPDPChannel);
		double current3 = Robot.pdp.getCurrent(RobotMap.motorFrontRightPDPChannel);
		double current4 = Robot.pdp.getCurrent(RobotMap.lifter1PDPChannel);
		double current5 = Robot.pdp.getCurrent(RobotMap.lifter2PDPChannel);


		//print out current on each of the PDPChannels
		System.out.println(" " + current0 + " "
				+ current1 + " "
				+ current2 + " "
				+ current3 + " "
				+ current4 + " "
				+ current5 + "\n");

		//break motors in
		while(Timer.getMatchTime() < 1800){
			System.out.println(Timer.getMatchTime()); 		  	//print how long the running has been for
			//set a particular motor to run					  	//motor controller id for quick reference
			Robot.driveTrainSubsystem.motorBackRight.set(-1);  	//1
			Robot.driveTrainSubsystem.motorBackLeft.set(-1); 	//2
			Robot.driveTrainSubsystem.motorFrontRight.set(-1);  //3
			Robot.driveTrainSubsystem.motorFrontLeft.set(-1);  	//4
			Robot.lifterSubsystem.lifterMotor1.set(-1);		  	//7

		}

		//update count
		counter++;
		
		//print number of updates
		System.out.println("Counter: " + counter);
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
