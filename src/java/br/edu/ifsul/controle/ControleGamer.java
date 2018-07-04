package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorizacaoDAO;
import br.edu.ifsul.dao.GamerDAO;
import br.edu.ifsul.modelo.Autorizacao;
import br.edu.ifsul.modelo.Gamer;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "controleGamer")
@ViewScoped
public class ControleGamer implements Serializable {
    
    @EJB
    private GamerDAO<Gamer> dao;
    private Gamer objeto;
    private Boolean editando;
    
    @EJB
    private AutorizacaoDAO<Autorizacao> daoAutorizacao;
    private Autorizacao autorizacao;
    private Boolean editandoAutorizacao;
    
    public ControleGamer(){
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/private/gamer/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        setEditandoAutorizacao((Boolean) false);
        objeto = new Gamer();
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            setEditandoAutorizacao((Boolean) false);
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + 
                    Util.getMensagemErro(e));
        } 
    }
    
    public void excluir(Object id){
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + 
                    Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + 
                    Util.getMensagemErro(e));
        }
    }
    
    public void novaAutorizacao(){
        setEditandoAutorizacao((Boolean) true);
        setAutorizacao(new Autorizacao());
    }
    
    public void salvaAutorizacao(){
        
        Boolean repetido = false;
        
        for (Autorizacao obj : objeto.getAutorizacoes()) {
            
            if(objeto.getAutorizacoes().contains(obj)){
                repetido = true;
                break;
            }
        }
        
        if(!repetido){
            objeto.getAutorizacoes().add(getAutorizacao());
            Util.mensagemInformacao("Autorização adicionado com sucesso!");
        }else{
            Util.mensagemErro("O usuário já possui essa Autorização");
        }
        
        setEditandoAutorizacao((Boolean) false);
    }
    
    public void removerAutorizacao(Autorizacao obj){
        objeto.getAutorizacoes().remove(obj);
        Util.mensagemInformacao("Autorização removida com sucesso");
    }

    public GamerDAO<Gamer> getDao() {
        return dao;
    }

    public void setDao(GamerDAO<Gamer> dao) {
        this.dao = dao;
    }

    public Gamer getObjeto() {
        return objeto;
    }

    public void setObjeto(Gamer objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public AutorizacaoDAO<Autorizacao> getDaoAutorizacao() {
        return daoAutorizacao;
    }

    public void setDaoAutorizacao(AutorizacaoDAO<Autorizacao> daoAutorizacao) {
        this.daoAutorizacao = daoAutorizacao;
    }

    public Autorizacao getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(Autorizacao autorizacao) {
        this.autorizacao = autorizacao;
    }

    public Boolean getEditandoAutorizacao() {
        return editandoAutorizacao;
    }

    public void setEditandoAutorizacao(Boolean editandoAutorizacao) {
        this.editandoAutorizacao = editandoAutorizacao;
    }

    
  

}
