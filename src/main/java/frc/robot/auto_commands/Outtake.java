package frc.robot.auto_commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Belt;

public class Outtake extends CommandBase {
  double startTime;
  public Outtake() {}
  @Override public void initialize() {
    startTime = Timer.getFPGATimestamp();
    Belt.getInstance().run(Constants.BELT_OUTTAKE_SPEED);
  }
  @Override public void execute() {}
  @Override public void end(boolean interrupted) {Belt.getInstance().run(0);}
  @Override public boolean isFinished() {return Timer.getFPGATimestamp() - startTime >= Constants.BELT_OUTTAKE_TIME;}
}
