
package acme.features.authenticated.investor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.investors.Investor;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedInvestorRepository extends AbstractRepository {

	@Query("select a from Investor a where a.id = ?1")
	Investor findOneById(int id);

	@Query("select a from Investor a")
	Collection<Investor> findManyAll();

}
