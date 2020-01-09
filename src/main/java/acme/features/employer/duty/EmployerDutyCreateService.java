package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.employer.job.EmployerJobRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository employerDutyRepository;

	@Autowired
	EmployerJobRepository employerJobRepository;

	@Override
	public boolean authorise(Request<Duty> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<Duty> request, Duty entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "job.title");
	}

	@Override
	public void unbind(Request<Duty> request, Duty entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentage");
		model.setAttribute("id", entity.getJob().getId());

	}

	@Override
	public Duty instantiate(Request<Duty> request) {
		// TODO Auto-generated method stub
		Duty result;
		Job j;
		int idJob;

		result = new Duty();
		idJob = request.getModel().getInteger("id");
		j = this.employerJobRepository.findOneById(idJob);

		if (j != null) {
			result.setJob(j);
		}

		return result;
	}

	@Override
	public void validate(Request<Duty> request, Duty entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(Request<Duty> request, Duty entity) {
		// TODO Auto-generated method stub
		this.employerDutyRepository.save(entity);
	}

}
