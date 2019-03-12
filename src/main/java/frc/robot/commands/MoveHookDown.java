/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveHookDown extends Command {
  private final int TIMEOUT = 2;
  private boolean isFinished;
  private Timer timer;

  public MoveHookDown() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    timer = new Timer();
    isFinished = false;
    requires(Robot.hook);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (!Robot.hook.limitSwitchIsPressed()) {
      Robot.hook.moveDown();
    }
    else isFinished = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(timerHasReachedTimeout()) {
      return true;
    }
    else return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.hook.stopHook();
    timer.stop();
    timer.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  private boolean timerHasReachedTimeout() {
    return timer.get() > TIMEOUT ? true:false;
  }
}
