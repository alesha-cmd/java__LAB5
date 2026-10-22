package app.geometry3d;
import app.exceptions.InvalidFigureParameterExeption;
import app.geometry2d.Figure;
public class Cylinder {
    private final Figure base;
    private final double height;

    public Cylinder(Figure base, double height){
        if (base == null){
            throw new InvalidFigureParameterExeption("основание цилиндра не может быть null");
        }
        if (Double.isNaN(height) || Double.isInfinite(height)){
            throw new InvalidFigureParameterExeption("высота цилиндра должна быть конечным числом, получено " + height);
        }
        if (height <= 0){
            throw new InvalidFigureParameterExeption("высота цилиндра должна быть положительна, получено " + height);
        }
        this.base = base;
        this.height = height;
    }
    public Figure getBase(){
        return base;
    }
    public double getHeight(){
        return height;
    }
    public double volume(){
        return base.area() * height;
    }
    @Override
    public String toString(){
        return "Cylinder(base=" + base + ", height=" + height + ")";
    }
}
