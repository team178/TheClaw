package org.usfirst.frc.team178.robot;

public class Message {
	public static boolean makeLiftSafe;
	public static boolean makeDeckSafe;
	public static boolean isLiftSafe;
	public static boolean isDeckSafe;
	
	/**auto flags
	all are false until the chain is initialized by @Link autonomousPeriodic()*/
	public static boolean inAuto=false,isCanHeld=false,isCaninAZ=false,isBotClearofAZ=false,isBotAlligned=false,isBotReadyToGrab=false,isToteHeld=false,isToteinAZ=false;	

	public static boolean isDown;
}