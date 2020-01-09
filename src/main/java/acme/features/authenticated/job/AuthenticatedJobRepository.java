package acme.features.authenticated.job;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedJobRepository extends AbstractRepository {
	@Query("select j from Job j where j.id = ?1")
	Job findOneById(int id);

	@Query("select j from Job j where j.deadline >= ?1")
	Collection<Job> findManyByTime(Date date);
	
	@Query("select j from Job j where j.finalMode <= ?1")
	Collection<Job> findManyByActive(Boolean res);
	
	@Query("select a.job from Application a where a.worker.id = ?1")
	Collection<Job> findJobsByWorker(int id);
}
