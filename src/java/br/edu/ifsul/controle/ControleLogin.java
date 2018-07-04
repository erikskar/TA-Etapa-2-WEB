package br.edu.ifsul.controle;

import br.edu.ifsul.dao.GamerDAO;
import br.edu.ifsul.modelo.Gamer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author eriks
 */
@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
    
    private List<Gamer> gamer;
    private Gamer usuarioAutenticado;
    
    @EJB
    private GamerDAO<Gamer> daoUsuario;
    
    String usuario;
    String senha;

    public ControleLogin() {
        gamer = new ArrayList<>();
    }
    
    public String paginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String efetuarLogin(){
        try {
            HttpServletRequest request = (HttpServletRequest) 
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            
            request.login(this.usuario, this.senha);
            
            System.out.println("Usuario: "+this.usuario);
            System.out.println("Senha: "+this.senha);
            
            if(request.getUserPrincipal() != null){
                setUsuarioAutenticado(getDaoUsuario().localiza(request.getUserPrincipal().getName()));
                getGamer().add(usuarioAutenticado);
                Util.mensagemInformacao("Login efetuado com sucesso");
                usuario = "";
                senha = "";
            }
            
            return "/index?faces-redirect=true";
            
        } catch (Exception e) {
            Util.mensagemErro("Usuário ou senha inválidos! " + Util.getMensagemErro(e));
            return "/login?faces-redirect=true";
        }
    }
    
    public String efetuarLogout(){
        try {
            getGamer().remove(usuarioAutenticado);
            setUsuarioAutenticado(null);
            HttpServletRequest request = (HttpServletRequest) 
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
        } catch (Exception e) {
            Util.mensagemErro("Erro: "+Util.getMensagemErro(e));
        }
        return "/index?faces-redirect=true";
    }

    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Gamer getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Gamer usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public GamerDAO<Gamer> getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(GamerDAO<Gamer> daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public List<Gamer> getGamer() {
        return gamer;
    }

    public void setGamer(List<Gamer> gamer) {
        this.gamer = gamer;
    }

 
    
    
    
    
}
