package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.Belt;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  boolean runningBelt;
  public RunIntake() {}
  @Override public void initialize() {
    Intake.getInstance().run(Constants.INTAKE_SPEED);
    runningBelt = false;
  }
  @Override public void execute() {
    if(Belt.getInstance().getLimitSwitch() && !runningBelt){new BeltScheduler().schedule();runningBelt = true;}
    else if(!Belt.getInstance().getLimitSwitch()) {runningBelt = false;}
  }
  @Override public void end(boolean interrupted) {Intake.getInstance().run(Constants.INTAKE_REVERSE_SPEED);}
  @Override public boolean isFinished() {return !OI.intake.get();}
}
