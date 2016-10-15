package com.jmedinilla.rpsdroid;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuMode_Activity extends AppCompatActivity {

    private LinearLayout layoutClassic;
    private LinearLayout layoutSpecial;
    private Button btnClassic;
    private Button btnSpecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_menu);

        layoutClassic = (LinearLayout) findViewById(R.id.menu_layout_classic);
        layoutSpecial = (LinearLayout) findViewById(R.id.menu_layout_special);
        btnClassic = (Button) findViewById(R.id.btnMenuClassic);
        btnSpecial = (Button) findViewById(R.id.btnMenuSpecial);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*
        If the user pauses the App, executing it
        again will also execute the animations

        They have to go invisible first in order
        to execute the animation, because it will
        only be executed if it's the first time the
        component is drawn in the screen
         */
        layoutClassic.setVisibility(View.INVISIBLE);
        layoutSpecial.setVisibility(View.INVISIBLE);

        layoutClassic.setVisibility(View.VISIBLE);
        layoutSpecial.setVisibility(View.VISIBLE);
        btnClassic.setEnabled(true);
        btnSpecial.setEnabled(true);

        //Animation that moves the buttons from left
        Animation classic_anim = AnimationUtils.loadAnimation(MenuMode_Activity.this, R.anim.menu_classic_animation);
        Animation special_anim = AnimationUtils.loadAnimation(MenuMode_Activity.this, R.anim.menu_special_animation);
        layoutClassic.setAnimation(classic_anim);
        layoutSpecial.setAnimation(special_anim);
    }

    public void getOnClickMenuButton (View view) {
        btnClassic.setEnabled(false);
        btnSpecial.setEnabled(false);

        Handler handler = new Handler();
        Animation selected_move = AnimationUtils.loadAnimation(MenuMode_Activity.this, R.anim.menu_selected_animation);
        switch (view.getId()) {
            /*
            When the player selects an option, the order
            one goes invisible, so he cant touch it while
            the animation is happening
             */

            case R.id.btnMenuClassic:
                layoutSpecial.setVisibility(View.INVISIBLE);
                layoutClassic.setAnimation(selected_move);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MenuMode_Activity.this, ClassicGame_Activity.class));
                    }
                }, 800);
                break;

            case R.id.btnMenuSpecial:
                layoutClassic.setVisibility(View.INVISIBLE);
                layoutSpecial.setAnimation(selected_move);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MenuMode_Activity.this, SpecialGame_Activity.class));
                    }
                }, 800);
                break;
        }
    }
}
