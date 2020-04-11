package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCidade is a Querydsl query type for Cidade
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCidade extends EntityPathBase<Cidade> {

    private static final long serialVersionUID = 440727863L;

    public static final QCidade cidade = new QCidade("cidade");

    public final NumberPath<Long> cep = createNumber("cep", Long.class);

    public final NumberPath<Integer> codigoEstado = createNumber("codigoEstado", Integer.class);

    public final StringPath descricao = createString("descricao");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath sigla = createString("sigla");

    public QCidade(String variable) {
        super(Cidade.class, forVariable(variable));
    }

    public QCidade(Path<? extends Cidade> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCidade(PathMetadata<?> metadata) {
        super(Cidade.class, metadata);
    }

}

