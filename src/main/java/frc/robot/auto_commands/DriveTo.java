package frc.robot.auto_commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class DriveTo extends CommandBase {
  double position, ffTimer, previousPos;
  boolean forceFinish;
  public DriveTo(double inches){position = (inches*Constants.TICKS_PER_INCH);}
  @Override public void initialize() {
    forceFinish = false;
    ffTimer = Timer.getFPGATimestamp();
    Drivetrain.getInstance().zero();
    Drivetrain.getInstance().setSetpoint(position);
    Drivetrain.getInstance().enable();
    previousPos = Drivetrain.getInstance().getRightPosition();
  }
  @Override public void execute() {
    if(Drivetrain.getInstance().getRightPosition() != previousPos){ffTimer = Timer.getFPGATimestamp();}
    if(Math.abs(Drivetrain.getInstance().getController().getSetpoint()) - Math.abs(Drivetrain.getInstance().getRightPosition()) < 0.75 && Math.floor(Timer.getFPGATimestamp() * 1000.0) / 1000.0 - Math.floor(ffTimer * 1000.0) / 1000.0 >= Constants.DRIVETRAIN_LAG_TIMER)
    {forceFinish = true;} 
    else if(Math.floor(Timer.getFPGATimestamp() * 1000.0) / 1000.0 - Math.floor(ffTimer * 1000.0) / 1000.0 >= Constants.DRIVETRAIN_LAG_TIMER*2)
    {forceFinish = true;}
    previousPos = Drivetrain.getInstance().getRightPosition();
  }
  @Override public void end(boolean interrupted) {
    Drivetrain.getInstance().disable();
    Drivetrain.getInstance().setSpeed(0, 0);
  }
  @Override public boolean isFinished(){return Math.abs(Drivetrain.getInstance().getRightPosition()) >= Math.abs(position)|| forceFinish;}
}
