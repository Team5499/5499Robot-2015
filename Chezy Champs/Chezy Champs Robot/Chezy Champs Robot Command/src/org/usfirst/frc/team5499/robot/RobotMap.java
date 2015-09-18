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
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	
	public static final int RIGHT_MOTOR_F_IND = 3;
	public static final int RIGHT_MOTOR_B_IND = 1;
	public static final int LEFT_MOTOR_F_IND = 5;
	public static final int LEFT_MOTOR_B_IND = 8;
	public static final int ELEVATOR_MOTOR_F_IND = 6;
	public static final int ELEVATOR_MOTOR_B_IND = 7;
	public static final int RIGHT_INTAKE_MOTOR_IND = 6;
	public static final int LEFT_INTAKE_MOTOR_IND = 7;
	
	public static final int RIGHT_SERVO_F_PWM_CH = 1;
	public static final int RIGHT_SERVO_B_PWM_CH = 2;
	public static final int LEFT_SERVO_F_PWM_CH = 3;
	public static final int LEFT_SERVO_B_PWM_CH = 4;
	
	public static final int COMPRESSOR_PCM_NODE_ID = 0;
	
	public static final int RIGHT_INTAKE_SOL_F_CH = 1;
	public static final int RIGHT_INTAKE_SOL_B_CH = 2;
	public static final int LEFT_INTAKE_SOL_F_CH = 3;
	public static final int LEFT_INTAKE_SOL_B_CH = 4;
	
	public static final int GYRO_CH = 1; //Can only be 1 or 0
	
	
}
