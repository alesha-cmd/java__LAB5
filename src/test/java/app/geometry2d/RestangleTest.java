package app.geometry2d;
import app.exceptions.InvalidFigureParameterExeption;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class RestangleTest {
    private static final double EPSILON = 1e-9;

    @Test
    void testArea(){
        Restangle rect = new Restangle(3.0, 4.0);
        assertEquals(12.0, rect.area(), EPSILON);
    }

    @Test
    void testPerimeter(){
        Restangle rect = new Restangle (3.0, 4.0);
        assertEquals(14.0, rect.perimeter(), EPSILON);
    }

    @Test
    void testInvalidNegativeWidth(){
        assertThrows(InvalidFigureParameterExeption.class, () -> new Restangle(-1.0, 4.0));
    }
}
