package org.firstinspires.ftc.teamcode.hardware;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class Lift extends Mechanism{
    DcMotorEx motor1;
    DcMotorEx motor2;
    // PID numbers
    public static double kP;
    public static double kG;
    public static double kD;
    public double lastTime = 0;
    double[] lastError = new double[2];
     double[] error = new double[2];
    public double target;
    public   double low;
    public double medium;
    public   double high;
    public double bound; // for when error is large
    ElapsedTime time = new ElapsedTime();

    @Override
    public void init(HardwareMap hwMap) {
        // motor setup stuff
        motor1 = hwMap.get(DcMotorEx.class, "lift1");
        motor2 = hwMap.get(DcMotorEx.class, "lift2");
        time.reset();
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor1.setDirection(DcMotorEx.Direction.FORWARD);
        motor2.setDirection(DcMotorEx.Direction.REVERSE);
        lastError[0] = 0;
        lastError[1] = 0;
        time.reset();
    }
    public double getError(DcMotorEx motor) {
        return target - motor.getCurrentPosition();
    }
    public void update() {
        lastError[0] = getError(motor1);
        lastError[1] = getError(motor2);
        lastTime = time.milliseconds();
    }
    public void setPosition(double x) {
        target = x;

        while (Math.abs(error[0]) >= bound) {
            error[0] = getError(motor1);
            error[1] = getError(motor2);
            double currentTime = time.milliseconds();
            motor1.setPower(Range.clip(kP * error[0] + kD * (error[0] -lastError[0])/(currentTime - lastTime) + kG, -1, 1));
            motor2.setPower(Range.clip(kP * error[1] + kD * (error[1] -lastError[1])/(currentTime - lastTime) + kG, -1, 1));
            update();
        }

    }
    public void scoreLow() {
        setPosition(low);

    }
    public void scoreHigh() {
        setPosition(high);

    }
    public void scoreMid() {
        setPosition(medium);

    }
}


