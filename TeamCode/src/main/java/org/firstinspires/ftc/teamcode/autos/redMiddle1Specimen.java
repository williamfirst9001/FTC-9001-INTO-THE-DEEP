package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.driveCMD;
import org.firstinspires.ftc.teamcode.commands.highChamberCMD;
import org.firstinspires.ftc.teamcode.commands.parkCMD;
import org.firstinspires.ftc.teamcode.commands.stowCMD;
import org.firstinspires.ftc.teamcode.globals;
import org.firstinspires.ftc.teamcode.robotHardware;
import org.firstinspires.ftc.teamcode.subsystems.driveBase;
import org.firstinspires.ftc.teamcode.subsystems.elevator;


@Autonomous(name = "\uD83D\uDD34 - redMiddle1Specimen",group = "Linear OpMode",preselectTeleOp = "mainOpMode")
public class redMiddle1Specimen extends LinearOpMode {
    private elevator arm = new elevator();
    private driveBase drive = new driveBase();
    private robotHardware robot = robotHardware.getInstance();


    public void runOpMode(){
        CommandScheduler.getInstance().reset();
        drive.setPos(new Pose2d(-10, -62, Math.toRadians(90)));
        globals.team= globals.Team.RED;


        CommandScheduler.getInstance().setDefaultCommand(arm,new stowCMD(arm));

        robot.init(hardwareMap);
        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(
                        new driveCMD(drive,new Pose2d(-10,-35,Math.toRadians(90))),
                        new highChamberCMD(arm),
                        //TODO: deposit on chamber
                        //park
                        new driveCMD(drive,new Pose2d(40,-40,Math.toRadians(90))),
                        new parkCMD(drive)
                ));


        while(!opModeIsActive() && globals.hardwareInit){
            telemetry.addData("status: ","ready");
            telemetry.update();
        }
        while (opModeIsActive() && !isStopRequested()) {
            CommandScheduler.getInstance().run();
        }

    }




}
