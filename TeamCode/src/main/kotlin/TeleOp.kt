package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.ElapsedTime
import com.qualcomm.robotcore.util.Range

@TeleOp(name = "BasicOpMode-xd", group = "Linear Opmode") //@Disabled
class BasicOpMode_Linear : LinearOpMode() {
    // Declare OpMode members.
    private val runtime = ElapsedTime()
    override fun runOpMode() {
        telemetry.addData("Status", "Initialized")
        telemetry.update()

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
        servo0.direction = Servo.Direction.REVERSE
        servo1.direction = Servo.Direction.FORWARD


        waitForStart()
        runtime.reset()

        while (opModeIsActive()) {

            var leftRearPower: Double
            var rightRearPower: Double
            var leftFrontPower: Double
            var rightFrontPower: Double

            val drive = -gamepad1.left_stick_y.toDouble() //-1.0
            val strafe = gamepad1.left_stick_x.toDouble()
            val rotate = gamepad1.right_stick_x.toDouble()

            rightRearPower = Range.clip(drive + strafe - rotate, -1.0, 1.0)
            rightFrontPower = Range.clip(drive - strafe - rotate, -1.0, 1.0)
            leftRearPower = Range.clip(drive - strafe + rotate, -1.0, 1.0)
            leftFrontPower = Range.clip(drive + strafe + rotate, -1.0, 1.0)

            leftRear.power = leftRearPower
            rightRear.power = rightRearPower
            leftFront.power = leftFrontPower
            rightFront.power = rightFrontPower


            telemetry.addData("Status", "Run Time: $runtime")
            telemetry.addData("LeftRearPos: ", leftRear.currentPosition)
            telemetry.addData("RightRearPos: ", rightRear.currentPosition)
            telemetry.addData("LeftFrontPos: ", leftFront.currentPosition)
            telemetry.addData("RightFrontPos: ", rightFront.currentPosition)
            telemetry.addData("servo0Pos: ", servo0.position)
            telemetry.addData("servo1Pos: ", servo1.position)
            telemetry.update()

            //Servo
            servo0.position = servo0.position - Range.clip( gamepad1.right_stick_y.toDouble()/100, -0.1, 0.1) //to test
            servo1.position = servo1.position - Range.clip(gamepad1.right_stick_y.toDouble()/100, -0.1, 0.1)

            val dpadUp = gamepad1.dpad_up
            val dpadDown = gamepad1.dpad_down
            if (dpadUp){
                servo0.position = servo0.position + 0.1
                servo1.position = servo1.position + 0.1
                sleep(30)
            }
            else if(dpadDown){
                servo0.position = servo0.position - 0.1
                servo1.position = servo1.position - 0.1
                sleep(30)
            }
        }
    }
}