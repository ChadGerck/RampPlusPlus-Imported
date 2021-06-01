package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.Elevator;

public class ElevatorDown extends CommandBase {
  public ElevatorDown() {}
  @Override public void initialize(){Elevator.getInstance().set(-1);}
  @Override public void execute(){if(Elevator.getInstance().getEncPos() > -5000){Elevator.getInstance().set(-.4);}}
  @Override public void end(boolean interrupted) {Elevator.getInstance().set(0);}
  @Override public boolean isFinished(){return Elevator.getInstance().getEncPos() >= (Constants.ELEVATOR_DOWN)||!OI.elevDown.get();}
}
