package service;


import dao.ProductDao;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> findAllProduct() {
        List<Product> list = productDao.findAllProduct();
        return list;
    }

    public void add(Product product) {
        if (product.getProductNum() != null && product.getProductNum() != " ") {
            List<Product> allProduct = findAllProduct();
            boolean b = true;
            for (Product product1 : allProduct) {
                if (product.getProductNum().equals(product1.getProductNum())) {
                    b = false;
                }
            }
            if (b) {
                product.setId(UUID.randomUUID().toString());
                productDao.add(product);
            }
        }
    }

    public Product findById(String id) {
        return productDao.findById(id);
    }

    public void updateById(Product product) {
        productDao.updateById(product);
    }

    public void deleteById(String[] ids) {
        for (String id:ids) {
            productDao.deleteById(id);
        }
    }
}
