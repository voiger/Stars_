package com.uiolert.stars_.modes.ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import com.uiolert.stars_.utils.Accelerometer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ModelBall {

    float fallingSpeed = 0.3f;
    ArrayList<Ball> balls = new ArrayList<>();
    Accelerometer accelerometer;

    public ModelBall(Context context) {
        accelerometer = new Accelerometer(context);
        //balls.add(new Ball(10,10,3,2,5,0));
    }


    public void update(Canvas canvas) {

        if (balls.size() < 3) {
            for (int i = 0; i < 5; i++) {

                balls.add(new Ball(random(0, canvas.getWidth()), random(100, 500), random(-5, 5), random(-5, 5), random(30, 30), random(4f, 5f)));
            }

        }
        // ArrayList<Ball> touchBall = new ArrayList<>();
        for (Ball ballMain : balls) {
            for (Ball touchBall : balls) {
                if (ballMain.id == touchBall.id) {
                    continue;
                }
                ArrayList<Ball> ballsOther = new ArrayList<>(balls);
                ballsOther.remove(ballMain);
                FreeMove freeMove = getFreeMoveBall(ballMain, balls);
                ballsOther.add(ballMain);

                //drivingDirectionsY(ballMain);
                if (freeMove.left) {
                    float speed = drivingDirectionsX(ballMain);
                    ballMain.speedX += speed;
                    ballMain.x += ballMain.speedX;
                } else {
                    float speed = drivingDirectionsX(ballMain);
                    if (0 < speed) {
                        ballMain.speedX = speed;
                        ballMain.x += ballMain.speedX;
                    } else {
                        ballMain.speedX = 0;
                    }
                }

                if (freeMove.right) {
                    float speed = drivingDirectionsX(ballMain);
                    ballMain.speedX += speed;
                    ballMain.x += ballMain.speedX;
                } else {
                    float speed = drivingDirectionsX(ballMain);
                    if (0 > speed) {
                        ballMain.speedX += speed;
                        ballMain.x += ballMain.speedX;
                    } else {
                        ballMain.speedX = 0;
                    }
                }


                if (freeMove.top) {
                    float speed = drivingDirectionsY(ballMain);
                    ballMain.speedY += speed;
                    ballMain.y += ballMain.speedY;
                } else {
                    float speed = drivingDirectionsY(ballMain);
                    if (0 < speed) {
                        ballMain.speedY = speed;
                        ballMain.y += ballMain.speedY;
                    } else {
                        ballMain.speedY = 0;
                    }
                }

                if (freeMove.bottom) {
                    float speed = drivingDirectionsY(ballMain);
                    ballMain.speedY += speed;
                    ballMain.y += ballMain.speedY;
                } else {
                    float speed = drivingDirectionsY(ballMain);
                    if (0 > speed) {
                        ballMain.speedY += speed;
                        ballMain.y += ballMain.speedY;
                    } else {
                        ballMain.speedY = 0;
                    }
                }
                int count = 1000000000;

                try {
                    if (!(freeMove.left && freeMove.top && freeMove.right && freeMove.bottom)) {
//                        ballMain.x = Math.abs(freeMove.getInterfereFreeMove().get(PossibleMove.left).x - freeMove.getInterfereFreeMove().get(PossibleMove.right).x);
//                        ballMain.y = Math.abs(freeMove.getInterfereFreeMove().get(PossibleMove.top).y - freeMove.getInterfereFreeMove().get(PossibleMove.bottom).y);
                    } else if (!(freeMove.top && freeMove.bottom)) {
                        ballMain.y = Math.abs(freeMove.getInterfereFreeMove().get(PossibleMove.top).y - freeMove.getInterfereFreeMove().get(PossibleMove.bottom).y - count);
                    } else if (!(freeMove.left && freeMove.right)) {
                        ballMain.x = Math.abs(freeMove.getInterfereFreeMove().get(PossibleMove.left).x - freeMove.getInterfereFreeMove().get(PossibleMove.right).x - count);
                    } else if (!freeMove.left) {
                        ballMain.x = ballMain.x + ballMain.radius + count;
                    } else if (!freeMove.right) {
                        ballMain.x = ballMain.x - ballMain.radius - count;
                    } else if (!freeMove.top) {
                        ballMain.y = ballMain.y + ballMain.radius + count;
                    } else if (!freeMove.bottom) {
                        ballMain.y = ballMain.y - ballMain.radius - count;
                    }

                } catch (Exception e) {

                }

            }
        }


        for (Ball ball : balls) {


            //ball.speedY += fallingSpeed;

//            if (round(ball.speedY,1) == 0.0){//если скорость мала даётся пинок
//                ball.speedX = random(-30,30);
//                ball.speedY = random(-30,30);
//            }

//            if (ball.speedX > 0) {
//                ball.speedX -= 0.01f;
//            }else {
//                ball.speedX += 0.01f;
//            }


            if (ball.y + ball.radius >= canvas.getHeight()) {
                ball.y = canvas.getHeight() - ball.radius;
                ball.speedY = -ball.speedY / ball.density;
            }

            if (ball.y + ball.radius <= 0) {
                ball.y = ball.radius;
                ball.speedY = -ball.speedY / ball.density;
            }

            if (ball.x + ball.radius >= canvas.getWidth()) {
                ball.x = canvas.getWidth() - ball.radius;
                ball.speedX = -ball.speedX / ball.density;
            }

            if (ball.x - ball.radius <= 0) {
                ball.x = ball.radius;
                ball.speedX = -ball.speedX / ball.density;
            }


        }


    }

    private boolean checkSpeedBall(Ball ball, ArrayList<Ball> ballTArr) {
        for (Ball ballT : ballTArr) {
            if (ball.x - ball.radius < ballT.x + ballT.radius
                    && ball.x + ball.radius > ballT.x - ballT.radius
                    && (Math.abs(ball.y - ballT.y) < ball.radius * 2)) {
                return true;
            }
        }
        return false;
    }

    private FreeMove getFreeMoveBall(Ball ballMain, ArrayList<Ball> otherBalls) {
        FreeMove freeMove = new FreeMove(ballMain);
        for (Ball ball : otherBalls) {
            if (ballMain.x - ballMain.radius < ball.x + ball.radius &&
                    ballMain.x + ballMain.radius > ball.x - ball.radius &&
                    (Math.abs(ballMain.y - ball.y) < ballMain.radius * 2)) {
                if (ballMain.x > ball.x) {
                    freeMove.left = false;
                    freeMove.getInterfereFreeMove().put(PossibleMove.left, ball);
                    ball.color = Color.RED;
                }
                if (ballMain.x < ball.x) {
                    freeMove.right = false;
                    freeMove.getInterfereFreeMove().put(PossibleMove.right, ball);
                    ball.color = Color.BLUE;
                }
            }
            if (ballMain.y - ballMain.radius < ball.y + ball.radius &&
                    ballMain.y + ballMain.radius > ball.y - ball.radius &&
                    (Math.abs(ballMain.x - ball.x) < ballMain.radius * 2)) {
                if (ballMain.y > ball.y) {
                    freeMove.top = false;
                    freeMove.getInterfereFreeMove().put(PossibleMove.top, ball);
                }
                if (ballMain.y < ball.y) {
                    freeMove.bottom = false;
                    freeMove.getInterfereFreeMove().put(PossibleMove.bottom, ball);
                }
            }

        }
        return freeMove;
    }

    float drivingDirectionsX(Ball ball) {
//        ball.speedX += -(accelerometer.x / ball.density);
//        ball.x += ball.speedX;
        return -((accelerometer.x / ball.density) / 100);
    }

    float drivingDirectionsY(Ball ball) {
//        ball.speedY += (accelerometer.y / ball.density) / 100;
//        ball.y += ball.speedY;
        return (accelerometer.y / ball.density) / 100;

    }

    private float random(float from, float to) {
        return (float) (from + (to - from) * Math.random());
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    private static float round(float value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
