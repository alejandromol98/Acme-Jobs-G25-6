package acme.features.anonymous.curriculum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.curriculums.Curriculum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousCurriculumListService implements AbstractListService<Anonymous, Curriculum> {

	// Internal Service -----------------------------------------------

	@Autowired
	AnonymousCurriculumRepository repository;

	// AbastractListService<Administrator, Curriculum> interface -----------

	@Override
	public boolean authorise(final Request<Curriculum> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Curriculum> findMany(final Request<Curriculum> request) {
		assert request != null;

		Collection<Curriculum> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<Curriculum> request, final Curriculum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "skills", "studies", "moment");
	}

}
