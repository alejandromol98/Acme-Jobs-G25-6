
package acme.features.administrator.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.entities.storages.Storage;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAuditorCreateService implements AbstractCreateService<Administrator, Auditor> {

	@Autowired
	AdministratorAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsabilityStatement");
	}

	@Override
	public Auditor instantiate(final Request<Auditor> request) {
		Auditor result;

		result = new Auditor();

		return result;
	}

	@Override
	public void validate(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Auditor> request, final Auditor entity) {
		this.repository.save(entity);

	}

	public void createFromStorage(final Storage storage) {
		assert storage != null;

		Auditor auditor = new Auditor();
		auditor.setFirm(storage.getFirm());
		auditor.setResponsibilityStatement(storage.getResponsibilityStatement());
		auditor.setUserAccount(storage.getAuthenticated().getUserAccount());

		this.repository.save(auditor);

	}

}
