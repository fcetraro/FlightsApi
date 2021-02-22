package com.ml.FlightsApi.stubs;

import com.ml.FlightsApi.model.PeopleDTO;

public class PeopleStub {
    public static PeopleDTO getPeople(){
        PeopleDTO people = new PeopleDTO();
        people.setName("Federico");
        people.setDni("123456789");
        people.setLastname("Cetraro");
        people.setMail("facetraro@gmail.com");
        people.setBirthDate("27/05/1995");
        return people;
    }
}
