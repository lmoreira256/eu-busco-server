package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEndereco is a Querydsl query type for Endereco
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEndereco extends EntityPathBase<Endereco> {

    private static final long serialVersionUID = 1970000156L;

    public static final QEndereco endereco = new QEndereco("endereco");

    public final StringPath bairro = createString("bairro");

    public final NumberPath<Integer> codigoCidade = createNumber("codigoCidade", Integer.class);

    public final NumberPath<Integer> codigoUsuario = createNumber("codigoUsuario", Integer.class);

    public final StringPath complemento = createString("complemento");

    public final StringPath descricaoEndereco = createString("descricaoEndereco");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Long> numero = createNumber("numero", Long.class);

    public final StringPath titulo = createString("titulo");

    public QEndereco(String variable) {
        super(Endereco.class, forVariable(variable));
    }

    public QEndereco(Path<? extends Endereco> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEndereco(PathMetadata<?> metadata) {
        super(Endereco.class, metadata);
    }

}

