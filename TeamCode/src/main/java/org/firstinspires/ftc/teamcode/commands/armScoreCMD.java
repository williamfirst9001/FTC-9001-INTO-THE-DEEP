package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.util.NanoClock;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.constants;
import org.firstinspires.ftc.teamcode.globals;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Wrist;
import org.firstinspires.ftc.teamcode.subsystems.elevator;

public class armScoreCMD extends CommandBase {
    private NanoClock clock = NanoClock.system();
    elevator m_arm;
    Wrist m_wrist;
    Claw m_claw;
    globals.armVal m_type;
    private boolean wrist_done = false,claw_done = false,place_done = false,stow_done = false;
    private double wrist_startTime = 0, claw_startTime = 0;
    public armScoreCMD(elevator arm, Wrist wrist, Claw claw, globals.armVal type){
        m_arm = arm;
        m_wrist = wrist;
        m_claw = claw;
        m_type = type;
        addRequirements(m_arm,m_wrist,m_claw);
    }
    @Override
    public void initialize(){
        clock = NanoClock.system();
        wrist_done = false;
        claw_done = false;
        place_done = false;
        stow_done = false;
        globals.armUpdated = true;


        m_arm.setPivotGains(constants.pivotConstants.P,constants.pivotConstants.I,constants.pivotConstants.D);
        m_arm.setArmVal(m_type);




        m_arm.setPivotGains(constants.pivotConstants.P,constants.pivotConstants.I,constants.pivotConstants.D);



    }
    @Override
    public void execute(){
        m_arm.update();
        if(m_arm.isDone()&&!place_done){
            m_wrist.move(constants.points.map.get(m_type));
            wrist_startTime = clock.seconds();
            place_done = true;
        }if(place_done && clock.seconds()-wrist_startTime>.75){
            claw_done = true;
            m_claw.open();
            claw_startTime = (clock.seconds());

        } if(claw_done &&!stow_done){
            m_wrist.move(constants.points.stow);
            wrist_startTime = (clock.seconds());
            stow_done = true;
        } if(stow_done && clock.seconds()-wrist_startTime>.75){
            wrist_done = true;
        }


    }
    @Override
    public boolean isFinished(){
        return m_arm.isDone() && wrist_done;
    }
}
