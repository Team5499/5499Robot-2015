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
	public final static int frontLeftWheelnum = 0;
	public final static int frontRightWheelnum = 1;
	public final static int backLeftWheelnum = 2;
	public final static int backRightWheelnum = 3;
	public final static int grabberMotor1num = 4;
	public final static int lifterMotor1num = 5;
	
	//This is the usb camera address
	public final static String cameraAddress = "cam0";
	
	/*
	 * Create DIO ports
	 */
	//These are the digital inputs ports on the roborio.
	//As of right now, there is nothing physically connected to them
	public final static int limitSwitch1Port = 1;
	public final static int limitSwitch2Port = 2;
	public final static int startSideSwitchPort = 3;
	
	/*
	 * ID nums for motors (CAN)
	 * The motor controllers of these motors are daisy chained and they don't have a port.
	 * Rather, you have to assign an id to the motor controllers thru the web interface.
	 */
	public final static int motorBackRightid = 1;
	public final static int motorBackLeftid = 2;
	public final static int motorFrontRightid = 3;
	public final static int motorFrontLeftid = 4;
	public final static int grabberMotorid = 5;
	public final static int lifterMotor2id = 6;
	public final static int lifterMotor1id = 8;

	
	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
