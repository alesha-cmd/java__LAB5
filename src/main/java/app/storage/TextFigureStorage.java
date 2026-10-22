package app.storage;
import app.exceptions.InvalidFileFormatExeption;
import app.geometry2d.Figure;
import app.geometry2d.Restangle;
import app.geometry2d.Circle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextFigureStorage implements FigureStorage{
    @Override
    public List<Figure> read (Path path) throws IOException, InvalidFileFormatExeption{
        List<Figure> figures = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line;
            int lineNumber = 0;
            while((line = reader.readLine()) != null){
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()){
                    continue;
                }
                try{
                    Figure figure = parseLine(line);
                    figures.add(figure);
                } catch (app.exceptions.InvalidFigureParameterExeption e){
                    throw new InvalidFileFormatExeption("ошибка параметров фигуры: " + e.getMessage(), lineNumber, e);
                } catch (NumberFormatException e){
                    throw new InvalidFileFormatExeption("не удалось преобразовать число: " + e.getMessage(), lineNumber, e);
                } catch (IllegalArgumentException e){
                    throw new InvalidFileFormatExeption(e.getMessage(), lineNumber, e);
                }
            }
        }
        return figures;
    }
    private Figure parseLine(String line){
        String[] parts = line.split("\\s+");
        if (parts.length < 2){
            throw new IllegalArgumentException("в строке недостаточно данных");
        }
        String type = parts[0].toLowerCase();
        if (type.equals("circle")){
            if (parts.length != 2){
                throw new IllegalArgumentException("для circle ожидается 1 параметр (радиус), получено " + (parts.length - 1));
            }
            double radius = Double.parseDouble(parts[1]);
            return new Circle(radius);
        }
        if (type.equals("rectangle")){
            if (parts.length != 3){
                throw new IllegalArgumentException("для rectangle ожидается 2 параметра (ширина, высота), получено " + (parts.length - 1));
            }
            double width = Double.parseDouble(parts[1]);
            double height = Double.parseDouble(parts[2]);
            return new Restangle(width, height);
        }
        throw new IllegalArgumentException("неизвестный тип фигуры: " + type);
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException{
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
            for (Figure figure : figures){
                if (figure instanceof Circle){
                    Circle c = (Circle) figure;
                    writer.write("circle " + c.getRadius());
                } else if (figure instanceof Restangle){
                    Restangle r = (Restangle) figure;
                    writer.write("rectangle " + r.getWidth() + " " + r.getHeight());
                } else {
                    throw new IOException("не умею записывать фигуру типа: " + figure.getClass().getName());
                }
                writer.newLine();
            }
        }
    }
}
