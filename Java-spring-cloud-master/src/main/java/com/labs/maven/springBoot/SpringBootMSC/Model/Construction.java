package com.labs.maven.springBoot.SpringBootMSC.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Construction")
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted = false;

    @JsonIgnore
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void  setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column(name = "Name", nullable = false)
    private String Name;
    @Column(name = "Floors", nullable = true)
    private Integer Floors;
    @Column(name = "Address", nullable = false)
    private String Address;

    public Construction(String name, Integer floors, String address)
    {
        this.Name = name;
        this.Floors = floors;
        this.Address = address;
    }

    public Construction() { }

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Builder> builders;

    public Set<Builder> getBuilders() {
        return builders;
    }

    public void setBuilders(Set<Builder> builders) {
        this.builders = builders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Integer getFloors() {
        return Floors;
    }

    public void setFloors(Integer floors) {
        this.Floors = floors;
    }

    @Override
    public boolean equals(Object obj)
    {
        Construction dep = (Construction)obj;
        return id == dep.id &&
                Name.equals(dep.Name) &&
                Floors.equals(dep.Floors);
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }
}
