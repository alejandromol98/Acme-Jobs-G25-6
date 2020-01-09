package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerDutyDeleteService implements AbstractDeleteService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;

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
		request.bind(entity, errors, "title");
	}

	@Override
	public void unbind(Request<Duty> request, Duty entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "description", "percentage", "job.title");
	}

	@Override
	public Duty findOne(Request<Duty> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Duty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

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
	public void delete(Request<Duty> request, Duty entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
