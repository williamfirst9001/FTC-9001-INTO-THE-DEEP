package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.robotHardware;

public class driveBase extends SubsystemBase {
    private robotHardware robot = robotHardware.getInstance();
    private Pose2d drivePoint;
    public driveBase(){

    }
    public void goToPos(Pose2d pos){
        drivePoint = pos;

    }
    public void setDriveMotorPower(Pose2d power){

        robot.drive.setWeightedDrivePower(power);

    }
    public void turn(double ang){
        robot.drive.turn(Math.toRadians(ang));
    }
    public Pose2d getVelo(){
        return robot.drive.getPoseVelocity();
    }
    public Pose2d getPos(){
        return robot.drive.getPoseEstimate();
    }
    public void update(){
        robot.drive.update();
        Trajectory traj = robot.drive.trajectoryBuilder(robot.drive.getPoseEstimate())
                .lineToLinearHeading(drivePoint)
                .build();
        robot.drive.followTrajectoryAsync(traj);
    }

    public void setPos(Pose2d pos){
        robot.drive.setPoseEstimate(pos);

    }
    public boolean isStopped(){
        return robot.drive.getPoseVelocity().getX()<.3 && robot.drive.getPoseVelocity().getY()<.3 && robot.drive.getPoseVelocity().getHeading()<.3;
    }

}
