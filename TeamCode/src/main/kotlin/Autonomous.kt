package org.firstinspires.ftc.teamcode

import autonomousCommands
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.ElapsedTime
import com.qualcomm.robotcore.util.Range
import java.text.FieldPosition

@Autonomous(name = "Autonomous-xd", group = "Autonomous")
class BasicAutonomous_xd : LinearOpMode() {
    private val runtime = ElapsedTime()
    override fun runOpMode() {
        //Add telemetry data
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        //Declare hardware
        val leftRear = hardwareMap.get(DcMotor::class.java, "rearleft")
        val rightRear = hardwareMap.get(DcMotor::class.java, "rearright")
        val leftFront = hardwareMap.get(DcMotor::class.java, "frontleft")
        val rightFront = hardwareMap.get(DcMotor::class.java, "frontright")
        val servo0 = hardwareMap.get(Servo::class.java, "servo0")
        val servo1 = hardwareMap.get(Servo::class.java, "servo1")

        leftRear.direction = DcMotorSimple.Direction.FORWARD
        rightRear.direction = DcMotorSimple.Direction.REVERSE
        leftFront.direction = DcMotorSimple.Direction.FORWARD
        rightFront.direction = DcMotorSimple.Direction.REVERSE
        servo0.direction = Servo.Direction.FORWARD
        servo1.direction = Servo.Direction.REVERSE


        waitForStart()
        runtime.reset()


        while (opModeIsActive()) {
            //Declare Variables
            var leftRearPower: Double
            var rightRearPower: Double
            var leftFrontPower: Double
            var rightFrontPower: Double

            var drive: Double = 0.0
            var strafe: Double = 0.0
            var rotate: Double = 0.0

            //Command Functions
            var x=1.0
            var y=1.0

            if (autonomousCommands.cmToTicks(y * 60.0) > leftRear.currentPosition) drive = 1.0
            else drive = 0.0

            if (autonomousCommands.cmToTicks(x * 60.0) > leftRear.currentPosition) strafe = 1.0
            else strafe = 0.0

            //Set motor power
            rightRearPower = Range.clip(drive + strafe - rotate, -1.0, 1.0)
            rightFrontPower = Range.clip(drive - strafe - rotate, -1.0, 1.0)
            leftRearPower = Range.clip(drive - strafe + rotate, -1.0, 1.0)
            leftFrontPower = Range.clip(drive + strafe + rotate, -1.0, 1.0)

            leftRear.power = leftRearPower
            rightRear.power = rightRearPower
            leftFront.power = leftFrontPower
            rightFront.power = rightFrontPower

            //Add telemetry data
            telemetry.addData("Status", "Run Time: $runtime")
            telemetry.addData("LeftRearPos: ", leftRear.currentPosition)
            telemetry.addData("RightRearPos: ", rightRear.currentPosition)
            telemetry.update()
        }
    }
}