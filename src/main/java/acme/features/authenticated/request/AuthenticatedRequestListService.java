package acme.features.authenticated.request;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.framework.components.Model;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedRequestListService implements AbstractListService<Authenticated,Request> {

	@Autowired
	AuthenticatedRequestRepository repository;
	
	@Override
	public boolean authorise(acme.framework.components.Request<Request> request) {
		// TODO Auto-generated method stub
		assert request!=null;
		return true;
	}

	@Override
	public void unbind(acme.framework.components.Request<Request> request, Request entity, Model model) {
		// TODO Auto-generated method stub
		assert request !=null;
		assert entity !=null;
		assert model !=null;
		
		request.unbind(entity, model, "moment","title");
	}

	@Override
	public Collection<Request> findMany(acme.framework.components.Request<Request> request) {
		// TODO Auto-generated method stub
		assert request !=null;
		
		Collection<Request> result;
		
		Date now = Calendar.getInstance().getTime();		
		
		result=this.repository.findManyAll(now);
		
		return result;
	}

}
