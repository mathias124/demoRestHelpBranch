package com.example.demoRest;

import java.util.List;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController
{
    @Autowired
    private IProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getProduct()
    {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/products")
    public ResponseEntity<String > addProduct(@RequestBody Product p) {
        boolean added = productService.addProduct(p);
        if(added)
            return ResponseEntity.ok().body("added");
        else
            return ResponseEntity.internalServerError().body("not added");

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product p = productService.getProductById(id);
        return ResponseEntity.ok().body(p);
    }

    @GetMapping("/api/users")
    public ResponseEntity<String> kapa()
    {
        User user = new User("Henrik", 23);


        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        return ResponseEntity.ok().body(jsonString);
    }

    @PostMapping("/api/users")
    public ResponseEntity<String>  getUser(@RequestBody String userJson)
    {
        Gson gson = new Gson();
        User user = gson.fromJson(userJson, User.class);
        System.out.println(user.name);
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping("/api/some-resource")
    public ResponseEntity<String > printString(@RequestBody String p) {
            return ResponseEntity.ok().body("added");
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product p) {
        boolean added = productService.updateProduct(id, p);
        return ResponseEntity.ok().body("updated");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean deleted = productService.deleteProductById(id);
        return ResponseEntity.ok().body("deleted");
    }

}