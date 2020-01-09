package acme.features.authenticated.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Employer;
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
public class AuthenticatedEmployerCreateService implements AbstractCreateService<Authenticated, Employer> {

	@Autowired
	private AuthenticatedEmployerRepository repository;

	@Override
	public boolean authorise(Request<Employer> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Boolean res = true;
		Principal principal = request.getPrincipal();
		Employer employer = this.repository.findOneEmployerByUserAccountId(principal.getAccountId());

		if (employer != null) {
			res = false;
		}

		return res;
	}

	@Override
	public void bind(Request<Employer> request, Employer entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<Employer> request, Employer entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "sector");
	}

	@Override
	public Employer instantiate(Request<Employer> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Employer result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Employer();
		result.setUserAccount(userAccount);
		return result;
	}

	@Override
	public void validate(Request<Employer> request, Employer entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(Request<Employer> request, Employer entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Employer> request, final Response<Employer> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}

	}
}
