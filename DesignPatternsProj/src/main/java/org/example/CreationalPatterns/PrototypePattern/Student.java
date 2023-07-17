package org.example.CreationalPatterns.PrototypePattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Cloneable{
    String name;
    int age;
    String planet;

    public Student getClone()
    {
        return new Student();
    }


}