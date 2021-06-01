package frc.robot.auto_commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Intake;

public class TimedIntake extends CommandBase {
  double startTime;
  double seconds;
  public TimedIntake(double sec){seconds = sec;}
  @Override public void initialize() {
    startTime = Timer.getFPGATimestamp();
    Intake.getInstance().run(Constants.INTAKE_SPEED);
  }
  @Override public void execute() {
    if(Belt.getInstance().getLimitSwitch()){Belt.getInstance().run(Constants.BELT_SPEED);}
    else{Belt.getInstance().run(0);}
  }
  @Override public void end(boolean interrupted) {
    Belt.getInstance().run(0);
    Intake.getInstance().run(0);
  }
  @Override public boolean isFinished() {return Timer.getFPGATimestamp() - startTime >= seconds;}
}
