package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  private final VictorSPX intake = new VictorSPX(RobotMap.intake);
  private static Intake in;
  public static Intake getInstance(){if(in==null)in=new Intake();return in;}
  private Intake(){intake.setNeutralMode(NeutralMode.Brake);}
  @Override public void periodic() {}
  public void run(double dir){intake.set(ControlMode.PercentOutput, dir);}
  public double getBusVoltage(){return intake.getBusVoltage();}
  public double getMotorOutputVoltage(){return intake.getMotorOutputVoltage();}
}
