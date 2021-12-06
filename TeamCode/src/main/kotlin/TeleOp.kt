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
        telemetry.addData("2Status", "Initialized")
        telemetry.update()


        val leftRear = hardwareMap.get(DcMotor::class.java, "rearleft")
        val rightRear = hardwareMap.get(DcMotor::class.java, "rearright")
        val leftFront = hardwareMap.get(DcMotor::class.java, "frontleft")
        val rightFront = hardwareMap.get(DcMotor::class.java, "frontright")
//        val servo = hardwareMap.get(Servo::class.java, "servo")

        leftRear.direction = DcMotorSimple.Direction.FORWARD
        rightRear.direction = DcMotorSimple.Direction.REVERSE
        leftFront.direction = DcMotorSimple.Direction.FORWARD
        rightFront.direction = DcMotorSimple.Direction.REVERSE


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
//            val turn = gamepad1.right_stick_x.toDouble() //1
//            leftPower = Range.clip(drive + turn, -1.0, 1.0) //-0
//            rightPower = Range.clip(drive - turn, -1.0, 1.0)
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
            telemetry.update()

//            //Servo
//            if (gamepad1.y){
//                servo.position=0.0
//            }
//            else if(gamepad1.x){
//                servo.position=0.5
//            }
//            else if(gamepad1.a){
//                servo.position=1.0
//            }
        }
    }
}