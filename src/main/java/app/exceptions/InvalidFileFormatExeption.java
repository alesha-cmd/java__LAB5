package app.exceptions;

public class InvalidFileFormatExeption extends Exception{
    private final int lineNumber;

    public InvalidFileFormatExeption(String message, int lineNumber, Throwable cause){
        super(message, cause);
        this.lineNumber = lineNumber;
    }
    public int getLineNumber(){
        return lineNumber;
    }
}
