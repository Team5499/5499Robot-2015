package org.usfirst.frc.team5499.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//Create a new joystick
	public final Joystick stick = new Joystick(joystickPort);

	//This is a usb port on a computer. It doesn't seem to have any impedance on anything.
	public final static int joystickPort = 1;
	
	
	//These are channels on the joystick
	public final int grabberCloseButton = 1;
	public final int rotateButton = 2;
	public final int grabberOpenButton = 3;
	public final int releaseButton = 4;
	public final int slowLifterButton = 5;
	public final int lowerToFloorButton = 6;
	public final int grabBinButton = 7;
	public final int getToBinButton = 8;
	public final int grabToteButton = 9;
	public final int getToTote1Button = 10;
	public final int getToTote2Button = 11;
	public final int getToTote3Button = 12;
	
	
	
	
	//Degress for the POV button
	public final int lifterRaiseDeg = 180;
	public final int lifterLowerDeg = 0;

	
	
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
}

