package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDocumento is a Querydsl query type for Documento
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDocumento extends EntityPathBase<Documento> {

    private static final long serialVersionUID = -238283839L;

    public static final QDocumento documento1 = new QDocumento("documento1");

    public final NumberPath<Integer> codigoTipoDocumento = createNumber("codigoTipoDocumento", Integer.class);

    public final NumberPath<Integer> codigoUsuario = createNumber("codigoUsuario", Integer.class);

    public final NumberPath<Long> documento = createNumber("documento", Long.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QDocumento(String variable) {
        super(Documento.class, forVariable(variable));
    }

    public QDocumento(Path<? extends Documento> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocumento(PathMetadata<?> metadata) {
        super(Documento.class, metadata);
    }

}

