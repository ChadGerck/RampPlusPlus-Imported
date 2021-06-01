package frc.robot.auto_commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Test extends SequentialCommandGroup {
  public Test() {
    super(new DriveTo(130));
  }
}
