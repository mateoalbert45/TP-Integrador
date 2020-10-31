package demo.model;

import java.io.Serializable;

import javax.persistence.Column;

public class ViajePlanPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "IdViaje")
    private Long IdViaje;

    @Column(name = "IdPlan")
    private Long IdPlan;

	public ViajePlanPK(){
		
	}
    
	public ViajePlanPK(Long IdViaje, Long IdPlan) {
		super();
		this.IdViaje = IdViaje;
		this.IdPlan = IdPlan;
	}

	public Long getId_Estudiante() {
		return IdViaje;
	}

	public Long getId_Carrera() {
		return IdPlan;
	}
    
    
	
	
}
