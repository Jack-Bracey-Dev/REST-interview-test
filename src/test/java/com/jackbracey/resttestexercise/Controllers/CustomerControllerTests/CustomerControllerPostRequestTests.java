package com.jackbracey.resttestexercise.Controllers.CustomerControllerTests;

import com.jackbracey.resttestexercise.Controllers.CustomerController;
import com.jackbracey.resttestexercise.Entities.CustomerEntity;
import com.jackbracey.resttestexercise.POJOs.Response;
import com.jackbracey.resttestexercise.Services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.util.List;

import static com.jackbracey.resttestexercise.Controllers.CustomerControllerTests.CustomerControllerTestHelper.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerControllerPostRequestTests {

    private static CustomerController customerController;

    private static CustomerService customerService;

    private static MockMultipartFile basicDataFile, changedLayoutDataFile;

    @BeforeAll
    static void beforeAll() {
        customerService = mock(CustomerService.class);
        customerController = new CustomerController(customerService);

        File basic = new File("src/test/resources/basic-data.csv");
        File changedLayout = new File("src/test/resources/changed-layout-data.csv");

        basicDataFile = new MockMultipartFile("basic-data.csv", getContentFromFile(basic));
        changedLayoutDataFile = new MockMultipartFile("changed-layout-data.csv", getContentFromFile(changedLayout));
    }

    @Test
    void shouldCreateCustomersFromBasicFile() {
        CustomerEntity customerOne = getCustomerOne();
        customerOne.setId(1);
        CustomerEntity customerTwo = getCustomerTwo();
        customerTwo.setId(2);
        when(customerService.saveAll(anyList())).thenReturn(List.of(customerOne, customerTwo));

        Response response = customerController.createCustomersFromFile(basicDataFile);
        List<CustomerEntity> responseEntities = (List<CustomerEntity>) response.getObject();
        CustomerEntity responseObjectOne = responseEntities.get(0);
        CustomerEntity responseObjectTwo = responseEntities.get(1);

        Assertions.assertEquals(response.getCode(), 200);
        Assertions.assertEquals(responseObjectOne, customerOne);
        Assertions.assertEquals(responseObjectTwo, customerTwo);
    }

    @Test
    void shouldCreateCustomersFromChangedLayoutFile() {
        CustomerEntity customerOne = getCustomerOne();
        customerOne.setId(1);
        CustomerEntity customerTwo = getCustomerTwo();
        customerTwo.setId(2);
        when(customerService.saveAll(anyList())).thenReturn(List.of(customerOne, customerTwo));

        Response response = customerController.createCustomersFromFile(changedLayoutDataFile);
        List<CustomerEntity> responseEntities = (List<CustomerEntity>) response.getObject();
        CustomerEntity responseObjectOne = responseEntities.get(0);
        CustomerEntity responseObjectTwo = responseEntities.get(1);

        Assertions.assertEquals(response.getCode(), 200);
        Assertions.assertEquals(responseObjectOne, customerOne);
        Assertions.assertEquals(responseObjectTwo, customerTwo);
    }

    @Test
    void shouldStillCreateCustomerDespiteIncorrectColumn() {
        CustomerEntity customerOne = getCustomerOne();
        customerOne.setId(1);
        CustomerEntity customerTwo = getCustomerTwo();
        customerTwo.setId(2);
        when(customerService.saveAll(anyList())).thenReturn(List.of(customerOne, customerTwo));

        Response response = customerController.createCustomersFromFile(changedLayoutDataFile);
        List<CustomerEntity> responseEntities = (List<CustomerEntity>) response.getObject();

        Assertions.assertEquals(response.getCode(), 200);
        Assertions.assertEquals(responseEntities.size(), 2);
    }

}
