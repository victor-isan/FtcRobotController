import com.qualcomm.robotcore.hardware.DcMotor
import java.lang.Math.PI

object autonomousCommands {

    val wheelDiameter = 10.16
    val ticks = 700
    fun cmToTicks(length: Double): Int = (length * ticks / (wheelDiameter * PI)).toInt()

}