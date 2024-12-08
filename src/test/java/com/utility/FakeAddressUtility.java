package com.utility;
import com.github.javafaker.Faker;
import com.ui.pojos.AddressPojo;

import java.util.Locale;

public class FakeAddressUtility {

    public static void main(String[] args){

        getFakeAddress();
    }
    public static AddressPojo getFakeAddress(){

        Faker faker = new Faker(Locale.US);

        AddressPojo addressPojo = new AddressPojo(faker.company().name(),faker.address().buildingNumber(),
                faker.address().streetAddress(),faker.address().city(),faker.numerify("17401"),
                faker.phoneNumber().cellPhone(),faker.phoneNumber().cellPhone(),
                "Its my address","Home Address",faker.address().state());

        return addressPojo;
    }
}
