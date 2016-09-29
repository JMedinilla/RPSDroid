package com.jmedinilla.rpsdroid;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class Game_Activity extends AppCompatActivity {

    private ImageView cpuPlay;
    private ImageView ownPlay;
    private Button btnRock;
    private Button btnPaper;
    private Button btnScissors;
    private TextView txtMessageBoard;

    private static final int ROCK_CHOICE = 1;
    private static final int PAPER_CHOICE = 2;
    private static final int SCISSORS_CHOICE = 3;

    private int ownChoice;
    private int cpuPoints;
    private int ownPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initialize();
        animatePlay();
        animateButtons();
    }

    public void getOnClick(View view) {

        cpuPlay.setVisibility(View.INVISIBLE);
        ownPlay.setVisibility(View.INVISIBLE);
        txtMessageBoard.setVisibility(View.INVISIBLE);

        switch (view.getId()) {
            case R.id.btnRock:
                ownPlay.setImageResource(R.drawable.rock);
                ownChoice = ROCK_CHOICE;
                break;

            case R.id.btnPaper:
                ownPlay.setImageResource(R.drawable.paper);
                ownChoice = PAPER_CHOICE;
                break;

            case R.id.btnScissors:
                ownPlay.setImageResource(R.drawable.scissors);
                ownChoice = SCISSORS_CHOICE;
                break;
        }

        generatePlay();
        animatePlay();
    }

    private void lose() {
        cpuPoints++;
        String msg = getString(R.string.lose) + "\nCPU -> " + cpuPoints + "   -   " + getString(R.string.you) + " -> " + ownPoints;
        txtMessageBoard.setText(msg);
    }

    private void win() {
        ownPoints++;
        String msg = getString(R.string.win) + "\nCPU -> " + cpuPoints + "   -   " + getString(R.string.you) + " -> " + ownPoints;
        txtMessageBoard.setText(msg);
    }

    private void draw() {
        String msg = getString(R.string.draw) + "\nCPU -> " + cpuPoints + "   -   " + getString(R.string.you) + " -> " + ownPoints;
        txtMessageBoard.setText(msg);
    }

    private void generatePlay() {
        Random rnd = new Random();
        int cpuChoice = (rnd.nextInt(3 - 1) + 1);

        if (cpuChoice == ROCK_CHOICE) {
            cpuPlay.setImageResource(R.drawable.rock);
        }else if (cpuChoice == PAPER_CHOICE) {
            cpuPlay.setImageResource(R.drawable.paper);
        }else if (cpuChoice == SCISSORS_CHOICE) {
            cpuPlay.setImageResource(R.drawable.scissors);
        }

        if (ownChoice == cpuChoice) {
            draw();
        }else if (ownChoice == ROCK_CHOICE && cpuChoice == PAPER_CHOICE) {
            lose();
        }else if (ownChoice == ROCK_CHOICE && cpuChoice == SCISSORS_CHOICE) {
            win();
        }else if (ownChoice == PAPER_CHOICE && cpuChoice == ROCK_CHOICE) {
            win();
        }else if (ownChoice == PAPER_CHOICE && cpuChoice == SCISSORS_CHOICE) {
            lose();
        }else if (ownChoice == SCISSORS_CHOICE && cpuChoice == ROCK_CHOICE) {
            lose();
        }else if (ownChoice == SCISSORS_CHOICE && cpuChoice == PAPER_CHOICE) {
            win();
        }
    }

    private void animateButtons() {
        Animation btn_rock_anim = AnimationUtils.loadAnimation(Game_Activity.this, R.anim.rock_animation);
        Animation btn_paper_anim = AnimationUtils.loadAnimation(Game_Activity.this, R.anim.paper_animation);
        Animation btn_scissors_anim = AnimationUtils.loadAnimation(Game_Activity.this, R.anim.scissors_animation);
        btnRock.setAnimation(btn_rock_anim);
        btnPaper.setAnimation(btn_paper_anim);
        btnScissors.setAnimation(btn_scissors_anim);
    }

    private void animatePlay() {
        cpuPlay.setVisibility(View.VISIBLE);
        ownPlay.setVisibility(View.VISIBLE);
        txtMessageBoard.setVisibility(View.VISIBLE);

        btnRock.setEnabled(false);
        btnPaper.setEnabled(false);
        btnScissors.setEnabled(false);

        Animation npc_play_animation = AnimationUtils.loadAnimation(Game_Activity.this, R.anim.right_move);
        Animation own_play_animation = AnimationUtils.loadAnimation(Game_Activity.this, R.anim.left_move);
        Animation board_animation = AnimationUtils.loadAnimation(Game_Activity.this, R.anim.board_move);
        cpuPlay.setAnimation(npc_play_animation);
        ownPlay.setAnimation(own_play_animation);
        txtMessageBoard.setAnimation(board_animation);

        Handler handler = new Handler();
        int DISABLE_DURATION_MS = 800;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnRock.setEnabled(true);
                btnPaper.setEnabled(true);
                btnScissors.setEnabled(true);
            }
        }, DISABLE_DURATION_MS);
    }

    private void initialize() {
        cpuPlay = (ImageView)findViewById(R.id.npcPlay);
        ownPlay = (ImageView)findViewById(R.id.ownPlay);

        btnRock = (Button)findViewById(R.id.btnRock);
        btnPaper = (Button)findViewById(R.id.btnPaper);
        btnScissors = (Button)findViewById(R.id.btnScissors);

        txtMessageBoard = (TextView)findViewById(R.id.txtMessageBoard);
        if (txtMessageBoard != null) {
            txtMessageBoard.setVisibility(View.INVISIBLE);
        }

        ownChoice = 0;
        cpuPoints = 0;
        ownPoints = 0;
    }
}
