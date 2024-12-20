package org.firstinspires.ftc.teamcode.autos;

import static org.firstinspires.ftc.teamcode.constants.autoGetPoints.leftStartPos;
import static org.firstinspires.ftc.teamcode.constants.autoGetPoints.rightStartPos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.armStart;
import org.firstinspires.ftc.teamcode.commands.driveCMD;
import org.firstinspires.ftc.teamcode.commands.parkCMD;
import org.firstinspires.ftc.teamcode.commands.stowCMD;
import org.firstinspires.ftc.teamcode.globals;
import org.firstinspires.ftc.teamcode.robotHardware;
import org.firstinspires.ftc.teamcode.storage;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;
import org.firstinspires.ftc.teamcode.subsystems.driveBase;
import org.firstinspires.ftc.teamcode.subsystems.elevator;


@Autonomous(name = "right park",group = "Linear OpMode",preselectTeleOp = "mainOpMode")
public class rightPark extends LinearOpMode {
    private elevator arm = new elevator();
    private driveBase drive = new driveBase();
    private robotHardware robot = robotHardware.getInstance();
    private Wrist wrist = new Wrist();


    public void runOpMode(){
        CommandScheduler.getInstance().reset();
        robot.init(hardwareMap);
        drive.setPos(rightStartPos);


        CommandScheduler.getInstance().registerSubsystem(arm);
        CommandScheduler.getInstance().setDefaultCommand(arm,new stowCMD(arm,wrist));






        while(!opModeIsActive() && globals.hardwareInit){
            armStart.start();
            telemetry.addData("status: ","ready");
            telemetry.update();
        }
        waitForStart();
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(

                        new driveCMD(drive,new Pose2d(40,-40,Math.toRadians(90))),
                        new parkCMD(drive)
                ));
        while (opModeIsActive() && !isStopRequested()) {
            CommandScheduler.getInstance().run();
        }
        storage.currentPose = drive.getPos();
    }




}
