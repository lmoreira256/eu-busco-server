package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTipoAvaliacao is a Querydsl query type for TipoAvaliacao
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTipoAvaliacao extends EntityPathBase<TipoAvaliacao> {

    private static final long serialVersionUID = 2126998098L;

    public static final QTipoAvaliacao tipoAvaliacao = new QTipoAvaliacao("tipoAvaliacao");

    public final StringPath descricao = createString("descricao");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QTipoAvaliacao(String variable) {
        super(TipoAvaliacao.class, forVariable(variable));
    }

    public QTipoAvaliacao(Path<? extends TipoAvaliacao> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTipoAvaliacao(PathMetadata<?> metadata) {
        super(TipoAvaliacao.class, metadata);
    }

}

