package frc.robot.auto_commands;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.commands.ToggleConveyor;
public class SlalomAuto extends SequentialCommandGroup{
    public SlalomAuto(){
      super(
        new DriveTo(51),//forward
        new Spin(90),
        new DriveTo(60), //before long one
        new Spin(-96),
        new DriveTo(86),
        new WaitCommand(0.2),
        new DriveTo(90),//long run
        new Spin(-90),
        new DriveTo(54),// crosses sides
        new Spin(90),
        new DriveTo(64) , //heads towards driver station
        new Spin(90),
        new DriveTo(63), //turns to middle
        new Spin(90),
        new DriveTo(51), // goes down the middle
        new Spin(90),
        new DriveTo(66), //crosses
        new Spin(-95),
        new DriveTo(80), 
        new Spin(-15),// long one
        new DriveTo(83),
        new Spin(-90),
        new DriveTo(54),
        new Spin(90),
        new DriveTo(51)
          ); 
    }
}
