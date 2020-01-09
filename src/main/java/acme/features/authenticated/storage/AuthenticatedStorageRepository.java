
package acme.features.authenticated.storage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.storages.Storage;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedStorageRepository extends AbstractRepository {

	@Query("select count(a) from Storage a where a.authenticated.userAccount.id = ?1")
	Integer numberOfStorageByUserAccountId(int id);

	@Query("select a from Storage a where a.authenticated.userAccount.id = ?1")
	Storage findOneStorageByUserAccountId(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByUserAccountId(int id);

	@Query("select count(a) from Auditor a where a.userAccount.id = ?1")
	int findNumberOfAuditorByUserAccountId(int id);

	@Query("select count(a) from Storage a where a.authenticated.userAccount.id = ?1 and (a.status='PENDING' or a.status='ACCEPTED')")
	int numberOfStoragePendingByUserAccountId(int userAccountId);

}
