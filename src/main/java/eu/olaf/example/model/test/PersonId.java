package eu.olaf.example.model.test;

import javax.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class PersonId implements Serializable {


    @Column(name= "ID")
    private Long id;

    @Column(name= "CASE_ID")
    private Long caseId;

    public PersonId(){

    }

    public PersonId(Long id, Long caseId){
        this.id = id;
        this.caseId = caseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( !(o instanceof PersonId) ) return false;
        PersonId personId = (PersonId) o;
        return Objects.equals(id, personId.id) &&
                Objects.equals(caseId, personId.caseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caseId);
    }
}
