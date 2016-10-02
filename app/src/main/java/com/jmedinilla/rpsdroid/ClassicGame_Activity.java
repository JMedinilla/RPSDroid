package com.jmedinilla.rpsdroid;

import android.content.Intent;
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

public class ClassicGame_Activity extends AppCompatActivity {

    //View components
    private ImageView cpuPlay;
    private ImageView ownPlay;
    private ImageView imgCoin;
    private Button btnRock;
    private Button btnPaper;
    private Button btnScissors;
    private TextView txtMessageBoard;
    private TextView txtCoin;

    //Final variables
    private static final int ROCK_CHOICE = 0;
    private static final int PAPER_CHOICE = 1;
    private static final int SCISSORS_CHOICE = 2;

    //Variables used in the plays
    private int cpuChoice; //What the CPU plays
    private int ownChoice; //What the player plays
    private int cpuPoints; //The points that the CPU has
    private int ownPoints; //The points that the user has
    private int ownWinsARow; //The times the player win in a row
    private int cpuWinsARow; //The times the CPU win in a row
    private int coinCount; //The coins the player has

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_classic);

        /*
        First, all the components must to be initialized
        and then, execute the animation of the ImageViews
        and the buttons at the botton of the screen
         */
        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();

        animatePlay();
        animateButtons();
    }

    /**
     * Method executed when the player use a button
     * @param view Component of the view
     */
    public void getOnClickClassicButton(View view) {

        /*
        If the player touch a button, the ImageViews and the
        TextView go invisible, so they can execute the animations
        the next time the Activity draws them
         */
        cpuPlay.setVisibility(View.INVISIBLE);
        ownPlay.setVisibility(View.INVISIBLE);
        txtMessageBoard.setVisibility(View.INVISIBLE);

        //The player ImageView loads a different image for each button
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

        /*
        When the player decides what to play, it is time for
        the CPU to do it and execute the animation
         */
        generatePlay();
        animatePlay();
    }

    /**
     * Method executed when the player use the coin button
     * @param view Component of the view
     */
    public void getOnClickClassicImage(View view) {
        switch (view.getId()) {
            case R.id.imgCoin:
                startActivity(new Intent(ClassicGame_Activity.this, ShopGame_Activity.class));
                break;
        }
    }

    /**
     * Method executed when the player lose a game
     * It adds a point to the CPU and create the
     * text the TextView has to show
     * Also modifies the coins
     */
    private void lose() {
        cpuPoints++;
        ownWinsARow = 0;
        cpuWinsARow++;

        if (coinCount > 0) {
            coinCount--;
        }

        String msg = getString(R.string.lose) + "\nCPU -> " + cpuPoints + "   -   " + getString(R.string.you) + " -> " + ownPoints;
        txtMessageBoard.setText(msg);
    }

    /**
     * Method executed when the player win a game
     * It adds a point to the player and create the
     * text the TextView has to show
     * Also modifies the coins
     */
    private void win() {
        ownPoints++;
        ownWinsARow++;
        cpuWinsARow = 0;

        if (ownWinsARow == 0 || ownWinsARow == 1) {
            coinCount++;
        }
        else if (ownWinsARow == 2 || ownWinsARow == 3) {
            coinCount += 3;
        }
        else if (ownWinsARow >= 4) {
            coinCount += 5;
        }

        String msg = getString(R.string.win) + "\nCPU -> " + cpuPoints + "   -   " + getString(R.string.you) + " -> " + ownPoints;
        txtMessageBoard.setText(msg);
    }

    /**
     * Method executed when there's a draw
     * create the text the TextView has to show
     */
    private void draw() {
        ownWinsARow = 0;
        cpuWinsARow = 0;
        String msg = getString(R.string.draw) + "\nCPU -> " + cpuPoints + "   -   " + getString(R.string.you) + " -> " + ownPoints;
        txtMessageBoard.setText(msg);
    }

    /**
     * Method implemented to decide the play of the
     * CPU and the winner of the match
     */
    private void generatePlay() {
        Random rnd = new Random();

        /*
        If the player wins 5 times in a row, the next match
        is an automatic victory for the CPU
         */
        if (ownWinsARow == 6) {
            if (ownChoice == ROCK_CHOICE) {
                cpuChoice = PAPER_CHOICE;
            }
            else if (ownChoice == PAPER_CHOICE) {
                cpuChoice = SCISSORS_CHOICE;
            }
            else {
                cpuChoice = ROCK_CHOICE;
            }
        }
        /*
        If the player wins 3 or 4 times in a row, the CPU has a
        50% chance of winning the next match
         */
        else if (ownWinsARow >= 4 && rnd.nextInt(2) == 0) {
            if (ownChoice == ROCK_CHOICE) {
                cpuChoice = PAPER_CHOICE;
            }
            else if (ownChoice == PAPER_CHOICE) {
                cpuChoice = SCISSORS_CHOICE;
            }
            else {
                cpuChoice = ROCK_CHOICE;
            }
        }
        /*
        If the CPU wins 5 times in a row, the next match
        is an automatic victory for the player
         */
        else if (cpuWinsARow >= 5) {
            if (ownChoice == ROCK_CHOICE) {
                cpuChoice = SCISSORS_CHOICE;
            }
            else if (ownChoice == PAPER_CHOICE) {
                cpuChoice = ROCK_CHOICE;
            }
            else {
                cpuChoice = PAPER_CHOICE;
            }
        }
        /*
        If the CPU wins 3 or 4 times in a row, the player has a
        50% chance of winning the next match
         */
        else if (cpuWinsARow >= 3 && rnd.nextInt(2) == 0) {
            if (ownChoice == ROCK_CHOICE) {
                cpuChoice = SCISSORS_CHOICE;
            }
            else if (ownChoice == PAPER_CHOICE) {
                cpuChoice = ROCK_CHOICE;
            }
            else {
                cpuChoice = PAPER_CHOICE;
            }
        }
        /*
        Otherwise, the CPU execute a random to decide
        what is it going to play
         */
        else {
            cpuChoice = rnd.nextInt(3);
        }

        //An image is assigned to the CPU ImageView
        if (cpuChoice == ROCK_CHOICE) {
            cpuPlay.setImageResource(R.drawable.rock);
        }else if (cpuChoice == PAPER_CHOICE) {
            cpuPlay.setImageResource(R.drawable.paper);
        }else if (cpuChoice == SCISSORS_CHOICE) {
            cpuPlay.setImageResource(R.drawable.scissors);
        }

        //Rules
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

        txtCoin.setText(String.valueOf(coinCount));
    }

    /**
     * Method implemented to create and use the animation
     * assigned to the buttons at the beginning
     */
    private void animateButtons() {
        Animation btn_rock_anim = AnimationUtils.loadAnimation(ClassicGame_Activity.this, R.anim.classic_rock_animation);
        Animation btn_paper_anim = AnimationUtils.loadAnimation(ClassicGame_Activity.this, R.anim.classic_paper_animation);
        Animation btn_scissors_anim = AnimationUtils.loadAnimation(ClassicGame_Activity.this, R.anim.classic_scissors_animation);

        Animation text_money_anim = AnimationUtils.loadAnimation(ClassicGame_Activity.this, R.anim.classic_coin_animation_text);
        Animation icon_money_anim = AnimationUtils.loadAnimation(ClassicGame_Activity.this, R.anim.classic_coin_animation);

        btnRock.setAnimation(btn_rock_anim);
        btnPaper.setAnimation(btn_paper_anim);
        btnScissors.setAnimation(btn_scissors_anim);

        imgCoin.setAnimation(icon_money_anim);
        txtCoin.setAnimation(text_money_anim);
    }

    /**
     * Method implemented to create and use the animations for
     * the components that show the play of each player and
     * the points they have
     */
    private void animatePlay() {
        cpuPlay.setVisibility(View.VISIBLE);
        ownPlay.setVisibility(View.VISIBLE);
        txtMessageBoard.setVisibility(View.VISIBLE);

        /*
        The first thing to do is disable the buttons so the
        player can't touch them while the screes is moving
         */
        btnRock.setEnabled(false);
        btnPaper.setEnabled(false);
        btnScissors.setEnabled(false);
        imgCoin.setEnabled(false);

        Animation npc_play_animation = AnimationUtils.loadAnimation(ClassicGame_Activity.this, R.anim.classic_right_move);
        Animation own_play_animation = AnimationUtils.loadAnimation(ClassicGame_Activity.this, R.anim.classic_left_move);
        Animation board_animation = AnimationUtils.loadAnimation(ClassicGame_Activity.this, R.anim.classic_board_move);
        cpuPlay.setAnimation(npc_play_animation);
        ownPlay.setAnimation(own_play_animation);
        txtMessageBoard.setAnimation(board_animation);

        /*
        After 800ms, less than a second, the buttons are
        available again to play
         */
        Handler handler = new Handler();
        int DISABLE_DURATION_MS = 800;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnRock.setEnabled(true);
                btnPaper.setEnabled(true);
                btnScissors.setEnabled(true);
                imgCoin.setEnabled(true);
            }
        }, DISABLE_DURATION_MS);
    }

    /**
     * Method that initialize all of the variables
     * and components of the view
     */
    private void initialize() {
        cpuPlay = (ImageView)findViewById(R.id.npcPlay);
        ownPlay = (ImageView)findViewById(R.id.ownPlay);
        imgCoin = (ImageView)findViewById(R.id.imgCoin);
        btnRock = (Button)findViewById(R.id.btnRock);
        btnPaper = (Button)findViewById(R.id.btnPaper);
        btnScissors = (Button)findViewById(R.id.btnScissors);
        txtMessageBoard = (TextView)findViewById(R.id.txtMessageBoard);
        txtCoin = (TextView)findViewById(R.id.txtCoin);

        cpuChoice = 0;
        ownChoice = 0;
        cpuPoints = 0;
        ownPoints = 0;
        ownWinsARow = 0;
        cpuWinsARow = 0;
        coinCount = 0;

        txtCoin.setText(String.valueOf(coinCount));
    }
}
