package com.masprog.request;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) throws IllegalArgumentException{
        if (strNumber == null || strNumber.isEmpty())
            throw new UnsupportedOperationException("Please set a numeric values");
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber){
        if (strNumber == null || strNumber.isEmpty()) return true;
        String number = strNumber.replace(",", ".");
        return !number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static boolean isGreaterOrEqual(String numberOne, String numberTwo){
        return convertToDouble(numberOne) >= convertToDouble(numberTwo);
    }
}
