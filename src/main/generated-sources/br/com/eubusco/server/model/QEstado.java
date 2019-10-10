package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEstado is a Querydsl query type for Estado
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEstado extends EntityPathBase<Estado> {

    private static final long serialVersionUID = 507698041L;

    public static final QEstado estado = new QEstado("estado");

    public final NumberPath<Integer> codigoPais = createNumber("codigoPais", Integer.class);

    public final StringPath descricao = createString("descricao");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath sigla = createString("sigla");

    public QEstado(String variable) {
        super(Estado.class, forVariable(variable));
    }

    public QEstado(Path<? extends Estado> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEstado(PathMetadata<?> metadata) {
        super(Estado.class, metadata);
    }

}

