package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Utilities.NavX;

public class TurnTo extends CommandBase {
    private double error, diffError, lastError, testPIDOutput, navTo;
    private volatile double PIDOutput = 0; 
    static final double kP = 1, kD = .1; 
    boolean forceFinish;
    public TurnTo(double degree){navTo = degree;}
    @Override public void initialize() {
        lastError = getError(); 
    }
    @Override public void execute() {
    	error = getError(); 
    	diffError = lastError - error; 
        testPIDOutput = kP * error + kD * diffError; 
        SmartDashboard.putNumber("error: ", error);
        SmartDashboard.putNumber("diffError: ", diffError);
        SmartDashboard.putNumber("testPIDOutput: ", testPIDOutput);
        testPIDOutput = Math.min(testPIDOutput, .5);
        PIDOutput = Math.max(testPIDOutput, -.5); 
        Drivetrain.getInstance().setRaw(PIDOutput, -PIDOutput);
        if(PIDOutput < .05  && PIDOutput > -.05){ forceFinish = true; }
        lastError = error; 
        
        SmartDashboard.putNumber("NavX: ", NavX.getInstance().Angle());
    }
    @Override public void end(boolean interrupted) {
        Drivetrain.getInstance().disable();
        Drivetrain.getInstance().setSpeed(0, 0);
    }
    @Override public boolean isFinished(){return forceFinish;}
    public double getError(){
        double navFinal = boundHalfDegrees(navTo-NavX.getInstance().Angle())/180; 
        SmartDashboard.putNumber("navFinal: ", navFinal);
        SmartDashboard.putNumber("navTo: ", navTo);
        SmartDashboard.putNumber("navTo Angle: ", NavX.getInstance().Angle());
        return navFinal;
    }	
	public static double boundHalfDegrees(double angle){while(angle>=180)angle-=360; while(angle<-180)angle+=360; return angle;}
    public double getPIDOutput(){try {return PIDOutput;} catch (Exception e) {return 0; }}
}
