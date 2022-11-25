package sva.ss.solver;

public record Position(int row, int col) {
    @Override
    public String toString() {
        return row + ", " + col;
    }
}
