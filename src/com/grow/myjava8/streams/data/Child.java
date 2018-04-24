package com.grow.myjava8.streams.data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Child extends Person  {
    private Employee parent;

    public Child(Person person) {
        super(person.getFirstName(), person.getSurname(), person.getBirthday(), person.getGender());
    }

    public Employee getParent() {
        return parent;
    }

    public void setParent(final Employee parent) {
        this.parent = parent;
    }

    public long getAge(){
        return ChronoUnit.YEARS.between(getBirthday(), LocalDate.now());
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(getFullName())
                .append("  ")
                .append(getGender())
                .append("  ")
                .append(getBirthday())
                .append("  ")
                .append(getAge());

        return builder.toString();
    }

}
