package com.jmedinilla.rpsdroid;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShopGame_Activity extends AppCompatActivity {

    LinearLayout layout_shop_changecolor;
    Button btnChangecolor;
    TextView txtChangecolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_shop);
        //txtMessageBoard.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDarkOrange));

        layout_shop_changecolor = (LinearLayout) findViewById(R.id.layout_shop_changecolor);
        btnChangecolor = (Button) findViewById(R.id.btnChangecolor);
        txtChangecolor = (TextView) findViewById(R.id.txtChangecolor);
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

        layout_shop_changecolor.setVisibility(View.INVISIBLE);
        layout_shop_changecolor.setVisibility(View.VISIBLE);
        btnChangecolor.setEnabled(true);

        Animation changecolor_animation = AnimationUtils.loadAnimation(ShopGame_Activity.this, R.anim.shop_changecolor);
        layout_shop_changecolor.setAnimation(changecolor_animation);
    }

    public void getOnClickShopButton(View view) {
        //btnChangecolor.setEnabled(false);

        Handler handler = new Handler();
        Animation selected_move = AnimationUtils.loadAnimation(ShopGame_Activity.this, R.anim.menu_selected_animation);
        switch (view.getId()) {
            case R.id.btnChangecolor:
                layout_shop_changecolor.setAnimation(selected_move);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Working...
                        Toast.makeText(ShopGame_Activity.this, "Not yet", Toast.LENGTH_SHORT).show();
                    }
                }, 800);
                break;
        }
    }
}
