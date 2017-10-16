package jesse.dev.jnoticias.models;

import java.util.List;

/**
 * Created by Slaush on 08/10/2017.
 */

public class Noticia {
    private String titulo;
    private String reportero;
    private String cuerpo;
    private String cover;
    private String createdAt; // createdAt
    private String categoria;
    private List<String> videoOrImage;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getReportero() {
        return reportero;
    }

    public void setReportero(String reportero) {
        this.reportero = reportero;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<String> getVideoOrImage() {
        return videoOrImage;
    }

    public void setVideoOrImage(List<String> videoOrImage) {
        this.videoOrImage = videoOrImage;
    }

    public boolean match(String s)
    {
        return titulo.contains(s) || cuerpo.contains(s) || reportero.contains(s) || categoria.contains(s) || s.isEmpty();
    }
}
