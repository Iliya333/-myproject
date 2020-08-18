import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Deystviya {
     private Integer iRez;
    private TestCalculator.Action action;

    public int calc(int ival, TestCalculator.Action operation) {
        if (iRez == null) {
            iRez = ival;
        } else {    //если уже было , делаем ранее запомненное действие, а потом запоминаем новое действие
            switch (action) {
                case Plus:
                    iRez += ival;
                    break;
                case Minus:
                    iRez -= ival;
                    break;
                case Mult:
                    iRez = iRez * ival;
                    break;
                case Div:
                    iRez = iRez / ival;
            }

        }
        action = operation;
        return iRez;
    }

    public Integer resultArabic() {
        return iRez;
    }

    public Integer resultRom() {
                return iRez;
       }

    }
