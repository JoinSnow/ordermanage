package service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public PageInfo<Product> findAllProduct(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> allProduct = productDao.findAllProduct();
        return new PageInfo<>(allProduct);
    }

    public PageInfo<Product> findAllProductOrderBy(int pageNum, int pageSize,String orderBy) {
        PageHelper.startPage(pageNum, pageSize,orderBy);
        List<Product> allProduct = productDao.findAllProduct();
        return new PageInfo<>(allProduct);
    }

    public PageInfo<Product> findByProductName(int pageNum, int pageSize, String productName) {
        PageHelper.startPage(pageNum, pageSize);
        productName = "%" + productName + "%";
        List<Product> allProduct = productDao.findByProductName(productName);
        return new PageInfo<>(allProduct);
    }

    public void add(Product product) {
        if (product.getProductNum() != null && product.getProductNum() != " ") {
            PageInfo<Product> allProduct = findAllProduct(1, 5);
            boolean b = true;
            for (Product product1 : allProduct.getList()) {
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
        for (String id : ids) {
            productDao.deleteById(id);
        }
    }

    public void openStatusById(String[] ids) {
        Product product = new Product();
        for (String id : ids) {
            product.setId(id);
            product.setProductStatus(1);
            productDao.updateStatus(product);
        }
    }

    public void closeStatusById(String[] ids) {
        Product product = new Product();
        for (String id : ids) {
            product.setId(id);
            product.setProductStatus(0);
            productDao.updateStatus(product);
        }
    }

}
