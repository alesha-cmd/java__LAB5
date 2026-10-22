package app.geometry2d;
import app.exceptions.InvalidFigureParameterExeption;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CircleTest {
    private static final double EPSILON = 1e-9;

    @Test
    void testArea(){
        Circle circle = new Circle(5.0);
        assertEquals(Math.PI * 25, circle.area(), EPSILON);
    }

    @Test
    void testPerimeter(){
        Circle circle = new Circle(2.0);
        assertEquals(2 * Math.PI * 2, circle.perimeter(), EPSILON);
    }

    @Test
    void testInvalidZeroRadius(){
        assertThrows(InvalidFigureParameterExeption.class, () -> new Circle(0.0));
    }

    @Test
    void testInvalidNegativRadius(){
        assertThrows(InvalidFigureParameterExeption.class, () -> new Circle(-5.0));
    }
}
