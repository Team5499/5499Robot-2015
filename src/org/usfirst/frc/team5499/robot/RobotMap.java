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
    
	//Used for indicating the axis of the wheel in the move method of the drivetrain subsystem.
	public static int frontLeftWheelnum = 0;
	public static int frontRightWheelnum = 1;
	public static int backLeftWheelnum = 2;
	public static int backRightWheelnum = 3;

	
	//This is the usb camera address
	public static String cameraAddress = "cam0";
	
	//These are the digital inputs ports on the roborio.
	//As of right now, there is nothing connected to them
	public static int limit1SwitchPort = 1;
	public static int limit2SwitchPort = 2;
	public static int broomServoChannel = 0;
	
	/*
	 * ID nums for motors (CAN)
	 * The motor controllers of these motors are daisy chained and they don't have a port.
	 * Rather, you have to assign an id to the motor controllers thru the web interface.
	 */
	public static int motorFrontLeftid = 2;
	public static int motorFrontRightid = 1;
	public static int motorBackLeftid = 3;
	public static int motorBackRightid = 4;
	public static int gripperMotor1id = 5;
	public static int gripperMotor2id = 6;
	public static int lifterMotor1id = 7;
	public static int lifterMotor2id = 8;


	/*
	 * These values are for getting the current. Do not use the motor controller ids for this.
	 */
	public static int motorFrontLeftPDPChannel = 15;
	public static int motorFrontRightPDPChannel = 0;
	public static int motorBackLeftPDPChannel = 1;
	public static int motorBackRightPDPChannel = 14; 
	public static int lifter1PDPChannel = 13;
	public static int lifter2PDPChannel = 12;
	
	//This is a usb port on a computer
	public static int joystickPort = 1;
	//This is a channel on the joystick
	public static int rotateButton = 1;

	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static double[] p = new double[4];
	public static double[] i = new double[4];
	public static double[] d = new double[4];
	public static double[] f = new double[4];
	public static int[] izone = new int[4];
	public static double[] ramp = new double[4];
	
	public void init(){
		p[frontLeftWheelnum] = 1.0;
		p[frontRightWheelnum] = 1.0;
		p[backLeftWheelnum] = 1.0;
		p[backRightWheelnum] = 1.0;
		i[frontLeftWheelnum] = 0.1;
		i[frontRightWheelnum] = 0.1;
		i[backLeftWheelnum] = 0.1;
		i[backRightWheelnum] = 0.1;
		d[frontLeftWheelnum] = 0.1;
		d[frontRightWheelnum] = 0.1;
		d[backLeftWheelnum] = 0.1;
		d[backRightWheelnum] = 0.1;
		f[frontLeftWheelnum] = 0.0;
		f[frontRightWheelnum] = 0.0;
		f[backLeftWheelnum] = 0.0;
		f[backRightWheelnum] = 0.0;
		izone[frontLeftWheelnum] = 0;
		izone[frontRightWheelnum] = 0;
		izone[backLeftWheelnum] = 0;
		izone[backRightWheelnum] = 0;
		ramp[frontLeftWheelnum] = 17.0;
		ramp[frontRightWheelnum] = 17.0;
		ramp[backLeftWheelnum] = 17.0;
		ramp[backRightWheelnum] = 17.0;
	}
}
