package com.mohamed.halim.essa.tictactoe;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Game {


    public static final int O_VALUE = 1;
    public static final int X_VALUE = 2;


    /**
     * check if the given won the game
     * @param values : array of the values
     * @return true if the given value won
     */
    public static boolean checkWon(int[] values){
        return checkRows(values) || checkColumns(values) || checkCross(values);
    }
    /**
     * check if the given won the game by cross
     * @param values : array of the values
     * @return true if the given value won
     */
    private static boolean checkCross(int[] values) {
        if(checkThreeEqual(values[0], values[4], values[8]))
            return true;
        if(checkThreeEqual(values[2], values[4], values[6]))
            return true;
        return false;
    }

/*
 * 0 1 2
 * 3 4 5
 * 6 7 8
 */
    /**
     * check if the given  won the game by columns
     * @param values : array of the values
     * @return true if the given value won
     */
    private static boolean checkColumns(int[] values) {
        if(checkThreeEqual(values[0], values[3], values[6]))
            return true;
        if(checkThreeEqual(values[1], values[4], values[7]))
            return true;
        if(checkThreeEqual(values[2], values[5], values[8]))
            return true;
        return false;
    }
    /**
     * check if the given  won the game by rows
     * @param values : array of the values
     * @return true if the given value won
     */
    private static boolean checkRows(int[] values) {
        for(int i = 0 ; i < values.length ; i+=3){
            if(checkThreeEqual(values[i], values[i + 1], values[i + 2]))
                return true;
        }
        return false;
    }

    /**
     * check if the three parameters are equal
     * @param x : first number
     * @param y : second number
     * @param z : third number
     * @return true if all three equal
     */
    private static boolean checkThreeEqual(int x, int y, int z){
        return  (x == y && y == z && x != 0);
    }
}
