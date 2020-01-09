package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.authenticated.employer.AuthenticatedEmployerRepository;
import acme.features.employer.duty.EmployerDutyRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository employerJobRepository;

	@Autowired
	EmployerDutyRepository employerDutyRepository;

	@Autowired
	AuthenticatedEmployerRepository authenticatedEmployerRepository;

	@Autowired
	AdministratorCustomisationRepository customisationRepository;

	@Override
	public boolean authorise(Request<Job> request) {

		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<Job> request, Job entity, Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<Job> request, Job entity, Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "title", "deadline", "salary", "description", "finalMode",
				"moreInfo");
		model.setAttribute("employerId", entity.getEmployer().getId());

	}

	@Override
	public Job instantiate(Request<Job> request) {
		Job result;
		Employer e;

		result = new Job();
		int employerId = request.getPrincipal().getAccountId();
		e = this.authenticatedEmployerRepository.findOneEmployerByUserAccountId(employerId);
		if (e != null) {
			result.setEmployer(e);
		}
		result.setFinalMode(false);
		return result;

	}

	@Override
	public void validate(Request<Job> request, Job entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setFinalMode(false);
		Boolean isValid = true;

		if (isValid == false) {
			errors.state(request, isValid, "finalMode", "employer.job.form.error.finalMode");
		}
	}

	@Override
	public void create(Request<Job> request, Job entity) {
		assert request != null;
		assert entity != null;
		this.employerJobRepository.save(entity);
	}

}
