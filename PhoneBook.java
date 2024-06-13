package Java_att;

import java.util.*;

public class PhoneBook {
    private static Map<String, Set<String>> phoneBook = new HashMap<>();
    //phoneBook.add("Ivan", "123");
    private static Scanner scanner = new Scanner(System.in);

    
    public static final String ANSI_GREEN = "\u001B[33m";
    public static final String ANSI_BACK = "\u001B[3m";
    public static final String ANSI_reset = "\u001B[0m";
    

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            head();
            //System.out.println("Телефонная книга:\n");
            System.out.println("1. ПРОСМОТР КНИГИ");
            System.out.println("2. ДОБАВИТЬ КОНТАКТ");
            System.out.println("3. УДАЛИТЬ КОНТАКТ");
            System.out.println("4. ИЗМЕНИТЬ КОНТАКТ");
            System.out.println("5. ВЫХОД");
            System.out.print("\nВАШ ВЫБОР: ");
            if(scanner.hasNextInt()){
                int choice = scanner.nextInt();
            
                scanner.nextLine();
                clearConsole();
             //System.out.println("choice = "+ choice);

                switch (choice) {
                    case 1:
                        showItems();
                        break;
                    case 2:
                        addItem();
                        break;
                    case 3:
                        deleteItem();
                        break;
                    case 4:
                        editItem();
                        break;
                    case 5:
                        run = false;
                        break;
                    default:
                        System.out.println("\nНЕВЕРНЫЙ ВВОД! \n");
                }
            }
            else{
                System.out.println("\nВведите число! \n");
                scanner.nextLine();
                pressEnter();
            }
        }
        System.out.println("\nРАБОТА ЗАВЕРШЕНА.\n" + ANSI_reset);
    }

    private static void showItems() {
        if (phoneBook.isEmpty()) {
            System.out.println("\nНЕТ ЗАПИСЕЙ!\n");
        } else {
            List<Map.Entry<String, Set<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());
            sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
            head();
            System.out.println("ОТСОРТИРОВАНО ПО УБЫВАНИЮ КОЛ-ВА ТЕЛЕФОНОВ:\n");
            for (Map.Entry<String, Set<String>> entry : sortedEntries) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
        pressEnter();
    }

    private static void addItem() {
        head();
        System.out.print("ДОБАВЛЕНИЕ КОНТАКТА\n\n");
        System.out.print("ИМЯ: ");
        String name = scanner.nextLine();
        System.out.print("НОМЕР ТЕЛЕФОНА: ");
        String phoneNumber = scanner.nextLine();
        phoneBook.computeIfAbsent(name, k -> new HashSet<>()).add(phoneNumber);
        System.out.println("\nКОНТАКТ СОХРАНЕН: `" + name + ": " + phoneNumber + "`");
        pressEnter();
    }
    private static void deleteItem() {
        head();
        System.out.print("УДАЛЕНИЕ КОНТАКТА\n\n");
        System.out.print("ИМЯ: ");
        String name = scanner.nextLine();
        if (phoneBook.containsKey(name)) {
            Set<String> phoneNumbers = phoneBook.remove(name);
            System.out.println("УДАЛЕНО: `" + name + ": " + phoneNumbers + "`");
        } else {
            System.out.println("КОНТАКТ НЕ НАЙДЕН.");
        }
        pressEnter();
    }

    private static void editItem() {
        head();
        System.out.print("РЕДАКТИРОВАНИЕ КОНТАКТА\n\n");
        System.out.print("ИМЯ: ");
        String name = scanner.nextLine();
        if (phoneBook.containsKey(name)) {
            System.out.print("НОВЫЙ НОМЕР ТЕЛЕФОНА: ");
            String newPhoneNumber = scanner.nextLine();
            phoneBook.get(name).add(newPhoneNumber);
            System.out.println("ГОТОВО: `" + name + ": " + phoneBook.get(name) + "`");
        } else {
            System.out.println("КОНТАКТ НЕ НАЙДЕН.");
        }
        pressEnter();
    }

    private static void pressEnter() {
        System.out.print("\n`ENTER` ДЛЯ ПРОДОЛЖЕНИЯ...");
        scanner.nextLine();
        clearConsole();
    }
    private static void head() {
        System.out.println(ANSI_BACK + ANSI_GREEN + "\n----ТЕЛЕФОННАЯ КНИГА----");
    }
    public static void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}

    

