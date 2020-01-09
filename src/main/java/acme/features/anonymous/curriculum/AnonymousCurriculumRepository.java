package acme.features.anonymous.curriculum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.curriculums.Curriculum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCurriculumRepository extends AbstractRepository {

	@Query("select c from Curriculum c")
	Collection<Curriculum> findMany();

}