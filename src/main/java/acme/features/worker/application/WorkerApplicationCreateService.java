
package acme.features.worker.application;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.TypeStatus;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.features.authenticated.job.AuthenticatedJobRepository;
import acme.features.authenticated.worker.AuthenticatedWorkerRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository		repository;

	@Autowired
	AuthenticatedJobRepository		jobRepository;

	@Autowired
	AuthenticatedWorkerRepository	workerRepository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		//		boolean result;

		//		int jobId = request.getModel().getInteger("jobId");
		//		Job job = this.jobRepository.findOneById(jobId);
		//
		//		Principal principal;
		//		principal = request.getPrincipal();
		//
		//		Collection<Job> jobsByWorker = this.jobRepository.findJobsByWorker(principal.getActiveRoleId());
		//
		//		result = !jobsByWorker.contains(job);

		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "status");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "statement", "skills", "qualifications", "worker.authorityName", "job.title");
		model.setAttribute("jobId", entity.getJob().getId());
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;

		result = new Application();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		int jobId = request.getModel().getInteger("jobId");
		Job job = this.jobRepository.findOneById(jobId);

		Principal principal = request.getPrincipal();
		Worker worker = this.workerRepository.findOneWorkerByUserAccountId(principal.getAccountId());

		result.setJob(job);
		result.setWorker(worker);
		result.setMoment(moment);
		result.setStatus(TypeStatus.PENDING);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int jobId = request.getModel().getInteger("jobId");
		Job job = this.jobRepository.findOneById(jobId);

		Principal principal;
		principal = request.getPrincipal();

		Collection<Job> jobsByWorker = this.jobRepository.findJobsByWorker(principal.getActiveRoleId());

		Boolean isValid;
		if (!errors.hasErrors("qualifications")) {
			isValid = !jobsByWorker.contains(job);
			errors.state(request, isValid, "qualifications", "worker.application.form.error.notapply");
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
