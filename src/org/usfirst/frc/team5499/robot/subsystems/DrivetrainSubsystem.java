package org.usfirst.frc.team5499.robot.subsystems;



import org.usfirst.frc.team5499.robot.Robot;
import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
	
	//Create new motors
	public CANTalon motorFrontLeft = new CANTalon(RobotMap.motorFrontLeftid);
	public CANTalon motorFrontRight = new CANTalon(RobotMap.motorFrontRightid);
	public CANTalon motorBackLeft = new CANTalon(RobotMap.motorBackLeftid);
	public CANTalon motorBackRight = new CANTalon(RobotMap.motorBackRightid);
	
	public RobotDrive mecanumDrive = new RobotDrive(motorFrontLeft, motorBackLeft, motorFrontRight, motorBackRight);
	
	
	
    public void initDefaultCommand() {
    	Robot.driveTrainSubsystem.motorFrontLeft.setPID(RobotMap.p[RobotMap.frontLeftWheelnum],
				RobotMap.i[RobotMap.frontLeftWheelnum], 
				RobotMap.d[RobotMap.frontLeftWheelnum], 
				RobotMap.f[RobotMap.frontLeftWheelnum], 
				RobotMap.izone[RobotMap.frontLeftWheelnum], 
				RobotMap.ramp[RobotMap.frontLeftWheelnum], 0);
    	Robot.driveTrainSubsystem.motorFrontRight.setPID(RobotMap.p[RobotMap.frontRightWheelnum],
				RobotMap.i[RobotMap.frontRightWheelnum], 
				RobotMap.d[RobotMap.frontRightWheelnum], 
				RobotMap.f[RobotMap.frontRightWheelnum], 
				RobotMap.izone[RobotMap.frontRightWheelnum], 
				RobotMap.ramp[RobotMap.frontRightWheelnum], 0);
    	Robot.driveTrainSubsystem.motorBackLeft.setPID(RobotMap.p[RobotMap.backLeftWheelnum],
				RobotMap.i[RobotMap.backLeftWheelnum], 
				RobotMap.d[RobotMap.backLeftWheelnum], 
				RobotMap.f[RobotMap.backLeftWheelnum], 
				RobotMap.izone[RobotMap.backLeftWheelnum], 
				RobotMap.ramp[RobotMap.backLeftWheelnum], 0);
    	Robot.driveTrainSubsystem.motorFrontLeft.setPID(RobotMap.p[RobotMap.backRightWheelnum],
				RobotMap.i[RobotMap.backRightWheelnum], 
				RobotMap.d[RobotMap.backRightWheelnum], 
				RobotMap.f[RobotMap.backRightWheelnum], 
				RobotMap.izone[RobotMap.backRightWheelnum], 
				RobotMap.ramp[RobotMap.backRightWheelnum], 0);
    	motorFrontLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	motorFrontRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	motorBackLeft.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	motorBackRight.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	motorFrontLeft.reverseOutput(false);
    	motorFrontRight.reverseOutput(true); //counter the opposite facing of the motor
    	motorBackLeft.reverseOutput(false);
    	motorBackRight.reverseOutput(true); //counter the opposite facing of the motor
    	//Set smooth accel and decel
    	
    	// not needed set in setPID
//    	motorFrontLeft.setVoltageRampRate(17);
//    	motorFrontRight.setVoltageRampRate(17);
//    	motorBackLeft.setVoltageRampRate(17);
//    	motorBackRight.setVoltageRampRate(17);
    }
    
    public void autoMove(){
    	
    }
    
    
    /**
     * Sets motor speeds to the output of {@link #motorspeeds(double, double, double, double) motorspeeds} method.
     * 
     * @param X, X-coordinate of desired motion vector, i.e. X-coordinate of joystick
     * @param Y, Y-coordinate of desired motion vector, i.e. Y-coordinate of joystick
     * @param Z, desired rotation
     *
     */
    public void move(double X, double Y, double Z){
    	System.out.print("X: ");
    	System.out.print(X);
    	System.out.print(" Y: ");
    	System.out.print(Y);
    	System.out.print(" Z: ");
    	System.out.println(Z);
    	double[] motorspeeds = motorspeeds(X, Y, Z, 0);
    	System.out.println(" Motor 1: ");
    	System.out.println(motorspeeds[0]);
    	System.out.println(" Motor 2: ");
    	System.out.println(motorspeeds[1]);
    	motorFrontLeft.set(motorspeeds[RobotMap.frontLeftWheelnum]);
    	motorFrontRight.set(motorspeeds[RobotMap.frontRightWheelnum]);
    	motorBackLeft.set(motorspeeds[RobotMap.backLeftWheelnum]);
    	motorBackRight.set(motorspeeds[RobotMap.backRightWheelnum]);
    	
    
    }
    

    
    /**
     * Rotates the xy-coordinates of the joystick by 45 degrees using a rotation matrix [ cos -sin][x]
     * 																					[ sin cos ][y]
     *  and then sets the front left and back right to y and front right and back left to x, 
     *  then adds rotation to left side motors and subtracts it from the right motors.
     *  Then normalizes the resulting speeds.
     *  wikipedia article on rotation matrices: <a href=http://en.wikipedia.org/wiki/Rotation_matrix Rotation Matrix> Rotation Matrix </a>.
     *  
     *  @param X, X-coordinate of desired movement vector, i.e. X-coordinate of joystick
     *  @param Y, Y-coordinate of desired movement vector, i.e. Y-coordinate of joystick
     *  @param Z, desired rotation
     *  @param gyroAng, rotation of gyroscope
     *  
     *  @return array of motor speeds needed in order to get the desired movement
     */
    public double[] motorspeeds(double X, double Y, double Z, double gyroAng){
    	double[] motorspeeds = new double[4];
    	double cosAng = Math.cos(Math.PI/4);
    	double sinAng = Math.sin(Math.PI/4);
    	double Xprime = cosAng * X - sinAng * Y;
    	double Yprime = sinAng * X + cosAng * Y;
    	
    	System.out.println("Xprime");
    	System.out.println(Xprime);
    	System.out.println("Yprime");
    	System.out.println(Yprime);
    	
    	motorspeeds[RobotMap.frontLeftWheelnum] = Yprime + Z;
    	motorspeeds[RobotMap.frontRightWheelnum] = Xprime - Z;
    	motorspeeds[RobotMap.backLeftWheelnum] = Xprime  + Z;
    	motorspeeds[RobotMap.backRightWheelnum] =Yprime - Z;
    	
    	normalize(motorspeeds);
    	
    	return motorspeeds;
    	
    }

    /**
     * This function normalizes the motor speeds, meaning it divides by the max speed so that they are all between -1, and 1.
     * 
     * @param motorspeeds, array of motorspeeds to be normalized
     */
	private void normalize(double[] motorspeeds) {
		double currentmax = 0;
		for (int i=0; i<4; i++){
			if(Math.abs(motorspeeds[i])>=currentmax){
				currentmax = Math.abs(motorspeeds[i]);
			}
		}
		System.out.println(currentmax);
		if(currentmax!=0){
			for (int i=0; i<4; i++){
				motorspeeds[i] = motorspeeds[i] / currentmax;
			}
		}
	}
	
	/**
	 * Only For Use In Quadrature Mode. Get the number of rotations the encoder disk has completed.
	 * Since the encoder is connected to the gearbox shaft, which spins about 1/4 as fast as the motor shaft due to the gears, one full rotation of the encoder is about 4 rotations of the motor shaft
	 * @param
	 * 		motorController the talonSRX, to which the desired encoder is connected
	 * @return
	 * 		The number of rotations the encoder has completed; derived from CANTalon.getPosition();
	 */
    public double getEncoderRevs(CANTalon motorController){
    	return motorController.getPosition()/250; //250 is the number of ticks in a revolution of the encoder that we are using
    }
    
    /**
     * Only For Use In Quadrature Mode. Get the number of rotations the motor shaft has completed.
	 * Since the encoder is connected to the gearbox shaft, which spins about 1/4 as fast as the motor shaft due to the gears, one full rotation of the motor shaft is about 1/4 rotations of the motor shaft
     * @param
     * 		motorController the talonSRX, to which the desired encoder is connected
     * @return
     * 		The number of rotations the motor has completed; derived from CANTalon.getPosition();
     */
    public double getMotorRevs(CANTalon motorController){
    	return motorController.getPosition()/250*4; //250 is the number of ticks in a revolution of the encoder that we are using
    }
}

