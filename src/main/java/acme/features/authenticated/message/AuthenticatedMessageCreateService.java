
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.entities.persons.Person;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageRepository	repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		Person person;

		person = this.repository.findPersons(request.getModel().getInteger("messageThread.id"), request.getPrincipal().getActiveRoleId());

		return person != null;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body", "authenticated.userAccount.username", "messageThread.id", "messageThread.title");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result;
		result = new Message();
		MessageThread messageThread;
		Authenticated authenticated;

		authenticated = this.repository.findAuthenticatedById(request.getPrincipal().getActiveRoleId());
		result.setAuthenticated(authenticated);

		int id = request.getModel().getInteger("messageThread.id");
		messageThread = this.repository.findOneMessageThreadById(id);
		result.setMessageThread(messageThread);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isAccepted;
		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "anonymous.user-account.error.must-accept");

		Customisation c;
		Boolean spamWordsTitle = true;
		Boolean spamWordsTags = true;
		Boolean spamWordsBody = true;

		c = this.customisationRepository.findOne();

		String[] partes = c.getCustomisations().split(",");

		for (String s : partes) {
			if (entity.getTitle().contains(s)) {
				spamWordsTitle = false;
				errors.state(request, spamWordsTitle, "title", "authenticated.message.form.error.title");

			}
			if (entity.getBody().contains(s)) {
				spamWordsBody = false;
				errors.state(request, spamWordsBody, "body", "authenticated.message.form.error.body");

			}
			if (entity.getTags().contains(s)) {
				spamWordsTags = false;
				errors.state(request, spamWordsTags, "tags", "authenticated.message.form.error.tags");

			}
		}
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
