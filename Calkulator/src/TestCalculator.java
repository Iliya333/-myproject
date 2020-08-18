import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCalculator {

        static boolean onlyFrom0to10=true;  //нстройка ограничения вводимого числа только от 0 до 10 При желании можно убрать ограничения
        static boolean only2digit=true;  //нстройка ограничения кол-ва вводимых чисел только 2-мя ри желании можно убрать ограничения

        enum RomArab { Arab(1),Rom(2),NoElse(0),Err(-1); //флаг сост. и типа вычислений: арабскими/ римскими /ни то ни се(ошибка)
            private int val;
            RomArab(int v) { this.val=v;       }
        }
        public static RomArab tipRA;

        enum Action{
            Plus,Minus,Div,Mult,Eqv,Err;
        }
        public static Action action=null;


        public static void main(String[] args) throws IOException {
                      System.out.println("Введи выр. вида 1+9 или  римск. вида VII+IIL .");
            Scanner sc=new Scanner(System.in);
            try {
                Pattern pattern=Pattern.compile("(?:([0-9]+)|([IVXLDMC]+))(\\s*[*/+-]\\s*)?+"); // шаблон
                while (sc.hasNext()) {
                    int cntDig=0;
                    String inputs=sc.nextLine();
                    Matcher matcher=pattern.matcher(inputs);
                    Deystviya exp=new Deystviya();
                    tipRA=RomArab.NoElse;
                    while (matcher.find()) {
                        int ival=-1;
                        if (matcher.start(1)>=0) {  //обнаружена гр. арабских
                            String si=matcher.group(1);
                            if (tipRA == RomArab.Rom) {// Выражение с 1 арабской 2 римская ошибка
                                throw new NoValidate ("НЕ РИМСКАЯ ЦИФРА:"+si);
                            }
                            tipRA=RomArab.Arab;
                            cntDig++;
                            ival= Parsingstrings.toInt(si);
                            if (onlyFrom0to10 && (ival< 0 || ival>10)) throw new NoValidate ("Только от 0 до 10ти, а ввели:"+si);// ошибка по 10> для арабских цифр
                            if (only2digit && (cntDig>2)) throw new NoValidate ("Только 2 числа или присвойте, а ввели:"+cntDig);// ошибка по больше 2х чисел для арабских цифр

                        }
                        if (matcher.start(2)>=0) { //обнаружена гр. римских цифр
                            String si=matcher.group(2);
                              if (tipRA == RomArab.Arab) {
                                throw new NoValidate ("НЕ АРАБСКАЯ ЦИФРА:"+si);
                            }
                            tipRA=RomArab.Rom;
                            ival= Parsingstrings.toInt(si);
                            if (onlyFrom0to10 && (ival < 0 || ival>10)) throw new NoValidate ("Только от I до X, а ввели:"+si);// ошибка по 10> для римских цифр
                            if (only2digit && (cntDig>2)) throw new NoValidate ("Только 2 числа или присвойте, а ввели:"+cntDig);// ошибка по больше 2х чисел для римских цифр
                        }
                        if (matcher.start(3)>=0) {
                            String sz = matcher.group(3).trim();
                            action = Parsingstrings.toAction(sz);
                        } else action=Action.Eqv;
                        exp.calc(ival,action);

                    }
                    if (tipRA==RomArab.Arab) {
                        System.out.println(exp.resultArabic());// выыод  выражения за араб
                    } else {
                        System.out.println( "Римский результат = " + exp.resultRom());// выыод  выражения за рим
                    }


                }
            } catch (Exception | NoValidate  e) {
                System.out.println("Что-то пошло не так:"+e.getMessage());
            }
            sc.close();

        }
    }

