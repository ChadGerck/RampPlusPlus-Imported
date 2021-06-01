package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ToggleCommand extends CommandBase {
  private CommandBase command;
  public ToggleCommand(CommandBase cmd) {command = cmd;}
  @Override public void initialize() {
    if(command.isScheduled()){command.cancel();} 
    else{command.schedule();}
  }
  @Override public void execute() {}
  @Override public void end(boolean interrupted) {}
  @Override public boolean isFinished() {return true;}
}
