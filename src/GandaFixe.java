import java.time.LocalDate;

public class GandaFixe {
    public static void main(String[] args) {
        LocalDate data = LocalDate.now().minusYears(3);
        Sapatilha test2 = new Sapatilha(42, true,"Branco", data.plusYears(1), true, false, Sapatilha.Estado.BOM, 1, 450);
        Sapatilha test = new Sapatilha(46, true,"Branco", data, false, false, Sapatilha.Estado.BOM, 3, 112);
        Sapatilha test3 = new Sapatilha(42, true,"Branco", data.plusYears(1), true, false, Sapatilha.Estado.BOM, 1, 450);
        System.out.println(test2.toString());
        System.out.println(test.toString());

        System.out.println(test2.equals(test3));
        System.out.println(test.equals(test2));
    }
}
