package app.storage;
import app.exceptions.InvalidFileFormatExeption;
import app.geometry2d.Figure;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
public interface FigureStorage {
    List<Figure> read(Path path) throws IOException, InvalidFileFormatExeption;
    void write(Path path, List<Figure> figures) throws IOException;
}
