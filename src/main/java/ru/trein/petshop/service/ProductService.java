package ru.trein.petshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trein.petshop.mapper.ProductMapper;
import ru.trein.petshop.model.Product;
import ru.trein.petshop.util.Util;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private Util util;

    @Transactional(readOnly = true)
    public List<Product> selectAllProduct() {
        return productMapper.selectAllProduct();
    }

    @Transactional
    public List<Product> create(Object data) {
        List<Product> newProducts = new ArrayList<>();

        List<Product> list = util.getProductsFromRequest(data);

        for (Product product : list){
            productMapper.insertProduct(product);
            newProducts.add(product);
        }

        return newProducts;
    }

    @Transactional
    public List<Product> update(Object data) {
        List<Product> newProducts = new ArrayList<>();

        List<Product> productList = util.getProductsFromRequest(data);

        for (Product product : productList) {
            productMapper.updateProduct(product);
            newProducts.add(product);
        }

        return newProducts;
    }

    @Transactional
    public void delete(Object data){
        List<Integer> articles = util.getArticleListFromJson(data);

        for (Integer article : articles) {
            productMapper.deleteProduct(article);
        }
    }
}
