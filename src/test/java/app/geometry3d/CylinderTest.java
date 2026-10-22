package app.geometry3d;
import app.exceptions.InvalidFigureParameterExeption;
import app.geometry2d.Circle;
import app.geometry2d.Restangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CylinderTest {
    @Test
    void testVolumeCircleBase(){
        Cylinder cylinder = new Cylinder(new Circle(2.0), 5.0);
        assertEquals(Math.PI * 4 * 5, cylinder.volume(), 1e-9);
    }

    @Test
    void testVolumeWithRectangleBase(){
        Cylinder cylinder = new Cylinder(new Restangle(2.0, 3.0), 4.0);
        assertEquals(24.0, cylinder.volume(), 1e-9);
    }

    @Test
    void testNullBase(){
        assertThrows(InvalidFigureParameterExeption.class, () -> new Cylinder(null, 5.0));
    }

    @Test
    void testInvalidHeight(){
        assertThrows(InvalidFigureParameterExeption.class, () -> new Cylinder(new Circle(1.0), -2.0));
    }

    @Test
    void testToString(){
        Cylinder cylinder = new Cylinder(new Circle(1.0), 10.0);
        String result = cylinder.toString();
        assertTrue(result.contains("Cylinder"));
        assertTrue(result.contains("Circle"));
        assertTrue(result.contains("10.0"));
    }
}
