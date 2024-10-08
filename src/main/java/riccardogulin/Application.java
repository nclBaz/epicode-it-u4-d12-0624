package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.StudentsDAO;
import riccardogulin.entities.Student;
import riccardogulin.exceptions.NotFoundException;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d12");

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager(); // Creeremo un unico Entity Manager per tutta l'applicazione

		StudentsDAO sd = new StudentsDAO(em);

		// Student aldo = new Student("Giovanni", "Storti", StudentType.FRONTEND);

		// sd.save(aldo);

		try {
			Student fromDb = sd.findById(2);
			System.out.println(fromDb);

			sd.findByIdAndDelete(1);

		} catch (NotFoundException ex) {
			System.out.println(ex.getMessage());
		}


		// Best practices, alla fine di tutto sempre bene chiudere le risorse aperte
		em.close();
		emf.close();

	}
}
