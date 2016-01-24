import javafx.scene.paint.Color;

public class Sprite {

    protected int positionX;
    protected int positionY;
    protected int width;
    protected int height;
    protected int dx;
    protected int dy;
    protected int init_velocity;
    protected int left_boundary;
    protected int right_boundary;
    protected int upper_boundary;
    protected int bottom_boundary;
    protected Color color;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getDx() { return dx; }

    public void setDx(int dx) {  this.dx = dx; }

    public int getDy() { return dy; }

    public void setDy(int dy) { this.dy = dy; }

    public int getInit_velocity() { return init_velocity; }

    public int getLeft_boundary() {
        return left_boundary;
    }

    public int getRight_boundary() {
        return right_boundary;
    }

    public int getUpper_boundary() {
        return upper_boundary;
    }

    public int getBottom_boundary() {
        return bottom_boundary;
    }

    protected void setBoundaries(){
        this.left_boundary = this.positionX - this.width/2;
        this.right_boundary = this.positionX + this.width/2;
        this.upper_boundary = this.positionY - this.height/2;
        this.bottom_boundary = this.positionY + this.height/2;
    }

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    protected void draw(){}

}
