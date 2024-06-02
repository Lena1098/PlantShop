import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShopAccounting {
    private HashMap<String, Integer> sales = new HashMap<String, Integer>();
    public void processSales(HashMap<String, Integer> cart){                            // ich geb dir die Hashmap cart, die aus Strings und Integern besteht
        for(String productName: cart.keySet()){                                         // Loope durch jeden key in cart, der ein String ist und den ich in dieser Methode productName nenne
            if(sales.containsKey(productName)){                                         // Falls sales einen der keys enthält ...
                sales.put(productName, sales.get(productName) + cart.get(productName)); // ... addiere für den String dem value in sales den value aus cart
            }else{                                                                      // Falls sales den key nicht enthält ...
                    sales.put(productName, cart.get(productName));                      // Füge der Hashmap sales einen neuen Eintrag mit dem key und value aus cart hinzu
                }
            }
//        System.out.println("updated sales: " + getSales());                           //  nur für Tests
    }
    public void printBestseller(){

        String bestseller = Collections.max(sales.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("The current Bestseller is: " + bestseller + "(" + sales.get(bestseller) + " products sold)");
    }
    public HashMap<String, Integer> getSales() {
        return sales;
    }

    public void setSales(HashMap<String, Integer> sales) {
        this.sales = sales;
    }
}
