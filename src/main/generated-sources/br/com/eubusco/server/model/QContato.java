package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QContato is a Querydsl query type for Contato
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QContato extends EntityPathBase<Contato> {

    private static final long serialVersionUID = 959235703L;

    public static final QContato contato = new QContato("contato");

    public final NumberPath<Integer> codigoTipoContato = createNumber("codigoTipoContato", Integer.class);

    public final NumberPath<Integer> codigoUsuario = createNumber("codigoUsuario", Integer.class);

    public final StringPath descricao = createString("descricao");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QContato(String variable) {
        super(Contato.class, forVariable(variable));
    }

    public QContato(Path<? extends Contato> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContato(PathMetadata<?> metadata) {
        super(Contato.class, metadata);
    }

}

