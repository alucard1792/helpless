/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.mascotas;

import indefensos.controladores.login.ControladorLogin;
import indefensos.modelo.dao.CiudadFacade;
import indefensos.modelo.dao.EstadoMascotaFacade;
import indefensos.modelo.dao.MascotaFacade;
import indefensos.modelo.entidades.Ciudad;
import indefensos.modelo.entidades.EstadoMascota;
import indefensos.modelo.entidades.Mascota;
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
import javax.faces.view.ViewScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author David
 */
@Named(value = "controladorCrearMascota")
@ViewScoped
public class ControladorCrearMascota implements Serializable {

    private final static String UPLOAD_DIR = "/fotos/";

    @EJB
    private MascotaFacade mascotaFacade;
    @EJB
    private EstadoMascotaFacade estadoMascotaFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @Inject
    private ControladorLogin controladorLogin;
    private List<EstadoMascota> listaEstadosMascotas;
    private List<Ciudad> listaCiudades;
    private Mascota mascota;

    public ControladorCrearMascota() {
    }

    @PostConstruct
    public void init() {
        listaCiudades = ciudadFacade.findAll();
        listaEstadosMascotas = estadoMascotaFacade.findAll();
        mascota = new Mascota();

    }

    public List<EstadoMascota> getListaEstadosMascotas() {
        return listaEstadosMascotas;
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public String crear() {
        mascota.setEstadoMascotasId(new EstadoMascota(1));
        mascota.setFechaRegistro(new Date());
        mascota.setDueñoId(controladorLogin.getUsuarioSesion());
        mascota.setHasProceso(0);
        mascotaFacade.create(mascota);
        return "/core/mascota/listarMascotas.xhtml?faces-redirect=true";

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
            mascota.setImagen(UPLOAD_DIR + name);
            mascota.setEstadoMascotasId(new EstadoMascota(1));
            mascota.setFechaRegistro(new Date());
            mascota.setDueñoId(controladorLogin.getUsuarioSesion());
            mascota.setHasProceso(0);
            mascotaFacade.create(mascota);
            return "/core/mascota/listarMascotas.xhtml?faces-redirect=true";
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        } catch (ServletException ex) {
            ex.printStackTrace();
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
