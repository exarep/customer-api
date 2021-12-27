package com.exarep.customer;

import com.exarep.customer.individual.CustomerIndividual;
import org.apache.commons.lang3.RandomStringUtils;

public class MockData {

    public static CustomerIndividual createCustomerIndividual() {
        CustomerIndividual customerIndividual = new CustomerIndividual();
        customerIndividual.setFirstName(randomString());
        customerIndividual.setLastName(randomString());
        return customerIndividual;
    }

    public static String randomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

}
