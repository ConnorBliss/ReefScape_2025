package frc.robot.Subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Intake extends SubsystemBase {
    private SparkMax intakeMotor;
    private RelativeEncoder intakeEncoder; 
    private SparkClosedLoopController intakePIDController;
    private SparkMaxConfig intakeConfig;

  public Intake() {

    //Make a right and left intake for this subsystem, CanID 19 and 20
    intakeMotor = new SparkMax(19, MotorType.kBrushless);
    intakeEncoder = intakeMotor.getEncoder();
    intakeConfig = new SparkMaxConfig();
    intakeConfig.idleMode(IdleMode.kBrake);
    intakeConfig.closedLoop.pidf(1,0,0,0);

    /*  // look into getting rid of this? Not sure if needed? Sat 1/25
   //intakeMotor.setOpenLoopRampRate(.5);
    intakeEncoder = intakeMotor.getEncoder();
    intakePIDController = intakeMotor.getClosedLoopController();

     //intakePIDController.pid(1, 0, 0);
     intakePIDController.setFF(0); */
   }
  
  /* Create your intake Methods here */

  //When called, this moves the motor at negative intakeSpeed to go up
  public void intakePickupCoral(){
    intakeMotor.set(-Constants.IntakeSpeeds.intakePickupCoral);
  }
  //When called, this moves the moter at positive elevatorSpeed to go down
  public void intakeScoreCoral(){
    intakeMotor.set(Constants.IntakeSpeeds.intakeScoreCoral);
  }
   public void intakeStop(){
     intakeMotor.set(0);
}

  /*Create manually controlled commands here */

   public Command intakePickupCoralCommand(){
      return run(() -> intakePickupCoral());
    }

    public Command intakeScoreCoralCommand(){
      return run(() -> intakeScoreCoral());
    }

    public Command intakeStopCommand(){
      return run(() -> intakeStop());
    }

    /*Create set position commands here */

  @Override
    public void periodic(){
      SmartDashboard.putNumber("intakePosition",intakeEncoder.getPosition());
    }
  }