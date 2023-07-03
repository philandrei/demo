package com.example.demo.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final List<Customer> customerList = new ArrayList<>();
    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
        customer.setAge("90");
        System.out.println(customer);
        customerList.add(customer);
        return ResponseEntity.ok(customerList.get(0));
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerList;
    }
}

