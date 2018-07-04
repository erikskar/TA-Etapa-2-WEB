package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Gamer;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class GamerDAO<TIPO> extends DAOGenerico<Gamer> implements Serializable {

    public GamerDAO(){
        super();
        classePersistente = Gamer.class;
        ordem = "name";
    }
    
    public Gamer getObjectById(Object id) throws Exception {
        Gamer obj = em.find(Gamer.class, id);
        /**
         * A linha obj.getPermissoes().size(); é necessária para inicializar a coleção
         * para quando ela for exibida na tela não gerar um erro de 
         * lazyInicializationException
         */
        obj.getAutorizacoes().size(); 
        return obj;
    }   
    
    public Gamer localiza(String pessoa){
        Query query = em.createQuery("from Pessoa where upper(pessoa) = :pessoa");
        query.setParameter("pessoa", pessoa.toUpperCase());
        Gamer obj = (Gamer) query.getSingleResult();
        obj.getAutorizacoes().size();
        return obj;
    }
}
