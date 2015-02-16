
package org.usfirst.frc.team5499.robot.commands;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command is for motor-rating purposes only
 * As of 1/20, 4 motors have been rated -- 3 CIMs and 1 mini-CIM
 */
public class RateMotors extends Command {
	// counter for counting the number of times the Riolog has been updated
	int counter;

	public RateMotors() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrainSubsystem);

	}

	// Called just before this Command runs the first time
	protected void initialize() {

		counter = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//    	double X = Robot.oi.stick.getX();
		//    	double Y = Robot.oi.stick.getY();
		//    	double Z = Robot.oi.stick.getZ();




		//This gets weird results. Better use the Driverstation. The results there seem to be more accurate.
		double volts = Robot.pdp.getVoltage();

		//the channel should be the channel on the pdp, not the can					//PDPChannel for quick reference
		double current0 = Robot.pdp.getCurrent(RobotMap.motorBackLeftPDPChannel);	//1
		double current1 = Robot.pdp.getCurrent(RobotMap.motorBackRightPDPChannel);	//13
		double current2 = Robot.pdp.getCurrent(RobotMap.motorFrontLeftPDPChannel);	//15
		double current3 = Robot.pdp.getCurrent(RobotMap.motorFrontRightPDPChannel);	//0
		double current4 = Robot.pdp.getCurrent(RobotMap.lifter1PDPChannel);			//13
		double current5 = Robot.pdp.getCurrent(RobotMap.lifter2PDPChannel);			//12


		//Print out current on each of the PDPChannels
		System.out.println(" " + current0 + " "
				+ current1 + " "
				+ current2 + " "
				+ current3 + " "
				+ current4 + " "
				+ current5 + "\n");
		//Print out voltage. This value seems to be incorrect, please use the values presented in the Driverstation
		//This might be because of the wiring of the battery to the PDP. The web interface says the same voltage as this method.
		System.out.println(volts);

		//Break motors in
		while(Timer.getMatchTime() < 1800){
			//Set a particular motor to run					  //Motor controller id for quick reference
			System.out.println(Timer.getMatchTime()); 		  //Print how long the running has been for 
			Robot.driveTrainSubsystem.motorFrontLeft.set(-1);  //1
			Robot.driveTrainSubsystem.motorFrontRight.set(-1); //2
			Robot.driveTrainSubsystem.motorBackLeft.set(-1);   //3
			Robot.driveTrainSubsystem.motorBackRight.set(-1);  //4
			Robot.lifterSubsystem.lifterMotor1.set(-1);		  //7
		}






		counter++;
		System.out.println("Counter: " + counter);




		//Robot.driveTrainSubsystem.motorFrontRight.set(1);
		//Robot.driveTrainSubsystem.motorBackRight.set(1);

		//
		//Robot.driveTrainSubsystem.motorBackRight.set(0.1);
		//Robot.driveTrainSubsystem.move(-.5,-.5,0);
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
