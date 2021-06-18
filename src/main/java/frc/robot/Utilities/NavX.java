package frc.robot.Utilities;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;

public class NavX {
    AHRS navx;
    private static NavX in;
    public static NavX getInstance(){if(in==null)in=new NavX();return in;}
    private NavX(){
        try { navx = new AHRS(SPI.Port.kMXP); } 
        catch (RuntimeException ex) { DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true); }
    }
    public void zeroAngle(){ navx.reset();  }
    public double getHeading(){ return (((navx.getAngle())%360)*Math.PI)/180; }
    public double Angle(){ return Angle(0); }
    public double Angle(double add){ double angle = navx.getAngle()+add;
        while(angle>180)angle-=360;while(angle<-180)angle+=360;return angle;
    }
}
