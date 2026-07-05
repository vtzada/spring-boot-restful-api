package br.com.vitortheof.utils;

public final class MathUtils {
    private MathUtils() {}

    public static void validateNumbers(String... numbers) {
        for (String number : numbers) {
            if (!isNumeric(number)) {
                throw new UnsupportedOperationException("Please enter a valid number");
            }
        }
    }

    public static Double toDouble(String number) {
        return Double.parseDouble(number.replace(",", "."));
    }

    private static boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",", "."); // R$ 5,00 USD 5.00
        return number.matches("[+-]?[0-9]*\\.?[0-9]+");
    }
}
