package frc.robot.auto_commands;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.commands.ToggleConveyor;

public class BarrelRacingAuto extends SequentialCommandGroup{
    public BarrelRacingAuto(){
        super(
            new DriveTo(115),
            new Spin(-60),
            new DriveTo(66),
            new Spin(55),
            new DriveTo(-90),
            new Spin(50),
            new DriveTo(75),
            new Spin(-50), // just changed
            new DriveTo(117),
            new Spin(75),
            new DriveTo(62),// will change
            new Spin(-67),
            new DriveTo(-103),
            new Spin(-50),
            new DriveTo(133),
            new Spin(54),// will change
            new DriveTo(71),
            new Spin(90),
            new DriveTo(60),
            new Spin(79),
            new DriveTo(180),
            new Spin(-20),
            new DriveTo(76),
            new WaitCommand(0.2)
        );
    }
}
