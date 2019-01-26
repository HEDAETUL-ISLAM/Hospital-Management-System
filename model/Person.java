/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hedaetul
 */
public class Person {
    
    private String personId;
    private String personName;
    private String personAddress;
    private String personPhone;

    public Person() {
    }

    public Person(String personId, String personName, String personAddress, String personPhone) {
        this.personId = personId;
        this.personName = personName;
        this.personAddress = personAddress;
        this.personPhone = personPhone;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    @Override
    public String toString() {
        return "Person{" + "personId=" + personId + ", personName=" + personName + ", personAddress=" + personAddress + ", personPhone=" + personPhone + '}';
    }
    
    
}
