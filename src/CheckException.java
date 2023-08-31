import java.util.List;

public class CheckException {
    public static void checkIsExampleCorrectMathExpression(List<String> example) {
        List<String> romanNumbers = List.of("I","II","III","IV","V","VI","VII","VIII","IX","X");
        List<String> arabicNumbers = List.of("1","2","3","4","5","6","7","8","9","10");

        if (example.size() != 3){
            throw new IllegalArgumentException("Пожалуйста, введите пример корректно");
        }

        if ((romanNumbers.contains(example.get(0)) && (arabicNumbers.contains(example.get(2)))) ||
                ((romanNumbers.contains(example.get(2)) && (arabicNumbers.contains(example.get(0)))))){
            throw new IllegalArgumentException("нельзя в одном выражении использовать сразу арбские и римские цифры одновременно");
        }

        if ((!(romanNumbers.contains(example.get(0))) && !(arabicNumbers.contains(example.get(2)))) ||
                (!(romanNumbers.contains(example.get(2))) && !(arabicNumbers.contains(example.get(0))))){
            throw new IllegalArgumentException("выражение должно состоять либо только из арабских цифр в диапозоне (1,2,3,4,5,6,7,8,9,10)," +
                    "\nлибо только из римских в диапазоне(I,II,III,IV,V,VI,VII,VIII,IX,X) и одним из математических операторов(+,-,*,/)");
        }
    }

    public static void checkIsExampleCorrectMathExpression(String example) {
        if (!example.contains("+") &&
                !example.contains("-") &&
                !example.contains("*") &&
                !example.contains("/")){
            throw new IllegalArgumentException("неправильное математическое выражение");
        }
    }

}
