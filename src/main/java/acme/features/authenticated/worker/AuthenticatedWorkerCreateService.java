package acme.features.authenticated.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedWorkerCreateService implements AbstractCreateService<Authenticated, Worker> {

	@Autowired
	private AuthenticatedWorkerRepository repository;

	@Override
	public boolean authorise(Request<Worker> request) {
		// TODO Auto-generated method stub
		assert request != null;
		Boolean res = true;
		Principal principal = request.getPrincipal();
		Worker worker = this.repository.findOneWorkerByUserAccountId(principal.getAccountId());

		if (worker != null) {
			res = false;
		}

		return res;
	}

	@Override
	public void bind(Request<Worker> request, Worker entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<Worker> request, Worker entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "qualificationsRecord", "skillsRecord");
	}

	@Override
	public Worker instantiate(Request<Worker> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Worker result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Worker();
		result.setUserAccount(userAccount);
		return result;
	}

	@Override
	public void validate(Request<Worker> request, Worker entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(Request<Worker> request, Worker entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Worker> request, final Response<Worker> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}

	}
}
