
package acme.features.authenticated.request;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedRequestCreateService implements AbstractCreateService<Authenticated, Request> {

	@Autowired
	AuthenticatedRequestRepository repository;


	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final acme.framework.components.Request<Request> request, final Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "ticker", "reward", "stars");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Request instantiate(final acme.framework.components.Request<Request> request) {
		Request result;
		result = new Request();
		return result;
	}

	@Override
	public void validate(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isValid;

		if (!errors.hasErrors("reward")) {
			isValid = entity.getReward().getCurrency().equals("EUR") || entity.getReward().getCurrency().equals("€");
			errors.state(request, isValid, "reward", "authenticated.request.form.error.invalidmoney");
		}

		Boolean isAccepted;
		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "anonymous.user-account.error.must-accept");
	}

	@Override
	public void create(final acme.framework.components.Request<Request> request, final Request entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
