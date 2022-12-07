package practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();
        final String PHONE_TEMPLATE = "7[0-9]{10}";
        final String NAME_TEMPLATE = "[А-ЯЁа-яё]+";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер, имя или команду LIST");

        while (true) {
            String input = scanner.nextLine().trim();
            String contactSaved = "Контакт сохранен";
            String noNameInBook = "Такого имени в телефонной книге нет. \nВведите номер телефона для абонента ";
            String noPhoneInBook = "Такого номера нет в телефонной книге. \nВведите имя абонента для номера ";

            boolean inputIsName = input.matches(NAME_TEMPLATE);
            boolean inputIsNumber = input.matches(PHONE_TEMPLATE);
            boolean inputIsBreak = input.equalsIgnoreCase("break");
            boolean inputIsList = input.equalsIgnoreCase("list");

            if (inputIsBreak) {
                break;
            }

            if (inputIsList) {
                System.out.println(phoneBook.getAllContacts());
            }

            if (!inputIsName && !inputIsNumber && !inputIsList) {
                System.out.println("Неверный формат ввода");
                continue;
            }

            if (inputIsName) {
                if (!phoneBook.book.containsValue(input)) {
                    System.out.println(noNameInBook + input + ":");
                    String number = scanner.nextLine().trim();

                    if (number.matches(PHONE_TEMPLATE)) {
                        phoneBook.addContact(number, input);
                        System.out.println(contactSaved);
                    } else {
                        System.out.println("Номер введен неверно. Введите имя контакта заново.");
                    }
                } else {
                    System.out.println(phoneBook.getContactByName(input));
                }
            }

            if (inputIsNumber) {
                if (!phoneBook.book.containsKey(input)) {
                    System.out.println(noPhoneInBook + input + ":");
                    String name = scanner.nextLine().trim();

                    if (name.matches(NAME_TEMPLATE)) {
                        phoneBook.addContact(input, name);
                        System.out.println(contactSaved);
                    } else {
                        System.out.println("Имя введено неверно. Введите имя контакта заново.");
                    }
                } else {
                    System.out.println(phoneBook.getContactByPhone(input));
                }
            }
        }
    }
}
