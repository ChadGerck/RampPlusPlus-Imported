package frc.robot.auto_commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.commands.ToggleConveyor;

public class TrenchAuto extends SequentialCommandGroup {
  public TrenchAuto() {
    super(
      new ParallelCommandGroup(
      new DriveTo(132),
      //new TimedIntake(3.2)),
      new Spin(30),
      new DriveTo(-131),
      new Spin(-35),
      //new LimelightOnOff(true),
      new WaitCommand(0.1),
      new LimelightDrive(),
     // new ToggleConveyor(),
      new WaitCommand(0.2)));
      //new Outtake());
  }
}
