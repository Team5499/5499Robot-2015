package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class DrivabaseSubsystem extends Subsystem{
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final CANTalon RIGHT_MOTOR_F = new CANTalon(RobotMap.RIGHT_MOTOR_F_IND);
	private final CANTalon RIGHT_MOTOR_B = new CANTalon(RobotMap.RIGHT_MOTOR_B_IND);
	private final CANTalon LEFT_MOTOR_F = new CANTalon(RobotMap.LEFT_MOTOR_F_IND);
	private final CANTalon LEFT_MOTOR_B = new CANTalon(RobotMap.LEFT_MOTOR_B_IND);
	
	/*private final int LEFT_STICK_IND = 0;
	private final int RIGHT_STICK_IND = 1;
	
	private double[] stickVals = getThreshSticks();
	
	private Timer timer = new Timer();*/
	
	//private I2C gyroChannel = new I2C(Port.kOnboard, 110101);
	
	//public Gyro gyro = new Gyro(/*RobotMap.GYRO_CH*/ I2C.Port.kOnboard.getValue());
	
	//Implement correction based on the accelerometer for now since implementation is
	//easier. Try implementing gyro afterwards.
	/*Accelerometer accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);*/
	
	//private final double KP = 0.03; //proportional gain
	
	/*private double Kp = 0.1;
	private double Ki = 0.001;
	
	
	//TODO do we need specify these for each side?
	private double p;
	private double i;
	private double rampRate = 27;*/
	
	/*private PIDController pidR_F = new PIDController(Kp, Ki, Kd, accel, RIGHT_MOTOR_F);
	private PIDController pidR_B = new PIDController(Kp, Ki, Kd, gyro, RIGHT_MOTOR_B);
	private PIDController pidL_F = new PIDController(Kp, Ki, Kd, gyro, LEFT_MOTOR_F);
	private PIDController pidL_B = new PIDController(Kp, Ki, Kd, gyro, LEFT_MOTOR_B);*/
	
	

	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		/*pidR_F.enable();
		pidR_B.enable();
		pidL_F.enable();
		pidL_B.enable();*/
		/*RIGHT_MOTOR_F.setP(p);
		RIGHT_MOTOR_F.setI(i);
		RIGHT_MOTOR_F.setCloseLoopRampRate(rampRate);
		RIGHT_MOTOR_B.setP(p);
		RIGHT_MOTOR_B.setI(i);
		RIGHT_MOTOR_B.setCloseLoopRampRate(rampRate);
		LEFT_MOTOR_F.setP(p);
		LEFT_MOTOR_F.setI(i);
		LEFT_MOTOR_F.setCloseLoopRampRate(rampRate);
		LEFT_MOTOR_B.setP(p);
		LEFT_MOTOR_B.setI(i);
		LEFT_MOTOR_B.setCloseLoopRampRate(rampRate);*/
		
	}
	

	/**
	 * Sets the drivebase motors to move to values that the thumbsticks on the XBOX controller provide
	 */
	public void move(){
		
		/*updatePIDVals(stickVals[RIGHT_STICK_IND],
				stickVals[LEFT_STICK_IND]);*/
		
		
		RIGHT_MOTOR_F.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.RIGHT_STICK_Y));
		RIGHT_MOTOR_B.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.RIGHT_STICK_Y));
		
		LEFT_MOTOR_F.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.LEFT_STICK_Y));
		LEFT_MOTOR_B.set(-Robot.oi.CONTROLLER.getRawAxis(Robot.oi.LEFT_STICK_Y));
		
		/*RIGHT_MOTOR_F.set(-stickVals[RIGHT_STICK_IND]);
		RIGHT_MOTOR_B.set(-stickVals[RIGHT_STICK_IND]);
		
		LEFT_MOTOR_F.set(-stickVals[LEFT_STICK_IND]);
		LEFT_MOTOR_B.set(-stickVals[LEFT_STICK_IND]);
	}*/
	}
	
	/**
	 * 
	 * @param time 
	 * 			time for the robot to move in seconds
	 * @param degrees
	 * 			direction of the move in degrees with forward being 0, right being 90
	 * @param speed
	 * 			speed with which to move 0 - 100
	 *//*
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
*/
	/**
	 * Assumes perfect precision in the joystick
	 * TODO add thresholds for activation by the 
	 * @param rightTarget
	 * 			The target values for the right side of the drivebase
	 * @param leftTarget
	 * 			The target values for the left side of the drivebase
	 *//*
	private void updatePIDVals(double rightTarget, double leftTarget){
		boolean isChanged = false;
		//TODO check if actually negative on the left
		double xVal = accel.getX()/4; //make out of one (max is 4)
		double dirAvg = (-rightTarget + leftTarget)/2;
		double error = dirAvg - xVal;
	
		if(error > 0.2){ //TODO calibrate error threshold
			//http://teamducttape.com/2008/11/gyro-vs-accelerometer-and-how-to-use-pid-control/
			p = error * Kp;
			i = error + i * Ki;
			isChanged = true;
		}
		if(isChanged){
			RIGHT_MOTOR_F.setP(p);
			RIGHT_MOTOR_F.setI(i);
			RIGHT_MOTOR_B.setP(p);
			RIGHT_MOTOR_B.setI(i);
			LEFT_MOTOR_F.setP(p);
			LEFT_MOTOR_F.setI(i);
			LEFT_MOTOR_B.setP(p);
			LEFT_MOTOR_B.setI(i);
		}
	}*/
	
	/**
	 * Puts a threshold of 0.2 on the thumbsticks on the controller.
	 * Below that number, the values are 0.
	 * @return
	 * 		Thresholded stick values. Index 0: left, Index 1: right.
	 */
	/*private double[] getThreshSticks(){
		
		double[] stickVals = new double[2];
		if(Robot.oi.CONTROLLER.getRawAxis(Robot.oi.LEFT_STICK_Y) < 0.2)
			stickVals[LEFT_STICK_IND] = 0;
		else 
			stickVals[LEFT_STICK_IND] = Robot.oi.CONTROLLER.getRawAxis(Robot.oi.LEFT_STICK_Y);
		
		if(Robot.oi.CONTROLLER.getRawAxis(Robot.oi.RIGHT_STICK_Y) < 0.2)
			stickVals[RIGHT_STICK_IND] = 0;
		else 
			stickVals[RIGHT_STICK_IND] = Robot.oi.CONTROLLER.getRawAxis(Robot.oi.RIGHT_STICK_Y);
		
		return stickVals;
	}*/
}
