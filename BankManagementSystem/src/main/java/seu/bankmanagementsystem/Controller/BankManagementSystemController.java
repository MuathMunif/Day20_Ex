package seu.bankmanagementsystem.Controller;

import org.springframework.web.bind.annotation.*;
import seu.bankmanagementsystem.Api.ApiReturn;
import seu.bankmanagementsystem.Model.Customers;

import java.util.ArrayList;

@RestController
public class BankManagementSystemController {

    ArrayList<Customers> customersList = new ArrayList<>();


    @GetMapping("get")
    public ArrayList<Customers> getCustomers() {
        return customersList;
    }


    @PostMapping("/add")
    public ApiReturn addCustomer(@RequestBody Customers customers) {
        customersList.add(customers);
        return new ApiReturn("Successfully added customer.");
    }


    @PutMapping("/update/{id}")
    public ApiReturn updateCustomer(@PathVariable int id, @RequestBody Customers customers) {
        for (int i = 0; i < customersList.size(); i++) {
            if (customersList.get(i).getId() == id) {
                customersList.set(i, customers);
                return new ApiReturn("Successfully updated customer.");
            }
        }
        return new ApiReturn("Customer not found.");
    }


    @DeleteMapping("delete/{id}")
    public ApiReturn deleteCustomer(@PathVariable int id) {
        for (int i = 0; i < customersList.size(); i++) {
            if (customersList.get(i).getId() == id) {
                customersList.remove(i);
                return new ApiReturn("Successfully deleted customer.");
            }
        }
        return new ApiReturn("Customer not found.");
    }


    @PutMapping("deposit/{id}/{amount}")
    public ApiReturn depositCustomer(@PathVariable int id, @PathVariable double amount) {
        for (Customers customer : customersList) {
            if (customer.getId() == id) {
                if (amount <= 0) {
                    return new ApiReturn("Invalid amount.");
                }
                customer.setBalance(customer.getBalance() + amount);
                return new ApiReturn("Successfully deposited.");
            }
        }
        return new ApiReturn("Customer not found.");
    }


    @PutMapping("/withdraw/{id}/{amount}")
    public ApiReturn withdrawCustomer(@PathVariable int id, @PathVariable double amount) {
        for (Customers customer : customersList) {
            if (customer.getId() == id) {
                if (amount <= 0) {
                    return new ApiReturn("Invalid amount.");
                }
                if (customer.getBalance() >= amount) {
                    customer.setBalance(customer.getBalance() - amount);
                    return new ApiReturn("Successfully withdrawn.");
                }
            }
        }
        return new ApiReturn("You don't have enough money to withdraw.");
    }

}
