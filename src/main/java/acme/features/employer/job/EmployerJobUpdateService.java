
package acme.features.employer.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.employer.duty.EmployerDutyRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	EmployerJobRepository					employerJobRepository;

	@Autowired
	EmployerDutyRepository					employerDutyRepository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		Job j;
		Integer jobId = request.getModel().getInteger("id");
		j = this.employerJobRepository.findOneById(jobId);

		if (j.isFinalMode()) {
			request.unbind(entity, model);
		} else {
			request.unbind(entity, model, "referenceNumber", "title", "deadline", "finalMode", "salary", "description", "moreInfo");
		}
	}

	@Override
	public Job findOne(final Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.employerJobRepository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
		Job j;
		Customisation c;
		Boolean isValid = true;
		Integer jobId = request.getModel().getInteger("id");
		j = this.employerJobRepository.findOneById(jobId);

		if (entity.getId() != 0) {

			if (entity.isFinalMode() == true) {

				c = this.customisationRepository.findOne();
				String[] partes = c.getCustomisations().split(",");
				Integer porcentaje = 0;
				Collection<Duty> dutties = this.employerDutyRepository.findManyDuty(jobId);

				List<Duty> dutis = new ArrayList<>(dutties);
				for (int i = 0; i < dutis.size(); i++) {
					porcentaje += dutis.get(i).getPercentage();
				}
				if (j.getDescription() != null & porcentaje == 100) {
					for (String parte : partes) {
						if (j.getDescription().contains(parte) || j.getTitle().contains(parte) || j.getMoreInfo().contains(parte)) {// falta spam
							isValid = false;
						}
					}
				} else {
					isValid = false;
				}
			}
		}
		if (isValid == false) {
			j.setFinalMode(false);
			errors.state(request, isValid, "finalMode", "employer.job.form.error.finalMode");
		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		this.employerJobRepository.save(entity);
	}

}
