package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTipoUsuario is a Querydsl query type for TipoUsuario
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTipoUsuario extends EntityPathBase<TipoUsuario> {

    private static final long serialVersionUID = 2122557223L;

    public static final QTipoUsuario tipoUsuario = new QTipoUsuario("tipoUsuario");

    public final StringPath descricao = createString("descricao");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QTipoUsuario(String variable) {
        super(TipoUsuario.class, forVariable(variable));
    }

    public QTipoUsuario(Path<? extends TipoUsuario> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTipoUsuario(PathMetadata<?> metadata) {
        super(TipoUsuario.class, metadata);
    }

}

