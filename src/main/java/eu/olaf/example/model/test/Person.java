package eu.olaf.example.model.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Random;

@Entity(name = "T_EX_PERSON")
@IdClass(CompositeId.class)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id = 0L;

    @Id
    @ManyToOne
    @JsonIgnore
//    @Column(name = "CASE_ID")
    private Case cas;

//    @EmbeddedId
//    @GenericGenerator(name = "sequence_comp_id", strategy = "eu.olaf.example.model.test.CompositeIdGenerator")
//    @GeneratedValue(generator = "sequence_comp_id")
//    private CompositeId compositeId;
//
    private String name;
    private String nationalNumber;

//    public Person() {
//        //compositeId = new CompositeId();
//    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Person withId(Long id) { setId(id); return this; }

//    public CompositeId getCompositeId() { return compositeId; }
//    public void setCompositeId(CompositeId compositeId) { this.compositeId = compositeId; }
//    public Person withCompositeId(CompositeId compositeId) { setCompositeId(compositeId); return this; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Person withName(String name) { setName(name); return this; }

    public String getNationalNumber() { return nationalNumber; }
    public void setNationalNumber(String nationalNumber) { this.nationalNumber = nationalNumber; }

    public Case getCas() {
        return cas;
    }
    public void setCas(Case cas) {
        this.cas = cas;
    }

    public static Person make() { return new Person(); }

    @Override
    public String toString() {
        return "Person{" +
                 "id=" + id +
//                "compositeId=" + compositeId +
                ", name='" + name + '\'' +
                ", nationalNumber='" + nationalNumber + '\'' +
                '}';
    }
}
