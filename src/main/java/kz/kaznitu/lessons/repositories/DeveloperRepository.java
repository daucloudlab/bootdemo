package kz.kaznitu.lessons.repositories;

import kz.kaznitu.lessons.models.Developer;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<Developer, Integer>{
}
