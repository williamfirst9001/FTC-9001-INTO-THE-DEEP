package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.teamcode.constants.autoGetPoints.*;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class intakeCMD extends CommandBase {

    private Intake m_intake;
    private boolean run = false;

    public intakeCMD(Intake intake,boolean start){
        m_intake = intake;
        run =start;
        addRequirements(m_intake);
    }

    @Override
    public void initialize(){
        if(run){
            m_intake.intakeStart();
        } else{
            m_intake.intakeStop();
        }
    }
    @Override
    public boolean isFinished(){
        return true;
    }
}
