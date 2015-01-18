package org.usfirst.frc.team5499.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
	public static int frontLeftWheelnum = 0;
	public static int frontRightWheelnum = 0;
	public static int backLeftWheelnum = 0;
	public static int backRightWheelnum = 0;
	
	/*
	 * ID nums for motors (CAN)
	 */
	public static int motorFrontLeftid = 1;
	public static int motorFrontRightid = 2;
	public static int motorBackLeftid = 3;
	public static int motorBackRightid = 4;
	public static int gripperMotor1id = 5;
	public static int gripperMotor2id = 6;
	public static int lifterMotor1id = 7;
	public static int lifterMotor2id = 8;
	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
