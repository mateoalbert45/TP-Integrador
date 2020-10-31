package demo.model;

import java.io.Serializable;

import javax.persistence.Column;

public class ViajePlanPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "IdViaje")
    private int IdViaje;

    @Column(name = "IdPlan")
    private int IdPlan;

	public ViajePlanPK(){
		
	}
    
	public ViajePlanPK(int IdViaje, int IdPlan) {
		super();
		this.IdViaje = IdViaje;
		this.IdPlan = IdPlan;
	}

	public int getId_Estudiante() {
		return IdViaje;
	}

	public int getId_Carrera() {
		return IdPlan;
	}
    
    
	
	
}
