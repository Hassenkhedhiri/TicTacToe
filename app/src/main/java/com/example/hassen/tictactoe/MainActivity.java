package com.example.hassen.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button pion0,pion1,pion2,pion3,pion4,pion5,pion6,pion7,pion8 ;
    Button[] pions = new Button[9];
    TextView gameStatuts;
    Button replayButton ;
    ImageView morpionImageView ;
    boolean gameIsOver = false ;
    int currentTurn = 0 ;
    String[] joueurs = {"X","O"};
   // MediaPlayer sound = MediaPlayer.create(getApplicationContext(),R.raw.son);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sound.start();
        initialize();

        bindEventsListnerOnButtons();



    }

    private void bindEventsListnerOnButtons() {
        for (Button pion : pions){
            pion.setOnClickListener(pionListener);

        }

        replayButton.setOnClickListener(replayListener);
    }

    private View.OnClickListener pionListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            // trouver le bouton sur lequel on a cliquer
            Button button = (Button) findViewById(v.getId());

            //Test sur le jeux est-il terminé
            if (gameIsOver){
                return;
            }

            //verification sur l'emplacement

            if (!isValid(button)){
                //Animation sur l'image

                gameStatuts.setText("Deplacément Invalide!");
            }else {
                setSymbole(button,joueurs[currentTurn]);

                gameIsOver = winnerExists();



            }

            if (gameIsOver){
                gameStatuts.setText("Le joueur "+joueurs[currentTurn]+" a gagné !");
                return;
            }

            if (boardIsFull()){
                gameStatuts.setText("Match Null !");
                gameIsOver = true ;
                return;
            }

            // ^ c'est le ou exclusif
            currentTurn = currentTurn ^ 1 ;

            gameStatuts.setText("Joueur "+joueurs[currentTurn]+" c'est à votre tour !");



        }

    };

    private boolean winnerExists() {

        if ((pions[0].getText().toString() ==  joueurs[currentTurn])
                &&(pions[1].getText().toString() ==  joueurs[currentTurn])
                &&(pions[2].getText().toString() ==  joueurs[currentTurn])){
            changeColorOfButton(0,1,2);
            return true ;

        }



        if ((pions[3].getText().toString() ==  joueurs[currentTurn])
                &&(pions[4].getText().toString() ==  joueurs[currentTurn])
                &&(pions[5].getText().toString() ==  joueurs[currentTurn])){

            changeColorOfButton(3,4,5);
            return true ;

        }

        if ((pions[6].getText().toString() ==  joueurs[currentTurn])
                &&(pions[7].getText().toString() ==  joueurs[currentTurn])
                &&(pions[8].getText().toString() ==  joueurs[currentTurn])){

            changeColorOfButton(6,7,8);
            return true ;

        }

        if ((pions[0].getText().toString() ==  joueurs[currentTurn])
                &&(pions[3].getText().toString() ==  joueurs[currentTurn])
                &&(pions[6].getText().toString() ==  joueurs[currentTurn])){

            changeColorOfButton(0,3,6);
            return true ;

        }

        if ((pions[1].getText().toString() ==  joueurs[currentTurn])
                &&(pions[4].getText().toString() ==  joueurs[currentTurn])
                &&(pions[7].getText().toString() ==  joueurs[currentTurn])){

            changeColorOfButton(1,4,7);
            return true ;

        }

        if ((pions[2].getText().toString() ==  joueurs[currentTurn])
                &&(pions[5].getText().toString() ==  joueurs[currentTurn])
                &&(pions[8].getText().toString() ==  joueurs[currentTurn])){

            changeColorOfButton(2,5,8);
            return true ;

        }


        if ((pions[0].getText().toString() ==  joueurs[currentTurn])
                &&(pions[4].getText().toString() ==  joueurs[currentTurn])
                &&(pions[8].getText().toString() ==  joueurs[currentTurn])){

            changeColorOfButton(0,4,8);
            return true ;

        }

        if ((pions[2].getText().toString() ==  joueurs[currentTurn])
                &&(pions[4].getText().toString() ==  joueurs[currentTurn])
                &&(pions[6].getText().toString() ==  joueurs[currentTurn])){

            changeColorOfButton(2,4,6);
            return true ;

        }

        return  false ;

    }

    private void changeColorOfButton(int i, int i1, int i2) {
        pions[i] .setTextColor(Color.argb(255,42,188,18));
        pions[i1] .setTextColor(Color.argb(255,42,188,18));
        pions[i2] .setTextColor(Color.argb(255,42,188,18));



    }

    private boolean isValid(Button button) {
        return button.getText().toString().length() == 0;

    }


    private boolean boardIsFull() {
        for (Button pion : pions ) {
            if (isValid(pion)){
                return  false ;
            }
        }

        return true ;

    }

    private void setSymbole(Button button, String symbole) {
        button.setText(symbole);
    }



    private View.OnClickListener replayListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            for (Button pion:pions) {

                pion.setText("");
                pion.setTextColor(Color.BLACK);


            }

            gameStatuts.setText(R.string.game_status);
            currentTurn = 0 ;
            gameIsOver = false ;

        }
    };



    private void initialize() {
        pion0 = (Button) findViewById(R.id.pion0Button);
        pion1 = (Button) findViewById(R.id.pion1Button);
        pion2 = (Button) findViewById(R.id.pion2Button);
        pion3 = (Button) findViewById(R.id.pion3Button);
        pion4 = (Button) findViewById(R.id.pion4Button);
        pion5 = (Button) findViewById(R.id.pion5Button);
        pion6 = (Button) findViewById(R.id.pion6Button);
        pion7 = (Button) findViewById(R.id.pion7Button);
        pion8 = (Button) findViewById(R.id.pion8Button);

        gameStatuts = (TextView) findViewById(R.id.gameStatusTextView);
        replayButton = (Button) findViewById(R.id.gameReplayButton);
        morpionImageView = (ImageView) findViewById(R.id.morpionImageView);

        pions[0] = pion0 ;
        pions[1] = pion1 ;
        pions[2] = pion2 ;
        pions[3] = pion3 ;
        pions[4] = pion4 ;
        pions[5] = pion5 ;
        pions[6] = pion6 ;
        pions[7] = pion7 ;
        pions[8] = pion8 ;





    }

    @Override
    protected void onPause() {
        super.onPause();
        //sound.release();

    }
}
