package app;
import app.exceptions.InvalidFileFormatExeption;
import app.geometry2d.Figure;
import app.geometry3d.Cylinder;
import app.storage.BinaryFigureStorage;
import app.storage.FigureStorage;
import app.storage.TextFigureStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        if (args.length < 2){
            System.out.println("Использование: java app.Main <входной.txt> <выходной.bin>");
            return;
        }
        Path inputPath = Paths.get(args[0]);
        Path outputPath = Paths.get(args[1]);

        FigureStorage textStorage = new TextFigureStorage();
        FigureStorage binaryStorage = new BinaryFigureStorage();

        try {
            List<Figure> figures = textStorage.read(inputPath);
            System.out.println("Прочитано фигур: " + figures.size());

            for (Figure figure : figures){
                System.out.println(figure);
                System.out.println(" площадь: " + figure.area());
                System.out.println(" периметр: " + figure.perimeter());
            }

            if (figures.isEmpty()){
                System.out.println("Список фигур пуст цилинд не создается.");
            }
            else {
                Random random = new Random();
                Figure base = figures.get(random.nextInt(figures.size()));
                double height = readHeightFronUser();

                Cylinder cylinder = new Cylinder(base, height);
                System.out.println("\nЦилиндр:");
                System.out.println(" основание: " + cylinder.getBase());
                System.out.println(" высота: " + cylinder.getHeight());
                System.out.println(" объем: " + cylinder.volume());
            }
            binaryStorage.write(outputPath, figures);
            System.out.println("\nСписок успешно записан в файл: " + outputPath);
        } catch (InvalidFileFormatExeption e){
            System.err.println("Ошибка формата файла: " + e.getMessage());
            if (e.getLineNumber() > 0){
                System.err.println("Строка: " + e.getLineNumber());
            }
            if (e.getCause() != null){
                System.err.println("Причина: " + e.getCause().getMessage());
            }
        } catch (app.exceptions.InvalidFigureParameterExeption e){
            System.err.println("Ошибка параметра фигуры: " + e.getMessage());
        } catch (IOException e){
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (NumberFormatException e){
            System.err.println("Введено не число: " + e.getMessage());
        }
    }
    private static double readHeightFronUser(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Введите высоту цилиндра: ");
            String line = scanner.nextLine().trim();
            try{
                return Double.parseDouble(line);
            } catch (NumberFormatException e){
                System.out.println("Это не число попробуйте еще раз.");
            }
        }
    }
}