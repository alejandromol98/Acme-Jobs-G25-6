
package acme.features.authenticated.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.persons.Person;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedPersonCreateService implements AbstractCreateService<Authenticated, Person> {

	@Autowired
	AuthenticatedPersonRepository repository;


	@Override
	public boolean authorise(final Request<Person> request) {
		assert request != null;

		Person member;

		member = this.repository.findPersonInThread(request.getModel().getInteger("messageThread.id"), request.getPrincipal().getActiveRoleId());
		return member.isAuthor();
	}

	@Override
	public void bind(final Request<Person> request, final Person entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated user;
		MessageThread messageThread;

		messageThread = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThread.id"));
		user = this.repository.findUserByUsername(request.getModel().getString("authenticated.userAccount.username"));
		entity.setAuthenticated(user);
		entity.setMessageThread(messageThread);

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Person> request, final Person entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "authenticated", "messageThread", "messageThread.title", "messageThread.id");
	}

	@Override
	public Person instantiate(final Request<Person> request) {
		Person member;

		member = new Person();
		MessageThread messageThread;

		messageThread = this.repository.findOneMessageThreadById(request.getModel().getInteger("messageThread.id"));
		member.setMessageThread(messageThread);

		return member;
	}

	@Override
	public void validate(final Request<Person> request, final Person entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated authenticated;
		authenticated = this.repository.findUserByUsername(request.getModel().getString("authenticated.userAccount.username"));

		errors.state(request, authenticated != null, "authenticated.userAccount.username", "authenticated.member.error.invalidUsername");

		if (authenticated != null) {
			errors.state(request, this.repository.findPersonInThread(request.getModel().getInteger("messageThread.id"), authenticated.getId()) == null, "authenticated.userAccount.username", "authenticated.member.error.duplicated");
		}
	}

	@Override
	public void create(final Request<Person> request, final Person entity) {
		assert entity != null;

		this.repository.save(entity);

	}

	public void createFromMessageThread(final int authenticatedId, final MessageThread messageThread) {
		Person member = new Person();
		member.setAuthenticated(this.repository.findUserById(authenticatedId));
		member.setAuthor(true);
		member.setMessageThread(messageThread);

		this.repository.save(member);

	}

}
