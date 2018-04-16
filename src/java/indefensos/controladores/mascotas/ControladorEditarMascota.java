/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.mascotas;

import indefensos.modelo.dao.CiudadFacade;
import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.entidades.Ciudad;
import indefensos.modelo.entidades.EstadoMascota;
import indefensos.modelo.entidades.Mascota;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author David
 */
@Named(value = "controladorEditarMascota")
@ConversationScoped
public class ControladorEditarMascota implements Serializable {

    private final static String UPLOAD_DIR = "/fotos/";

    @EJB
    private MascotaFacade mascotaFacade;
    @EJB
    private EstadoMascotaFacade estadoMascotaFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @Inject
    private Conversation conversation;
    private List<EstadoMascota> listaEstadosMascotas;
    private List<Ciudad> listaCiudades;
    private Mascota mascotaSeleccionada;

    public ControladorEditarMascota() {
    }

    @PostConstruct
    public void init() {
        listaCiudades = ciudadFacade.findAll();
        listaEstadosMascotas = estadoMascotaFacade.findAll();
    }

    public List<EstadoMascota> getListaEstadosMascotas() {
        return listaEstadosMascotas;
    }

    public void setListaEstadosMascotas(List<EstadoMascota> listaEstadosMascotas) {
        this.listaEstadosMascotas = listaEstadosMascotas;
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public Mascota getMascotaSeleccionada() {
        return mascotaSeleccionada;
    }

    public void setMascotaSeleccionada(Mascota mascotaSeleccionada) {
        this.mascotaSeleccionada = mascotaSeleccionada;
    }

    public void iniciarConversacion() {
        if (conversation.isTransient()) {
            conversation.begin();
        }

    }

    public void terminarConversacion() {
        if (!conversation.isTransient()) {
            conversation.end();
        }

    }

    public String cancelar() {
        terminarConversacion();
        return "/core/mascota/listarMascotas.xhtml?faces-redirect=true";

    }

    public String prepararEditar(Mascota m) {
        iniciarConversacion();
        mascotaSeleccionada = m;
        return "/core/mascota/editarMascota.xhtml?faces-redirect=true";

    }

    public String editar() {
        mascotaFacade.edit(mascotaSeleccionada);
        return cancelar();

    }

    public String upload() {
        try {
            String name = "";
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            List<FileBean> filesBeans = getFilesUpload(ec);
            for (FileBean fileBean : filesBeans) {
                savePart(ec, fileBean);
                System.out.println("Datos: " + fileBean.toString());
                name = fileBean.getFileNameFull();
            }
            //deleteFile(ec, "Tablero y TV.c4d");
            mascotaSeleccionada.setImagen(UPLOAD_DIR + name);
            mascotaFacade.edit(mascotaSeleccionada);
            return cancelar();

        } catch (IOException ex) {
            ex.printStackTrace();
            terminarConversacion();
            return "";
        } catch (ServletException ex) {
            ex.printStackTrace();
            terminarConversacion();
            return "";
        }

    }

    private List<FileBean> getFilesUpload(ExternalContext ec) throws IOException, ServletException {
        List<FileBean> files = new ArrayList<>();
        Collection<Part> parts = getParts(ec);
        for (Part part : parts) {
            if (part.getSize() > 0 && part.getSubmittedFileName() != null) {
                files.add(new FileBean(part.getName(), part.getContentType(), part.getSize(), part));
            }
        }
        return files;

    }

    private Collection<Part> getParts(ExternalContext ec) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) ec.getRequest();
        return rq.getParts();
    }

    private void savePart(ExternalContext ec, FileBean fileBean) throws IOException {
        File dir = new File(ec.getRealPath("") + UPLOAD_DIR);
        dir.mkdirs();
        File file = new File(dir, fileBean.getFileNameFull());
        file.createNewFile();

        FileOutputStream outputStream = new FileOutputStream(file);
        InputStream inputStream = fileBean.getPart().getInputStream();

        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.close();
        inputStream.close();
    }

    private void deleteFile(ExternalContext ec, String name) {
        File dir = new File(ec.getRealPath("") + UPLOAD_DIR);
        dir.mkdirs();
        File file = new File(dir, name);
        file.delete();
    }

}
