package jm.task.spring213.app.model;


import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Dog extends Animal {
    private String name;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Objects.equals(name, dog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

