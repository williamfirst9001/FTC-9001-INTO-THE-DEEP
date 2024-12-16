package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.constants.autoGetPoints.*;

import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.globals;
import org.firstinspires.ftc.teamcode.robotHardware;

public class Intake extends SubsystemBase {
    private robotHardware robot = robotHardware.getInstance();
    public void intakeStart(){
        robot.intakeRight.setPosition(1);
        robot.intakeLeft.setPosition(-1);

    }
    public void intakeStop(){
        robot.intakeRight.setPosition(.5);
        robot.intakeLeft.setPosition(.5);
    }
    public void dispense(){
        robot.intakeRight.setPosition(-1);
        robot.intakeLeft.setPosition(1);
        globals.hasSample = false;
    }
    public void update(){
        if(globals.team == globals.Team.RED){
            if(((robot.intakeSensor.red()>150&&robot.intakeSensor.blue()<50&&robot.intakeSensor.green()<50)||
                    (robot.intakeSensor.red()>150&&robot.intakeSensor.blue()<50&&robot.intakeSensor.green()>150))&&robot.intakeSensor.getDistance(DistanceUnit.CM)<2.5){
                intakeStop();
                globals.hasSample = true;
            }
        } else{
            if(((robot.intakeSensor.red()<50&&robot.intakeSensor.blue()>150&&robot.intakeSensor.green()<50)||
                    (robot.intakeSensor.red()>150&&robot.intakeSensor.blue()<50&&robot.intakeSensor.green()>150))&&robot.intakeSensor.getDistance(DistanceUnit.CM)<2.5){
                intakeStop();
                globals.hasSample = true;
            }
        }
    }

}
