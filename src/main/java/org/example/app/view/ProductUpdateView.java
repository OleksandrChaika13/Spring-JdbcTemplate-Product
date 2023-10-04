package org.example.app.view;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProductUpdateView {

    public Map<String, String> getData() {
        Map<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String title = "Input new product name: ";
        System.out.print(title);
        map.put("name", scanner.nextLine().trim());
        title = "Input quota: ";
        System.out.print(title);
        map.put("quota", String.valueOf(scanner.nextInt()));
        title = "Input price: ";
        System.out.print(title);
        map.put("price", String.valueOf(scanner.nextDouble()));
        scanner.nextLine();
        title = "Input product`s id: ";
        System.out.print(title);
        map.put("id", scanner.nextLine().trim());
        return map;
    }

    public void getOutput(String output) {
        System.out.println(output);
    }
}