public class Deystviya {
    private  Integer iRez;
    private  TestCalculator.Action action;



    public  int calc(int ival, TestCalculator.Action operation){
        if (iRez == null){
            iRez = ival;
        } else {    //если уже было , делаем ранее запомненное действие, а потом запоминаем новое действие
            switch (action) {
                case Plus:
                    iRez+=ival;
                    break;
                case Minus:
                    iRez-=ival;
                    break;
                case Mult:
                    iRez =iRez*ival;
                    break;
                case Div:
                    iRez= iRez/ival;
            }

        }
        action = operation;
        return iRez;
    }

    public  Integer getiRez() {
        return iRez;
    }
}
