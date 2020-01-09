package acme.features.authenticated.job;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedListMineJobListService implements AbstractListService<Authenticated, Job> {

	// -----------------Internal state-------------------------------

	@Autowired
	AuthenticatedJobRepository repository;

	@Override
	public boolean authorise(Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void unbind(Request<Job> request, Job entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "title", "deadline");
	}

	@Override
	public Collection<Job> findMany(Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;
		Collection<Job> result;
		Calendar cal = Calendar.getInstance();
		Date dateNow = cal.getTime();

		result = this.repository.findManyByTime(dateNow);
		result.removeAll(this.repository.findManyByActive(false));

		return result;

	}

}
