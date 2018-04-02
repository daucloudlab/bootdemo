package kz.kaznitu.lessons.repositories;

import kz.kaznitu.lessons.models.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
    List<Skill> findByLabel(String label) ;
}
