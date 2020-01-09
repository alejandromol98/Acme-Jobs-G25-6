
package acme.features.anonymous.announcement;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousAnnouncementListService implements AbstractListService<Anonymous, Announcement> {

	// Internal State -------------------------------------------------

	@Autowired
	AnonymousAnnouncementRepository repository;


	//AbstractListService<Anonymous, Announcement> interface -------

	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title");
	}

	@Override
	public List<Announcement> findMany(final Request<Announcement> request) {
		assert request != null;

		List<Announcement> result;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		Date dateBefore30Days = cal.getTime();

		result = this.repository.findManyAll(dateBefore30Days);

		return result;
	}

}
