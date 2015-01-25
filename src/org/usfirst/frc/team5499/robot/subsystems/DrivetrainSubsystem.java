package org.usfirst.frc.team5499.robot.subsystems;

import java.util.Arrays;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Create new motors
	public CANTalon motorFrontLeft = new CANTalon(RobotMap.motorFrontLeftid);
	public CANTalon motorFrontRight = new CANTalon(RobotMap.motorFrontRightid);
	public CANTalon motorBackLeft = new CANTalon(RobotMap.motorBackLeftid);
	public CANTalon motorBackRight = new CANTalon(RobotMap.motorBackRightid);
	
	
    public void initDefaultCommand() {
    	
    }
    
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
    public double[] motorspeeds_polar(double direction, double magnitude, double rotation){
    	double motorspeeds[] = new double[4];
    	double sinDir = Math.sin(direction + Math.PI/4);
    	double cosDir = Math.cos(direction + Math.PI/4);
    	
    	motorspeeds[RobotMap.frontLeftWheelnum] = magnitude * sinDir + rotation;
    	motorspeeds[RobotMap.frontRightWheelnum] = magnitude * cosDir - rotation;
    	motorspeeds[RobotMap.backLeftWheelnum] = -1 * magnitude * cosDir + rotation;
    	motorspeeds[RobotMap.backRightWheelnum] = - 1 * magnitude * sinDir - rotation;
    	
    	normalize(motorspeeds);
    	
    	return motorspeeds;
    }
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
    	motorspeeds[RobotMap.backLeftWheelnum] = -1 * (Xprime  + Z);
    	motorspeeds[RobotMap.backRightWheelnum] = -1 * (Yprime - Z);
    	
    	normalize(motorspeeds);
    	
    	return motorspeeds;
    	
    }

	private void normalize(double[] motorspeeds) {
		double currentmax = 0;
		for (int i=0; i<4; i++){
			if(Math.abs(motorspeeds[i])>=currentmax){
				currentmax = Math.abs(motorspeeds[i]);
			}
		}
		System.out.println(currentmax);
		for (int i=0; i<4; i++){
			motorspeeds[i] = motorspeeds[i] / currentmax;
		}
	}
}

