package eu.olaf.example.model.test;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "T_PERSON")
public class Person {
//    @Id
//    @GeneratedValue
//    private Long id;

    @EmbeddedId
    private PersonId id;

    private String name;
    private String nationalNumber;

    public PersonId getId() { return id; }
    public void setId(PersonId id) { this.id = id; }
    public Person withId(PersonId id) { setId(id); return this; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Person withName(String name) { setName(name); return this; }

    public String getNationalNumber() { return nationalNumber; }
    public void setNationalNumber(String nationalNumber) { this.nationalNumber = nationalNumber; }

    public Person withNationalNumber(String nationalNumber) {setNationalNumber(nationalNumber); return this;}
    public static Person make() { return new Person(); }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationalNumber='" + nationalNumber + '\'' +
                '}';
    }
}
