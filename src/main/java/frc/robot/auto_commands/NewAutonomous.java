package frc.robot.auto_commands;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;

public class NewAutonomous extends SequentialCommandGroup{
    public NewAutonomous(){
        super(
            //new MoveDistance(0,.5,5),
            new MoveDistance(0,.5,3),
            new TurnTo(-90),
            new MoveDistance(-90,.5,3)
        );
    }
}
