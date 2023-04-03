package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw extends Mechanism {
    Servo claw;
    boolean isOpen;
    double clawClosed = 0;
    double clawOpen = 1;

    @Override
    public void init(HardwareMap hwMap) {
        claw = hwMap.get(Servo.class, "clawMotor");
        open();
    }

    public void open() {
        claw.setPosition(clawOpen);

        isOpen = true;
    }

    public void close() {
        claw.setPosition(clawClosed);

        isOpen = false;
    }

    public void toggle() {
        if (isOpen) {
            close();
        } else {
            open();
        }
    }
}
