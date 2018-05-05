package ru.trein.petshop.mapper;

import ru.trein.petshop.model.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> selectAllProduct();
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Integer article);

}
