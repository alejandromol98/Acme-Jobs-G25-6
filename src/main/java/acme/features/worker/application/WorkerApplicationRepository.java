
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.worker.id = ?1")
	Collection<Application> findAppsByWorker(int id);

	@Query("select a from Application a where a.id = ?1")
	Application findOneById(int id);

	@Query("select a from Worker a where a.userAccount.id = ?1")
	Worker findByUserAccountId(int userAccount);

}
