package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DrivabaseSubsystem extends Subsystem{
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final CANTalon RIGHT_MOTOR_F = new CANTalon(RobotMap.RIGHT_MOTOR_F_IND);
	private final CANTalon RIGHT_MOTOR_B = new CANTalon(RobotMap.RIGHT_MOTOR_B_IND);
	private final CANTalon LEFT_MOTOR_F = new CANTalon(RobotMap.LEFT_MOTOR_F_IND);
	private final CANTalon LEFT_MOTOR_B = new CANTalon(RobotMap.LEFT_MOTOR_B_IND);
	
	private Timer timer = new Timer();
	
	//private I2C gyroChannel = new I2C(Port.kOnboard, 110101);
	
	public Gyro gyro = new Gyro(/*RobotMap.GYRO_CH*/ I2C.Port.kOnboard.getValue());	
	
	//private final double KP = 0.03; //proportional gain
	
	private double Kp = 0.1;
	private double Ki = 0.001;
	private double Kd = 0.0;	
	
	
	private PIDController pidR_F = new PIDController(Kp, Ki, Kd, gyro, RIGHT_MOTOR_F);
	private PIDController pidR_B = new PIDController(Kp, Ki, Kd, gyro, RIGHT_MOTOR_B);
	private PIDController pidL_F = new PIDController(Kp, Ki, Kd, gyro, LEFT_MOTOR_F);
	private PIDController pidL_B = new PIDController(Kp, Ki, Kd, gyro, LEFT_MOTOR_B);
	
	

	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		pidR_F.enable();
		pidR_B.enable();
		pidL_F.enable();
		pidL_B.enable();
	}

	public void move(){
		RIGHT_MOTOR_F.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.RIGHT_STICK_Y));
		RIGHT_MOTOR_B.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.RIGHT_STICK_Y));
		
		LEFT_MOTOR_F.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.LEFT_STICK_Y));
		LEFT_MOTOR_B.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.LEFT_STICK_Y));
	}
	
	/**
	 * 
	 * @param time time for the robot to move in seconds
	 * @param degrees direction of the move in degrees with forward being 0, right being 90
	 * @param speed speed with which to move 0 - 100
	 */
	public void timedMove(int time, int angle, int speed){
		timer.start();
		
		double rightSpeed = Math.sin(angle * Math.PI/180) * speed - Math.sin(angle * Math.PI/180) * speed;
		double leftSpeed = Math.cos(angle * Math.PI/180) * speed + Math.sin(angle * Math.PI/180) * speed;
		
		
		while(timer.get() < time){
			RIGHT_MOTOR_F.set(rightSpeed);
			RIGHT_MOTOR_B.set(rightSpeed);
			
			LEFT_MOTOR_F.set(leftSpeed);
			LEFT_MOTOR_B.set(leftSpeed);
		}
		
	}
}
