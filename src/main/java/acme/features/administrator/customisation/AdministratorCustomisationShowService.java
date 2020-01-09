
package acme.features.administrator.customisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCustomisationShowService implements AbstractShowService<Administrator, Customisation> {

	// Internal Service -----------------------------------------------

	@Autowired
	AdministratorCustomisationRepository repository;


	@Override
	public boolean authorise(final Request<Customisation> request) {
		assert request != null;

		return true;
	}

	@Override
	public Customisation findOne(final Request<Customisation> request) {
		assert request != null;

		Customisation result;
		result = this.repository.findOne();

		return result;
	}

	@Override
	public void unbind(final Request<Customisation> request, final Customisation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "customisations", "threshold");
	}

}
