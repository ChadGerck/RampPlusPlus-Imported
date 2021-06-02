package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Utilities.Limelight;
import frc.robot.Utilities.NavX;
//import frc.robot.auto_commands.BarrelRacingAuto;
import frc.robot.auto_commands.BounceAuto;
import frc.robot.auto_commands.SlalomAuto;
//import frc.robot.auto_commands.TrenchAuto;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  // private RobotContainer m_robotContainer;
  SendableChooser<SequentialCommandGroup> autoChooser;
  @Override public void robotInit() {
    Limelight.getInstance().turnOff();
    new OI();
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
  @Override public void disabledInit() { Drivetrain.getInstance().disable(); }
  @Override public void disabledPeriodic() {}
  @Override public void autonomousInit() {
    CommandScheduler.getInstance().cancelAll();
    Drivetrain.getInstance().setLeftReverse(false);
    NavX.getInstance().zeroAngle();
    Drivetrain.getInstance().zero();
    Limelight.getInstance().turnOff();
    new BounceAuto().schedule();
  }
  @Override public void autonomousPeriodic() {}
  @Override public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
    Drivetrain.getInstance().setLeftReverse(false);
    NavX.getInstance().zeroAngle();
    Intake.getInstance().run(Constants.INTAKE_REVERSE_SPEED);
  }
  @Override public void teleopPeriodic() {
    Drivetrain.getInstance().setSpeed(OI.LeftY(), OI.RightX());
    System.out.println(Elevator.getInstance().getEncPos());
  }
  @Override public void testInit() { CommandScheduler.getInstance().cancelAll(); }
  @Override public void testPeriodic() {}
}

