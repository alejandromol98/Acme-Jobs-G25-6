
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.auditor.auditRecord.AuditorAuditRecordRepository;
import acme.features.employer.application.EmployerApplicationRepository;
import acme.features.employer.duty.EmployerDutyRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	@Autowired
	EmployerJobRepository			employerJobRepository;

	@Autowired
	AuditorAuditRecordRepository	auditorRepository;

	@Autowired
	EmployerDutyRepository			employerDutyRepository;

	@Autowired
	EmployerApplicationRepository	employerAppRepository;


	@Override
	public boolean authorise(final Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;
		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.employerJobRepository.findOneById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
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

		request.unbind(entity, model, "referenceNumber", "title", "finalMode");
		request.unbind(entity, model, "referenceNumber", "title", "deadline", "finalMode", "salary", "description", "moreInfo");
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
		int jobId = entity.getId();
		Boolean isValid = true;
		if (!this.employerAppRepository.findAppsByJob(jobId).isEmpty()) {
			isValid = false;
		}
		if (isValid == false) {
			errors.state(request, isValid, "referenceNumber", "employer.job.form.error.reference");
		}
	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		int jobId = entity.getId();

		Collection<AuditRecord> auditRecords = this.auditorRepository.findAuditRecordsByJob(jobId);
		Collection<Duty> duties = this.employerDutyRepository.findManyDuty(jobId);

		this.auditorRepository.deleteAll(auditRecords);
		this.employerDutyRepository.deleteAll(duties);
		this.employerJobRepository.delete(entity);

	}

}
