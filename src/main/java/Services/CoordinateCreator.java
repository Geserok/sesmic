package Services;

public class CoordinateCreator {

    public double[] getCoordinates(int numPoints) {

        double[] y = new double[numPoints];
        y[0] = 0;
        for (int i = 1; i < y.length; i++) {
            y[i] = y[i - 1] + Math.random() - 0.5;
        }
        return y;
    }

}
