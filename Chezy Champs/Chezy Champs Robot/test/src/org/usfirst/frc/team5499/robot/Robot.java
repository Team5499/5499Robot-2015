
package org.usfirst.frc.team5499.robot;


import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
	
	//MOTORS
    private final CANTalon leftMotorF;
    private final CANTalon leftMotorB;
    private final CANTalon rightMotorF;
    private final CANTalon rightMotorB;
    private final CANTalon elevatorMotorF;
    private final CANTalon elevatorMotorB;
    private final CANTalon conveyorMotorL;
    private final CANTalon conveyorMotorR;
    //STICK CONTROLL
    private Joystick stick;
    //axes
  	private final int LEFT_STICK_Y = 1;
  	private final int RIGHT_STICK_Y = 5;
  	private final int RIGHT_TRIGGER = 3;
  	private final int LEFT_TRIGGER = 2;
 	//buttons
  	private final int A = 1;
  	private final int B = 2;
  	private final int X = 3;
  	private final int Y = 4;
  	private final int LEFT_BUMPER = 5;
  	private final int RIGHT_BUMPER = 6;
  	private final int BACK = 7;
  	private final int START = 8;
  	private final int LEFT_THUMB_BUTTON = 9;
  	private final int RIGHT_THUMB_BUTTON = 10;
  	
  	//PID
  	private final double Kp = 0.1;
  	private final double Ki = 0.001;
  	private final double RAMP_RATE = 12;
  	private Accelerometer accel = new BuiltInAccelerometer(Accelerometer.Range.k2G);
  	private I2C gyro = new I2C(Port.kOnboard, 0x6B);

    public Robot() {
        stick = new Joystick(0);
        leftMotorF = new CANTalon(5);
        leftMotorB = new CANTalon(8);
        rightMotorF = new CANTalon(1);
        rightMotorB = new CANTalon(3);
        elevatorMotorF = new CANTalon(6);
        elevatorMotorB = new CANTalon(7);
        conveyorMotorL = new CANTalon(2);
        conveyorMotorR = new CANTalon(4);
        
        leftMotorF.setCloseLoopRampRate(RAMP_RATE);
        leftMotorB.setCloseLoopRampRate(RAMP_RATE);
        rightMotorF.setCloseLoopRampRate(RAMP_RATE);
        rightMotorB.setCloseLoopRampRate(RAMP_RATE);
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void autonomous() {
    }

    /**
     * Runs the motors with arcade steering. //Teleop
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
        	
        	leftMotorF.set(-1 * stick.getRawAxis(1));
        	leftMotorB.set(-1 * stick.getRawAxis(1));
        	rightMotorF.set(stick.getRawAxis(5));
        	rightMotorB.set(stick.getRawAxis(5));
        	if(stick.getRawAxis(2) > 0.1){
        		elevatorMotorF.set(-stick.getRawAxis(2));
        		elevatorMotorB.set(-stick.getRawAxis(2));
        	}
        	else if(stick.getRawAxis(3) > 0.1){
        		elevatorMotorF.set(stick.getRawAxis(3));
        		elevatorMotorB.set(stick.getRawAxis(3));
        	} else{
        		elevatorMotorF.set(0.0);
        		elevatorMotorB.set(0.0);
        	}
        		
        	if(stick.getRawButton(3)){
        		conveyorMotorL.set(1);
        		conveyorMotorR.set(1);
        	} else{
        		conveyorMotorL.set(0.0);
        		conveyorMotorR.set(0.0);
        	}        	
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
    
    
    
    /**
	 * Updates the 
	 * @param leftTarget
	 * 			The target values for the left side of the drivebase
	 * @param rightTarget
	 * 			The target values for the right side of the drivebase
	 * @param useGyro
	 * 			Choose to use the accelerometer or the gyro. True if using gyro, otherwise false
	 **/
	private void updatePIDVals(double leftTarget, double rightTarget, boolean useGyro){
		double p = 1;
		double i = 1;
		double error = 1;
		if(useGyro){
			//TODO check if actually negative on the left
			double xVal = accel.getX()/1.20577; //1.20577G is absolute max accel of robot (w/out load) 
			double dirAvg = (-leftTarget + rightTarget)/2;
			error = dirAvg - xVal;
		} else{
			
		}
	
		//TODO RIO IS NOT IN THE CENTER
		
		if(Math.abs(error) > 0.2){ //TODO calibrate error threshold
			//http://teamducttape.com/2008/11/gyro-vs-accelerometer-and-how-to-use-pid-control/
			p = error * Kp;
			i = error + i * Ki;
			if(error > 0){ //if error is on the right side
				rightMotorF.setP(p);
				rightMotorB.setI(i);
				rightMotorF.setP(p);
				rightMotorB.setI(i);
			} else if(error < 0){ //if error is on the left side
				leftMotorF.setP(p);
				leftMotorB.setI(i);
				leftMotorF.setP(p);
				leftMotorB.setI(i);
			}
		}
	}
    
}
