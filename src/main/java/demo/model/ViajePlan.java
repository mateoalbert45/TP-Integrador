package demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class ViajePlan {

    	@EmbeddedId
    	private ViajePlanPK id;

    	
	    @ManyToOne
	    @MapsId("id") 
	    @JoinColumn(name = "IdViaje", referencedColumnName = "id")
	    private Viaje viaje;

	    @ManyToOne
	    @MapsId("id")
	    @JoinColumn(name = "idPlan", referencedColumnName = "id")
	    private Plan plan;
	
//	    @Column
//	    (nullable = true)
//	    private Date fechaEgreso;
	    
		public ViajePlan(){
			super();
		}

	    
	    
	    
		public ViajePlan(ViajePlanPK id, Viaje viaje, Plan plan) {
			super();
			this.id = id;
			this.viaje = viaje;
			this.plan = plan;
		}



		@Override
		public String toString() {
			return "ViajePlan [viaje=" + viaje + ", plan=" + plan + "]";
		}    



		public Viaje getViaje() {
			return viaje;
		}



		public Plan getPlan() {
			return plan;
		}
		
		
		
}
