import java.util.HashMap;

public class ShoppingCart {
    private HashMap<String, Integer> cart = new HashMap<>();
    public ShopInventory inventory;

    public ShoppingCart(ShopInventory inventory) {
        this.inventory = inventory;
    }

    public void addProduct(String productName, int amount) {
        if(inventory.isProductAmountInStock(productName, amount)){
            if (cart.containsKey(productName)) {
                cart.put(productName, cart.get(productName) + amount);
            } else {
                cart.put(productName, amount);
            }
            System.out.println(productName + " x" + amount + " added to cart.");
        }else{
            System.out.println("insufficient stock");
        }
    }

    public void deleteProduct(String productName, int amount) {
        if (cart.containsKey(productName)) {
            if (cart.get(productName) >= amount) {
                cart.put(productName, cart.get(productName) - amount);
            } else {
                cart.remove(productName);
            }
            System.out.println(productName + amount + " x" + amount + "removed from cart");
        } else {
            System.out.println("There is no " + productName + " in the cart");
        }

    }

    public float calculateTotal() {
        float total = 0;
        for (String productName : cart.keySet()) {
            total += cart.get(productName) * inventory.getPriceForProduct(productName);
        }
        return total;
    }

    public float calculateSubtotal(String productName) {
        float totalPrice = 0;
        if (cart.containsKey(productName)) {
            totalPrice = cart.get(productName) * inventory.getPriceForProduct(productName);
        }
        return totalPrice;
    }

    public void printCart() {
        if (cart.isEmpty()) {
            System.out.println("cart is empty");
        }else{
            for (String productName : cart.keySet()) {
                System.out.println(productName + ": " + "x" + cart.get(productName) + " unit price: " + inventory.getPriceForProduct(productName) + " subtotal: " + calculateSubtotal(productName));
                }
        System.out.println("Total price: " + calculateTotal());
        }
    }

    public void processCheckOut(){
        inventory.processCheckOut(cart);
        cart.clear();
        System.out.println("check out successful");
        }

// getter / setter

    public HashMap<String, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, Integer> cart) {
        this.cart = cart;
    }
}


