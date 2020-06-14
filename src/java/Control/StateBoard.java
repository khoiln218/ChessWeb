/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author conrongchautien
 */
public class StateBoard {

    private ArrayList<Point> undo;
    private ArrayList<String> msg;
    private byte[][] board;
    private boolean RED;

    public StateBoard(ArrayList<Point> undo, ArrayList<String> msg, byte[][] board, boolean RED) {
        this.undo = undo;
        this.msg = msg;
        this.board = board;
        this.RED = RED;
    }

    public void setRED(boolean RED) {
        this.RED = RED;
    }

    public void setUndo(ArrayList<Point> undo) {
        this.undo = undo;
    }

    public void setMsg(ArrayList<String> msg) {
        this.msg = msg;
    }

    public void setBoard(byte[][] board) {
        this.board = board;
    }

    public ArrayList<Point> getUndo() {
        return undo;
    }

    public ArrayList<String> getMsg() {
        return msg;
    }

    public byte[][] getBoard() {
        return board;
    }

    public boolean isRED() {
        return RED;
    }
}
