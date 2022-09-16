package com.siyararslan.catchthekennymy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewTime;
    TextView textViewScore;
    ImageButton button;
    static int count=0;
    Random random=new Random();
    static double time=10;

    Runnable runnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime =findViewById(R.id.textView);
        button=findViewById(R.id.imageButton);
        textViewScore=findViewById(R.id.textView2);


        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                int changingX=random.nextInt(2);//sol üst kose  0-800
                int changingY=random.nextInt(2);//150-1400

                if(changingX==1&&button.getX()<=500)
                    button.setX(button.getX()+200);
                else if(changingX==0 &&button.getX()>=200 )
                    button.setX(button.getX()-200);


                if(changingY==1&&button.getY()<=1100)
                    button.setY(button.getY()+200);
                else if(changingY==0&&button.getY()>=350)
                    button.setY(button.getY()-200);

                handler.postDelayed(runnable,250);

                time-=0.25;
                int intTime=(int)time;
                textViewTime.setText("Time: "+intTime);


                if(time==0.0){

                    handler.removeCallbacks(runnable);

                    AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Restart?");
                    alert.setMessage("Are you sure to restart game?");

                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //


                            count=0;
                            time=10;
                            button.setY(700);
                            button.setY(400);
                            textViewScore.setText("Score: "+count);
                            handler.postDelayed(runnable,500);

                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            System.exit(0);
                        }
                    });

                    alert.show();

                }


            }
        };


        handler.post(runnable);






    }

    public void clicked(View view){









        count++;
        textViewScore.setText("Score: "+count);




    }
}