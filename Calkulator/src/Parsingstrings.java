import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Parsingstrings {
    private static String s;

    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    public Parsingstrings(String si) {
        s=si;
    }

    public static int toInt(String si) throws NoValidate {
        if (TestCalculator.tipRA == TestCalculator.RomArab.Arab) {
            return Integer.parseInt(si);
        } else if (TestCalculator.tipRA == TestCalculator.RomArab.Rom){
            return romanToArabic(si);
        } else throw new  NoValidate ("НЕ УСТАНОВЛЕН ТИП ЧИСЛА:"+si);
    }

    public static TestCalculator.Action toAction(String si){
        switch (si){
            case "+":
                return TestCalculator.Action.Plus;
            case "-":
                return TestCalculator.Action.Minus;
            case "/":
                return TestCalculator.Action.Div;
            case "*":
                return TestCalculator.Action.Mult;
            default:
        }
        return TestCalculator.Action.Err;
    }


    private static int romanToArabic(String input) throws NoValidate {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new  NoValidate ("НЕ распознала, как РИМСКИЕ:"+input);
        }

        return result;
    }


}


