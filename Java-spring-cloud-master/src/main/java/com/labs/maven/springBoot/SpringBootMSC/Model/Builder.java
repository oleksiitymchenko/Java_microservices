package com.labs.maven.springBoot.SpringBootMSC.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="Builder")
public class Builder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted = false;

    @JsonIgnore
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void  setIsDeleted(Boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    @Column(name = "FirstName", nullable = false)
    private String FirstName;
    @Column(name = "LastName", nullable = false)
    private String LastName;
    @Column(name = "Age", nullable = false)
    private Integer Age;

    public Builder(String FirstName, String LastName, Integer age)
    {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Age = age;
    }

    public Builder()
    { }


    @ManyToOne(targetEntity = com.labs.maven.springBoot.SpringBootMSC.Model.Construction.class)
    private Construction Construction;

    @JsonIgnore
    public Construction getConstruction() {
        return Construction;
    }

    public void setConstruction(Construction construction) {
        this.Construction = construction;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        this.Age = age;
    }

    @Override
    public boolean equals(Object obj)
    {
        Builder doc = (Builder)obj;
        return id == doc.id &&
                FirstName.equals(doc.FirstName) &&
                LastName.equals(doc.LastName) &&
                Age.equals(doc.Age);
    }
}
