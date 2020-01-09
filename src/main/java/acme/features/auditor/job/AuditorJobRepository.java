
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select distinct j from Job j where j.id = ?1")
	Job findJobById(int id);

	@Query("select distinct a from Auditor a where a.id = ?1 ")
	Auditor findOneById(int id);

	//	@Query("select j from Job j where j.auditor.id = ?1")
	//	Collection<Job> findManyByAuditorId(int auditorId);

	@Query("select distinct a.job from AuditRecord a where a.auditor.id = ?1")
	Collection<Job> findJobsByAuditor(int id);

	@Query("select distinct a.job from AuditRecord a")
	Collection<Job> findManyJobs();

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);
}
