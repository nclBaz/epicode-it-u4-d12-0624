package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import riccardogulin.entities.Student;
import riccardogulin.exceptions.NotFoundException;

public class StudentsDAO {
	// DAO (Data Access Object) è un Design Pattern. Questa classe serve per semplificare l'accesso al DB fornendo tutta una serie di metodi
	// custom più pratici di quelli dell'Entity Manager. Semplificare nel senso anche di nascondere alcuni dettagli implementativi, che in alcuni
	// casi potrebbero essere anche ben complessi. Nascondendoli forniremo a chi ha bisogno di interagire con la tabella degli studenti dei metodi
	// semplici e più immediati magari dotandoli anche di nomi appropriati e "parlanti".
	private final EntityManager entityManager;

	public StudentsDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Student newStudent) {
		// 1. Chiediamo all'entity manager di creare una nuova transazione
		EntityTransaction transaction = entityManager.getTransaction();

		// 2. Facciamo la partire la transazione
		transaction.begin();

		// 3. Aggiungiamo il nuovo studente al Persistence Context (a questo step lo studente non fa ancora parte del db)
		entityManager.persist(newStudent);

		// 4. Chiudiamo la transazione (qua lo studente verrà effettivamente inviato al db)
		transaction.commit();

		System.out.println("Lo studente " + newStudent.getSurname() + " è stato salvato correttamente nel db");

	} // Dato un nuovo studente me lo salverà nel db

	public Student findById(long studentId) {
		Student found = entityManager.find(Student.class, studentId);
		if (found == null) throw new NotFoundException(studentId);
		return found;
	} // Dato un id mi torna lo studente se lo trova, altrimenti eccezione

	public void findByIdAndDelete(long studentId) {

		// 0. Cerco lo studente nel db
		Student found = this.findById(studentId);

		// 1. Chiediamo all'entity manager di creare una nuova transazione
		EntityTransaction transaction = entityManager.getTransaction();

		// 2. Facciamo la partire la transazione
		transaction.begin();

		// 3. Rimuoviamo lo studente dal persistence (a questo step ancora non sarà rimosso dal DB)
		entityManager.remove(found);

		// 4. Chiudiamo la transazione (qua lo studente verrà effettivamente rimosso dal db)
		transaction.commit();

		System.out.println("Lo studente " + found.getSurname() + " è stato rimosso dal DB!");

	}  // Dato un id cancella lo studente se lo trova, altrimenti eccezione
}
