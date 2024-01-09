//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

class Calc {

    public static void main (String [] args) throws Exception {
        Scanner scanner = new Scanner (System.in);
        System.out.println("Input:");
        String input = scanner.nextLine();
        System.out.println("Output:");
        System.out.println(calc(input));
    }

    public static String calc (String input) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String [] operands = input.split ("[+\\-*/]");
        if (operands.length !=2) throw new Exception("Должно быть два операнда");
        oper = detectOperation (input);
        if (oper==null) throw new Exception("Неподдерживаемая математическая операция");

        // если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            // конвертируем оба числа в арабские для вычисления
            Roman roman1 = Roman.valueOf(operands[0]);
            Roman roman2 = Roman.valueOf(operands[1]);

            num1 = roman1.getConversion();
            num2 = roman2.getConversion();
            isRoman = true;
        }

        //если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }

        // если одно число римское, а другое арабское
        else {
            throw new Exception("Используются разные системы счисления");
        }

        if (num1<1 || num1>10 || num2<1 || num2>10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int arabian = calc (num1, num2, oper);

        // конвертируем арабское число в тип String
        if (isRoman) {
            // если римское число получилось меньше или равно нулю, генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            // конвертируем результат операции из арабского в римское
            result = String.valueOf(Roman.getRoman(arabian));
        }
        else result = String.valueOf(arabian);

        //возвращаем результат
        return result;
    }

    // определяем математический оператор
    static String detectOperation (String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    // выполняем математическую операцию
    static int calc(int a, int b, String oper) {
        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
        }

    enum Roman {
        I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10), XI(11), XII(12),
        XIII(13), XIV(14), XV(15), XVI(16), XVII(17), XVIII(18), XIX(19), XX(20), XXI(21), XXIV(24),
        XXV(25), XXVII(27), XXVIII(28), XXX(30), XXXII(32), XXXV(35), XXXVI(36), XL(40), XLII(42),
        XLV(45), XLVIII(48), XLIX(49), L(50), LIV(54), LVI(56), LX(60), LXIII(63), LXIV(64),
        LXX(70), LXXII(72), LXXX(80), LXXXI(81), XC(90), C(100);

        private final int conversion;

        Roman (int conversion) {
            this.conversion = conversion;
        }
        public int getConversion() {
            return conversion;
        }

        // получаем римский эвивалент арабского числа
        public static Roman getRoman (int conversion) {
            for (Roman roman: Roman.values()) {
                if (roman.conversion == conversion) return roman;
            }
            return null;
        }

        // проверяем, относится ли число к римской системе
        public static boolean isRoman(String val) {
            for (Roman roman: Roman.values()) {
                if (val.equals(roman.toString())) return true;
            }
            return false;
        }
    }
    }


