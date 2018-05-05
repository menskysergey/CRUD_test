package ru.trein.petshop.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import ru.trein.petshop.model.Product;

import java.util.ArrayList;
import java.util.List;


public class Util {
    public List<Product> getProductsFromRequest(Object data){

        List<Product> list;

        if (data.toString().indexOf('[') > -1){
            list = getListProductFROMJSON(data);
        } else {
            Product product = getProductFromJSON(data);

            list = new ArrayList<>();
            list.add(product);
        }

        return list;
    }

    private Product getProductFromJSON(Object data) {
        JSONObject jsonObject = JSONObject.fromObject(data);
        return (Product) JSONObject.toBean(jsonObject, Product.class);
    }

    @SuppressWarnings("unchecked")
    private List<Product> getListProductFROMJSON(Object data) {
        JSONArray jsonArray = JSONArray.fromObject(data);
        return (List<Product>) JSONArray.toCollection(jsonArray, Product.class);
    }

    @SuppressWarnings("unchecked")
    public List<Integer> getArticleListFromJson(Object data){
        JSONArray jsonArray = JSONArray.fromObject(data);
        return (List<Integer>) JSONArray.toCollection(jsonArray,Integer.class);
    }
}
