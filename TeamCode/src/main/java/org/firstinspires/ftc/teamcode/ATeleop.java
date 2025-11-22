package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ATeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("fl");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("bl");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("fr");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("br");
        DcMotor shooterMotor = hardwareMap.dcMotor.get("shoot");
        CRServo servoleft = hardwareMap.crservo.get("sl");
        CRServo servoright = hardwareMap.crservo.get("sr");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double frontBack = gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double leftRight = gamepad1.left_stick_x; // Counteract imperfect strafing
            double rightTurn = gamepad1.right_stick_x;
            double leftTurn = gamepad1.rignt_tick_y

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower * 0.75);
            backLeftMotor.setPower(backLeftPower * 0.75);
            frontRightMotor.setPower(frontRightPower * 0.75);
            backRightMotor.setPower(backRightPower * 0.75);

            shooterMotor.setPower(0.75);

            if (gamepad1.dpad_left) {
                shooterMotor.setPower(1);
            }

            if (gamepad1.left_bumper) {
                servoleft.setPower(-1);
                servoright.setPower(1);
            } else {
                servoleft.setPower(0);
                servoright.setPower(0);
            }
            if(frontBack > 0)
            {
                frontLeftMotor.setPower(0.75);
                frontRightMotor.setPower(0.75);
                backLeftMotor.setPower(0.75);
                backRightMotor.setPower(0.75);
            }
            if(frontBack < 0)
            {
                frontLeftMotor.setPower(-0.75);
                frontRightMotor.setPower(-0.75);
                backLeftMotor.setPower(-0.75);
                backRightMotor.setPower(-0.75);
            }
            if(rightTurn > 0)
            {
                frontLeftMotor.setPower(0.75);
                frontRightMotor.setPower(-0.75);
                backLeftMotor.setPower(-0.75);
                backRightMotor.setPower(-0.75);
            }
            if(leftTurn > 0)
            {
                frontLeftMotor.setPower(-0.75);
                frontRightMotor.setPower(0.75);
                backLeftMotor.setPower(0.75);
                backRightMotor.setPower(0.75);
            }
            if(leftRight > 0)
            {
                frontLeftMotor.setPower(0.75);
                frontRightMotor.setPower(-0.75);
                backLeftMotor.setPower(-0.75);
                backRightMotor.setPower(0.75);
            }
            if(leftRight < 0)
            {
                frontLeftMotor.setPower(-0.75);
                frontRightMotor.setPower(0.75);
                backLeftMotor.setPower(0.75);
                backRightMotor.setPower(-0.75);
            }

        }
    }
}
