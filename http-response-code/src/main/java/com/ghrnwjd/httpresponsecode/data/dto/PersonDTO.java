package com.ghrnwjd.httpresponsecode.data.dto;


import com.ghrnwjd.httpresponsecode.data.RoleType;
import com.ghrnwjd.httpresponsecode.data.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String firstName;
    private String lastName;

    public Person toEntity() {
        return new Person(this.firstName, this.lastName, RoleType.USER);
    }
}
