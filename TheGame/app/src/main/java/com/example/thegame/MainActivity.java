package com.example.thegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view)
    {
        setContentView(R.layout.game_now);
    }
    public void againClick(View view)
    {
        setContentView(R.layout.game_now);
    }
    public void overAgain(View view)
    {
        setContentView(R.layout.game_now);
    }

    private static boolean Cflag=false; //未点击状态
    private static String beforeTag;
    private static ImageView beforeImg;
    private static int beforeId;
    private static int count=8;
    private static Integer chance=2;
    public void imgClick(View view)
    {
        //((ImageView)view).setVisibility(View.INVISIBLE);
        if(!Cflag)
        {
            Cflag=true;
            beforeTag = (String)((ImageView)view).getTag();
            beforeId = (int)((ImageView)view).getId();
            beforeImg = (ImageView)view;
        }
        else
        {
            String tag=(String)((ImageView)view).getTag();
            int id = (int)((ImageView)view).getId();
            if(tag.equals(beforeTag)&&id!=beforeId)
            {
                beforeImg.setVisibility(View.INVISIBLE);
                ((ImageView)view).setVisibility(View.INVISIBLE);
                count--;
            }
            else
            {
                String temp =(String)((TextView)findViewById(R.id.textView3)).getText();
                Integer chance= Integer.parseInt(temp);
                chance = chance-1;
                ((TextView)findViewById(R.id.textView3)).setText(chance.toString());
                if(chance==0)
                {
                    count=8;
                    setContentView(R.layout.game_over);
                }
            }
            Cflag=false;
            if(count==0)
            {
                count=8;
                setContentView(R.layout.game_win);
            }
        }
    }
}

