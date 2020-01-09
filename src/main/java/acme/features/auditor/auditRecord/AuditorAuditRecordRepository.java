
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.job.id = ?1 and a.status = 'PUBLISHED'")
	Collection<AuditRecord> findAuditRecordsByJob(int id);

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOneById(int id);

	@Query("select j.finalMode from Job j where j.id=?1")
	Boolean IsJobFinalMode(int jobId);

	@Query("select j from Job j where j.id=?1")
	Job findJobById(int jobId);

	@Query("select au from Auditor au where au.userAccount.id=?1")
	Auditor findAuditorById(int auditorId);

}
