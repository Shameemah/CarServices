/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.web;

import edu.iit.sat.itmd4515.sfuseini.domain.Parts;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Shameemah
 */
@FacesConverter("equipmentConverter")
public class EquipmentConverter implements Converter {

    private static final Logger LOG = Logger.getLogger(EquipmentConverter.class.getName());

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOG.info("getAsObject received " + value);
        Long id = Long.parseLong(value);
        Parts e = new Parts(id);
        return e;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LOG.info("getAsString received " + value.toString());
        return String.valueOf(((Parts)value).getSerialNumber() );
    }

}
