package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.auto_commands.TrenchAuto;
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmUp;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.Rotation;
import frc.robot.commands.ManualBeltControl;
import frc.robot.commands.RunIntake;
import frc.robot.commands.Shift;
import frc.robot.commands.SpinTo;
import frc.robot.commands.ToggleCommand;
import frc.robot.commands.ToggleConveyor;
import frc.robot.Utilities.Limelight;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class OI {

    private static final double DEADZONE_LIMIT = 0.1;
    private static final int ARM_UP_PORT = 4; //Xbox Y
    private static final int ARM_DOWN_PORT = 3; //Xbox X
    private static final int INTAKE_PORT = 1; //Xbox A
    private static final int SPIN_TO_PORT = 2; //Xbox B
    private static final int SPIN_THREE_PORT = 6; //Xbox Right Bumper
    private static final int CONVEYOR_POSITION_PORT = 5; //XBox Left Bumper
    private static final int SHIFTER_PORT = 5; //Right Wheel Shifter *Unverified*
    
    private static final int ELEVATOR_UP_ANGLE = 0; //Xbox DPad Up
    private static final int ELEVATOR_DOWN_ANGLE = 180; //Xbox DPad Down
    private static final int LIMELIGHT_SHIFT_PORT = 1; //Stick Trigger

    private static Joystick stick;
    private static Joystick wheel;
    public static XboxController xbox,xbox1;

    public static Trigger armUp, armDown, intake, spinTo, spinThree, conveyorPosition, runBelt, shifter, limelightState;

    public static Trigger spinCommand;
    
    public static POVButton elevUp, elevDown;



    public OI() {

        stick = new Joystick(1);
        wheel = new Joystick(2);

        xbox = new XboxController(0);
        xbox1 = new XboxController(1);


        elevUp = new POVButton(xbox, ELEVATOR_UP_ANGLE);
        elevDown = new POVButton(xbox, ELEVATOR_DOWN_ANGLE);

        elevUp.whenActive(new ElevatorUp());
        elevDown.whenActive(new ElevatorDown());

        spinCommand = new POVButton(xbox, 90);
        spinCommand.whenActive(new TrenchAuto());

        armUp = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(ARM_UP_PORT);

            }

        };

        armDown = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(ARM_DOWN_PORT);

            }

        };

        intake = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(INTAKE_PORT);

            }

        };

        spinTo = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(SPIN_TO_PORT);

            }

        };

        spinThree = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(SPIN_THREE_PORT);

            }

        };

        conveyorPosition = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawButton(CONVEYOR_POSITION_PORT);

            }

        };

        runBelt = new Trigger() {

            @Override
            public boolean get() {

                return xbox.getRawAxis(3) > Constants.BELT_DEAD_ZONE || xbox.getRawAxis(2) > Constants.BELT_DEAD_ZONE;

            }

        };
        
        shifter = new Trigger(){

            @Override
            public boolean get() {

                return wheel.getRawButton(SHIFTER_PORT);

            }

        };

        limelightState = new Trigger(){

            @Override
            public boolean get() {
                
                return stick.getRawButton(LIMELIGHT_SHIFT_PORT);

            }

        };

        armUp.whenActive(new ArmUp());
        armDown.whenActive(new ArmDown());
        intake.whenActive(new RunIntake());
        spinTo.whenActive(new ToggleCommand(SpinTo.getInstance()));
        spinThree.whenActive(new ToggleCommand(Rotation.getInstance()));
        conveyorPosition.whenActive(new ToggleConveyor());
        runBelt.whenActive(new ManualBeltControl());
        shifter.whenActive(new Shift());
        limelightState.whenActive(new CommandBase() {

            @Override
            public void initialize() {

                Limelight.getInstance().switchState();

            }

            @Override
            public boolean isFinished() {
                
                return true;

            }
            
        });

    }

    public static double getDriveHoz() {

        if (Math.abs(wheel.getRawAxis(0)) > Constants.WHEEL_DEADZONE) {

            if(wheel.getRawAxis(0) > 0){
                return -Math.pow(wheel.getRawAxis(0), 2);
            }else{
                return Math.pow(wheel.getRawAxis(0), 2);
            }

        }

        return 0;

    }

    public static double getDriveFwd() {

        if (Math.abs(stick.getRawAxis(1)) > Constants.STICK_DEADZONE) {
            
            return stick.getRawAxis(1);
        
        }
        
        return 0;

    }


    public static double LeftX  (){ double raw = xbox.getRawAxis(0); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public static double LeftY  (){ double raw = xbox.getRawAxis(1); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public static double RightX (){ double raw = xbox.getRawAxis(4); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public static double RightY (){ double raw = xbox.getRawAxis(5); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public double RightArc(int x){ Remote(x); return Math.toDegrees(Math.atan2(RightY(x), RightX(x))) + 90; }
    public double LeftArc(int x){ Remote(x); return Math.toDegrees(Math.atan2(LeftY(x), LeftX(x))) + 90; }
    public double RightArc(int x){ Remote(x); return Math.toDegrees(Math.atan2(RightY(x), RightX(x))) + 90; }
    public double LeftArc(int x){ Remote(x); return Math.toDegrees(Math.atan2(LeftY(x), LeftX(x))) + 90; }
    
    public double getLeftJoystickAngle(int x){ Remote(x); return Math.toDegrees(Math.atan2(control.getRawAxis(0), -control.getRawAxis(1))); }
    public double LeftMag (int x){ Remote(x); return Math.hypot(control.getRawAxis(1), control.getRawAxis(0)); }
    public double RightMag(int x){ Remote(x); return Math.hypot(control.getRawAxis(4), control.getRawAxis(5)); }

    public double LeftX  (int x){ Remote(x); double raw = control.getRawAxis(0); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public double LeftY  (int x){ Remote(x); double raw = control.getRawAxis(1); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public double RightX (int x){ Remote(x); double raw = control.getRawAxis(4); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public double RightY (int x){ Remote(x); double raw = control.getRawAxis(5); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public double LeftTrigger (int x){ Remote(x); double raw = control.getRawAxis(2); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    public double RightTrigger(int x){ Remote(x); double raw = control.getRawAxis(3); return Math.abs(raw) < DEADZONE_LIMIT ? 0.0 : raw; }
    
    public boolean LeftBumper     (){  return xbox.getBumperPressed(Hand.kLeft); }
    public boolean LeftBumperDown (){  return xbox.getBumper(Hand.kLeft); }
    public boolean RightBumper    (){  return xbox.getBumperPressed(Hand.kRight); }
    public boolean RightBumperDown(){  return xbox.getBumper(Hand.kRight); }
    public boolean AButton        (){  return xbox.getAButtonPressed(); }
    public boolean AButtonDown    (){  return xbox.getAButton(); }
    public boolean BButton        (){  return xbox.getBButtonPressed(); }
    public boolean BButtonDown    (){  return xbox.getBButton(); }
    public boolean XButton        (){  return xbox.getXButtonPressed(); }
    public boolean XButtonDown    (){  return xbox.getXButton(); }
    public boolean YButton        (){  return xbox.getYButtonPressed(); }
    public boolean YButtonDown    (){  return xbox.getYButton(); }
    public boolean StartButton    (){  return xbox.getStartButtonPressed(); }
    public boolean BackButton     (){  return xbox.getRawButtonPressed(7); }
    public boolean LSClick        (){  return xbox.getStickButtonPressed(Hand.kLeft); }
    public boolean RSClick        (){  return xbox.getStickButtonPressed(Hand.kRight); }
    public boolean LSClickDown    (){  return xbox.getStickButton(Hand.kLeft); }
    public boolean RSClickDown    (){  return xbox.getStickButton(Hand.kRight); }
    public double  Dpad           (){  return xbox.getPOV(); }
}