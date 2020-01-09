
package acme.features.administrator.customisation;

import org.springframework.data.jpa.repository.Query;

import acme.entities.customisations.Customisation;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorCustomisationRepository extends AbstractRepository {

	@Query("select a from Customisation a")
	Customisation findOne();

}
