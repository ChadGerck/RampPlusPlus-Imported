/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.auto_commands;

//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import frc.robot.commands.ToggleConveyor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SlalomAuto extends SequentialCommandGroup{
    /**
     * Creates new Slalom Auto
     */

    public SlalomAuto(){
      super(
        new DriveTo(70),//forward
        new DriveTo(70),
        new Spin(180),//turn around
        new DriveTo(70),//drive forward
        new DriveTo(70),
        new WaitCommand(0.2)
          ); 
    }
}
