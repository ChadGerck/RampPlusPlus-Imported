package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.Arm;

public class ArmDown extends CommandBase {
  public ArmDown() {}
  @Override public void initialize() {
    Arm.getInstance().setSetpoint(Constants.ARM_MID);
    Arm.getInstance().enable();
  }
  @Override public void execute() {}
  @Override public void end(boolean interrupted) {Arm.getInstance().disable();}
  @Override public boolean isFinished() {return !OI.armDown.get();}
}