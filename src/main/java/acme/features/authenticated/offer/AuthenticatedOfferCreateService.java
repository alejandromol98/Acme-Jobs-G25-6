
package acme.features.authenticated.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedOfferCreateService implements AbstractCreateService<Authenticated, Offer> {

	@Autowired
	AuthenticatedOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "min", "max", "text", "deadline");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;

		result = new Offer();

		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isValidmin;
		if (!errors.hasErrors("min")) {
			isValidmin = entity.getMin().getCurrency().equals("EUR") || entity.getMin().getCurrency().equals("€");
			errors.state(request, isValidmin, "min", "authenticated.offer.form.error.invalidmoney");
		}

		Boolean isValidmax;
		if (!errors.hasErrors("max")) {
			isValidmax = entity.getMax().getCurrency().equals("EUR") || entity.getMax().getCurrency().equals("€");
			errors.state(request, isValidmax, "max", "authenticated.offer.form.error.invalidmoney");
		}

		Boolean isAccepted;
		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "anonymous.user-account.error.must-accept");
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);

		this.repository.save(entity);
	}

}
