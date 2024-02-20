package com.jackbracey.resttestexercise.Controllers.CustomerControllerTests;

import com.jackbracey.resttestexercise.Controllers.CustomerController;
import com.jackbracey.resttestexercise.Entities.CustomerEntity;
import com.jackbracey.resttestexercise.POJOs.Response;
import com.jackbracey.resttestexercise.Services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static com.jackbracey.resttestexercise.Controllers.CustomerControllerTests.CustomerControllerTestHelper.getCustomerOne;
import static com.jackbracey.resttestexercise.Controllers.CustomerControllerTests.CustomerControllerTestHelper.getCustomerTwo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerControllerGetRequestTests {

    private static CustomerController customerController;

    private static CustomerService customerService;

    @BeforeAll
    static void beforeAll() {
        customerService = mock(CustomerService.class);
        customerController = new CustomerController(customerService);
    }

    @Test
    void getSingleCustomer() {
        CustomerEntity customer = getCustomerOne();
        when(customerService.findCustomersById(any())).thenReturn(Collections.singletonList(customer));
        Response response = customerController.getCustomers("1");

        CustomerEntity responseObject = ((List<CustomerEntity>) response.getObject()).get(0);

        Assertions.assertEquals(response.getCode(), 200);
        Assertions.assertEquals(responseObject, customer);
    }

    @Test
    void getTwoCustomers() {
        CustomerEntity customerOne = getCustomerOne();
        CustomerEntity customerTwo = getCustomerTwo();
        when(customerService.findCustomersById(any())).thenReturn(List.of(customerOne, customerTwo));
        Response response = customerController.getCustomers("1,2");

        List<CustomerEntity> responseEntities = (List<CustomerEntity>) response.getObject();
        CustomerEntity responseObjectOne = responseEntities.get(0);
        CustomerEntity responseObjectTwo = responseEntities.get(1);

        Assertions.assertEquals(response.getCode(), 200);
        Assertions.assertEquals(responseObjectOne, customerOne);
        Assertions.assertEquals(responseObjectTwo, customerTwo);
    }

    @Test
    void malformedRequestMissingIds() {
        Response response = customerController.getCustomers("");

        Assertions.assertEquals(response.getCode(), 400);
        Assertions.assertEquals(response.getMessage(), "Missing comma separated 'ids' request param");
    }

    @Test
    void malformedRequestAsciiInIdsParam() {
        Response response = customerController.getCustomers("abc,123,daw");

        Assertions.assertEquals(response.getCode(), 400);
        Assertions.assertEquals(response.getMessage(), "'ids' request param must be comma separated integers");
    }

}
