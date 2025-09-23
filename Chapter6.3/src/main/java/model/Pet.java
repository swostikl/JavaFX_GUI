package model;

public class Pet {
    private double x;
    private double y;
    private final double size = 100;
    private final double speed = 2.0;

    private Double targetX = null;
    private Double targetY = null;

    public Pet(double startX, double startY) {
        this.x = startX;
        this.y = startY;
    }

    public synchronized void setTarget(Double tx, Double ty) {
        this.targetX = tx;
        this.targetY = ty;
    }

    public synchronized void clearTarget() {
        this.targetX = null;
        this.targetY = null;
    }

    public synchronized void update() {
        if (targetX != null && targetY != null) {
            double dx = targetX - x;
            double dy = targetY - y;
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance > 1) {
                x += (dx / distance) * speed;
                y += (dy / distance) * speed;

                if (distance < speed) {
                    x = targetX;
                    y = targetY;
                }
            }
        }
    }

    public synchronized double getX() { return x; }
    public synchronized double getY() { return y; }
    public double getSize() { return size; }
}
