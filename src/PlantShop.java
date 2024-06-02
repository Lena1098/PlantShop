public class PlantShop {
    public static void main(String[] args) {

// Herstellen der Verbindungen zwischen Klassen

        ShopAccounting accounting = new ShopAccounting();
        ShopInventory inventory = new ShopInventory(accounting);
        ShoppingCart cart = new ShoppingCart(inventory);
        PlantShopInputController controller = new PlantShopInputController(inventory, cart);

 // Für schnelleres testen sind bereits zum Start des Programms Produkte im Inventory:

        inventory.addProduct(new Product("Begonia", "plant", 12), 10);
        inventory.addProduct(new Product("Philodendron", "plant", 8), 10);
        inventory.addProduct(new Product("cactus", "plant", 8), 10);

        controller.startShop();

    }
}
