
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.TypeStatus;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationRejectService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		boolean result;

		Application ap;
		int appId;
		Employer employer;
		Principal principal;

		appId = request.getModel().getInteger("id");
		ap = this.repository.findOneById(appId);

		employer = ap.getJob().getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "worker.authorityName", "job.title", "moment", "status");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "statement", "skills", "qualifications", "justification");
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isValid;
		if (!errors.hasErrors("justification")) {
			isValid = !entity.getJustification().equals("");
			errors.state(request, isValid, "justification", "employer.application.form.error.justification");
		}

		Boolean isValid2;
		if (!errors.hasErrors("status")) {
			isValid2 = entity.getStatus().equals(TypeStatus.PENDING);
			errors.state(request, isValid2, "status", "employer.application.form.error.status");
		}
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		entity.setStatus(TypeStatus.REJECTED);
		this.repository.save(entity);
	}

}
