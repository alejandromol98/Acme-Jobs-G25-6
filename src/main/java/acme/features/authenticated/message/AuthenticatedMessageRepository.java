
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.entities.persons.Person;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyByMessageThreadId(int id);

	@Query("select m from Message m where m.id = ?1")
	Message findOneMessageById(int id);

	@Query("select m from MessageThread m where m.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findAuthenticatedById(int authenticatedId);

	@Query("select m from Person m where m.messageThread.id=?1 and m.authenticated.id=?2")
	Person findPersons(int messageThreadId, int authenticatedId);

}
