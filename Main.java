import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Notebook {
    private String brand;
    private int ram;
    private int storageCapacity;
    private String operatingSystem;
    private String color;

    public Notebook(String brand, int ram, int storageCapacity, String operatingSystem, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storageCapacity = storageCapacity;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }


    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("RAM: " + ram + " GB");
        System.out.println("Storage Capacity: " + storageCapacity + " GB");
        System.out.println("Operating System: " + operatingSystem);
        System.out.println("Color: " + color);
        System.out.println("----------------------------");
    }
}

public class Main {
    public static void main(String[] args) {
        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook("HP", 8, 512, "Windows 10", "Silver"));
        notebooks.add(new Notebook("Dell", 16, 256, "Ubuntu", "Black"));
        notebooks.add(new Notebook("Lenovo", 12, 1024, "Windows 11", "Gray"));
        notebooks.add(new Notebook("Lenovo", 12, 1024, "Windows 11", "Black"));
        notebooks.add(new Notebook("Apple", 8, 512, "MacOS", "Silver"));

        // Запрашиваем у пользователя критерии фильтрации
        Map<String, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите критерии фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Считываем символ новой строки после ввода числа

        switch (choice) {
            case 1:
                System.out.print("Введите минимальное значение ОЗУ (в GB): ");
                int minRam = scanner.nextInt();
                filters.put("ram", minRam);
                break;
            case 2:
                System.out.print("Введите минимальный объем ЖД (в GB): ");
                int minStorage = scanner.nextInt();
                filters.put("storageCapacity", minStorage);
                break;
            case 3:
                System.out.print("Введите операционную систему: ");
                String os = scanner.nextLine();
                filters.put("operatingSystem", os);
                break;
            case 4:
                System.out.print("Введите цвет: ");
                String color = scanner.nextLine();
                filters.put("color", color);
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        List<Notebook> filteredNotebooks = filterNotebooks(notebooks, filters);

        // Выводим результаты фильтрации
        System.out.println("Результаты фильтрации:");
        for (Notebook notebook : filteredNotebooks) {
            notebook.displayInfo();
        }
    }

    // Метод для фильтрации ноутбуков
    private static List<Notebook> filterNotebooks(List<Notebook> notebooks, Map<String, Object> filters) {
        List<Notebook> filteredNotebooks = new ArrayList<>();

        for (Notebook notebook : notebooks) {
            boolean meetsCriteria = true;

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();

                // Проверка условий фильтрации для каждого критерия
                switch (key) {
                    case "ram":
                        meetsCriteria = meetsCriteria && (notebook.getRam() >= (int) entry.getValue());
                        break;
                    case "storageCapacity":
                        meetsCriteria = meetsCriteria && (notebook.getStorageCapacity() >= (int) entry.getValue());
                        break;
                    case "operatingSystem":
                        meetsCriteria = meetsCriteria && notebook.getOperatingSystem().equalsIgnoreCase((String) entry.getValue());
                        break;
                    case "color":
                        meetsCriteria = meetsCriteria && notebook.getColor().equalsIgnoreCase((String) entry.getValue());
                        break;
                    default:
                        System.out.println("Неподдерживаемый критерий фильтрации.");
                        return filteredNotebooks;
                }
            }

            if (meetsCriteria) {
                filteredNotebooks.add(notebook);
            }
        }

        return filteredNotebooks;
    }
}
