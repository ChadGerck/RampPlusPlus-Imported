package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
import frc.robot.Utilities.ColorSensor;

public class Rotation extends CommandBase {
  private int counter;
  private int pastColor;
  private static Rotation in;
  public static Rotation getInstance(){if(in==null)in=new Rotation();return in;}
  private Rotation() {counter = 0;}
  @Override public void initialize() {
    counter = 0;
    pastColor = 1;
    Arm.getInstance().spin(.3);
    Arm.getInstance().setRawSpeed(Constants.SPIN_DOWN_FORCE);
  }
  @Override public void execute() {
    if(ColorSensor.getInstance().getEstimatedColor() == 4 && pastColor != 4){counter++;}
    if(counter >= 2 && counter <= Constants.RED_DETECTIONS-2){Arm.getInstance().spin(.5);} 
    else {Arm.getInstance().spin(.3);}
    pastColor = ColorSensor.getInstance().getEstimatedColor();
  }
  @Override public void end(boolean interrupted) {
    Arm.getInstance().spin(0);
    Arm.getInstance().setRawSpeed(0.0);
  }
  @Override public boolean isFinished() {return counter >= Constants.RED_DETECTIONS;}
}