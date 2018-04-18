/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.mascotas;

import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.entidades.Mascota;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author David
 */
@Named(value = "controladorListarMascotas")
@ViewScoped
public class ControladorListarMascotas implements Serializable {

    @EJB
    private MascotaFacade mascotaFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private Mascota mascotaSeleccionada;
    private List<Mascota> listaMascotas;
    private JasperPrint jasperPrint;

    public ControladorListarMascotas() {
    }

    @PostConstruct
    public void init() {
        listaMascotas = mascotaFacade.listarMascotasUsuarioSesion(controladorLogin.getUsuarioSesion());

    }

    public Mascota getMascotaSeleccionada() {
        return mascotaSeleccionada;
    }

    public void setMascotaSeleccionada(Mascota mascotaSeleccionada) {
        this.mascotaSeleccionada = mascotaSeleccionada;
    }

    public List<Mascota> getListaMascotas() {
        return listaMascotas;
    }

    public void prepararImagen(Mascota m) {
        this.mascotaSeleccionada = m;
    }

    public void prepararReporte(Mascota m) throws JRException {

        Map<String, Object> params = new HashMap<>();
        params.put("fechaRegistro", m.getFechaRegistro());
        params.put("nombre", m.getNombre());
        params.put("edad", m.getEdad());
        params.put("descripcion", m.getDescripcion());
        params.put("tipo", m.getTipoMascota());
        params.put("estado", m.getEstadoMascotasId().getNombre());
        params.put("ciudad", m.getCiudadesId().getNombre());
        params.put("fechaGeneracion", new Date());
        params.put("usuarioSesion", controladorLogin.getUsuarioSesion().getNombres() + " " + controladorLogin.getUsuarioSesion().getApellidos());
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "WEB-INF/reportes/report1.jasper";
        JRBeanCollectionDataSource bcds = new JRBeanCollectionDataSource(new ArrayList<>());
        jasperPrint = JasperFillManager.fillReport(path, params, new JREmptyDataSource());

    }

    public void exportarPDF(Mascota m) throws JRException, IOException {
        prepararReporte(m);
        ServletOutputStream servletOutputStream = null;
        String contentType = "aplication/PDF";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse httpServletResponse = (HttpServletResponse) externalContext.getResponse();
        httpServletResponse.setContentType(contentType);
        httpServletResponse.setHeader("Content-disposition", "attachment; filename=\"Reporte.pdf\"");
        servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();

    }

}
