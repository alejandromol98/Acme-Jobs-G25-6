package acme.features.anonymous.curriculum;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.curriculums.Curriculum;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousCurriculumCreateService implements AbstractCreateService<Anonymous, Curriculum> {

	// Internal Service -----------------------------------------------

	@Autowired
	AnonymousCurriculumRepository repository;


	@Override
	public boolean authorise(final Request<Curriculum> request) {
		assert request != null;

		return true;
	}

	@Override
	public Curriculum instantiate(final Request<Curriculum> request) {
		assert request != null;

		Curriculum result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Curriculum();
		result.setName("Peter Parker");
		result.setStudies("Biology");
		result.setSkills("I can fly");
		result.setMoment(moment);

		return result;

	}

	@Override
	public void unbind(final Request<Curriculum> request, final Curriculum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "skills", "studies");
	}

	@Override
	public void bind(final Request<Curriculum> request, final Curriculum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<Curriculum> request, final Curriculum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Curriculum> request, final Curriculum entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
