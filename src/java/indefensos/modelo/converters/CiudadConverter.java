/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.modelo.converters;

import indefensos.modelo.dao.CiudadFacade;
import indefensos.modelo.entidades.Ciudad;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author David
 */
@FacesConverter(forClass = Ciudad.class)
public class CiudadConverter implements Converter{
    
    private CiudadFacade ciudadFacade;
    
    public CiudadConverter(){
        ciudadFacade = CDI.current().select(CiudadFacade.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {

                return ciudadFacade.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Ciudad) {
            return ((Ciudad) value).getId().toString();
        }
        return "";

    }

}
