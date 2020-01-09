
package acme.features.administrator.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "rewardGoalGold", "rewardGoalSilver", "rewardGoalBronze");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;

		result = new Challenge();

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isValidS, isValido, isValid;
		if (!errors.hasErrors("rewardGoalSilver")) {
			isValidS = entity.getRewardGoalSilver().getCurrency().equals("€") || entity.getRewardGoalSilver().getCurrency().equals("EUR");
			errors.state(request, isValidS, "rewardGoalSilver", "administrator.challenge.form.error.invalidmoney");
		}

		if (!errors.hasErrors("rewardGoalGold")) {
			isValido = entity.getRewardGoalGold().getCurrency().equals("€") || entity.getRewardGoalGold().getCurrency().equals("EUR");
			errors.state(request, isValido, "rewardGoalGold", "administrator.challenge.form.error.invalidmoney");
		}

		if (!errors.hasErrors("rewardGoalBronze")) {
			isValid = entity.getRewardGoalBronze().getCurrency().equals("€") || entity.getRewardGoalBronze().getCurrency().equals("EUR");
			errors.state(request, isValid, "rewardGoalBronze", "administrator.challenge.form.error.invalidmoney");
		}
	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		this.repository.save(entity);

	}

}
