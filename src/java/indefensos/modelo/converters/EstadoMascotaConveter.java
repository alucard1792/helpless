/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.modelo.converters;

import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.entidades.EstadoMascota;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author David
 */
@FacesConverter(forClass = EstadoMascota.class)
public class EstadoMascotaConveter implements Converter {

    private EstadoMascotaFacade estadoMascotaFacade;

    public EstadoMascotaConveter() {
        estadoMascotaFacade = CDI.current().select(EstadoMascotaFacade.class).get();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {

                return estadoMascotaFacade.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof EstadoMascota) {
            return ((EstadoMascota) value).getId().toString();
        }
        return "";

    }

}
