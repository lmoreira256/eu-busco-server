package br.com.eubusco.server.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEntrega is a Querydsl query type for Entrega
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEntrega extends EntityPathBase<Entrega> {

    private static final long serialVersionUID = -1583868411L;

    public static final QEntrega entrega = new QEntrega("entrega");

    public final NumberPath<Integer> codigoCliente = createNumber("codigoCliente", Integer.class);

    public final NumberPath<Integer> codigoEnderecoColeta = createNumber("codigoEnderecoColeta", Integer.class);

    public final NumberPath<Integer> codigoEnderecoEntrega = createNumber("codigoEnderecoEntrega", Integer.class);

    public final NumberPath<Integer> codigoEntregador = createNumber("codigoEntregador", Integer.class);

    public final DateTimePath<java.util.Date> dataCadastro = createDateTime("dataCadastro", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataColeta = createDateTime("dataColeta", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataExclusao = createDateTime("dataExclusao", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataManutencao = createDateTime("dataManutencao", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataPrazoEntrega = createDateTime("dataPrazoEntrega", java.util.Date.class);

    public final StringPath descricao = createString("descricao");

    public final BooleanPath flagFinalizada = createBoolean("flagFinalizada");

    public final StringPath foto = createString("foto");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath titulo = createString("titulo");

    public final StringPath volume = createString("volume");

    public QEntrega(String variable) {
        super(Entrega.class, forVariable(variable));
    }

    public QEntrega(Path<? extends Entrega> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEntrega(PathMetadata<?> metadata) {
        super(Entrega.class, metadata);
    }

}

