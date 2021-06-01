package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.Elevator;

public class ElevatorUp extends CommandBase {
  public ElevatorUp() {}
  @Override public void initialize(){Elevator.getInstance().set(1);}
  @Override public void execute(){if(Elevator.getInstance().getEncPos() < -24000){Elevator.getInstance().set(.5);}}
  @Override public void end(boolean interrupted){Elevator.getInstance().set(0);}
  @Override public boolean isFinished(){return Elevator.getInstance().getEncPos() <= (Constants.ELEVATOR_UP)||!OI.elevUp.get();}
}
