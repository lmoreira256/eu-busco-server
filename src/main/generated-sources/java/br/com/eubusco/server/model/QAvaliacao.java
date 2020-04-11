package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAvaliacao is a Querydsl query type for Avaliacao
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAvaliacao extends EntityPathBase<Avaliacao> {

    private static final long serialVersionUID = -1832611418L;

    public static final QAvaliacao avaliacao = new QAvaliacao("avaliacao");

    public final NumberPath<Integer> codigoCliente = createNumber("codigoCliente", Integer.class);

    public final NumberPath<Integer> codigoEntrega = createNumber("codigoEntrega", Integer.class);

    public final NumberPath<Integer> codigoEntregador = createNumber("codigoEntregador", Integer.class);

    public final NumberPath<Integer> codigoTipoAvaliacao = createNumber("codigoTipoAvaliacao", Integer.class);

    public final StringPath descricao = createString("descricao");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Long> nota = createNumber("nota", Long.class);

    public QAvaliacao(String variable) {
        super(Avaliacao.class, forVariable(variable));
    }

    public QAvaliacao(Path<? extends Avaliacao> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAvaliacao(PathMetadata<?> metadata) {
        super(Avaliacao.class, metadata);
    }

}

