package frc.robot.auto_commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Utilities.Limelight;

public class LimelightOnOff extends CommandBase {
  boolean mode;
  public LimelightOnOff(boolean on) {mode = on;}
  @Override public void initialize() {
    if(mode){Limelight.getInstance().turnOn();}
    else {Limelight.getInstance().turnOff();}
  }
  @Override public void execute() {}
  @Override public void end(boolean interrupted) {}
  @Override public boolean isFinished() {return true;}
}
