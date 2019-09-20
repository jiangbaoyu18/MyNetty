package com.jby.thrift7;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class ThriftClient {
    public static void main(String[] args) throws Exception{
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);//与server端一致
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        transport.open();
        Person person = client.getPersonByName("zs");
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
        System.out.println("----------");
        Person person1 = new Person();
        person1.setMarried(false);
        person1.setAge(22);
        person1.setName("ls");
        client.setPerson(person1);
        try {

        }catch (Exception e){
            System.out.println("exception");

        }finally {
            transport.close();
        }
    }
}
