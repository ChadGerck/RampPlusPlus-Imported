package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Utilities.Limelight;
import frc.robot.Utilities.NavX;
//import frc.robot.auto_commands.BarrelRacingAuto;
import frc.robot.auto_commands.BounceAuto;
import frc.robot.auto_commands.NewAutonomous;
import frc.robot.auto_commands.SlalomAuto;
//import frc.robot.auto_commands.TrenchAuto;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  
  public static final Drivetrain Drivetrain = new Drivetrain();
  public static double distanceDone = 0; 
  SendableChooser<SequentialCommandGroup> autoChooser;
  @Override public void robotInit() {
    Limelight.getInstance().turnOff();
    new Constants();
    Arm.getInstance().zeroArmEnc();
    //Elevator.getInstance().setEncoderZero();
    autoChooser = new SendableChooser<>();
    //autoChooser.addOption("Barrel Racing Auto", new BarrelRacingAuto());
    autoChooser.addOption("Bounce Auto", new BounceAuto());
    autoChooser.addOption("Slalom Auto", new SlalomAuto());
    SmartDashboard.putData("AUTO PICK", autoChooser);
  }
  @Override public void robotPeriodic() { CommandScheduler.getInstance().run(); }
  @Override public void disabledInit() { Drivetrain.disable(); }
  @Override public void disabledPeriodic() {}
  @Override public void autonomousInit() {
    CommandScheduler.getInstance().cancelAll();
    Drivetrain.setLeftReverse(false);
    NavX.getInstance().zeroAngle();
    Drivetrain.zero();
    Limelight.getInstance().turnOff();
    //new BounceAuto().schedule();
    //Autonomous.Auto();
    new NewAutonomous().schedule();
  }
  @Override public void autonomousPeriodic() {}
  @Override public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
    Drivetrain.setLeftReverse(false);
    NavX.getInstance().zeroAngle();
    Intake.getInstance().run(Constants.INTAKE_REVERSE_SPEED);
  }
  @Override public void teleopPeriodic() {
    Drivetrain.setSpeed(OI.LeftY(), -OI.RightX());
    System.out.println(Elevator.getInstance().getEncPos());
    SmartDashboard.putNumber("NavX: ", NavX.getInstance().Angle());
  }
  
  public static void MoveDistance(double angle, double speed, double distance) { 
		
		double distanceL = Drivetrain.getLeftDistance();
		double distanceR = Drivetrain.getRightDistance();
		double avgDistance = 0; 
		double distanceDone = (distanceL+distanceR)/2;
		double templ = speed; 
		double tempr = speed; 
		
		while(avgDistance < distance ) {
			SmartDashboard.putNumber("Gyro: ", NavX.getInstance().Angle());
			distanceL = Drivetrain.getLeftDistance();
			distanceR = Drivetrain.getRightDistance();
			SmartDashboard.putNumber("DistanceL: ", distanceL);
			SmartDashboard.putNumber("DistanceR: ", distanceR);
      Drivetrain.setRaw(templ, tempr);
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
			try{Thread.sleep(20);}catch(InterruptedException e){e.printStackTrace();}
			avgDistance = ((Drivetrain.getLeftDistance()) + (Drivetrain.getRightDistance())/2)-distanceDone;
			System.out.println(avgDistance); 
			
		}  
		Drivetrain.setRaw(0, 0); 
  }
  public static void MoveForward(){
    MoveDistance(NavX.getInstance().Angle(), .25, 1);
  }
  
  
  @Override public void testInit() { CommandScheduler.getInstance().cancelAll(); }
  @Override public void testPeriodic() {}
}

