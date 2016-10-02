package com.jmedinilla.rpsdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MenuMode_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_menu);

        //Animation that moves the buttons from the left to the center
        Animation classic_anim = AnimationUtils.loadAnimation(MenuMode_Activity.this, R.anim.menu_classic_animation);
        Animation special_anim = AnimationUtils.loadAnimation(MenuMode_Activity.this, R.anim.menu_special_animation);

        LinearLayout layoutClassic = (LinearLayout) findViewById(R.id.menu_layout_classic);
        LinearLayout layoutSpecial = (LinearLayout) findViewById(R.id.menu_layout_special);
        if (layoutClassic != null) {
            layoutClassic.setAnimation(classic_anim);
        }
        if (layoutSpecial != null) {
            layoutSpecial.setAnimation(special_anim);
        }
    }

    public void getOnClickMenuButton (View view) {
        switch (view.getId()) {
            case R.id.btnMenuClassic:
                startActivity(new Intent(MenuMode_Activity.this, ClassicGame_Activity.class));
                break;

            case R.id.btnMenuSpecial:
                startActivity(new Intent(MenuMode_Activity.this, SpecialGame_Activity.class));
                break;
        }
    }
}
