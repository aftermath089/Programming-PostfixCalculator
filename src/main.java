/**
 * Created by aftermath089 on 12/05/2017.
 */
import java.io.IOException;
import java.util.Scanner;
public class main{
    public static void main(String[] args)  throws IOException {
        Scanner scan= new Scanner(System.in);
        int x=1;
        System.out.print("KALKULATOR");
        while(x==1) {
            System.out.print("\npersamaan anda: ");
            String persamaan = scan.nextLine();

            penghitung kalkulator = new penghitung();
            kalkulator.setToken(persamaan);

        }
    }
}
