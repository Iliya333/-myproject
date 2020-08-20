import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Parsingstrings{

    private String str;
    private String regexArab;
    private String regexRome;
    private Map<String, Integer> toInt;
    private char [] operations;

    public Parsingstrings (String str) {
        this.str = str.trim();
        this.regexArab = "[0-9]{1,2}\\s*[\\+\\-\\*/]{1}\\s*[0-9]{1,2}";
        this.regexRome = "[IVX]{1,4}\\s*[\\+\\-\\*/]{1}\\s*[IVX]{1,4}";
        toInt = new HashMap<>();
        toInt.put("I", 1);
        toInt.put("II", 2);
        toInt.put("III", 3);
        toInt.put("IV", 4);
        toInt.put("V", 5);
        toInt.put("VI", 6);
        toInt.put("VII", 7);
        toInt.put("VIII", 8);
        toInt.put("IX", 9);
        toInt.put("X", 10);
        operations = new char[]{'+', '-', '*', '/'};
    }

        public Deystviya getResult() {
        int a=0, b=0;
        String operation = "";
        String [] arr;
        String strTmp = "";
        String numberType = "";

        if (Pattern.matches(regexArab, str)) {
            for (char tmp: operations) {
                if (str.indexOf(tmp) != -1) {
                    numberType = "arab";
                    operation = String.valueOf(tmp);
                    strTmp = str.replace(tmp, ';');
                    arr = strTmp.split(";");
                    if (arr.length == 2) {
                        a = Integer.parseInt(arr[0].trim());
                        b = Integer.parseInt(arr[1].trim());

                    } else {
                        System.err.println("Выражение не правильное");
                        return null;
                    }
                    break;
                }
            }
        } else if (Pattern.matches(regexRome, str)) {
            for (char tmp: operations) {
                if (str.indexOf(tmp) != -1) {
                    numberType = "rome";
                    operation = String.valueOf(tmp);
                    strTmp = str.replace(tmp, ';');
                    arr = strTmp.split(";");
                    if (arr.length == 2) {
                        if (toInt.containsKey(arr[0].trim()) && toInt.containsKey(arr[1].trim())) {
                            a = toInt.get(arr[0].trim());
                            b = toInt.get(arr[1].trim());
                        } else {
                            System.err.println("Не правильные римские числа");
                            return null;
                        }
                    } else {
                        System.err.println("Выражение не правильное");
                        return null;
                    }
                    break;
                }
            }
        } else {
            System.err.println("Выражение введено не правильно!");
        }
        if ((a>10) || (b>10)) {
            System.err.println("Числа должны быть не больше 10 или Х!!!");
            System.exit(0);
        }
        Deystviya result = new Deystviya(a, b, operation, numberType);
        return result;
    }
}