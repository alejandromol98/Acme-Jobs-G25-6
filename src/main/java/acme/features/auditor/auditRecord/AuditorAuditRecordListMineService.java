
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditRecordListMineService implements AbstractListService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		//		int jobId = request.getModel().getInteger("id");
		return true;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		//		int jobId = request.getModel().getInteger("id");

		//		model.setAttribute("jobId", jobId);
		request.unbind(entity, model, "moment", "status", "auditor.authorityName", "title");

	}

	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;

		Collection<AuditRecord> result;
		Principal principal;
		principal = request.getPrincipal();

		int auditorId = principal.getActiveRoleId();

		result = this.repository.findAllByAuditorId(auditorId);

		return result;
	}
}
