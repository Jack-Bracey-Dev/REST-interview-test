package com.jackbracey.resttestexercise.Services;

import com.jackbracey.resttestexercise.POJOs.Customer;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVService {

    public CSVService() {
    }

    public List<Customer> convertCsvToCustomers(MultipartFile file) {
        List<Customer> customers = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        String line;
        boolean handledHeaders = false;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Customer customer = new Customer();

                if (!handledHeaders) {
                    headers.addAll(Arrays.stream(data).toList());
                    handledHeaders = true;
                    continue;
                }

                for (int i = 0; i < data.length; i++) {
                    String header = headers.get(i);
                    String value = data[i];

                    switch (header.toLowerCase()) {
                        case "id" -> {
                            if (Strings.isBlank(value))
                                break;
                            try {
                                customer.setId(Integer.parseInt(value));
                            } catch (NumberFormatException e) {
                                System.out.printf("Failed to convert id {%s}%n", value);
                            }
                        }
                        case "name" -> customer.setName(value);
                        case "addresslineone" -> customer.setAddressLineOne(value);
                        case "addresslinetwo" -> customer.setAddressLineTwo(value);
                        case "town" -> customer.setTown(value);
                        case "county" -> customer.setCounty(value);
                        case "country" -> customer.setCountry(value);
                        case "postcode" -> customer.setPostcode(value);
                        default -> System.out.printf("Failed to find csv header: %s%n", header);
                    }
                }

                customers.add(customer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return customers;
    }

}
