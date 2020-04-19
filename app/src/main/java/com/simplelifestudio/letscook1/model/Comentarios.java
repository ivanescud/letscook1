package com.simplelifestudio.letscook1.model;

public class Comentarios {
    private String usuarioID;
    private String usuarioPerfil;
    private String usuarioNombre;
    private  String comentario;
    private String comentarioId;

    public Comentarios(String usuarioID, String usuarioPerfil, String usuarioNombre, String comentario, String comentarioId) {
        this.usuarioID = usuarioID;
        this.usuarioPerfil = usuarioPerfil;
        this.usuarioNombre = usuarioNombre;
        this.comentario = comentario;
        this.comentarioId = comentarioId;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(String usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(String comentarioId) {
        this.comentarioId = comentarioId;
    }
}
