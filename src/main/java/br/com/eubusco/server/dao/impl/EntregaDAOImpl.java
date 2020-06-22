package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QAvaliacao.avaliacao;
import static br.com.eubusco.server.model.QContato.contato;
import static br.com.eubusco.server.model.QEntrega.entrega;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Projections;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;

import br.com.eubusco.server.dao.EntregaDAO;
import br.com.eubusco.server.dto.EntregaDTO;
import br.com.eubusco.server.enumerator.TipoUsuarioEnum;
import br.com.eubusco.server.model.Entrega;
import br.com.eubusco.server.model.QCidade;
import br.com.eubusco.server.model.QEndereco;
import br.com.eubusco.server.model.QUsuario;
import br.com.eubusco.server.util.DateUtil;
import br.com.eubusco.server.util.ListUtil;
import br.com.eubusco.server.util.LongUtil;
import br.com.eubusco.server.util.QueryDslUtil;

@Repository
public class EntregaDAOImpl extends GenericDAOImpl<Entrega> implements EntregaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

	@Override
	public List<Entrega> buscarEntregasAvaliacao(Integer codigoUsuario, Integer codigoTipoUsuario) {
		SQLSubQuery subAvaliacao = sqlSubQuery().from(avaliacao)
				.where(avaliacao.codigoTipoAvaliacao.eq(codigoTipoUsuario == 2 ? 1 : 2));

		return from().where(entrega.codigoCliente.eq(codigoUsuario).or(entrega.codigoEntregador.eq(codigoUsuario))
				.and(entrega.id.notIn(subAvaliacao.list(avaliacao.codigoEntrega)))
				.and(entrega.flagFinalizada.eq(Boolean.TRUE))).list(entrega);
	}

	@Override
	public Integer buscarCodigoCliente(Integer codigoEntrega) {
		return from().where(entrega.id.eq(codigoEntrega)).uniqueResult(entrega.codigoCliente);
	}

	@Override
	public Integer buscarCodigoEntregador(Integer codigoEntrega) {
		return from().where(entrega.id.eq(codigoEntrega)).uniqueResult(entrega.codigoEntregador);
	}

	private JPAQuery montarQueryEntregas(Integer codigoUsuario, Integer pagina) {
		JPAQuery query = query().from(entrega);

		QUsuario qUsuarioCliente = new QUsuario("qUsuarioCliente");
		QUsuario qUsuarioEntregador = new QUsuario("qUsuarioEntregador");
		QEndereco qEnderecoColeta = new QEndereco("qEnderecoColeta");
		QCidade qCidadeColeta = new QCidade("cidadeColeta");
		QEndereco qEnderecoEntrega = new QEndereco("qEnderecoEntrega");
		QCidade qCidadeEntrega = new QCidade("qCidadeEntrega");

		query.innerJoin(qUsuarioCliente).on(qUsuarioCliente.id.eq(entrega.codigoCliente));
		query.leftJoin(qUsuarioEntregador).on(qUsuarioEntregador.id.eq(entrega.codigoEntregador));
		query.innerJoin(qEnderecoColeta).on(entrega.codigoEnderecoColeta.eq(qEnderecoColeta.id));
		query.innerJoin(qCidadeColeta).on(qEnderecoColeta.codigoCidade.eq(qCidadeColeta.id));
		query.innerJoin(qEnderecoEntrega).on(entrega.codigoEnderecoColeta.eq(qEnderecoEntrega.id));
		query.innerJoin(qCidadeEntrega).on(qEnderecoEntrega.codigoCidade.eq(qCidadeEntrega.id));
		query.leftJoin(contato).on(entrega.codigoCliente.eq(contato.codigoUsuario));

		query.limit(LongUtil.QUATRO);
		query.offset((pagina - 1) * 4);

		return query;
	}

	private List<Expression<?>> montarProjectionsEntregas() {
		NumberPath<Integer> codigo = new NumberPath<>(Integer.class, "codigo");
		StringPath titulo = new StringPath("titulo");
		StringPath descricao = new StringPath("descricao");
		StringPath nomeCliente = new StringPath("nomeCliente");
		StringPath nomeEntregador = new StringPath("nomeEntregador");
		StringPath cidadeEntrega = new StringPath("cidadeEntrega");
		StringPath cidadeColeta = new StringPath("cidadeColeta");
		StringPath volume = new StringPath("volume");
		BooleanPath finalizada = new BooleanPath("finalizada");
		StringPath contatoCliente = new StringPath("contatoCliente");
		StringPath dataColeta = new StringPath("dataColeta");
		StringPath dataEntrega = new StringPath("dataEntrega");
		StringPath dataExclusao = new StringPath("dataExclusao");

		QUsuario qUsuarioCliente = new QUsuario("qUsuarioCliente");
		QUsuario qUsuarioEntregador = new QUsuario("qUsuarioEntregador");
		QCidade qCidadeColeta = new QCidade("cidadeColeta");
		QCidade qCidadeEntrega = new QCidade("qCidadeEntrega");

		List<Expression<?>> projections = new ArrayList<>();
		projections.add(entrega.id.as(codigo));
		projections.add(entrega.titulo.as(titulo));
		projections.add(entrega.descricao.as(descricao));
		projections.add(entrega.volume.as(volume));
		projections.add(contato.descricao.as(contatoCliente));
		projections.add(entrega.flagFinalizada.as(finalizada));
		projections.add(QueryDslUtil.dataString(entrega.dataColeta, DateUtil.FORMATO_DD_MM_YYYY).as(dataColeta));
		projections.add(QueryDslUtil.dataString(entrega.dataPrazoEntrega, DateUtil.FORMATO_DD_MM_YYYY).as(dataEntrega));
		projections.add(QueryDslUtil.dataString(entrega.dataExclusao, DateUtil.FORMATO_DD_MM_YYYY).as(dataExclusao));
		projections.add(qUsuarioCliente.nome.as(nomeCliente));
		projections.add(qUsuarioEntregador.nome.coalesce("-").as(nomeEntregador));
		projections.add(qCidadeColeta.descricao.as(cidadeColeta));
		projections.add(qCidadeEntrega.descricao.as(cidadeEntrega));

		return projections;
	}

	private BooleanBuilder montarWhereEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoEntregador.isNull());
		where.and(entrega.dataExclusao.isNull());
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		if (tipoUsuario.equals(TipoUsuarioEnum.CLIENTE.toInteger())) {
			where.and(entrega.codigoCliente.eq(codigoUsuario));
		}

		return where;
	}

	private BooleanBuilder montarWhereEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoEntregador.isNotNull());
		where.and(entrega.dataExclusao.isNull());
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		if (tipoUsuario.equals(TipoUsuarioEnum.CLIENTE.toInteger())) {
			where.and(entrega.codigoCliente.eq(codigoUsuario));
		} else if (tipoUsuario.equals(TipoUsuarioEnum.ENTREGADOR.toInteger())) {
			where.and(entrega.codigoEntregador.eq(codigoUsuario));
		}

		return where;
	}

	private BooleanBuilder montarWhereEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.isNotNull());
		where.and(entrega.codigoEntregador.isNotNull());
		where.and(entrega.dataExclusao.isNull());
		where.and(entrega.flagFinalizada.eq(Boolean.TRUE));

		if (tipoUsuario.equals(TipoUsuarioEnum.CLIENTE.toInteger())) {
			where.and(entrega.codigoCliente.eq(codigoUsuario));
		} else if (tipoUsuario.equals(TipoUsuarioEnum.ENTREGADOR.toInteger())) {
			where.and(entrega.codigoEntregador.eq(codigoUsuario));
		}

		return where;
	}

	private BooleanBuilder montarWhereEntregasExcluidas(Integer codigoUsuario, Integer tipoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.dataExclusao.isNotNull());
		where.and(entrega.flagFinalizada.eq(Boolean.TRUE));

		if (tipoUsuario.equals(TipoUsuarioEnum.CLIENTE.toInteger())) {
			where.and(entrega.codigoCliente.eq(codigoUsuario));
		} else if (tipoUsuario.equals(TipoUsuarioEnum.ENTREGADOR.toInteger())) {
			where.and(entrega.codigoEntregador.eq(codigoUsuario));
		}

		return where;
	}

	@Override
	public List<EntregaDTO> buscarEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		JPAQuery query = montarQueryEntregas(codigoUsuario, pagina);

		query.where(montarWhereEntregasAbertas(codigoUsuario, tipoUsuario));

		List<Expression<?>> projections = montarProjectionsEntregas();

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario) {
		return query().from(entrega).where(montarWhereEntregasAbertas(codigoUsuario, tipoUsuario)).count();
	}

	@Override
	public List<EntregaDTO> buscarEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		JPAQuery query = montarQueryEntregas(codigoUsuario, pagina);

		query.where(montarWhereEntregasAndamento(codigoUsuario, tipoUsuario));

		List<Expression<?>> projections = montarProjectionsEntregas();

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario) {
		return query().from(entrega).where(montarWhereEntregasAndamento(codigoUsuario, tipoUsuario)).count();
	}

	@Override
	public List<EntregaDTO> buscarEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		JPAQuery query = montarQueryEntregas(codigoUsuario, pagina);

		query.where(montarWhereEntregasFinalizadas(codigoUsuario, tipoUsuario));

		List<Expression<?>> projections = montarProjectionsEntregas();

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario) {
		return query().from(entrega).where(montarWhereEntregasFinalizadas(codigoUsuario, tipoUsuario)).count();
	}

	@Override
	public List<EntregaDTO> buscarEntregasExcluidas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		JPAQuery query = montarQueryEntregas(codigoUsuario, pagina);

		query.where(montarWhereEntregasExcluidas(codigoUsuario, tipoUsuario));

		List<Expression<?>> projections = montarProjectionsEntregas();

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasExcluidas(Integer codigoUsuario, Integer tipoUsuario) {
		return query().from(entrega).where(montarWhereEntregasExcluidas(codigoUsuario, tipoUsuario)).count();
	}

}
