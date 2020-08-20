public class Deystviya {

        private int a;
        private int b;
        private int outputArabic;
        private String outputRoman;
        private String operation;
        private String numberType;

        public Deystviya (int a, int b, String operation, String numberType) {
            this.a = a;
            this.b = b;
            this.operation = operation;
            if (!numberType.equals("arab") && !numberType.equals("rome")) {
                System.err.println("Не соответствие типов чисел!");
                System.exit(0);
            }
            this.numberType = numberType;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public String getOperation() {
            return operation;
        }


        public int getOutputArabic() {
            switch (operation) {
                case "+":
                    outputArabic = (int) (a + b);
                    break;
                case "-":
                    outputArabic = (int) (a - b);
                    break;
                case "*":
                    outputArabic = (int) (a * b);
                    break;
                case "/":
                    outputArabic = (int) (a / b);
                    break;
                default:
                    outputArabic = 0;
                    break;
            }
            return outputArabic;
        }


        public String getOutputRoman() {
            outputRoman = RomanNumbers.arabicToRoman(getOutputArabic());
            return outputRoman;
        }


        public String getOutput() {
            if (numberType.equals("arab")) {
                return String.valueOf(getOutputArabic());
            } else if (numberType.equals("rome")) {
                return getOutputRoman();
            }
            return null;
        }
    }