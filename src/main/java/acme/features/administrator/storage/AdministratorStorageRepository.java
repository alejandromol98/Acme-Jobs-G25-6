
package acme.features.administrator.storage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.storages.Storage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorStorageRepository extends AbstractRepository {

	@Query("select a from Storage a where a.id = ?1")
	Storage findOneById(int id);

	@Query("select a from Storage a where a.status = 'PENDING'")
	Collection<Storage> findManyPending();

}
