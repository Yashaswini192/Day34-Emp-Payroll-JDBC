package com.jdbc.bl.JDBCDemo;

public class CustomException extends RuntimeException {

    enum ExceptionType{
        SQLException, NullPointer, ClassNotFound, ListIsNull
    }

    ExceptionType type;

    public CustomException(String message , ExceptionType type) {
        super(message);
        this.type = type;
    }
}
