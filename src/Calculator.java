import java.util.ArrayList;
import java.util.List;

public class Calculator {
    public static String calc(String mathExpression){
        List<String> example = parsingMathExpression(mathExpression.toUpperCase());
        int answer;
        boolean isRomanNumbers = checkRomanOrArabicNumbers(example);
        if (isRomanNumbers) {
            convertRomanToArabic(example);
        }
        switch (example.get(1)) {
            case "+"  -> answer = Integer.parseInt(example.get(0)) + Integer.parseInt(example.get(2));
            case "-"  -> answer = Integer.parseInt(example.get(0)) - Integer.parseInt(example.get(2));
            case "*"  -> answer = Integer.parseInt(example.get(0)) * Integer.parseInt(example.get(2));
            case "/"  -> answer = Integer.parseInt(example.get(0)) / Integer.parseInt(example.get(2));
            default -> throw new IllegalStateException("Неправильная математическая операция: " + example.get(1));
        };
        if (isRomanNumbers){
            if (answer <= 0){
                throw new IllegalArgumentException("В римской системе нет отрицательных чисел и нуля");
            }
            return convertArabicToRoman(answer);
        }
        return String.valueOf(answer);
    }

    private static String convertArabicToRoman(int result) {
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((result > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= result) {
                sb.append(currentSymbol.name());
                result -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    private static boolean checkRomanOrArabicNumbers(List<String> example) {
        List<String> romanNumbers = List.of("I","II","III","IV","V","VI","VII","VIII","IX","X");
        if (romanNumbers.contains(example.get(0))){
            return true;
        }
        return false;
    }

    private static void convertRomanToArabic(List<String> example) {
        for (int i = 0; i <= 2; i+=2) {
            String romanNumeral = example.get(i).toUpperCase();
            int result = 0;

            List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

            int k = 0;

            while ((!romanNumeral.isEmpty()) && (k < romanNumerals.size())) {
                RomanNumeral symbol = romanNumerals.get(k);
                if (romanNumeral.startsWith(symbol.name())) {
                    result += symbol.getValue();
                    romanNumeral = romanNumeral.substring(symbol.name().length());
                } else {
                    k++;
                }
            }

            if (!romanNumeral.isEmpty()) {
                throw new IllegalArgumentException(example.get(i) + " cannot be converted to a Roman Numeral");
            }
            example.set(i, String.valueOf(result));
        }
    }

    private static List<String> parsingMathExpression(String example) {
        CheckException.checkIsExampleCorrectMathExpression(example);
        List<String> mathExpression = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < example.length(); i++) {
            if (example.charAt(i) == ' '){
                continue;
            }
            if (example.charAt(i) == '+' ||
                    example.charAt(i) == '-' ||
                    example.charAt(i) == '*' ||
                    example.charAt(i) == '/'){
                mathExpression.add(temp.toString());
                mathExpression.add(String.valueOf(example.charAt(i)));//добавляем математическую операцию в коллекцию(+,-,*,/)
                temp = new StringBuilder();
                continue;
            }
            temp.append(example.charAt(i));
            if (i == example.length()-1) {
                mathExpression.add(temp.toString());
            }
        }
        CheckException.checkIsExampleCorrectMathExpression(mathExpression);
        return mathExpression;
    }
}
