package com.grow.myjava8.streams.data;

import java.time.LocalDate;

import com.grow.myjava8.streams.enums.Gender;

public class Person {
    private String firstName;
    private String surname;
    private LocalDate birthday;
    private Gender gender;

    public Person(String firstName, String surname, LocalDate birthday, Gender gender){
        this.firstName = firstName;
        this.surname = surname;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(final LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public String getFullName(){
        return firstName + " " + surname;
    }
}
