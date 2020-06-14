package ChineseChess;

import java.awt.Point;
import java.util.ArrayList;

public final class Board {

    public final static int ROW = 9;
    public final static int COL = 8;
    public final static byte[][] cellStartup = {
        {19, 18, 17, 16, 15, 16, 17, 18, 19},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 20, 0, 0, 0, 0, 0, 20, 0},
        {21, 0, 21, 0, 21, 0, 21, 0, 21},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {14, 0, 14, 0, 14, 0, 14, 0, 14},
        {0, 13, 0, 0, 0, 0, 0, 13, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {12, 11, 10, 9, 8, 9, 10, 11, 12}};
    public byte[][] cell;

    public Board(byte[][] cell) {
        setBoard(cell);
    }

    public void setBoard(byte[][] board) {
        cell = new byte[ROW + 1][COL + 1];
        for (int i = 0; i <= ROW; i++) {
            for (int j = 0; j <= COL; j++) {
                cell[i][j] = board[i][j];
            }
        }
    }

    public ArrayList<Point> FindPieces(boolean _RED) {
        ArrayList<Point> allpiece = new ArrayList<Point>();
        allpiece.add(0, new Point(-1, -1));
        for (int i = 0; i <= ROW; i++) {
            for (int j = 0; j <= COL; j++) {
                byte val = cell[i][j];
                if (val >= 8 && val <= 21) {
                    if ((_RED && val == 15) || (!_RED && val == 8)) {
                        allpiece.remove(0);
                        allpiece.add(0, new Point(i, j));
                    }
                    if ((_RED && val <= 14) || (!_RED && val > 14)) {
                        allpiece.add(new Point(i, j));
                    }
                }
            }
        }
        return allpiece;
    }

    public ArrayList<State> allPossibleMove(boolean _RED) {
        ArrayList<Point> allpiece = FindPieces(_RED);
        ArrayList<State> arrMoves = new ArrayList<State>();
        allpiece.get(0);
        for (int i = 1; i < allpiece.size(); i++) {
            Point pos = allpiece.get(i);
            byte val = cell[pos.x][pos.y];
            switch (val) {
                case 8:
                case 15:
                    CKing king = new CKing(this, pos);
                    arrMoves.addAll(king.FindAllPossibleMoves());
                    break;
                case 9:
                case 16:
                    CBishop bishop = new CBishop(this, pos);
                    arrMoves.addAll(bishop.FindAllPossibleMoves());
                    break;
                case 10:
                case 17:
                    CElephant elephant = new CElephant(this, pos);
                    arrMoves.addAll(elephant.FindAllPossibleMoves());
                    break;
                case 11:
                case 18:
                    CKnight knight = new CKnight(this, pos);
                    arrMoves.addAll(knight.FindAllPossibleMoves());
                    break;
                case 12:
                case 19:
                    CRook rook = new CRook(this, pos);
                    arrMoves.addAll(rook.FindAllPossibleMoves());
                    break;
                case 13:
                case 20:
                    CCannon cannon = new CCannon(this, pos);
                    arrMoves.addAll(cannon.FindAllPossibleMoves());
                    break;
                case 14:
                case 21:
                    CPawn pawn = new CPawn(this, pos);
                    arrMoves.addAll(pawn.FindAllPossibleMoves());
                    break;
            }
        }
        return arrMoves;
    }

    public boolean IsGameOver(boolean _RED) {
        ArrayList<Point> allpiece = FindPieces(_RED);
        allpiece.get(0);
        for (int i = 1; i < allpiece.size(); i++) {
            Point pos = allpiece.get(i);
            ArrayList<State> arrMoves = null;
            byte val = cell[pos.x][pos.y];
            switch (val) {
                case 8:
                case 15:
                    CKing king = new CKing(this, pos);
                    arrMoves = king.FindAllPossibleMoves();
                    break;
                case 9:
                case 16:
                    CBishop bishop = new CBishop(this, pos);
                    arrMoves = bishop.FindAllPossibleMoves();
                    break;
                case 10:
                case 17:
                    CElephant elephant = new CElephant(this, pos);
                    arrMoves = elephant.FindAllPossibleMoves();
                    break;
                case 11:
                case 18:
                    CKnight knight = new CKnight(this, pos);
                    arrMoves = knight.FindAllPossibleMoves();
                    break;
                case 12:
                case 19:
                    CRook rook = new CRook(this, pos);
                    arrMoves = rook.FindAllPossibleMoves();
                    break;
                case 13:
                case 20:
                    CCannon cannon = new CCannon(this, pos);
                    arrMoves = cannon.FindAllPossibleMoves();
                    break;
                case 14:
                case 21:
                    CPawn pawn = new CPawn(this, pos);
                    arrMoves = pawn.FindAllPossibleMoves();
                    break;
            }
            if (arrMoves != null && !arrMoves.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<State> select(Point pos) {
        Piece piece = null;
        byte value = cell[pos.x][pos.y];
        switch (value) {
            case 8:
            case 15:
                piece = new CKing(this, pos);
                break;
            case 9:
            case 16:
                piece = new CBishop(this, pos);
                break;
            case 10:
            case 17:
                piece = new CElephant(this, pos);
                break;
            case 11:
            case 18:
                piece = new CKnight(this, pos);
                break;
            case 12:
            case 19:
                piece = new CRook(this, pos);
                break;
            case 13:
            case 20:
                piece = new CCannon(this, pos);
                break;
            case 14:
            case 21:
                piece = new CPawn(this, pos);
                break;
        }
        return piece.FindAllPossibleMoves();
    }

    public void moveTo(Point prevMove, Point pos) {
        byte val = cell[prevMove.x][prevMove.y];
        cell[pos.x][pos.y] = val;
        cell[prevMove.x][prevMove.y] = 0;
    }
}
