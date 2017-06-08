package org.usfirst.frc.team852.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team852.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
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

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

    public Joystick stick1 = new Joystick(0);
    public Joystick stick2 = new Joystick(1);
    public Joystick xbox = new Joystick(2);


    // Joystick buttons start at 1
    public Button xboxA = new JoystickButton(xbox, 1);
    public Button xboxB = new JoystickButton(xbox, 2);
    public Button xboxX = new JoystickButton(xbox, 3);
    public Button xboxY = new JoystickButton(xbox, 4);
    public Button xboxLB = new JoystickButton(xbox, 5);
    public Button xboxRB = new JoystickButton(xbox, 6);
    public Button xboxBack = new JoystickButton(xbox, 7);
    public Button xboxStart = new JoystickButton(xbox, 8);
    public Button xboxLS = new JoystickButton(xbox, 9);
    public Button xboxRS = new JoystickButton(xbox, 10);


}
