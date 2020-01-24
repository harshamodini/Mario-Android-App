package com.example.brandon.mario.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import com.example.brandon.mario.Enemy.Bloober;
import com.example.brandon.mario.Enemy.BuzzyBeetle;
import com.example.brandon.mario.Enemy.Enemy;
import com.example.brandon.mario.Enemy.PiranhaPlant;
import com.example.brandon.mario.Items.Coins;
import com.example.brandon.mario.Items.FireFlower;
import com.example.brandon.mario.Items.SuperMushroom;
import com.example.brandon.mario.Map.Mario;
import com.example.brandon.mario.R;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap icons[];
    int[][] levelOne1 = new int[11][64];
    private Mario mario = new Mario();
    int val;
    int x = 0;
    int marioState = mario.getState();
    boolean jump = false;
    int gravity = 1;
    int jumping = 0;
    int points = 0;
    int level = 1;
    int fireCount = 0;
    int fireX = 12;
    int fireY;
    PiranhaPlant plant;// = new PiranhaPlant(18,10);
    Bloober bloober = new Bloober(10,10);
    Coins coin = new Coins(22,5);
    SuperMushroom mushroom = new SuperMushroom(22,10);
    FireFlower flower = new FireFlower(21,10);
    BuzzyBeetle beetle = new BuzzyBeetle(30,10);


    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        System.out.println("In Constructor");

        icons = new Bitmap[17];

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("Touch event occured");
        int prevX;
        int prevY;
        int startRowNum;
        int startColNum;

        int width = getWidth();
        int height = getHeight();

        int rowHeight = height / 2;
        int columnWidth = width / 3;

        int temp = 0;
        int marioy = 0;

        if(x == 40){
            level = level + 1;
            x = 0;
        }

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            prevX = (int) event.getX();
            prevY = (int) event.getY();

            startRowNum = prevY / rowHeight;
            startColNum = prevX / columnWidth;

            //UP
            if((startColNum == 1) && (startRowNum == 0)){
                System.out.println("User Selected UP");
                if(jumping == 0) jumping = 3;
                //if(jumping > 0) jumping = 0;
                //else jumping = 3;
            }
            //LEFT
            else if((startColNum == 0) && (startRowNum == 0 || startRowNum == 1)){
                System.out.println("User Selected LEFT " + x);
                if (mario.getState() == 9 || mario.getState() == 10) mario.changeState(9);
                if (mario.getState() == 14 || mario.getState() == 15) mario.changeState(14);
                if (mario.getState() == 5 || mario.getState() == 6) mario.changeState(5);
                if(x != 0 && levelOne1[mario.getY() - 1][x+10] == 16 && levelOne1[mario.getY() - 2][x+10] == 16){
                    x--;
                }
            }
            //RIGHT
            else if((startColNum == 2) && (startRowNum == 0 || startRowNum == 1)){
                System.out.println("User Selected RIGHT " + x);
                if (mario.getState() == 9 || mario.getState() == 10) mario.changeState(10);
                if (mario.getState() == 14 || mario.getState() == 15) mario.changeState(15);
                if (mario.getState() == 5 || mario.getState() == 6) mario.changeState(6);
                if(x < 41 && levelOne1[mario.getY() - 1][x + 12] == 16 && levelOne1[mario.getY() - 2][x + 12] == 16){
                    x++;
                }
            }
            //FIRE
            else if((startColNum == 1) && (startRowNum == 1)){
                System.out.println("User Selected FIRE!");
                fireCount = 6;
                fireY = x + 12;
                fireX = mario.getY();

            }
            else {
                System.out.println("INVALID MOVE!");
            }
        }
        else if(event.getAction() == MotionEvent.ACTION_UP) {
            System.out.println("MOVE STOPPED");
        }
        return true;

    }



    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(60);
        p.setTextAlign(Paint.Align.CENTER);

        //System.out.println("Draw called");
        canvas.drawColor(Color.WHITE);
        Rect rect = new Rect();

        int width = getWidth();
        int height = getHeight();

        //board is current 11 tall and 23 wide
        int rowHeight = height / 11;
        int columnWidth = width / 23;//46

        for(int i = 0; i < 11; ++i) {
            for(int j = x; j < (x + 23/*46*/); ++j /*j+=2*/) {
                rect.set((j - x)* columnWidth, i * rowHeight, (j + 1 - x) * columnWidth, (i + 1) * rowHeight);
                val = levelOne1[i][j /*/2*/];
                canvas.drawBitmap(icons[val], null, rect, null);
            }
            //System.out.println();
        }

        //mario is at x = 12 y = 10
        //jumping
        if(jumping == 0 && (mario.getY() + gravity < 12) && levelOne1[mario.getY() + gravity - 1][x+11] != 16/*(levelOne1[mario.getY() + gravity - 1][x+11] == 1 || levelOne1[mario.getY() + gravity - 1][x+11] == 3)*/) {
            rect.set(11 * columnWidth, (mario.getY() - 2) * rowHeight, 12 * columnWidth, (mario.getY()) * rowHeight);
            canvas.drawBitmap(icons[mario.getState()], null, rect, null);
            //levelOne1[mario.getY() + gravity -4][x+11] = 16;
            System.out.println(mario.getY() + gravity - 3);
        }
        else{//16, falling
            if(mario.getY() + gravity - jumping - 2 >= 0 && levelOne1[mario.getY() + gravity - jumping - 2][x+11] == 16 && mario.getY() + gravity - jumping > 0)
                mario.changeY(-gravity + jumping);

            if((mario.getY() == bloober.getY()&& x+12== bloober.getX())){
                bloober.changeX(1000);
                jumping = 4;
            }
            if((mario.getY() == beetle.getY()&& x+12== beetle.getX())){
                beetle.changeX(1000);
                jumping = 4;
            }
            if (jumping > 0) jumping--;
            rect.set(11 * columnWidth, (mario.getY() - 2) * rowHeight, 12 * columnWidth, mario.getY() * rowHeight);
            canvas.drawBitmap(icons[mario.getState()], null, rect, null);
        }
        //System.out.println(bloober.getY() + " " + mario.getY());
        plant.attack(x+12, mario);
        //System.out.println(mario.getLives());
        rect.set((plant.getX() - 1 - x) * columnWidth, (plant.getY() - 1) * rowHeight, (plant.getX() - x)* columnWidth, (plant.getY()) * rowHeight);
        canvas.drawBitmap(icons[13], null, rect, null);

        points += coin.addCoin(x+12, mario);
        rect.set((coin.getX() - 1 - x) * columnWidth, (coin.getY() - 1) * rowHeight, (coin.getX() - x)* columnWidth, (coin.getY()) * rowHeight);
        canvas.drawBitmap(icons[4], null, rect, null);


        bloober.chaseMario(x+12, mario);
        rect.set((bloober.getX() - 1 - x) * columnWidth, (bloober.getY() - 1) * rowHeight, (bloober.getX() - x)* columnWidth, (bloober.getY()) * rowHeight);
        canvas.drawBitmap(icons[2], null, rect, null);

        points += mushroom.powerUp(x+12, mario);
        rect.set((mushroom.getX() - 1 - x) * columnWidth, (mushroom.getY() - 1) * rowHeight, (mushroom.getX() - x)* columnWidth, (mushroom.getY()) * rowHeight);
        canvas.drawBitmap(icons[11], null, rect, null);

        points += flower.powerUp(x+12, mario);
        rect.set((flower.getX() - 1 - x) * columnWidth, (flower.getY() - 1) * rowHeight, (flower.getX() - x)* columnWidth, (flower.getY()) * rowHeight);
        canvas.drawBitmap(icons[8], null, rect, null);

        beetle.move(levelOne1);
        beetle.attack(x+12, mario);
        rect.set((beetle.getX() - 1 - x) * columnWidth, (beetle.getY() - 1) * rowHeight, (beetle.getX() - x)* columnWidth, (beetle.getY()) * rowHeight);
        canvas.drawBitmap(icons[0], null, rect, null);

        /*if (mario.getState() == 6 && fireCount > 0){//facing right and fire
            if(levelOne1[mario.getY() - 1][x + 12+6-fireCount] != 16) fireCount = 0;
            if (fireCount !=0) {
                rect.set((1 + 12 + 6 - fireCount - 1) * columnWidth, (fireY - 1) * rowHeight, (1 + 12 + 6 - fireCount) * columnWidth, fireY * rowHeight);
                canvas.drawBitmap(icons[7], null, rect, null);
            }
            fireCount--;
        }
        else if (mario.getState() == 5 && fireCount > 0){//facing left and fire
            if(levelOne1[mario.getY() - 1][x + 12-6+fireCount] != 16) fireCount = 0;
            if (fireCount !=0) {
                rect.set((1 + 12 - 6 + fireCount - 1-2) * columnWidth, (fireY - 1) * rowHeight, (1 + 12 - 6 + fireCount-2) * columnWidth, fireY * rowHeight);
                canvas.drawBitmap(icons[7], null, rect, null);
            }
            fireCount--;
        }
        fireX = 1 + 12 + 6 - fireCount;
        if (fireX == beetle.getX() )beetle.changeX(1000);
        if (fireX == bloober.getX() && fireY == bloober.getY())beetle.changeX(1000);*/

        if (mario.getState() == 6 && fireCount > 0){//facing right and fire
            if(levelOne1[mario.getY() - 1][x + 12+6-fireCount] != 16) fireCount = 0;
            if (fireCount !=0) {
                rect.set((1 + 12 + 6 - fireCount - 1) * columnWidth, (fireY - 1) * rowHeight, (1 + 12 + 6 - fireCount) * columnWidth, fireY * rowHeight);
                canvas.drawBitmap(icons[7], null, rect, null);
            }
            fireCount--;
        }
        else if (mario.getState() == 5 && fireCount > 0){//facing left and fire
            if(levelOne1[mario.getY() - 1][x + 12-6+fireCount] != 16) fireCount = 0;
            if (fireCount !=0) {
                rect.set((1 + 12 - 6 + fireCount - 1-2) * columnWidth, (fireY - 1) * rowHeight, (1 + 12 - 6 + fireCount-2) * columnWidth, fireY * rowHeight);
                canvas.drawBitmap(icons[7], null, rect, null);
            }
            fireCount--;
        }
        fireX = 1 + 12 + 6 - fireCount;
        if (fireX == beetle.getX() )beetle.changeX(1000);
        if (fireX == bloober.getX() && fireY == bloober.getY())beetle.changeX(1000);

        //System.out.print(fireX + " " + bloober.getX() + " " + fireY + " " + bloober.getY());

        //super.draw(canvas);

        canvas.drawText("lives: " + mario.getLives(), (int) (getWidth() * .1), (int) (getHeight() * .1), p);
        canvas.drawText("points: " + points, (int) (getWidth() * .3), (int) (getHeight() * .1), p);
        canvas.drawText("level: " + level, (int) (getWidth() * .8), (int) (getHeight() * .1), p);
        if(mario.getLives() == 0 || (level == 3 && x == 40)) {
            String s3;
            s3 = ("GAME OVER!");
            Paint p1 = new Paint();
            p1.setColor(Color.RED);
            p1.setTextSize(120);
            p1.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(s3, (int) (getWidth() * .5), (int) (getHeight() * .5), p1);
            System.exit(0);
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("Surface created");
        //setWillNotDraw(false);

        icons[0] = BitmapFactory.decodeResource(getResources(), R.drawable.beetle);
        icons[1] = BitmapFactory.decodeResource(getResources(), R.drawable.block);
        icons[2] = BitmapFactory.decodeResource(getResources(), R.drawable.bloober);
        icons[3] = BitmapFactory.decodeResource(getResources(), R.drawable.box);
        icons[4] = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        icons[5] = BitmapFactory.decodeResource(getResources(), R.drawable.firemarioleft);
        icons[6] = BitmapFactory.decodeResource(getResources(), R.drawable.firemmarioright);
        icons[7] = BitmapFactory.decodeResource(getResources(), R.drawable.fireball);
        icons[8] = BitmapFactory.decodeResource(getResources(), R.drawable.fireflower);
        icons[9] = BitmapFactory.decodeResource(getResources(), R.drawable.marioleft);
        icons[10] = BitmapFactory.decodeResource(getResources(), R.drawable.marioright);
        icons[11] = BitmapFactory.decodeResource(getResources(), R.drawable.mushroom);
        icons[12] = BitmapFactory.decodeResource(getResources(), R.drawable.pipe);
        icons[13] = BitmapFactory.decodeResource(getResources(), R.drawable.plant);
        icons[14] = BitmapFactory.decodeResource(getResources(), R.drawable.supermarioleft);
        icons[15] = BitmapFactory.decodeResource(getResources(), R.drawable.supermarioright);
        icons[16] = BitmapFactory.decodeResource(getResources(), R.drawable.white);
        //icons[17] = BitmapFactory.decodeResource(getResources(), R.drawable.unbreakable);a

        InputStream is = this.getResources().openRawResource(R.raw.level1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        String[][] levelOne = new String[11][64];
        String in = "";

        int i = 0;
        while (true) {
            try {
                line = reader.readLine();
                if (i == 11) break;
            }
            catch (IOException e){
                e.printStackTrace();
            }
            StringTokenizer q = new StringTokenizer(line);
            for (int j = 0; j < 64; j++){
                levelOne[i][j] = q.nextToken();
                levelOne1[i][j] = Integer.parseInt(levelOne[i][j]);
                if (levelOne1[i][j] == 13){
                    levelOne1[i][j] = 12;
                    plant = new PiranhaPlant(j+1,i);
                }
            }
            i++;
            System.out.println();
            //add val
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        System.out.println("Surface changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        System.out.println("Surface Destoryed");
    }
}