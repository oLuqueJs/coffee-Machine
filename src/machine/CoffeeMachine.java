package machine;
import java.util.Scanner;

public class CoffeeMachine {

    static Scanner sc = new Scanner(System.in);

    /*    Coffee Machine Reservoir   */
    static int moneyRes = 550;
    static int waterRes = 400;
    static int milkRes = 540;
    static int coffeeRes = 120;
    static int cupsRes = 9;

    /*    Espresso   */
    static final int esWaterReq = 250;
    static final int esCoffeeReq = 16;
    static final int esCost = 4;

    /*    Latte   */
    static final int laWaterReq = 350;
    static final int laMilkReq = 75;
    static final int laCoffeeReq = 20;
    static final int laCost = 7;

    /*    Cappuccino   */
    static final int caWaterReq = 200;
    static final int caMilkReq = 100;
    static final int caCoffeeReq = 12;
    static final int caCost = 6;

    public static void main(String[] args) {
        String choice;
        showMachineRes();
        do {
            showMenu();
            choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                case "buy":
                    buyAction();
                    break;
                case "2":
                case "fill":
                    fillAction();
                    break;
                case "3":
                case "take":
                    takeAction();
                    break;
                case "4":
                case "exit":
                    System.out.println("Goodbye!");
                    break;
                default:
                    break;
            }

        } while (!choice.equals("4") && !choice.equalsIgnoreCase("exit"));
        sc.close();
    }

    public static void showMenu() {
        System.out.println("======================");
        System.out.println("         MENU         ");
        System.out.println("      1 -   Buy       ");
        System.out.println("      2 -   Fill      ");
        System.out.println("      3 -   Take      ");
        System.out.println("      4 -   Exit      ");
        System.out.println("======================");
        System.out.println("Write action (buy, fill, take, exit):");
    }

    public static void buyAction() {
        System.out.println("=========================");
        System.out.println("         COFFEE          ");
        System.out.println("      1 -   Espresso     ");
        System.out.println("      2 -   Latte        ");
        System.out.println("      3 -   Cappuccino    ");
        System.out.println("      4 -   Return       ");
        System.out.println("=========================");
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");

        int coffeeChoice = sc.nextInt();

        switch(coffeeChoice) {
            case 1: // Espresso
                if (validateChoice(1)) {
                    waterRes -= esWaterReq;
                    coffeeRes -= esCoffeeReq;
                    cupsRes -= 1;
                    moneyRes += esCost;
                    System.out.println("I have enough resources, making you a coffee!");
                    showMachineRes();
                }
                break;
            case 2: // Latte
                if (validateChoice(2)) {
                    waterRes -= laWaterReq;
                    milkRes -= laMilkReq;
                    coffeeRes -= laCoffeeReq;
                    cupsRes -= 1;
                    moneyRes += laCost;
                    System.out.println("I have enough resources, making you a coffee!");
                    showMachineRes();
                }
                break;
            case 3: // Capuccino
                if (validateChoice(3)) {
                    waterRes -= caWaterReq;
                    milkRes -= caMilkReq;
                    coffeeRes -= caCoffeeReq;
                    cupsRes -= 1;
                    moneyRes += caCost;
                    System.out.println("I have enough resources, making you a coffee!");
                    showMachineRes();
                }
                break;
            case 4:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid selection!");
                break;
        }
    }

    public static void fillAction() {
        System.out.println("Write how many ml of water you want to add:");
        int fillWater = sc.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        int fillMilk = sc.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        int fillCoffee = sc.nextInt();

        System.out.println("Write how many disposable cups you want to add:");
        int fillCups = sc.nextInt();

        waterRes += fillWater;
        milkRes += fillMilk;
        coffeeRes += fillCoffee;
        cupsRes += fillCups;
        showMachineRes();
    }

    public static void takeAction() {
        if (moneyRes <= 0) {
            System.out.println("Sorry, there is no cash available to take.");
            return;
        }
        System.out.printf("I gave you $%d%n", moneyRes);
        moneyRes = 0;
        showMachineRes();
    }

    public static void showMachineRes() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water%n", waterRes);
        System.out.printf("%d ml of milk%n", milkRes);
        System.out.printf("%d g of coffee beans%n", coffeeRes);
        System.out.printf("%d disposable cups%n", cupsRes);
        System.out.printf("$%d of money%n", moneyRes);
        System.out.println();
    }

    public static boolean validateChoice(int coffeeId) {
        if (cupsRes < 1) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }

        switch (coffeeId) {
            case 1: // Espresso
                if (waterRes < esWaterReq) {
                    System.out.println("Sorry, not enough water!");
                    return false;
                }
                if (coffeeRes < esCoffeeReq) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return false;
                }
                return true;

            case 2: // Latte
                if (waterRes < laWaterReq) {
                    System.out.println("Sorry, not enough water!");
                    return false;
                }
                if (milkRes < laMilkReq) {
                    System.out.println("Sorry, not enough milk!");
                    return false;
                }
                if (coffeeRes < laCoffeeReq) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return false;
                }
                return true;

            case 3: // Cappuccino
                if (waterRes < caWaterReq) {
                    System.out.println("Sorry, not enough water!");
                    return false;
                }
                if (milkRes < caMilkReq) {
                    System.out.println("Sorry, not enough milk!");
                    return false;
                }
                if (coffeeRes < caCoffeeReq) {
                    System.out.println("Sorry, not enough coffee beans!");
                    return false;
                }
                return true;

            default:
                System.out.println("Invalid selection!");
                return false;
        }
    }
}