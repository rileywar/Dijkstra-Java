package a6;

public class ShortestPathQueueObject {
    private String label;
    private double distance;

    public ShortestPathQueueObject(String label, double distance) {
        this.label = label;
        this.distance = distance;
    }

    public String getLabel() {
        return label;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
