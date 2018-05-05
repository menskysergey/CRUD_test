package ru.trein.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.trein.petshop.model.Product;
import ru.trein.petshop.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/view.action")
    @ResponseBody
    public Map<String, Object> read() {
        try {
            List<Product> products = productService.selectAllProduct();
            return getMap(products);
        } catch (Exception e) {
            return getModelMapError("Ошибка загрузки Продуктов из базы данных.");
        }
    }

    @RequestMapping(value = "/product/create.action")
    @ResponseBody
    public Map<String, Object> create(@RequestBody Map data) {
        try {

            List<Product> contacts = productService.create(data.get("data"));

            return getMap(contacts);

        } catch (DuplicateKeyException e) {
            return getModelMapError("Продукт с таким артикулом уже существует.");
        } catch (Exception e) {
            return getModelMapError("Ошибка при добавление продукта.");
        }
    }

    @RequestMapping(value = "/product/update.action")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Map data) {
        try {

            List<Product> products = productService.update(data.get("data"));

            return getMap(products);
        } catch (DuplicateKeyException e) {
            return getModelMapError("Продукт с таким артикулом уже существует.");
        } catch (Exception e) {
            return getModelMapError("Ошибка при обновление продуктов.");
        }
    }

    @RequestMapping(value = "/product/delete.action")
    @ResponseBody
    public Map<String, Object> delete(@RequestBody Map data) {
        try {
            productService.delete(data.get("data"));

            Map<String, Object> modelMap = new HashMap<>(1);
            modelMap.put("success", true);

            return modelMap;

        } catch (Exception e) {
            return getModelMapError("Ошибка при удаление продуктов.");
        }
    }


    private Map<String, Object> getMap(List<Product> products) {

        Map<String, Object> modelMap = new HashMap<>(3);
        modelMap.put("total", products.size());
        modelMap.put("data", products);
        modelMap.put("success", true);

        return modelMap;
    }

    private Map<String, Object> getModelMapError(String msg) {

        Map<String, Object> modelMap = new HashMap<>(2);
        modelMap.put("message", msg);
        modelMap.put("success", false);

        return modelMap;
    }

}
