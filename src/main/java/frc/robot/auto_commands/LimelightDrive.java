package frc.robot.auto_commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Utilities.Limelight;

public class LimelightDrive extends CommandBase {
  double ffTimer, previousPos;
  boolean forceFinish;
  public LimelightDrive() {}
  @Override public void initialize() {
    forceFinish = false;
    ffTimer = Timer.getFPGATimestamp();
    Limelight.getInstance().turnOn();
    previousPos = Drivetrain.getInstance().getRightPosition();
  }
  @Override public void execute() {
    double error = Limelight.getInstance().getX();
    double y = Limelight.getInstance().getY();
    double speed;
    if(Math.abs(error) < 0.65){error = 0;}
    double rotSpeed = (0.3 * error)/ Math.sqrt(1 + (0.8 * Math.pow(error, 2)));
    if(y > 3){speed = 0.4;} 
    else {speed = 0.7;}
    if(y == 0){speed = 0.3;}
    if(Math.abs(error) > 15){error = 0;speed = 0;}
    Drivetrain.getInstance().setSpeed(speed, -rotSpeed);
    if(Math.floor(Drivetrain.getInstance().getRightPosition() * 10000)/10000.0 != Math.floor(previousPos * 10000)/10000.0){ffTimer = Timer.getFPGATimestamp();}
    else{System.out.println("Stay");}
    forceFinish = Timer.getFPGATimestamp() - ffTimer >= 0.2;
    previousPos = Drivetrain.getInstance().getRightPosition();
  }
  @Override public void end(boolean interrupted) {Limelight.getInstance().turnOff();}
  @Override public boolean isFinished(){return forceFinish;}
}
