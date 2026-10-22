package app.storage;
import app.exceptions.InvalidFileFormatExeption;
import app.geometry2d.Figure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class BinaryFigureStorage implements FigureStorage{
    @Override
    @SuppressWarnings("unchecked")
    public List<Figure> read(Path path) throws IOException, InvalidFileFormatExeption{
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))){
            Object obj = in.readObject();
            if (!(obj instanceof List)){
                throw new InvalidFileFormatExeption("файл не содержит список фигур", 0, null);
            }
            List<?> rawList = (List<?>) obj;

            for (Object item : rawList){
                if (!(item instanceof Figure)){
                    throw new InvalidFileFormatExeption("элемент списка не является фигурой: " + item.getClass().getName(), 0, null);
                }
            }
            return (List<Figure>) rawList;
        } catch (ClassNotFoundException e){
            throw new InvalidFileFormatExeption("класс из файла не найден", 0, e);
        }
    }
    @Override
    public void write(Path path, List<Figure> figures) throws IOException{
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))){
            out.writeObject(figures);
        }
    }
}
