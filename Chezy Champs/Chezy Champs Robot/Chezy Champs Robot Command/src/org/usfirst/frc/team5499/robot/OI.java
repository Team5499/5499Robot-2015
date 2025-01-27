package org.usfirst.frc.team5499.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public final Joystick CONTROLLER = new Joystick(1);
	
	//AXES
	//public final int LEFT_STICK_X = 1;
	public final int LEFT_STICK_Y = 1;
	
	public final int RIGHT_TRIGGER = 3;
	public final int LEFT_TRIGGER = 2;
	
	//public final int RIGHT_STICK_X = 4;
	public final int RIGHT_STICK_Y = 5;
	
	public final int D_PAD = 6;
	
	
	//BUTTONS
	public final int A = 1; //toggle grabber on/off
	public final int B = 2;
	public final int X = 3;
	public final int Y = 4; //toggle bin tabs
	public final int LEFT_BUMPER = 5;
	public final int RIGHT_BUMPER = 6;
	public final int BACK = 7;
	public final int START = 8;
	public final int LEFT_THUMB_BUTTON = 9;
	public final int RIGHT_THUMB_BUTTON = 10;
	
}

