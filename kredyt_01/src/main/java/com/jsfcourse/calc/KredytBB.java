package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class KredytBB {
	private Double kwota;
	private Double okres;
	private Double oprocentowanie;
	private Double rata;

	@Inject
	FacesContext ctx;

	

	public Double getKwota() {	
		return kwota;
	}

	public void setKwota(Double kwota) {
	    if (kwota != null && kwota > 5000) {
	        this.kwota = kwota;
	    } else {
	        FacesMessage message = new FacesMessage("Kwota musi być większa od 5000");
	        message.setSeverity(FacesMessage.SEVERITY_ERROR);
	        ctx.addMessage("form:idkwota", message);
	    }
	}
	
	public Double getOkres() {	
		return okres;
	}

	public void setOkres(Double okres) {
	    if (okres != null && okres >= 6 && okres <= 60) {
	        this.okres = okres;
	    } else {
	        FacesMessage message = new FacesMessage("Okres musi być z przedziału 6-60");
	        message.setSeverity(FacesMessage.SEVERITY_ERROR);
	        ctx.addMessage("form:idokres", message);
	    }
	}
	
	
	public Double getOprocentowanie() {	
		return oprocentowanie;
	}

	public void setOprocentowanie(Double oprocentowanie) {
	    if (oprocentowanie != null && oprocentowanie >= 10 && oprocentowanie <= 40) {
	        this.oprocentowanie = oprocentowanie;
	    } else {
	        FacesMessage message = new FacesMessage("Oprocentowanie musi być z przedziału 10-40");
	        message.setSeverity(FacesMessage.SEVERITY_ERROR);
	        ctx.addMessage("form:idoprocentowanie", message);
	    }
	}
	
	
	public Double getRata() {
		return rata;
	}

	public void setRata(Double rata) {
		this.rata = rata;
	}
	
	public String info() {
		return "info";
	}

	public String calc() {
		try {
			rata = (kwota + (kwota * (oprocentowanie / 100))) / okres;
			

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rata obliczona poprawnie", null));
			return "showresult";
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return null;
		}
	}

	

}
