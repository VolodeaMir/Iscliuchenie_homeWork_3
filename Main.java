import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите данные (Фамилия Имя Отчество, дата рождения, номер телефона, пол):");
            String dataString = scanner.nextLine();
            UserData userData = new UserData(dataString);
            userData.saveToFile();
            System.out.println("Данные успешно сохранены в файл.");
        } catch (InvalidDataFormatException e) {
            System.out.println("Ошибка в формате данных: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
