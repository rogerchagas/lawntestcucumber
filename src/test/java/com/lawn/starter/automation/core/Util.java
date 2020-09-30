package com.lawn.starter.automation.core;

import io.cucumber.datatable.DataTable;

import java.util.*;

public class Util {

    public Map<String, List<String>> putDataTableIntoMap (DataTable dTable) {
        Map <String, List <String>> inputsMap = new HashMap<>();

        // Grab everything out of the dataTable
        List <List <String>> inputsList = dTable.asLists();
        int intDataTableSize            = inputsList.get(0).size();

        // And save it out to use
        for (int i = 0; i<inputsList.size(); i++) {
            inputsMap.put(inputsList.get(i).get(0), new ArrayList<>(inputsList.get(i).subList(1, intDataTableSize)));

            // Get rid of any blanks entries that were inputed via dataTable
            inputsMap.get(inputsList.get(i).get(0)).removeIf(x->x.isEmpty());
        }

        return inputsMap;
    }

    public User getPopulatedUserFromDataTable(DataTable dataTable){
        Map<String, List<String>> registration = new Util().putDataTableIntoMap(dataTable);
        User user = new User();
        user.setFirstName(registration.get("First Name").get(0));
        user.setLastName(registration.get("Last Name").get(0));
        user.setAddress(registration.get("Address").get(0));
        user.setPhone(registration.get("Phone").get(0));
        user.setCity(registration.get("City").get(0));
        user.setZipCode(registration.get("Zip").get(0));
        user.setState(registration.get("State").get(0));
        user.setCountry(registration.get("Country").get(0));
        user.setEmail(registration.get("Email").get(0));

        return user;
    }

    public String getRandomEmail () {
        String[] rand        = UUID.randomUUID().toString().split("-");
        String strNewEmail   =  rand[1] + rand[2] + "@phimail.mailinator.com";
        return strNewEmail;
    }
}
