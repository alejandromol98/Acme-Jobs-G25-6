
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerJobShowService implements AbstractShowService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;

	@Override
	public boolean authorise(Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;

		boolean result;
		Job job;
		int jobId;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneById(jobId);

		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = job.isFinalMode()
				|| !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(Request<Job> request, Job entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "title", "deadline", "finalMode", "salary", "description","moreInfo");
		request.unbind(entity, model, "finalMode", "salary", "moreInfo");

	}

	@Override
	public Job findOne(Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}

