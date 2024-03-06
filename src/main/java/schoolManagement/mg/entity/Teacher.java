package schoolManagement.mg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    
    public Teacher() {
        // No-args constructor
    }

    public Teacher(String id) {
        this.id = Long.parseLong(id);
    }
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "codeprof")
    private String codeprof;

    @Column(name = "name")
    private String name;
    
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "grade")
    private String grade;

	public String getCodeprof() {
		return codeprof;
	}

	public void setCodeprof(String codeprof) {
		this.codeprof = codeprof;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

    // getters and setters
}
