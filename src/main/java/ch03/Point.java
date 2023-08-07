package ch03;

public record Point(double x, double y) {

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}
