package app.geometry2d;
import app.exceptions.InvalidFigureParameterExeption;

public class Restangle implements Figure{
    private static final long serialVersionUID = 1L;

    private final double width;
    private final double height;

    public Restangle(double width, double height){
        checkSide(width, "ширина");
        checkSide(height, "высота");
        this.width = width;
        this.height = height;
    }
    private void checkSide(double side, String name){
        if (Double.isNaN(side) || Double.isInfinite(side)){
            throw new InvalidFigureParameterExeption(name + "прямоугольника должна быть конечным числом, получено " + side);
        }
        if (side <= 0){
            throw new InvalidFigureParameterExeption(name + "прямоугольника должна быть положительна, получено " + side);
        }
    }
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }
    @Override
    public double area(){
        return width * height;
    }
    @Override
    public double perimeter(){
        return 2 * (width + height);
    }
    @Override
    public String toString(){
        return "Rectangle(width=" + width + ", height=" + height + ")";
    }
}
