import java.security.SecureRandom;
import java.util.Random;

public class RandomGenerator {
    public String alfanumericalGenerator() {

        String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int LENGTH = 12;


        StringBuilder sb = new StringBuilder(LENGTH);
        Random random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());

        for (int i = 0; i < LENGTH; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }

        return sb.toString();
    }
}
