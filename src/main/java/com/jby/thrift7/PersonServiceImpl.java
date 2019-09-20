package com.jby.thrift7;

import org.apache.thrift.TException;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByName(String name) throws TException {
        System.out.println("get client param:"+name);
        Person person = new Person();
        person.setName(name);
        person.setAge(12);
        person.setMarried(true);
        return person;
    }

    @Override
    public void setPerson(Person person) throws TException {
        System.out.println("got person");
        System.out.println(person.getAge());
        System.out.println(person.getName());
        System.out.println(person.isMarried());

    }
}
