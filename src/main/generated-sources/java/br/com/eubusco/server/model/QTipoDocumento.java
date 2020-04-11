package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTipoDocumento is a Querydsl query type for TipoDocumento
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTipoDocumento extends EntityPathBase<TipoDocumento> {

    private static final long serialVersionUID = -573641619L;

    public static final QTipoDocumento tipoDocumento = new QTipoDocumento("tipoDocumento");

    public final StringPath descricao = createString("descricao");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QTipoDocumento(String variable) {
        super(TipoDocumento.class, forVariable(variable));
    }

    public QTipoDocumento(Path<? extends TipoDocumento> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTipoDocumento(PathMetadata<?> metadata) {
        super(TipoDocumento.class, metadata);
    }

}

