package com.jackbracey.resttestexercise.Controllers;

import com.jackbracey.resttestexercise.Entities.CustomerEntity;
import com.jackbracey.resttestexercise.POJOs.Customer;
import com.jackbracey.resttestexercise.POJOs.Response;
import com.jackbracey.resttestexercise.Services.CSVService;
import com.jackbracey.resttestexercise.Services.CustomerService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@SuppressWarnings("unused")
public class CustomerController {

    private final CustomerService customerService;

    private final CSVService csvService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
        this.csvService = new CSVService();
    }

    @GetMapping
    public Response getCustomers(@RequestParam(name = "ids", required = false) String commaSeparatedIds) {
        /* Setting required to false, so I can handle the check myself and give a useful response for malformed
           requests */

        if (Strings.isBlank(commaSeparatedIds))
            return new Response(null, 400, "Missing comma separated 'ids' request param");

        List<Integer> ids;
        try {
            ids = Arrays.stream(commaSeparatedIds.split(","))
                    .map(Integer::parseInt)
                    .toList();
        } catch (Exception e) {
            return new Response(null, 400, "'ids' request param must be comma separated integers");
        }

        List<CustomerEntity> customers = customerService.findCustomersById(ids);

        return Response.Success(customers);
    }

    @PostMapping
    public Response createCustomersFromFile(@RequestParam(value = "file") MultipartFile file) {
        List<Customer> customers = csvService.convertCsvToCustomers(file);
        List<CustomerEntity> customerEntities = Customer.convertAllToEntity(customers);
        return Response.Success(customerService.saveAll(customerEntities));
    }

}
