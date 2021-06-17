package frc.robot.auto_commands;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;

public class NewAutonomous extends SequentialCommandGroup{
    public NewAutonomous(){
        super(
            new MoveDistance(0, .25, 4),
            new MoveDistance(0, .1, 5), 
            new MoveDistance(0, .5, 6)
        );
    }
}
