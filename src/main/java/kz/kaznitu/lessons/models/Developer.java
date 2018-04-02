package kz.kaznitu.lessons.models;

import javax.persistence.*;
import java.util.List;
@Entity
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String firstName ;
    private String lastName ;
    private String email ;
    @ManyToMany
    private List<Skill> skills ;

    public Developer(){
        super() ;
    }

    public Developer(int id, String firstName, String lastName, String email, List<Skill> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.skills = skills;
    }

    public Developer(String firstName, String lastName, String email, List<Skill> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
    public boolean hasSkill(Skill skill) {
        for (Skill containedSkill: getSkills()) {
            if (containedSkill.getId() == skill.getId()) {
                return true;
            }
        }
        return false;
    }

}
