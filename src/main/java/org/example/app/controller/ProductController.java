package org.example.app.controller;

import org.example.app.entity.Product;
import org.example.app.service.ProductService;
import org.example.app.utils.Constants;
import org.example.app.utils.AppStarter;
import org.example.app.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductController {

    @Autowired
    ProductMenuView menuView;
    @Autowired
    ProductCreateView createView;
    @Autowired
    ProductReadView readView;
    @Autowired
    ProductReadByIdView readByIdView;
    @Autowired
    ProductUpdateView updateView;
    @Autowired
    ProductDeleteView deleteView;
    @Autowired
    ProductService service;


    public void getOption() {
        int option = Integer.parseInt(menuView.getOption());
        switch (option) {
            case 1 -> create();
            case 2 -> getAll();
            case 3 -> getById();
            case 4 -> update();
            case 5 -> delete();
            case 0 -> menuView.getOutput(Constants.APP_CLOSE_MSG);
        }
    }

    public void create() {
        String[] data = createView.getData();
        String name = data[0];
        String quotaInput = data[1];
        String priceInput = data[2];

        try {
            Integer quota = Integer.parseInt(quotaInput);
            Double price = Double.parseDouble(priceInput);
            Product product = new Product(name, quota, price);
            createView.getOutput(service.create(product));
        } catch (NumberFormatException e) {
            createView.getOutput(Constants.INCORRECT_VALUE_MSG);
        }


        AppStarter.startApp();
    }

    public void getAll() {
        readView.getOutput(service.getAll());
        AppStarter.startApp();
    }

    public void getById() {
        readByIdView.getOutput(service
                .getById(readByIdView.getData()));
        AppStarter.startApp();
    }

    public void update() {
        Map<String, String> data = updateView.getData();
        Integer id = Integer.parseInt(data.get("id"));
        String name = data.get("name");
        Integer quota = Integer.parseInt(data.get("quota"));
        Double price = Double.parseDouble(data.get("price"));
        Product product = new Product(id, name, quota, price);
        updateView.getOutput(service.update(product));
        AppStarter.startApp();
    }

    public void delete() {
        deleteView.getOutput(service
                .delete(deleteView.getData()));
        AppStarter.startApp();
    }
}