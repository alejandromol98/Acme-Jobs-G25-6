
package acme.features.authenticated.person;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.persons.Person;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedPersonRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findUserById(int id);

	@Query("select a from Authenticated a where a.userAccount.username = ?1")
	Authenticated findUserByUsername(String username);

	@Query("select m from Person m where m.messageThread.id = ?1")
	Collection<Person> findManyByThreadId(int threadId);

	@Query("select m from Person m where m.id= ?1")
	Person findPersonById(int id);

	@Query("select m from MessageThread m where m.id= ?1")
	MessageThread findOneMessageThreadById(int threadId);

	@Query("select m from Person m where m.messageThread.id= ?1 and m.authenticated.id= ?2")
	Person findPersonInThread(int messageThreadId, int authenticatedId);

}
