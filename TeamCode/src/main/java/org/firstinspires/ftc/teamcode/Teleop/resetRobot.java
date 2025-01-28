package org.firstinspires.ftc.teamcode.Teleop;

import static org.firstinspires.ftc.teamcode.constants.autoGetPoints.*;

import com.arcrobotics.ftclib.trajectory.TrapezoidProfile;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotHardware;

@TeleOp(name = "Reset Robot",group = "Reset")
public class resetRobot extends LinearOpMode {
    private robotHardware robot = robotHardware.getInstance();
    public void runOpMode(){
        robot.init(hardwareMap);
    }
}
