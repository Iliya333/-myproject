import java.util.Scanner;

public class TestCalculator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введи выр. вида 1+9 или  римск. вида VII+II:");
        String str = scan.nextLine();
        System.out.println("Рузультат: ");
        Parsingstrings parser = new Parsingstrings(str);
        System.out.println(parser.getResult().getOutput());
    }
}
