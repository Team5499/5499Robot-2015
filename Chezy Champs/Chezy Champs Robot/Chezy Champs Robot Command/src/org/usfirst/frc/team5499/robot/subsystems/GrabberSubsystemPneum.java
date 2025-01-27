
package org.usfirst.frc.team5499.robot.subsystems;

import org.usfirst.frc.team5499.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;


/**
 *
 */
public class GrabberSubsystemPneum extends ElevatorSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final Compressor COMPRESSOR = new Compressor(RobotMap.COMPRESSOR_PCM_NODE_ID);
	
	private final DoubleSolenoid RIGHT_INTAKE = new DoubleSolenoid(RobotMap.RIGHT_INTAKE_SOL_F_CH, RobotMap.RIGHT_INTAKE_SOL_B_CH);
	private final DoubleSolenoid LEFT_INTAKE = new DoubleSolenoid(RobotMap.LEFT_INTAKE_SOL_F_CH, RobotMap.LEFT_INTAKE_SOL_B_CH);
	
	private final CANTalon RIGHT_MOTOR = new CANTalon(RobotMap.RIGHT_INTAKE_MOTOR_IND);
	private final CANTalon LEFT_MOTOR = new CANTalon(RobotMap.LEFT_INTAKE_MOTOR_IND);
	
	private boolean isClosedLoopOn;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	isClosedLoopOn = true;
    	
    	COMPRESSOR.setClosedLoopControl(isClosedLoopOn);
    }
    
    public void open(){
    	RIGHT_INTAKE.set(DoubleSolenoid.Value.kForward);
    	LEFT_INTAKE.set(DoubleSolenoid.Value.kForward);
    	
    }
    
    public void close(){
    	RIGHT_INTAKE.set(DoubleSolenoid.Value.kReverse);
    	LEFT_INTAKE.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void grab(){
    	close();
    	RIGHT_MOTOR.set(70);
    	LEFT_MOTOR.set(70);
    }
}

