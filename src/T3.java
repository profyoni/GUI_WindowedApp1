public class T3 {
    public enum CellValue {X, O, NONE}

    private CellValue[][] board;

    public void makeMove(int row, int col) {
    }

    public CellValue winner() {
        return CellValue.NONE;
    }

    public CellValue currentPlayer() {
        return CellValue.NONE;
    }

    public boolean isGameOver() {
        return true;
    }
}
