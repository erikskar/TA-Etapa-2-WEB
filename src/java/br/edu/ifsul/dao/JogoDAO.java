package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Jogo;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class JogoDAO<TIPO> extends DAOGenerico<Jogo> implements Serializable {

    public JogoDAO(){
        super();
        classePersistente = Jogo.class;
        ordem = "jogo";
    }
}
