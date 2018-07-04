package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Autorizacao;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class AutorizacaoDAO<TIPO> extends DAOGenerico<Autorizacao> implements Serializable {

    public AutorizacaoDAO(){
        super();
        classePersistente = Autorizacao.class;
        ordem = "name";
    }
}
