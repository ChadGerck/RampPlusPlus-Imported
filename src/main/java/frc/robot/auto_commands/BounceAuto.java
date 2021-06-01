package frc.robot.auto_commands;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.commands.ToggleConveyor;

public class BounceAuto extends SequentialCommandGroup{
    public BounceAuto(){
        super(
            new DriveTo(49),
            new Spin(-90),
            new DriveTo(-43),// hits first
            new WaitCommand(0.2),
            new DriveTo(43),
            new Spin(90),
            new DriveTo(40),
            new Spin(-90),
            new DriveTo(55),
            new Spin(90),
            new DriveTo(57),
            new Spin(-85),
            new DriveTo(-100),
            new WaitCommand(0.2),
            new DriveTo(98),
            new Spin(90),
            new DriveTo(95),
            new Spin(-90),
            new DriveTo(-98),
            new WaitCommand(0.2),
            new DriveTo(46),
            new Spin(90),
            new DriveTo(62)
            /*,
            new DriveTo(-89),
            new Spin(-135),
            new DriveTo(41),
            new Spin(45),
            new DriveTo(58),
            new Spin(90),
            new DriveTo(118),
            new Spin(45),
            new DriveTo(144)*/
        );
    }
}
