package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lifter;

public class ToggleConveyor extends CommandBase {
  public ToggleConveyor() {}
  @Override public void initialize() {Lifter.getInstance().cycle();}
  @Override public void execute() {}
  @Override public void end(boolean interrupted) {}
  @Override public boolean isFinished(){return true;}
}
