package com.example.user.connect3;

import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 = yellow 1 = red
    int activePlayer = 0;
    boolean gameInactive= true;
    // 2 mean unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    //winning position
    int[][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        //System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameInactive)
        {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if(activePlayer == 0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else if(activePlayer == 1)
            {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(300).setDuration(300);
            for(int[] winningPosition : winningPosition)
            {
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]]!=2)
                {
                    gameInactive=false;
                    String winner = "Red";
                    if(gameState[winningPosition[0]] == 0)
                    {
                        winner = "Yellow";
                    }
                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has WON");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }

            }
        }
        else {

            boolean gameIsOver = true;

            for (int counterState : gameState) {

                if (counterState == 2) gameIsOver = false;

            }

            if (gameIsOver) {

                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                winnerMessage.setText("It's a draw");

                LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                layout.setVisibility(View.VISIBLE);

            }

        }
    }
    public void playAgain(View view)
    {
        gameInactive=true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        // 2 mean unplayed
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
