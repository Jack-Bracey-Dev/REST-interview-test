package com.jackbracey.resttestexercise.Controllers.CustomerControllerTests;

import com.jackbracey.resttestexercise.Entities.CustomerEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CustomerControllerTestHelper {

    public static CustomerEntity getCustomerOne() {
        return new CustomerEntity(
                1,
                "Tom Jones",
                "London Road",
                "Line two of London road",
                "Camberley",
                "Surrey",
                "England",
                "GU153RS");
    }

    public static CustomerEntity getCustomerTwo() {
        return new CustomerEntity(2,
                "Gordon Ramsay",
                "59 Park Lane",
                "Line Two",
                "Torquaytown",
                "Torquay",
                "England",
                "TQ69WZ");
    }

    public static byte[] getContentFromFile(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.printf("Failed to read content from file: %s%n", file.getPath());
            return null;
        }
    }

}
