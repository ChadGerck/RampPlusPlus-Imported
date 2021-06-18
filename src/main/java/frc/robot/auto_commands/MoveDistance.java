package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Utilities.NavX;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Robot;

public class MoveDistance extends CommandBase {
  double ffTimer, previousPos;
  double degree, velocity, position; 
  double distanceL, distanceR, avgDistance, distanceDone, templ, tempr;
  boolean forceFinish;
  public MoveDistance(){position = 1; velocity = .25; degree = 0; }
  public MoveDistance(double feet){position = feet;}
  public MoveDistance(double angle, double speed, double distance){
    position = distance; velocity = speed; degree = angle; avgDistance=0; forceFinish = false; 
  }
  @Override public void initialize() {
    //forceFinish = false;
    ffTimer = Timer.getFPGATimestamp();
    //distanceL = Drivetrain.getInstance().getLeftDistance();
    //distanceR = Drivetrain.getInstance().getRightDistance();
    //avgDistance = 0; 
    //distanceDone = (distanceL+distanceR)/2;
    //SmartDashboard.putNumber("DistanceDone: ", distanceDone);
    //templ = velocity; 
    //tempr = velocity; 
    Drivetrain.getInstance().zero();
    //Drivetrain.getInstance().setSetpoint(position);
    //Drivetrain.getInstance().enable();
    //previousPos = Drivetrain.getInstance().getRightPosition();
  }
  @Override public void execute() {
    templ = velocity; 
    tempr = velocity; 
    SmartDashboard.putNumber("Gyro: ", NavX.getInstance().Angle());
    distanceL = Drivetrain.getInstance().getLeftDistance();
    distanceR = Drivetrain.getInstance().getRightDistance();
    SmartDashboard.putNumber("DistanceL: ", distanceL);
    SmartDashboard.putNumber("DistanceR: ", distanceR);
    Drivetrain.getInstance().setRaw(templ, tempr);
    /*
    if(Math.sin(Math.toRadians(NavX.getInstance().Angle()+angle)) < -.01) {
        if(templ < speed + .05) { templ += .001;  }
        else { templ -= .001; tempr -= .002; }
    }else if(Math.sin(Math.toRadians(NavX.getInstance().Angle()+angle)) > .01 ) {
        if(tempr < speed + .05) { tempr += .001; }
        else { tempr -= .001; templ -= .002; } 
    }else {
        templ = speed; 
        tempr = speed; 
    }
    */
    avgDistance = ((Drivetrain.getInstance().getLeftDistance() + Drivetrain.getInstance().getRightDistance())/2);
    SmartDashboard.putNumber("AverageDistance: ", avgDistance);
    if(avgDistance > position){ forceFinish = true; }
    
      /*
    if(Drivetrain.getInstance().getRightPosition() != previousPos){ffTimer = Timer.getFPGATimestamp();}
    if(Math.abs(Drivetrain.getInstance().getController().getSetpoint()) - Math.abs(Drivetrain.getInstance().getRightPosition()) < 0.75 && Math.floor(Timer.getFPGATimestamp() * 1000.0) / 1000.0 - Math.floor(ffTimer * 1000.0) / 1000.0 >= Constants.DRIVETRAIN_LAG_TIMER)
    {forceFinish = true;} 
    else if(Math.floor(Timer.getFPGATimestamp() * 1000.0) / 1000.0 - Math.floor(ffTimer * 1000.0) / 1000.0 >= Constants.DRIVETRAIN_LAG_TIMER*2)
    {forceFinish = true;}
    previousPos = Drivetrain.getInstance().getRightPosition();
    */
  }
  @Override public void end(boolean interrupted) {
    Drivetrain.getInstance().disable();
    Drivetrain.getInstance().setRaw(0, 0);
  }
  @Override public boolean isFinished(){return  forceFinish;}
}