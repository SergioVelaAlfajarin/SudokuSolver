package sva.ss.solver;

public record Position(int row, int col, String type) {

    @Override
    public String toString() {
        return String.format("Position{%d, %d}", row, col);
    }
}
