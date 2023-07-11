import java.util.Scanner;

class EmployeeManagement {

    public static final String DOMAIN_NAME = "@work.net";

    public static String createEmail(String name, String surname) {
        StringBuilder email = new StringBuilder();

        email.append(name).append(surname).append(DOMAIN_NAME);

        return email.toString();
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        String surname = scanner.next();

        String completeEmail = createEmail(name, surname);

        System.out.println(completeEmail);
    }
}