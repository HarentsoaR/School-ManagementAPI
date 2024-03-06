package schoolManagement.mg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Room() {
        // No-args constructor
    }

    public Room(String id) {
        this.id = Long.parseLong(id);
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "codesalle")
    private String codesalle;

    @Column(name = "Designation")
    private String designation;

    public String getCodesalle() {
        return codesalle;
    }

    public void setCodesalle(String codesalle) {
        this.codesalle = codesalle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
