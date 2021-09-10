package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Shift;
import frc.robot.Utilities.Limelight;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class OI {
    private static final double DEADZONE_LIMIT = 0.1;

    public static XboxController xbox,xbox1;
    public static Trigger shifter, llState;
    //public static Trigger spinCommand;
    //public static POVButton elevUp, elevDown;

    public OI() {
        xbox = new XboxController(0);
        xbox1 = new XboxController(1);

        shifter=new Trigger(){@Override public boolean get(){return xbox.getStickButtonPressed(Hand.kLeft );}};//LS Click
        llState=new Trigger(){@Override public boolean get(){return xbox.getStickButtonPressed(Hand.kRight);}};//RS Click
        
        shifter.whenActive(new Shift());
        llState.whenActive(new CommandBase() {
            @Override public void initialize(){Limelight.getInstance().switchState();}
            @Override public boolean isFinished(){ return true; }
        });
    }

    public static double LeftX  (){ double raw = xbox.getRawAxis(0); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public static double LeftY  (){ double raw = xbox.getRawAxis(1); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public static double RightX (){ double raw = xbox.getRawAxis(4); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public static double RightY (){ double raw = xbox.getRawAxis(5); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public static double RightArc(){ return Math.toDegrees(Math.atan2(RightY(), RightX())) + 90; }
    public static double LeftArc(){ return Math.toDegrees(Math.atan2(LeftY(), LeftX())) + 90; }
    public static double getLeftJoystickAngle() {  return Math.toDegrees(Math.atan2(xbox.getRawAxis(0), -xbox.getRawAxis(1))); }
    public static double LeftMag()  { return Math.hypot(xbox.getRawAxis(1), xbox.getRawAxis(0)); }
    public static double RightMag() { return Math.hypot(xbox.getRawAxis(4), xbox.getRawAxis(5)); }
    public static double LeftTrigger() { double raw = xbox1.getRawAxis(2); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public static double RightTrigger() { double raw = xbox1.getRawAxis(3); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public static boolean LeftBumper()     { return xbox.getBumperPressed(Hand.kLeft); }
    public static boolean LeftBumperDown() { return xbox.getBumper(Hand.kLeft); }
    public static boolean RightBumper()    { return xbox.getBumperPressed(Hand.kRight); }
    public static boolean RightBumperDown(){ return xbox.getBumper(Hand.kRight); }
    public static boolean AButton()        { return xbox.getAButtonPressed(); }
    public static boolean AButtonDown()    { return xbox.getAButton(); }
    public static boolean BButton()        { return xbox.getBButtonPressed(); }
    public static boolean BButtonDown()    { return xbox.getBButton(); }
    public static boolean XButton()        { return xbox.getXButtonPressed(); }
    public static boolean XButtonDown()    { return xbox.getXButton(); }
    public static boolean YButton()        { return xbox.getYButtonPressed(); }
    public static boolean YButtonDown()    { return xbox.getYButton(); }
    public static boolean StartButton()    { return xbox.getStartButtonPressed(); }
    public static boolean BackButton()     { return xbox.getRawButtonPressed(7); }
    public static boolean LSClick()        { return xbox.getStickButtonPressed(Hand.kLeft); }
    public static boolean RSClick()        { return xbox.getStickButtonPressed(Hand.kRight); }
    public static boolean LSClickDown()    { return xbox.getStickButton(Hand.kLeft); }
    public static boolean RSClickDown()    { return xbox.getStickButton(Hand.kRight); }
    public static double  Dpad()           { return xbox.getPOV(); }

    public static double LEDValue() { return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").getNumber(0).doubleValue(); }
    public static void LEDOn() { NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3); } 
    public static void LEDOff() { NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1); } 
    public static double LimelightTx() { return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0); }
    
    public static boolean DpadUp() { double POV = xbox.getPOV(); 
        if((POV >= 0 && POV < 45) || (POV >= 315 && POV < 360)) { return true; } else { return false; }
    } public static boolean DpadRight(){ double POV = xbox.getPOV(); 
        if(POV >= 45 && POV < 135) { return true; } else { return false; }
    } public static boolean DpadDown() { double POV = xbox.getPOV(); 
        if(POV >= 135 && POV < 225) { return true; } else { return false; }
    } public static boolean DpadLeft() { double POV = xbox.getPOV(); 
        if(POV >= 225 && POV < 315) { return true; } else { return false; }
    }
}