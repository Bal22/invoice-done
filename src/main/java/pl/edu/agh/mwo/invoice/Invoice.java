package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.DairyProduct;
import pl.edu.agh.mwo.invoice.product.Product;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

public class Invoice {
   // private List<Product> products = new ArrayList<>();
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        this.addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quanity must be above 0");
        }
        this.products.put(product, quantity);
    }

    public BigDecimal getSubtotal() {
        BigDecimal sum = BigDecimal.ZERO;
        //for (Product product : this.products) {

       // }
        for (Product product : this.products.keySet()) {
            Integer quantity = this.products.get(product);
            sum = sum.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }
        return sum;
    }

    public BigDecimal getTax() {
        BigDecimal tax = BigDecimal.ZERO;
        for (Product product : this.products.keySet()) {
            Integer quantity = this.products.get(product);
            tax = tax.add(product.getPrice().multiply(product.getTaxPercent()).multiply(BigDecimal.valueOf(quantity)));
        }
        return tax;
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet()) {
            Integer quantity = this.products.get(product);
            sum = sum.add(product.getPriceWithTax().multiply(BigDecimal.valueOf(quantity)));
        }
        return sum;
    }
}
