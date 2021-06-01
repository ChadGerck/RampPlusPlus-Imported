package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Lifter extends SubsystemBase {
  private boolean isHigh;
  DoubleSolenoid lifter = new DoubleSolenoid(RobotMap.liftHigh, RobotMap.liftLow);
  private static Lifter in;
  public static Lifter getInstance(){if(in==null)in=new Lifter();return in;}
  private Lifter() { lifter.set(Value.kReverse); isHigh = false;}
  @Override public void periodic() {}
  public void cycle(){
    if(isHigh){
      lifter.set(Value.kReverse);
      System.out.println("GETTING LOW");
      Intake.getInstance().run(Constants.INTAKE_REVERSE_SPEED);
    }
    else{
      lifter.set(Value.kForward);
      System.out.println("GETTING HIGH");
      Intake.getInstance().run(0);
    }
    isHigh = !isHigh;
  }
}
