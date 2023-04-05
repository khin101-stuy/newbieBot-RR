package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm extends Mechanism{
    Servo armLeft, armRight;
    int pickPos = 0, scorePos = 0, readyPos = 1, startPos = 0;
    @Override
    public void init(HardwareMap hwMap) {
        armLeft = hwMap.get(Servo.class, "armLeft");
        armRight = hwMap.get(Servo.class, "armRight");
        start();
    }
    public void start() {
        armLeft.setPosition(startPos);
        armRight.setPosition(startPos);
    }
    public void pickUp() {
        armLeft.setPosition(pickPos);
        armRight.setPosition(pickPos);

    }
    public void ready() {
        armLeft.setPosition(readyPos);
        armRight.setPosition(readyPos);
    }
    public void score() {
        armLeft.setPosition(scorePos);
        armRight.setPosition(scorePos);
    }
}
