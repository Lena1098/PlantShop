import java.util.HashMap;

public class ShopInventory {
    public ShopAccounting accounting;
    private HashMap<Product, Integer> stock = new HashMap<>();

    public ShopInventory(ShopAccounting accounting) {
        this.accounting = accounting;
    }

    public void addProduct(Product product, int amount) {
        stock.put(product, amount);
    }

    public void addStock(String productName, int addedAmount) {
        for (Product product : stock.keySet()) {
            if (product.getName().equals(productName)) {
                stock.put(product, stock.get(product) + addedAmount);
                System.out.println("The new stock amount for " + productName + " is " + stock.get(product));
                return;
            }
        }
        System.out.println("Could not restock stock for " + productName);
    }

    public void printInventory() {
        System.out.println("The following products are available:");
        for (Product product : getStock().keySet()) {
            System.out.println(product.getName() + " (x" + stock.get(product) + " available) unit price: " + product.getPrice());
        }
    }

    public void deleteProduct(String productName) {
        for (Product product : getStock().keySet()) {
            if (product.getName().equals(productName)) {
                getStock().remove(product);
                return;
            }
        }
    }

    public int getPriceForProduct(String productName) {
        for (Product product : getStock().keySet()) {
            if (product.getName().equals(productName)) {
                return product.getPrice();
            }
        }
        return 0;
    }


    public void processCheckOut(HashMap<String, Integer> cart) {                    // ich gebe dir eine HM cart
        accounting.processSales(cart);                                              // gib den cart ans accounting weiter
        for (String productName : cart.keySet()) {                                  // für jeden key in der HM cart, der ein String namens productName ist...
            for (Product product : getStock().keySet()) {                           //... gehe durch jedes Objekt in der HM stock, das ich hier product nenne
                if (product.getName().equals(productName)) {                        // ...wenn der Wert der Variable name von product dem key aus dem cart entspricht...
                    stock.put(product, stock.get(product) - cart.get(productName)); // ...subtrahiere den zugehörigen value aus dem cart dem value aus dem stock
                }
            }
        }
    }

    public void transferBestseller() {
        accounting.printBestseller();
    }

    // ----------------Helfermethoden------------------
    public boolean doesProductExist(String productName) {
        for (Product product : getStock().keySet()) {
            if (product.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isProductAmountInStock(String productName, int amount) {
        for (Product product : getStock().keySet()) {
            if (product.getName().equals(productName)) {
                return getStock().get(product) >= amount;
            }
        }
        return false;
    }

    // getter und setter
    public HashMap<Product, Integer> getStock() {
        return stock;
    }

    public void setStock(HashMap<Product, Integer> stock) {
        this.stock = stock;
    }
}
