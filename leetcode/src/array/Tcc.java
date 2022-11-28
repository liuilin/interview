package array;

/**
 * @author Daniel Liu
 * @date 2022-10-13
 */
public class Tcc {
    public static void main(String[] args) {
//        System.out.println( Math.sqrt(7));
        System.out.println(isPrime(27));
    }
    public static boolean isPrime(final int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
