
package org.usfirst.frc.team5499.robot;

import org.usfirst.frc.team5499.robot.commands.ExampleCommand;
import org.usfirst.frc.team5499.robot.commands.OutOfWayAutoCommand;
import org.usfirst.frc.team5499.robot.commands.TeleopCommand;
import org.usfirst.frc.team5499.robot.subsystems.DrivabaseSubsystem;
import org.usfirst.frc.team5499.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team5499.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team5499.robot.subsystems.GrabberSubsystemConveyor;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static final DrivabaseSubsystem drivebaseSubsystem = new DrivabaseSubsystem();
	public static final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
	public static final GrabberSubsystemConveyor grabberSubsystemConveyor = new GrabberSubsystemConveyor();
	//public static final GrabberSubsystemPneum grabberSubsystemPneum = new GrabberSubsystemPneum();
	
	Command teleopCommand = new TeleopCommand();
	//Command autoFullCommand = new FullAutoCommand();
	Command autoOutCommand = new OutOfWayAutoCommand();
    Command autonomousCommand = autoOutCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new ExampleCommand();
        //drivebaseSubsystem.gyro.initGyro();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
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
        teleopCommand.start();
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
