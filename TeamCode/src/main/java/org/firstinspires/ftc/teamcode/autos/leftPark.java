package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.driveCMD;
import org.firstinspires.ftc.teamcode.commands.parkCMD;
import org.firstinspires.ftc.teamcode.commands.stowCMD;
import org.firstinspires.ftc.teamcode.globals;
import org.firstinspires.ftc.teamcode.poseStorage;
import org.firstinspires.ftc.teamcode.robotHardware;
import org.firstinspires.ftc.teamcode.subsystems.driveBase;
import org.firstinspires.ftc.teamcode.subsystems.elevator;
import static org.firstinspires.ftc.teamcode.constants.autoGetPoints.*;


@Autonomous(name = "left park",group = "Linear OpMode",preselectTeleOp = "mainOpMode")
public class leftPark extends LinearOpMode {
    private elevator arm = new elevator();
    private driveBase drive = new driveBase();
    private robotHardware robot = robotHardware.getInstance();


    public void runOpMode(){
        CommandScheduler.getInstance().reset();
        robot.init(hardwareMap);
        drive.setPos(leftStartPos);



        CommandScheduler.getInstance().setDefaultCommand(arm,new stowCMD(arm));






        while(!opModeIsActive() && globals.hardwareInit){
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
        poseStorage.currentPose = drive.getPos();
    }




}
