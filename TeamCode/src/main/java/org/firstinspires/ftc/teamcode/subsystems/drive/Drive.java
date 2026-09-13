
package org.firstinspires.ftc.teamcode.subsystems.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 *
 */
public class Drive {

    private DcMotor leftBack=null;
    private DcMotor rightBack=null;
    private DcMotor leftFront=null;
    private DcMotor rightFront=null;

    private double strafeAdjust;  //use this parameter account for imperfect strafing

    /**
     * Default constructor,  initializes strafe adjust to 1.
     */
    public Drive(){
        strafeAdjust=1.;
    }

    /**
     * Constructor that allows programmer to account for imperfect strafing
     *
     * @param strfAdj
     */
    public Drive(double strfAdj){
        strafeAdjust=strfAdj;
    }


    /**
     * Initializes the mecanum drivetrain motors.
     *
     * @param hwMap
     */
    public void init(HardwareMap hwMap){
        leftBack=hwMap.get(DcMotor.class, "leftBack");
        rightBack=hwMap.get(DcMotor.class, "rightBack");
        leftFront=hwMap.get(DcMotor.class, "leftFront");
        rightFront=hwMap.get(DcMotor.class, "rightFront");

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    /**
     *
     * @param x  used for strafing
     * @param y  used for forward and backward motion
     * @param rx used for rotation
     */
    public void move(double x, double y, double rx){
        x= x*strafeAdjust;
        double denominator = Math.max(Math.abs(y)+ Math.abs(x)+ Math.abs(rx),1);
        leftFront.setPower((y+x+rx)/denominator);
        leftBack.setPower((y-x+rx)/denominator);
        rightFront.setPower((y-x-rx)/denominator);
        rightBack.setPower((y+x-rx)/denominator);
    }
}
