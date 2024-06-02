import java.util.*;

public class PlantShopInputController {

    Scanner scan = new Scanner(System.in);
    ShopInventory inventory;
    ShoppingCart cart;

// Diese Liste beinhaltet alle Funktionen des Shops
    private ArrayList<String> optionsList = new ArrayList<String>(Arrays.asList(
            "\nWhat do you want to do?\n", "(1) add product: add a new product to the shop.", "(2) restock product: restock an existing product.", "(3) remove product: remove a product from the shop", "(4) view products: view available products.", "(5) add product to cart.", "(6) remove product from cart", "(7) view cart", "(8) check out", "(9) show Bestseller", "(10) Type exit to exit the Program"));

 // Diese Liste beinhaltet alle Komanndos, welche die Funktionen des Shops aufrufen
    private ArrayList<String> commandList = new ArrayList<String>(Arrays.asList("add product", "restock product", "remove product", "view products", "add product to cart", "remove product from cart", "view cart", "check out", "show bestseller", "exit"));

// Konstruktor für den PlantShopInputController
    public PlantShopInputController(ShopInventory inventory, ShoppingCart cart) {
        this.inventory = inventory;
        this.cart = cart;
    }

// Diese Methode wird beim Start des Shops aufgerufen. Sie Listet das aktuelle Inventar sowie die Funktionen des Shops auf. Danach wird je nach input des Nutzers (case) die entsprechende Methode aufgerufen.
    public void startShop() {
        System.out.println("Plant Shop started.");
        String input = "";

        inventory.printInventory();

        printOptionsList();

        while (true) {
            input = scan.nextLine().toLowerCase();

            if (commandList.contains(input)) {
                switch (commandList.indexOf(input)) {
                    case 0:
                        createProduct();
                        break;
                    case 1:
                        restockProduct();
                        break;
                    case 2:
                        removeProduct();
                        break;
                    case 3:
                        displayInventory();
                        break;
                    case 4:
                        addProductToCart();
                        break;
                    case 5:
                        removeProductFromCart();
                        break;
                    case 6:
                        viewCart();
                        break;
                    case 7:
                        checkOutCart();
                        break;
                    case 8:
                        showBestseller();
                        break;
                    case 9:
                        System.exit(0);
                        break;
                }


            } else {
                System.out.println("Unknown command. Pleas use one of the following commands:");
                printOptionsList();
            }

        }


// ------------ Methoden für die Inputverarbeitung --------------------


    }

    //  (1) Input add product: Hinzufügen eines Produktes zum Inventar
    private void createProduct() {
        System.out.println("Please enter name of Product");
        String name = scan.nextLine();
        System.out.println("Please enter type of " + name);
        String type = scan.nextLine();
        System.out.println("Please enter price of " + name);
        int price = Integer.parseInt(scan.nextLine());
        System.out.println("Please enter amount of " + name);
        int amount = Integer.parseInt(scan.nextLine());
        Product product = new Product(name, type, price);
        inventory.addProduct(product, amount);
        System.out.println("The product " + name + " has been added to the Shop.");
    }

    // (2) Input restock product: Restock eines Produktes im Inventar
    private void restockProduct() {
        System.out.println("Please enter the Name of the Product you want to restock:");
        String productName = scan.nextLine();
        if (productCheck(productName)) {
            System.out.println("Please enter the amount you want to restock:");
            int amount = Integer.parseInt(scan.nextLine());
            inventory.addStock(productName, amount);
        }
    }

    // (3) Input remove product: entfernen eines Produktes aus dem Inventar
    private void removeProduct() {
        System.out.println("Please enter the Name of the Product you want to remove:");
        String productName = scan.nextLine();
        if (productCheck(productName)) {
            inventory.deleteProduct(productName);
        }
    }

    // (4) Input view products: Anzeigen aller verfügbaren Produkte
    private void displayInventory() {
        inventory.printInventory();
    }

    // (5) Input add product to cart: hinzufügen eines Produktes zum Warenkorb
    private void addProductToCart(){
        System.out.println("Please enter the Name of the Product you want to add to the cart:");
        String productName = scan.nextLine();
        if (productCheck(productName)) {
            System.out.println("Please enter the amount of " + productName + " you want to the cart:");
            int amount = Integer.parseInt(scan.nextLine());
                cart.addProduct(productName, amount);
        }
    }

// (6) Input remove product from cart: entfernen eines Produktes aus dem Warenkorb

private void removeProductFromCart(){
        System.out.println("Please enter the Name of the Product you want to remove from the cart:");
        String productName = scan.nextLine();

        if (productCheck(productName)) {
            System.out.println("Enter the amount you want to remove from the cart:");
            int amount = Integer.parseInt(scan.nextLine());
            cart.deleteProduct(productName, amount);
        }
}

// (7) Input view cart: Anzeigen des aktuellen Warenkorbs

    private void viewCart(){
        cart.printCart();
    }


// (8) Input Check out: check out

    private void checkOutCart(){
        cart.processCheckOut();
    }

 // (9) Input show bestseller: Anzeigen des meistverkauften Produktes

    private void showBestseller(){
        inventory.transferBestseller();
    }

 // Helfermethoden

    private void printOptionsList() {
        for (String option : optionsList) {
            System.out.println(option);
        }
    }

    private boolean productCheck(String productName) {
        if (!inventory.doesProductExist(productName)) {
            System.out.println("That product does not exist.");
            return false;
        }
        return true;
    }
}
