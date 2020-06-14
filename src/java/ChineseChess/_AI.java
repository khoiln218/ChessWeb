package ChineseChess;

import java.awt.Point;
import java.util.ArrayList;

public class _AI {

    Board board;
    private int MaxDepth = 4;
    private State MyBestMove;

    public _AI(Board b) {
        this.board = b;
    }

    int Bonous(boolean RED) {

        int materialnumber[][] = {{5, 2, 2, 2, 2, 2, 1},
            {5, 2, 2, 2, 2, 2, 1}};
        int i, s, bn[][] = {{-2, -3, -3, -4, -4, -5, 0},
            {-2, -3, -3, -4, -4, -5, 0}};

        for (i = 0; i < 2; i++) {
            if (materialnumber[i][1] < 2) {
                bn[1 - i][5] += 4;
                bn[1 - i][3] += 2;
                bn[1 - i][0] += 1;
            }

            if (materialnumber[i][2] < 2) {
                bn[1 - i][5] += 2;
                bn[1 - i][4] += 2;
                bn[1 - i][0] += 1;
            }
        }

        if (board.cell[0][0] == 19 && board.cell[0][1] == 18) {
            bn[0][6] -= 10;
        }
        if (board.cell[0][8] == 19 && board.cell[0][7] == 18) {
            bn[0][6] -= 10;
        }
        if (board.cell[9][0] == 13 && board.cell[9][1] == 12) {
            bn[1][6] -= 10;
        }
        if (board.cell[9][8] == 13 && board.cell[9][7] == 12) {
            bn[1][6] -= 10;
        }

        int side, xside;
        if (RED) {
            side = 0;
            xside = 1;
        } else {
            side = 1;
            xside = 0;
        }

        s = bn[side][6] - bn[xside][6];

        for (i = 0; i < 6; i++) {
            s += materialnumber[side][i] * bn[side][i]
                    - materialnumber[xside][i] * bn[xside][i];
        }
        return s;
    }

    int Eval(boolean RED) {
        int s = 0;
        for (int x = 0; x <= 9; x++) {
            for (int y = 0; y <= 8; y++) {
                byte value = board.cell[x][y];
                switch (value) {
                    case 8:
                        s -= CKing.GetPositionValue(new Point(x, y), !RED);
                        break;
                    case 15:
                        s += CKing.GetPositionValue(new Point(x, y), RED);
                        break;
                    case 9:
                        s -= CBishop.GetPositionValue(new Point(x, y), !RED);
                        break;
                    case 16:
                        s += CBishop.GetPositionValue(new Point(x, y), RED);
                        break;
                    case 10:
                        s -= CElephant.GetPositionValue(new Point(x, y), !RED);
                        break;
                    case 17:
                        s += CElephant.GetPositionValue(new Point(x, y), RED);
                        break;
                    case 11:
                        s -= CKnight.GetPositionValue(new Point(x, y), !RED);
                        break;
                    case 18:
                        s += CKnight.GetPositionValue(new Point(x, y), RED);
                        break;
                    case 12:
                        s -= CRook.GetPositionValue(new Point(x, y), !RED);
                        break;
                    case 19:
                        s += CRook.GetPositionValue(new Point(x, y), RED);
                        break;
                    case 13:
                        s -= CCannon.GetPositionValue(new Point(x, y), !RED);
                        break;
                    case 20:
                        s += CCannon.GetPositionValue(new Point(x, y), RED);
                        break;
                    case 14:
                        s -= CPawn.GetPositionValue(new Point(x, y), !RED);
                        break;
                    case 21:
                        s += CPawn.GetPositionValue(new Point(x, y), RED);
                        break;
                }
            }
        }
        if (!RED) {
            s = -s;
        }
        return s + Bonous(RED);
    }

    void MakeMove(State state) {
        board.cell[state.curr.x][state.curr.y] = state.value1;
        board.cell[state.prev.x][state.prev.y] = 0;
    }

    void UnMakeMove(State state) {
        board.cell[state.curr.x][state.curr.y] = state.value2;
        board.cell[state.prev.x][state.prev.y] = state.value1;
    }

    public int AlphaBeta(int depth, boolean RED, int Alpha, int Beta) {
        if (depth == 0) {
            return Eval(RED);
        }

        int best = -100000;
        State bestmove = null;
        ArrayList<State> arrMoves = board.allPossibleMove(!RED);

        if (arrMoves.isEmpty()) {
            return -10000 - depth;
        }

        int i = 0;
        while (i < arrMoves.size() && best < Beta) {
            State m;
            m = arrMoves.get(i);

            if (best > Alpha) {
                Alpha = best;
            }

            MakeMove(m);
            RED = !RED;

            int value = -AlphaBeta(depth - 1, RED, -Beta, -Alpha);

            UnMakeMove(m);
            RED = !RED;

            if (value > best) {
                best = value;
                bestmove = m;
            }
            i++;
        }
        if (depth == MaxDepth) {
            MyBestMove = bestmove;
        }
        return best;
    }

    public State GenerateMove(boolean RED) {
        int alpha = -100000;
        int beta = 100000;

        AlphaBeta(MaxDepth, RED, -beta, -alpha);

        return MyBestMove;
    }
}
