package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drivetrain extends PIDSubsystem {
  private static CANSparkMax leftMaster ,leftSlaveA ,leftSlaveB;
  private static CANSparkMax rightMaster,rightSlaveA,rightSlaveB;
  private static DoubleSolenoid shift = new DoubleSolenoid(RobotMap.shiftHigh, RobotMap.shiftLow);
  private static boolean isHigh;
  private final DifferentialDrive drive;
  public Drivetrain() {
    super(new PIDController(Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D));
    leftMaster = new CANSparkMax(RobotMap.leftMaster , MotorType.kBrushless);
    leftSlaveA = new CANSparkMax(RobotMap.leftSlaveA , MotorType.kBrushless);
    leftSlaveB = new CANSparkMax(RobotMap.leftSlaveB , MotorType.kBrushless);
    rightMaster= new CANSparkMax(RobotMap.rightMaster, MotorType.kBrushless);
    rightSlaveA= new CANSparkMax(RobotMap.rightSlaveA, MotorType.kBrushless);
    rightSlaveB= new CANSparkMax(RobotMap.rightSlaveB, MotorType.kBrushless);
    zero();
    setSetpoint(0);
    leftSlaveA .follow(leftMaster );leftSlaveB .follow(leftMaster );
    rightSlaveA.follow(rightMaster);rightSlaveB.follow(rightMaster);
    leftMaster.setIdleMode(IdleMode.kBrake);rightMaster.setIdleMode(IdleMode.kBrake);
    isHigh = true;
    shift.set(Value.kReverse);
    drive = new DifferentialDrive(leftMaster, rightMaster);
    disable();
  }
  public void setLeftReverse(boolean reverse){leftMaster.setInverted(reverse);}
  public static void shift(){
    if(isHigh)shift.set(Value.kForward);
     else shift.set(Value.kReverse);
     isHigh = !isHigh;
  }
  @Override public void periodic(){super.periodic();}
  public void setSpeed(final double xSpeed, final double zRotation){drive.arcadeDrive(xSpeed, zRotation);}
  public static void driveForward(double speed){ leftMaster.set(speed); rightMaster.set(speed);}
  public static void setRaw(double leftValue, double rightValue){ leftMaster.set(-leftValue); rightMaster.set(rightValue); }
  public static double getLeftPosition(){return -leftMaster.getEncoder().getPosition();}
  public static double getLeftDistance(){return -leftMaster.getEncoder().getPosition()/6.46;}
  public static double getRightPosition(){return rightMaster.getEncoder().getPosition();}
  public static double getRightDistance(){return rightMaster.getEncoder().getPosition()/6.46;}
  public static void setPoint(double point){setSetpoint(point);} 
  public static void zero(){ leftMaster.getEncoder().setPosition(0); rightMaster.getEncoder().setPosition(0);}
  @Override protected double getMeasurement() {return rightMaster.getEncoder().getPosition();}
  @Override protected void useOutput(double output, final double setpoint) {
    output = -output;
    if(output > 1) output = 1;
    else if (output < -1) output = -1;
    setSpeed(output, 0);
  }
}