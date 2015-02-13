
package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.robot.commands.GetToTote1;
import org.usfirst.frc.team5499.robot.commands.GetToTote2;
import org.usfirst.frc.team5499.robot.commands.GetToTote3;
import org.usfirst.frc.team5499.robot.commands.GrabTote;
import org.usfirst.frc.team5499.robot.commands.LowerToFloor;
import org.usfirst.frc.team5499.robot.commands.MoveBackward;
import org.usfirst.frc.team5499.robot.commands.MoveForward;
import org.usfirst.frc.team5499.robot.commands.MoveLeftward;
import org.usfirst.frc.team5499.robot.commands.MoveRightward;
import org.usfirst.frc.team5499.robot.commands.RateMotors;
import org.usfirst.frc.team5499.robot.commands.Release;
import org.usfirst.frc.team5499.robot.commands.RotateLeft;
import org.usfirst.frc.team5499.robot.commands.RotateRight;
import org.usfirst.frc.team5499.robot.commands.TeleOpDrive;
import org.usfirst.frc.team5499.robot.commands.TurnAroundLeft;
import org.usfirst.frc.team5499.robot.commands.TurnAroundRight;
import org.usfirst.frc.team5499.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team5499.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team5499.robot.subsystems.GrabberSubsystem;
import org.usfirst.frc.team5499.robot.subsystems.LifterSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static PowerDistributionPanel pdp;
	

	Command autonomousCommand;
	public Command teleopCommand;

	public static Command moveForward;
	public static Command moveBackward;
	public static Command moveRightward;
	public static Command moveLeftward;
	public static Command rotateRight;
	public static Command rotateLeft;
	
	public static Command getToTote1;
	public static Command getToTote2;
	public static Command getToTote3;
	
	public static Command grabTote;
	
	public static Command release;
	public static Command turnAroundLeft;
	public static Command turnAroundRight;
	public static Command lowerToFloor;
	
	public static CameraSubsystem cameraSubsystem;
	public static DrivetrainSubsystem driveTrainSubsystem;
	public static GrabberSubsystem grabberSubsystem;
	public static LifterSubsystem lifterSubsystem;
	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		// instantiate the command used for the autonomous period
		cameraSubsystem = new CameraSubsystem();
		driveTrainSubsystem = new DrivetrainSubsystem();
		pdp = new PowerDistributionPanel();
		lifterSubsystem = new LifterSubsystem();
		//Auto Commands
		moveForward = new MoveForward();
		moveBackward = new MoveBackward();
		moveRightward = new MoveRightward();
		moveLeftward = new MoveLeftward();
		rotateRight = new RotateRight();
		rotateLeft = new RotateLeft();
		
		getToTote1 = new GetToTote1();
		getToTote2 = new GetToTote2();
		getToTote3 = new GetToTote3();
		
		grabTote = new GrabTote();
		
		release = new Release();
		turnAroundLeft = new TurnAroundLeft();
		turnAroundRight = new TurnAroundRight();
		lowerToFloor = new LowerToFloor();
		
		//Commands
		autonomousCommand = new RateMotors();
		teleopCommand = new TeleOpDrive();

		//The Talons are on break mode, which is ideal for our purpose.
		//However, sudden breaking is bad for the gears, so this should gradually decrease the speed of the motors at stopping
		//This is for both up and down
		//the 17 is somewhat arbitrary -- the ramp up is noticeable, but only tested on full (1.0)

//		System.out.println("Setting the voltage ramp rate");
//		Robot.driveTrainSubsystem.motorFrontLeft.setVoltageRampRate(17);
//		Robot.driveTrainSubsystem.motorFrontRight.setVoltageRampRate(17);
//		Robot.driveTrainSubsystem.motorBackLeft.setVoltageRampRate(17);
//		Robot.driveTrainSubsystem.motorBackRight.setVoltageRampRate(17);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		// autonomousCommand.start(); 	

		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();
		if (teleopCommand != null) teleopCommand.start();

	}

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit(){

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
