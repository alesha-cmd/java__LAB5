package app.geometry2d;
import app.exceptions.InvalidFigureParameterExeption;
public class Circle implements Figure{
    private static final long serialVersionUID = 1L;
    private final double radius;
    public Circle(double radius){
        if (Double.isNaN(radius) || Double.isInfinite(radius)){
            throw new InvalidFigureParameterExeption("радиус круга должен быть конечным числом, получено " + radius);
        }
        if (radius <= 0){
            throw new InvalidFigureParameterExeption("рфдиус круга должен быть положительным, получено " + radius);
        }
        this.radius = radius;
    }
    public double getRadius(){
        return radius;
    }
    @Override
    public double area(){
        return Math.PI * radius * radius;
    }
    @Override
    public double perimeter(){
        return 2 * Math.PI * radius;
    }
    @Override
    public String toString(){
        return "Circle(radius= " + radius + ")";
    }
}
