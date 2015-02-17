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
    
	//Set index in arrays to be constant for variables
	public static int frontLeftWheelnum = 0;
	public static int frontRightWheelnum = 1;
	public static int backLeftWheelnum = 2;
	public static int backRightWheelnum = 3;
	public static int grabberMotor1num = 4;
	public static int lifterMotor1num = 5;
	
	//This is the usb camera address
	public static String cameraAddress = "cam0";
	
	/*
	 * Create DIO ports
	 */
	//These are the digital inputs ports on the roborio.
	//As of right now, there is nothing physically connected to them
	public static int limitSwitch1Port = 1;
	public static int limitSwitch2Port = 2;
	public static int startSideSwitchPort = 3;
	public static int broomServoChannel = 0;
	
	/*
	 * ID nums for motors (CAN)
	 * The motor controllers of these motors are daisy chained and they don't have a port.
	 * Rather, you have to assign an id to the motor controllers thru the web interface.
	 */
	public static int motorBackRightid = 1;
	public static int motorBackLeftid = 2;
	public static int motorFrontRightid = 3;
	public static int motorFrontLeftid = 4;
	public static int grabberMotorid = 5;
	public static int lifterMotorid = 6;
	


	/**
	 * These values are for getting the current. Do not use the motor controller ids for this.
	 */
	public static int motorFrontLeftPDPChannel = 15;
	public static int motorFrontRightPDPChannel = 0;
	public static int motorBackLeftPDPChannel = 1;
	public static int motorBackRightPDPChannel = 14; 
	public static int lifter1PDPChannel = 13;
	public static int grabber1PDPChannel = 12;
	

	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	
	public void init(){
	}
}
