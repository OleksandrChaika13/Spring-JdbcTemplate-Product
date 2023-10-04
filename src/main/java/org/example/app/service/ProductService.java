package org.example.app.service;

import org.example.app.entity.Product;
import org.example.app.exceptions.ProductDataException;
import org.example.app.repository.ProductRepositoryImpl;
import org.example.app.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ProductService {

    @Autowired
    Product product;
    @Autowired
    ProductRepositoryImpl repoImpl;

    Map<String, String> errors = new HashMap<>();

    public String create(Product product) {
        validateData(product);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repoImpl.create(product)) {
            return Constants.DATA_INSERT_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    public String getAll() {
        Optional<List<Product>> optional = repoImpl.getAll();
        if (optional.isPresent()) {
            AtomicInteger count = new AtomicInteger(0);
            StringBuilder stringBuilder = new StringBuilder();
            List<Product> list = optional.get();
            list.forEach((contact) ->
                    stringBuilder.append(count.incrementAndGet())
                            .append(") ")
                            .append(contact.toString())
            );
            return stringBuilder.toString();
        } else return Constants.DATA_ABSENT_MSG;
    }

    public String getById(String id) {
        validateId(id);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        Optional<Product> optional = repoImpl.getById(Integer.parseInt(id));
        if (optional.isEmpty()) {
            return Constants.DATA_ABSENT_MSG;
        } else {
            Product product = optional.get();
            return product.toString();
        }
    }

    public String update(Product product) {
        validateData(product);
        validateId(String.valueOf(product.getId()));
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs",
                        errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        if (repoImpl.update(product)) {
            return Constants.DATA_UPDATE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    public String delete(String id) {
        validateId(id);
        if (!errors.isEmpty()) {
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e) {
                return e.getErrors(errors);
            }
        }

        product.setId(Integer.parseInt(id));
        if (repoImpl.delete(product)) {
            return Constants.DATA_DELETE_MSG;
        } else {
            return Constants.SMTH_WRONG_MSG;
        }
    }

    private void validateData(Product product) {
        errors.clear();

        if (product.getName().isEmpty()) {
            errors.put("name", Constants.INPUT_REQ_MSG);
        }
        if (!isInteger(String.valueOf(product.getQuota()))) {
            errors.put("quota", Constants.QUOTA_ERROR_MSG);
        }
        if (!isDouble(String.valueOf(product.getPrice()))) {
            errors.put("price", Constants.PRICE_ERROR_MSG);
        }
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void validateId(String id) {
        if (IdValidator.isIdValid(id))
            errors.put("id", Constants.ID_ERR_MSG);
    }
}