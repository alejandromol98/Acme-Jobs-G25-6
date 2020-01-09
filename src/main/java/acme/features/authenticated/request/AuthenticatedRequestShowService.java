package acme.features.authenticated.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.framework.components.Model;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedRequestShowService implements AbstractShowService<Authenticated,Request> {
	
	
	@Autowired
	private AuthenticatedRequestRepository repository;
	
	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
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
		
		request.unbind(entity, model, "title","moment","deadline","description","reward","ticker","telephone","stars");
	}

	@Override
	public Request findOne(acme.framework.components.Request<Request> request) {
		// TODO Auto-generated method stub
		assert request !=null;
		
		Request res;
		int id;
		
		id=request.getModel().getInteger("id");
		res=this.repository.findOneById(id);
		
		
		return res;
	}

}
