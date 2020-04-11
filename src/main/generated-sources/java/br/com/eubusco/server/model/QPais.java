package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPais is a Querydsl query type for Pais
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPais extends EntityPathBase<Pais> {

    private static final long serialVersionUID = -1058378322L;

    public static final QPais pais = new QPais("pais");

    public final StringPath descricao = createString("descricao");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath sigla = createString("sigla");

    public QPais(String variable) {
        super(Pais.class, forVariable(variable));
    }

    public QPais(Path<? extends Pais> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPais(PathMetadata<?> metadata) {
        super(Pais.class, metadata);
    }

}

