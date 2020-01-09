package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDutyShowService implements AbstractShowService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;

	@Override
	public boolean authorise(Request<Duty> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(Request<Duty> request, Duty entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description","percentage","job.title");

	}

	@Override
	public Duty findOne(Request<Duty> request) {
		assert request != null;
		Duty result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		

		return result;
	}

}
