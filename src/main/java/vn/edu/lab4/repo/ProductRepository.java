package vn.edu.lab4.repo;

import vn.edu.lab4.model.Product;
import java.util.*;

public class ProductRepository {
    private static final List<Product> LIST = List.of(
        new Product(1, "T-Shirt", 120000, "/img/tee.png"),
        new Product(2, "Jeans", 320000, "/img/jeans.png"),
        new Product(3, "Cap", 90000, "/img/cap.png")
    );
    public List<Product> findAll(){ return LIST; }
    public Product findById(int id){
        return LIST.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
