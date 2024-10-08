package riccardogulin.entities;

import jakarta.persistence.*;

@Entity // <-- Qua dichiariamo che questa classe dovrà essere mappata ad una specifica tabella del db
// Sarà Hibernate a creare la tabella se questa non esiste grazie all'impostazione <property name="hibernate.hbm2ddl.auto" value="update"/>
// e sarà lui stesso a provare a modificare la tabella se dovessimo modificare la classe
@Table(name = "students") // <-- Annotazione OPZIONALE. Serve per personalizzare il nome della tabella
public class Student {
	@Id // <-- Annotazione OBBLIGATORIA! Serve per indicare quale colonna saràà la chiave primaria PK della mia tabella. Ogni tabella
	// deve avere una PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // OPZIONALE. Serve per chiedere al DB di gestire lui la creazione della chiave primaria
	// Se non la usassi vorrebbe dire che dovrei essere io ad inserire manualmente tale id.
	// IDENTITY chiede al db di rendere quel long un bigserial
	private long id;
	@Column(name = "nome", nullable = false)
	// <-- Annotazione OPZIONALE. Serve per personalizzare la colonna. Ovvero posso stabilire un nome, posso stabilire vari vincoli tipo
	// non-nullness, uniqueness, ecc
	private String name;
	@Column(name = "cognome", nullable = false)
	private String surname;
	@Column(name = "tipo_studente")
	@Enumerated(EnumType.STRING) // Di default gli enum vengono trattati come numeri interi! Possiamo però mettere @Enumerated per specificare
	// che vogliamo avere un campo testuale
	private StudentType studentType;

	public Student() { // OBBLIGATORIO nelle entities il costruttore vuoto! Serve a JPA per poterci fornire degli oggetti di tipo Student
		// quando andremo a leggere dalla tabella students

	}

	public Student(String name, String surname, StudentType studentType) {
		this.name = name;
		this.surname = surname;
		this.studentType = studentType;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public StudentType getStudentType() {
		return studentType;
	}

	public void setStudentType(StudentType studentType) {
		this.studentType = studentType;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", studentType=" + studentType +
				'}';
	}
}
