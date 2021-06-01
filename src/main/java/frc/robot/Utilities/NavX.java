package frc.robot.Utilities;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;

public class NavX {
    AHRS navx;
    double angularZero;
    private static NavX in;
    public static NavX getInstance(){if(in==null)in=new NavX();return in;}
    private NavX(){
        try { navx = new AHRS(SPI.Port.kMXP); } 
        catch (RuntimeException ex) { DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true); }
    }
    public void zeroAngle(){ angularZero = navx.getAngle(); }
    public double getHeading(){ return ((-(navx.getAngle()-angularZero)%360)*Math.PI)/180; }
    public double getHeadingDeg(){ return -(navx.getAngle()-angularZero)%360; }
}
