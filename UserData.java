import java.io.FileWriter;
import java.io.IOException;

public class UserData {
    private String surname;
    private String name;
    private String patronymic;
    private String birthDate;
    private String phoneNumber;
    private char gender;

    public UserData(String dataString) throws InvalidDataFormatException {
        String[] data = dataString.split(" ");
        System.out.println(data.length);
        for (String string : data) {
            System.out.println(string);
        }
        if (data.length != 6) {
            throw new InvalidDataFormatException("Invalid number of data elements.");
        }

        surname = data[0];
        name = data[1];
        patronymic = data[2];
        birthDate = data[3];
        phoneNumber = data[4];

        if (!isValidBirthDate(birthDate)) {
            throw new InvalidDataFormatException("Invalid birth date format. Expected dd.mm.yyyy.");
        }

        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new InvalidDataFormatException("Invalid phone number format. Expected an unsigned integer.");
        }

        if (data[5].length() != 1 || (!data[5].equalsIgnoreCase("f") && !data[5].equalsIgnoreCase("m"))) {
            throw new InvalidDataFormatException("Invalid gender format. Expected 'f' or 'm'.");
        }
        gender = data[5].charAt(0);
    }

    private boolean isValidBirthDate(String date) {
        // Дополнительная проверка формата даты может быть реализована здесь
        // В данном примере, проверка формата просто осуществляется на количество
        // символов.
        return date.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }

    public String getFullName() {
        return surname + " " + name + " " + patronymic;
    }

    public String toString() {
        return getFullName() + ", " + birthDate + ", " + phoneNumber + ", " + gender;
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter(surname + ".txt", true)) {
            writer.write(toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
