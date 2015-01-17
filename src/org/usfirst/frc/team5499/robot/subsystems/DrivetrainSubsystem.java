package org.usfirst.frc.team5499.robot.subsystems;

import java.util.Arrays;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon motorFrontLeft = new CANTalon(RobotMap.motorFrontLeftnum);
	CANTalon motorFrontRight = new CANTalon(RobotMap.motorFrontRightnum);
	CANTalon motorBackLeft = new CANTalon(RobotMap.motorBackLeftnum);
	CANTalon motorBackRight = new CANTalon(RobotMap.motorBackRightnum);
	
	
    public void initDefaultCommand() {}
    
    public void move(double direction, double magnitude, double rotation){
    	double[] motorspeeds = motorspeeds(direction, magnitude, rotation);
    	motorFrontLeft.set(motorspeeds[RobotMap.frontLeftWheelnum]);
    	motorFrontRight.set(motorspeeds[RobotMap.frontRightWheelnum]);
    	motorBackLeft.set(motorspeeds[RobotMap.backLeftWheelnum]);
    	motorBackRight.set(motorspeeds[RobotMap.backRightWheelnum]);
    }
    public double[] motorspeeds(double direction, double magnitude, double rotation){
    	double motorspeeds[] = new double[4];
    	double sinDir = Math.sin(direction + Math.PI/4);
    	double cosDir = Math.cos(direction + Math.PI/4);
    	
    	motorspeeds[RobotMap.frontLeftWheelnum] = magnitude * sinDir + rotation;
    	motorspeeds[RobotMap.frontRightWheelnum] = magnitude * cosDir - rotation;
    	motorspeeds[RobotMap.backLeftWheelnum] = magnitude * cosDir + rotation;
    	motorspeeds[RobotMap.backRightWheelnum] = magnitude * sinDir - rotation;
    	
    	normalize(motorspeeds);
    	
    	return motorspeeds;
    }

	private void normalize(double[] motorspeeds) {
		Arrays.sort(motorspeeds);
		double max = motorspeeds[3];
		for (int i=0; i<4; i++){
			motorspeeds[i] = motorspeeds[i] / max;
		}
	}
}

