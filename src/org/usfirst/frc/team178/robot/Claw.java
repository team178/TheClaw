package org.usfirst.frc.team178.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw implements RunningComponent {

	private Talon leftClaw;
	private Talon rightClaw;
	private DigitalInput toteTouchingLS; //is item when limit switch is pressed
	private DigitalInput leftFrontLS;
	private DigitalInput rightFrontLS;
	private DigitalInput leftBackLS;
	private DigitalInput rightBackLS;
	
	//to help human comprehension
	private final int opening = 1;
	private final int closing = -1;
		
	public Claw(Talon leftClaw, Talon rightClaw, DigitalInput toteTouchingLS,
			DigitalInput leftFrontLS, DigitalInput rightFrontLS,
			DigitalInput leftBackLS, DigitalInput rightBackLS) {
		super();
		this.leftClaw = leftClaw;
		this.rightClaw = rightClaw;
		this.toteTouchingLS = toteTouchingLS;
		this.leftFrontLS = leftFrontLS;
		this.rightFrontLS = rightFrontLS;
		this.leftBackLS = leftBackLS;
		this.rightBackLS = rightBackLS;
	}

	@Override
	public void teleop(Joystick joystick, Joystick aux) {
		SmartDashboard.putBoolean("toteTouchingLS",toteTouchingLS.get());
		SmartDashboard.putBoolean("leftFrontLS", leftFrontLS.get());
		SmartDashboard.putBoolean("rightFrontLS",rightFrontLS.get());
		SmartDashboard.putBoolean("rightBackLS", rightBackLS.get());
		SmartDashboard.putBoolean("leftBackLS", leftBackLS.get());
		
		if(aux.getRawButton(1)){ //opening
			this.moveClaw(1);
		}
		else if(aux.getRawButton(2)){ //closing
			this.moveClaw(-1);
		}
		else{
			rightClaw.set(0);
			leftClaw.set(0);
		}
	}
	
	private void moveClaw(int direction){
		boolean isTouchingTote = !toteTouchingLS.get();
		
		rightClaw.set(direction);
		leftClaw.set(direction);

		if(!leftFrontLS.get() && direction == opening ){
			leftClaw.set(0);
		}
		
		if (!leftBackLS.get() && direction == closing){
			leftClaw.set(0);
		}
		
		if (!rightFrontLS.get() && direction == opening){
			rightClaw.set(0);
		}
		if (!rightBackLS.get() && direction == closing){
			rightClaw.set(0);
		}
		if (isTouchingTote && direction == closing){
			rightClaw.set(0);
			leftClaw.set(0);
		}
	}
		

	
	/*
	 * We only need this because the limit switches are being wired in parallel :)
	 */

	@Override
	public void test(Joystick driver) {
		// TODO Auto-generated method stub

	}
}
