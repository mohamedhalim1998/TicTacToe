package com.mohamed.halim.essa.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TIE_MESSAGE = "The game is tie";
    TextView mOScore;
    TextView mXScore;
    TextView winMessage;
    TextView mTieScore;

    ImageView zero;
    ImageView one;
    ImageView two;
    ImageView three;
    ImageView four;
    ImageView five;
    ImageView six;
    ImageView seven;
    ImageView eight;

    Button retry;

    View finishedView;

    private int[] squareValues = new int[9];
    private int counter = 0;
    private int oScore = 0;
    private int xScore = 0;

    private boolean gameDone = false;


    Bundle bundle = null;
    private int tieScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mOScore = findViewById(R.id.o_score);
        mXScore = findViewById(R.id.x_score);
        mTieScore = findViewById(R.id.tie_score);
        winMessage = findViewById(R.id.win_message);

        retry = findViewById(R.id.reset_game);

        finishedView = findViewById(R.id.game_finish_view);

        zero = findViewById(R.id.img_zero);
        one = findViewById(R.id.img_one);
        two = findViewById(R.id.img_two);
        three = findViewById(R.id.img_three);
        four = findViewById(R.id.img_four);
        five = findViewById(R.id.img_five);
        six = findViewById(R.id.img_six);
        seven = findViewById(R.id.img_seven);
        eight = findViewById(R.id.img_eight);


        zero.setOnClickListener(imageClickListener(0));
        one.setOnClickListener(imageClickListener(1));
        two.setOnClickListener(imageClickListener(2));
        three.setOnClickListener(imageClickListener(3));
        four.setOnClickListener(imageClickListener(4));
        five.setOnClickListener(imageClickListener(5));
        six.setOnClickListener(imageClickListener(6));
        seven.setOnClickListener(imageClickListener(7));
        eight.setOnClickListener(imageClickListener(8));

        retry.setOnClickListener(resetGame());

        mXScore.setText(getString(R.string.x_score, xScore));
        mOScore.setText(getString(R.string.o_score, oScore));
        mTieScore.setText(getString(R.string.tie_score,tieScore));

        if (savedInstanceState != null) {
            squareValues = savedInstanceState.getIntArray("SQUARE_VALUES");
            Log.i("savedInstanceState","load data");
            ImageView[] imageViews = {zero, one, two, three, four, five, six, seven, eight};
            for (int i = 0; i < squareValues.length; i++) {
                setImage(imageViews[i], squareValues[i]);
            }
        }
    }

    /**
     * set the image of the image
     *
     * @param position : the position of the image
     * @return OnClickListener object
     */
    private View.OnClickListener imageClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!gameDone) {
                    if (squareValues[position] == 0) {
                        ImageView img = (ImageView) view;
                        if (counter % 2 == 0) {
                            img.setImageResource(R.drawable.letter_o);
                            squareValues[position] = Game.O_VALUE;
                            if (Game.checkWon(squareValues)) {
                                oScore++;
                                updateScore("O");
                                gameDone = true;
                            }
                        } else {
                            img.setImageResource(R.drawable.letter_x);
                            squareValues[position] = Game.X_VALUE;
                            if (Game.checkWon(squareValues)) {
                                xScore++;
                                updateScore("X");
                                gameDone = true;
                            }
                        }
                        counter++;
                        if(counter == 9 && !gameDone){
                            gameDone = true;
                            tieScore++;
                            updateScore(TIE_MESSAGE);
                        }
                    }
                }
            }
        };
    }

    private void updateScore(String s) {
        finishedView.setVisibility(View.VISIBLE);
        if (s.equals(TIE_MESSAGE))
            winMessage.setText(s);
        else
            winMessage.setText(getString(R.string.win_message, s));
        mXScore.setText(getString(R.string.x_score, xScore));
        mOScore.setText(getString(R.string.o_score, oScore));
        mTieScore.setText(getString(R.string.tie_score,tieScore));

    }

    private View.OnClickListener resetGame() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                squareValues = new int[9];
                counter = 0;
                gameDone = false;
                finishedView.setVisibility(View.GONE);
                zero.setImageResource(R.drawable.blank);
                one.setImageResource(R.drawable.blank);
                two.setImageResource(R.drawable.blank);
                three.setImageResource(R.drawable.blank);
                four.setImageResource(R.drawable.blank);
                five.setImageResource(R.drawable.blank);
                six.setImageResource(R.drawable.blank);
                seven.setImageResource(R.drawable.blank);
                eight.setImageResource(R.drawable.blank);
            }
        };

    }

    private void setImage(ImageView image, int value) {
        if (value == Game.O_VALUE) {
            image.setImageResource(R.drawable.letter_o);
        } else if (value == Game.X_VALUE) {
            image.setImageResource(R.drawable.letter_x);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        bundle = new Bundle();
        bundle.putIntArray("SQUARE_VALUES", squareValues);
        onSaveInstanceState(bundle);
    }


}
