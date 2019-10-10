package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUsuario is a Querydsl query type for Usuario
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUsuario extends EntityPathBase<Usuario> {

    private static final long serialVersionUID = -125136005L;

    public static final QUsuario usuario = new QUsuario("usuario");

    public final NumberPath<Integer> codigoTipoUsuario = createNumber("codigoTipoUsuario", Integer.class);

    public final DateTimePath<java.util.Date> dataCadastro = createDateTime("dataCadastro", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataExclusao = createDateTime("dataExclusao", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataManutencao = createDateTime("dataManutencao", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataNascimento = createDateTime("dataNascimento", java.util.Date.class);

    public final StringPath foto = createString("foto");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath login = createString("login");

    public final StringPath nome = createString("nome");

    public final StringPath senha = createString("senha");

    public QUsuario(String variable) {
        super(Usuario.class, forVariable(variable));
    }

    public QUsuario(Path<? extends Usuario> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsuario(PathMetadata<?> metadata) {
        super(Usuario.class, metadata);
    }

}

