package com.dv.superracyfutbol3000;

import jig.*;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

import static com.dv.superracyfutbol3000.CollidesHelper.*;
import static com.dv.superracyfutbol3000.SuperRacyFutbol3000.*;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static org.newdawn.slick.util.FastTrig.cos;
import static org.newdawn.slick.util.FastTrig.sin;

public class Cars extends Entity{
    private float wall_bounce_factor = 0.003f;
    private Vector next_move_location = new Vector(0,0);

    enum CarExtentNames {minXY, maxXY, maxMinXY, minMaxXY};
    Quadrant facing_direction = new Quadrant();
    Quadrant moving_direction = new Quadrant();


    Players controlling_player;
    private float health_level = 0f;
    private float boost_level = 0f;

    boolean reverse = false;
    private float mass = 1f;
    private boolean turned = false;
    private double prev_turn_rads;
    private double turn_rads = (PI/180)*this.getRotation();

    private double turn_increment = PI/175; // angular acceleration
    private double deg_turn_increment = (180*turn_increment)/PI;
    private float top_speed = 3.5f;
    private float acceleration = 1.075f;
    private float top_boost_speed = 0f;
    private float max_boost_vel = 0f;

    private float min_vel = 0.25f;
    private float speed = 0.0f;
    private float vel_0 = 0.7f;
    private float friction = 0.90f; // simulates friction, gravity, and mass
    private int player_number = -1; //  controlling player number
    private boolean isRed = false;

    Vector next_move_direction = new Vector(0f,0f);

    float dx, dy, dy_180;

    enum TurnDirection {Left, Right}

    public double getTurn_rads() {
        return turn_rads;
    }
    public float getSpeed(){
        return speed;
    }

    public float getAcceleration(){
        return acceleration;
    }

    public double getTurnIncrement(){
        return turn_increment;
    }

    public void setTurn_rads(double turn_rads) {
        this.turn_rads = turn_rads;
    }

    public double getTurn_increment() {
        return turn_increment;
    }

    public void setTurn_increment(double turn_increment) {
        this.turn_increment = turn_increment;
    }

    //  Cars Constructor
    public Cars(float x, float y, Players controlling_player) {
        super(x, y);
        this.controlling_player = controlling_player;

        SetCarImage();
        next_move_direction = new Vector(1f,0);

//        this.debugThis = true;
//        Entity.setDebug(true);
    }


    private void SetCarImage() {
        if(controlling_player.isRed)
            this.addImageWithBoundingBox(ResourceManager.getImage(cars_red_rsc));
            //this.addImage(ResourceManager.getImage(cars_red_rsc));
        else
            this.addImageWithBoundingBox(ResourceManager.getImage(cars_blue_rsc));
        //    this.addImage(ResourceManager.getImage(cars_blue_rsc));
        this.scale(0.67f);  // the image is a little big for the field so scale this down to gain space
      //  SetBoundingBox();
    }
//
//    private void SetBoundingBox() {
//        Image car_img = ResourceManager.getImage(cars_blue_rsc);
//        float w = car_img.getWidth();
//        float h = car_img.getHeight();
//
//        this.addShape(new ConvexPolygon(w, h),new Vector(0,0));
//        if(SuperRacyFutbol3000.isDebugCarCreation)System.out.println(this.getNumShapes());
//    }

    void ProcessInput(Input i){
        switch(controlling_player.control_type){
            case Keyboard:
                if(i.isKeyDown(Input.KEY_UP)){
                    Accelerate();
                }else if(i.isKeyDown(Input.KEY_DOWN)){
                    Decelerate();
                }else {
                    //  If not accelerating or decelerating
                    //  losing velocity due to friction
                    if (abs(speed) > min_vel)
                        speed *= friction;
                    else
                        speed = 0f;
                }
                if(abs(speed) > 0){
                    if(i.isKeyDown(Input.KEY_A))
                        Steer(TurnDirection.Left);
                    if(i.isKeyDown(Input.KEY_D))
                        Steer(TurnDirection.Right);
                }
                break;
            case Gamepad:
                int controller_num = controlling_player.GetControllerNumber();
                if(i.isButton1Pressed(controller_num)){
                    Accelerate();
                }else if(i.isButton2Pressed(controller_num)){
                    Decelerate();

                }else {
                    //  If not accelerating or decelerating
                    //  losing velocity due to friction
                    if (abs(speed) > min_vel)
                        speed *= friction;
                    else
                        speed = 0f;
                }
                if(abs(speed)>0f){
                    if(i.isControllerLeft(controller_num))
                        Steer(TurnDirection.Left);
                    if(i.isControllerRight(controller_num))
                        Steer(TurnDirection.Right);
                }
                break;
        }
    }

    private void Steer(TurnDirection dir) {
        prev_turn_rads = turn_rads;
        if(reverse ){

            if(dir == TurnDirection.Left){
                dir = TurnDirection.Right;
            }else{
                dir = TurnDirection.Left;
            }
        }


        switch(dir){
            case Left :
                turn_rads += turn_increment;
                this.rotate(-deg_turn_increment);
//                if (!reverse){
//                    turn_rads += turn_increment;
//                    this.rotate(-deg_turn_increment);
//                }
//                else{
//                    turn_rads -= turn_increment;
//                    this.rotate(deg_turn_increment);
//                }
//                this.rotate(-(180f/ PI)*turn_increment);

                break;
            case Right:
                /*if(abs(turn_rads)>=(2f*PI))
                    turn_rads = 0;
                else*/
                turn_rads -= turn_increment;
                this.rotate(deg_turn_increment);
/*
                if (!reverse){
                    turn_rads -= turn_increment;
                    this.rotate(deg_turn_increment);
                }
                else{

                    turn_rads += turn_increment;
                    this.rotate(-deg_turn_increment);
                }
*/
//                this.rotate((180f/ PI)*turn_increment);
                break;
            default:
                break;
        }
        if(SuperRacyFutbol3000.isTurnDebug){
            System.out.println("turn rads:" + turn_rads);
            System.out.println("turn rad degrees:" + ((180/PI)*turn_rads));
            System.out.println("Entity Degrees: " + this.getRotation());
            System.out.println("Entity Adj Deg: "+ (90 - (this.getRotation())));
        }
        turned = true;
        turn_rads %=2*PI;
    }

    //  GenerateNextMove should be a function
    public void GenerateNextMove(Input input){
        //  input causes turn angle and speed to change
        //  steering will cause a rotation of the entity but not movement
        ProcessInput(input);

        //  set the x and y components of the move direction based upon the rotated angle*speed
        //  direction*scaler
        next_move_direction = new Vector((float) cos(turn_rads),
                                        (float)(sin(turn_rads+PI)));
        next_move_location = new Vector(this.getX()+speed*next_move_direction.getX(),
                                        this.getY()+speed*next_move_direction.getY());
    }

    //  implments the translation on the next move
    public void UpdateCar(Ellipse goal_ellipse_bounds, Rectangle center_rectangle_bounds){
        //  center next move
        Vector next_move = new Vector(0f,0f);

        //  positive =
        if( isDebugMovingDirection &&
                controlling_player.control_type != Players.Controller.AI)
            System.out.println("current angle from zero degrees: "+((180f/Math.PI)*turn_angle));
        //  a vector for each point of the car rectangle next move
        Vector next_front_left = new Vector(0,0); Vector next_front_right = new Vector(0,0);
        Vector next_back_left = new Vector(0,0); Vector next_back_right = new Vector(0,0);

    //  calculate newX adding to old x the cos of the angle that has ben turned through
//      the x component of the velocity vector given the turn_angle at the magnitude of speed
        //  the positive and negative will give weight to the turning direction however when choosing degrees from facing zero
        //  abs should be used in calculations. Since -360 degrees != 360 degrees == 180 degrees
        // Speed and Direction -> velocity
        float dx = (float)(speed*cos(turn_angle));  // change in x direction
        float dy = (float)(speed*sin(turn_angle));  // change in y direction
        float dy_180 = (float)(speed*sin(turn_angle+PI));  // change in y direction

        float newX = this.getX()+dx;
    //  calculate new Y component
        float newY = this.getY() + dy_180;  // dy 180 inverts the y coordinate system

        next_move = next_move.setX(newX);
        next_move = next_move.setY(newY);

        facing_direction = facing_direction.Get_Direction_Vector((float) ((180/PI)*turn_angle));
        facing_direction.DetermineLabel();
        moving_direction = moving_direction.GetMovingDirection(facing_direction, dx, -1f*dy);
        moving_direction.DetermineLabel();
        if(SuperRacyFutbol3000.isVelocityDebug &&
                controlling_player.GetControl_type() != Players.Controller.AI){
            System.out.println("Velocity X: "+dx);
            System.out.println("Velocity Y: "+-1f*dy);
        }


        //Check if collisions then if that's all good move the car.
        //  collisions must be checked at each of the four points of the car
        //  based on which point detects which side of the car collided and
        //  how to steer the car in towards the circle

        //  create a collision type for catch when a collision occurs
        CollisionType collision_type = CollisionType.None;

        float minX, maxX, minY, maxY;
        Shape car_box = this.getGloballyTransformedShapes().getFirst();
        minX = car_box.getMinX();
        maxX = car_box.getMaxX();
        minY = car_box.getMinY();
        maxY = car_box.getMaxY();


        //  check all extents
        CarExtentNames collide_point_name;
        Vector[] collisions_check = new Vector[4];
        collisions_check[0]  = new Vector(minX, minY);
        collisions_check[1] = new Vector (maxX, maxY);
        collisions_check[2] = new Vector(maxX, minY);
        collisions_check[3] = new Vector(minX,maxY);


        // if minX clear skip
        int i;
        for (i = 0; i < collisions_check.length;i++){
            //  We already know what side of the field we are on. proper ellipse is passed in.
            collision_type = CheckCollisions(collisions_check[i],goal_ellipse_bounds, center_rectangle_bounds);
            if(isWallDebug)System.out.println("collision result" + collision_type);

            if (collision_type != CollisionType.None){
                if(isWallDebug)System.out.println("index i");
                collide_point_name = GetCollidePointName(i);
                break;
            }
        }

        if(collision_type == CollisionType.None){
            /*if(SuperRacyFutbol3000.isVelocityDebug &&
                    controlling_player.GetControl_type() != Players.Controller.AI){
                System.out.println("velocity_X: "+(newX-this.getX()));
                System.out.println("velocity_Y: "+(newY-this.getY()));
            }*/
            this.setX(newX);
            this.setY(newY);
        }else if(collision_type == CollisionType.Wall){
            // process hit will determine what the newX and newY
            // direction and magnitude should be after the collision
            ProcessHit(collision_type, next_move, center_rectangle_bounds);
        }
    }

    private CarExtentNames GetCollidePointName(int index) {
        switch(index){
            case 0:
                return CarExtentNames.minXY;

            case 1:
                return CarExtentNames.maxXY;

            case 2:
                return CarExtentNames.maxMinXY;

            case 3:
                return CarExtentNames.minMaxXY;

            default:
                return null;
        }
    }

    private void Accelerate() {
//        if (reverse){
//            turn_angle += Math.PI;
//            reverse = false;
//        }
        if(speed <= top_speed) {
            //  if car is moving
            if (speed > 0) {
                speed *= acceleration;
            }
            //  car moving in reverse
            //  change direction
            else if (speed < -min_vel) {
                speed *= (1 - (acceleration - 1) * 6);
            } else {
                //  car is stopped
                //  this is first acceleration
                //  start slow normal accel == 1.25
                speed = vel_0;
            }
        }else{
            speed = top_speed;
        }
    }

    private void Decelerate() {
//        if (!reverse){
//            turn_angle = turn_angle-Math.PI;
//            reverse = true;
//        }
        if(speed >= -0.5f*top_speed){
            if (speed < 0) {
                speed *= acceleration;
            } else if (speed > min_vel) {
                speed *= 1 - (acceleration - 1)*6;
                System.out.println("decel: "+(1-(acceleration-1)*2));
            } else {
                speed = -1f*vel_0;
            }
        }else
            //  half top_speed in reverse
            speed = -0.5f*top_speed;
    }

    public double getTurn_increment() {
        return turn_increment;
    }

    public void setTurn_increment(double turn_increment) {
        this.turn_increment = turn_increment;
    }

    public void ProcessHit(CollisionType collision_type, Vector next_move, Rectangle rect){
        //  takes a parameter of collison type and affects the car based on what it ran into.
        //  reduces the health of the cars hit based on the velocity when the hit occured.
        //  shoudl both cars take damage?
        //  that's easy
        //  just only if they are at max speed..they get (1-0.05)*health off
        //  boost hit are worth 0.15 of health. (1-0.15)*health
        //  Get newX and newY
        float newX = next_move.getX();
        if(isDebugWallBounce)System.out.println("ProcessHit new_X: "+newX);
        float newY = next_move.getY();
        if(isDebugWallBounce)System.out.println("ProcessHit new_Y: "+newY);

        float dx = newX - this.getX();
        float dy = newY - this.getY();

        System.out.println(moving_direction.getQuadrant_label());
        switch(collision_type){
            case Wall:
                if(isDebugWallBounce)System.out.println("see Quadrant 3 bouncing away straight on hit");
/*                moving_direction.InvertMovement();
//                if (dx!=0f && dy !=0f){
//                    dx *= moving_direction.getDx();
//                    dy *= moving_direction.getDy();
//                }else{
//                    dx = moving_direction.getDx();
//                    dy = moving_direction.getDy();
//                }

                //  dx dy will determine the bounce through an inverision of both x and y
                //  this is because dx dy ar very close
//                newY += dy*newY*(wall_bounce_factor);
//                newX += dx*newX*(wall_bounce_factor);


                //if(isDebugWallBounce)System.out.println("WALL HERE!!!!");
                //  reverses both x and y the same
                //  this should be done in components based on direction of movement
                //  not just a reversal but a reflection and continuation of motion
                //this.speed *= 0.8f;

                //  ratio by which the car is moved away from the wall
                //float wall_bounce_factor = 0.03125f;//.0.03125f;

                //*/
//               Find the angle of an object and center of ellipse
                double y_diff = this.getY() - 360;
                double y_diff_by_a = y_diff/362;
                double theta = Math.asin(y_diff_by_a);
                speed*=-1f;

                //  hit occured on right side of field
                if(newX > rect.getMaxX()){
                    //  if you moving towards q1 when collision happens
                    if(moving_direction.getQuadrant_label() == Quadrant.QuadrantLabel.first_q){
                        if(dx > 0 && dy < 0)
                            Steer(TurnDirection.Left);
                       // newY = (float)((this.getY())-(speed)*0.8f*sin(theta));
                    }
//                  Find the angle of an object and center of ellipse

//                    moving_direction.InvertMovement();
                    //speed *=-1f;
                    newX = this.getX()-5-(float)(speed*0.8f*cos(theta));//opposite direction to rigth side of  field
//                    if(moving_direction.getQuadrant_label() == Quadrant.QuadrantLabel.first_q)
//                        newY = (float)(this.getY()+(speed*0.8f*sin(theta)));


//                  ===================================================

                    this.setY(newY);
                    this.setX(newX);
                }

                //  hit occured on LEFT side of field
                else if(newX < rect.getMinX() && newX > 0f){

                    setTurn_increment(PI/8);

                    //  move towards q1 when Wall Collisions
                    //  collide with side of car while looking at q1
                    if(moving_direction.getQuadrant_label() == Quadrant.QuadrantLabel.first_q){
                        if(facing_direction.getQuadrant_label()!=Quadrant.QuadrantLabel.first_q){
                            // reversing into a wall
                            System.out.println("reverse into q2");
                        }
                        //  see q1 and dx and dy are pos; turn right
                        if(dx>0 && dy>0){
                            System.out.println("see Quadrant 1 steer right");
                            Steer(TurnDirection.Left);
                        }
                        //  see q1 and dx and dy are neg; turn left
                        else if(dx<0&&dy<0){
                            System.out.println("see Quadrant 1 steer left");
                            Steer(TurnDirection.Right);
                        }
                    }

                    //  moving towards Quadrant 2 and a Wall collisons occurs
                    if(moving_direction.getQuadrant_label() == Quadrant.QuadrantLabel.second_q ){
                        if(facing_direction.getQuadrant_label()!=Quadrant.QuadrantLabel.second_q){
                            // reversing into q2
                            System.out.println("reverse into q2");
                        }
                        //  see q2 and dy and dx decreasing
                        if(dy<0 && dx<0){
                            //  the changin in y is more than x meaning travel from q3; turn right
                            if(this.getX() < SuperRacyFutbol3000.WIDTH/2){
                                System.out.println("see Quadrant 2 dx and dy pos; steer right");
                                Steer(TurnDirection.Right);
                            }
                        }

                    }

                    //  Quadrant 3 Wall Collisions
                    if(moving_direction.getQuadrant_label() == Quadrant.QuadrantLabel.third_q){
                        System.out.println("see Quadrant 3 bouncing away straight on hit");
                        if(facing_direction.getQuadrant_label()!=Quadrant.QuadrantLabel.third_q){
                            // reversing into q3
                            System.out.println("reverse into q3");
                        }
                        if(dx<0 && dy>0){
                            //turn left
                            Steer(TurnDirection.Left);
                        }else if( dx <0 && dy <0){
                            //left
                            Steer(TurnDirection.Right);
                        }else if((dx-dy)<0.3f){
//                            if(isDebugWallBounce)System.out.println("Left Field!");
                            moving_direction.InvertMovement();
                        }

                    }

                    newX = this.getX()+5f+(float)(speed*0.90f*cos(theta));
                    newY = this.getY()-(float)(speed*0.90f*sin(theta));
////                  ===================================================
//
                    this.setY(newY);
                    this.setX(newX);

                }
                //  inside rectange collisions
                else if ((rect.getMinX()-50) <= newX && newX<= (50+rect.getMaxX())){
                    if(this.getY()< SuperRacyFutbol3000.HEIGHT/2){
                        if(moving_direction.getQuadrant_label() == Quadrant.QuadrantLabel.second_q){
                            Steer(TurnDirection.Left);
                            newX = this.getX()-5f-(float)(speed*0.70f*cos(theta));
                        }
                        else if (moving_direction.getQuadrant_label() == Quadrant.QuadrantLabel.first_q){
                            Steer(TurnDirection.Right);
                            newX = this.getX()+5f+(float)(speed*0.70f*cos(theta));
                        }
                        newY = this.getY()+5f+(float)(speed*0.90f*sin(theta));
////                  ===================================================
                        this.setY(newY);
                        this.setX(newX);
                    }
                    else if(this.getY()> SuperRacyFutbol3000.HEIGHT/2){
                        if(moving_direction.getQuadrant_label() == Quadrant.QuadrantLabel.fourth_q){
                            Steer(TurnDirection.Left);
                            newX = this.getX()+5f+(float)(speed*0.70f*cos(theta));
                        }
                        else if (moving_direction.getQuadrant_label()== Quadrant.QuadrantLabel.third_q){
                            Steer(TurnDirection.Right);
                            newX = this.getX()-5f-(float)(speed*0.70f*cos(theta));
                        }
                        newY = this.getY()-5f-(float)(speed*0.90f*sin(theta));
////                  ===================================================
                        this.setY(newY);
                        this.setX(newX);
                    }
                }
                setTurn_increment(PI/175);
                break;
            default:
                System.out.println("OtherHitType");
                break;
       }

    }
}
