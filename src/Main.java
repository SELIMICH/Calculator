import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String example = scanner.nextLine();
            if (example.equalsIgnoreCase("q")){
                break;
            }
            System.out.println(Calculator.calc(example.trim()));
        }
    }
}