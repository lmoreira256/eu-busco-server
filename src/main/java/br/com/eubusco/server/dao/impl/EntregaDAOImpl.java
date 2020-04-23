package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QAvaliacao.avaliacao;
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
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;

import br.com.eubusco.server.dao.EntregaDAO;
import br.com.eubusco.server.dto.EntregaDTO;
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
	public Long adquirirEntregasAbertasUsuario(Integer idUsuario) {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoCliente.eq(idUsuario))
				.or(entrega.codigoEntregador.eq(idUsuario)).and(entrega.dataExclusao.isNull())
				.and(entrega.flagFinalizada.isFalse())).count();
	}

	@Override
	public Long adquirirTotalEntregasUsuario(Integer idUsuario) {
		return from().where(entrega.codigoCliente.eq(idUsuario).or(entrega.codigoEntregador.eq(idUsuario))).count();
	}

	@Override
	public List<Entrega> buscarAbertasCliente(Integer idUsuario) {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoCliente.eq(idUsuario))
				.and(entrega.dataExclusao.isNull())).list(entrega);
	}

	@Override
	public List<Entrega> buscarAbertasEntregador(Integer idUsuario) {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoEntregador.eq(idUsuario))
				.and(entrega.dataExclusao.isNull())).list(entrega);
	}

	@Override
	public List<Entrega> buscarDisponiveis() {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoEntregador.isNull())
				.and(entrega.dataExclusao.isNull())).list(entrega);
	}

	@Override
	public List<Entrega> buscarTodasAbertas() {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.dataExclusao.isNull())).list(entrega);
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

	@Override
	public List<EntregaDTO> buscarEntregasUsuarioAbertas(Integer codigoUsuario, Integer pagina) {
		NumberPath<Integer> codigo = new NumberPath<>(Integer.class, "codigo");
		StringPath titulo = new StringPath("titulo");
		StringPath descricao = new StringPath("descricao");
		StringPath nomeCliente = new StringPath("nomeCliente");
		StringPath cidadeEntrega = new StringPath("cidadeEntrega");
		StringPath cidadeColeta = new StringPath("cidadeColeta");
		StringPath volume = new StringPath("volume");
		StringPath dataColeta = new StringPath("dataColeta");
		StringPath dataEntrega = new StringPath("dataEntrega");

		JPAQuery query = query().from(entrega);

		QUsuario qUsuarioCliente = new QUsuario("qUsuarioCliente");
		QEndereco qEnderecoColeta = new QEndereco("qEnderecoColeta");
		QCidade qCidadeColeta = new QCidade("cidadeColeta");
		QEndereco qEnderecoEntrega = new QEndereco("qEnderecoEntrega");
		QCidade qCidadeEntrega = new QCidade("qCidadeEntrega");

		query.innerJoin(qUsuarioCliente).on(qUsuarioCliente.id.eq(entrega.codigoCliente));
		query.innerJoin(qEnderecoColeta).on(entrega.codigoEnderecoColeta.eq(qEnderecoColeta.id));
		query.innerJoin(qCidadeColeta).on(qEnderecoColeta.codigoCidade.eq(qCidadeColeta.id));
		query.innerJoin(qEnderecoEntrega).on(entrega.codigoEnderecoColeta.eq(qEnderecoEntrega.id));
		query.innerJoin(qCidadeEntrega).on(qEnderecoColeta.codigoCidade.eq(qCidadeEntrega.id));

		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.eq(codigoUsuario));
		where.and(entrega.codigoEntregador.isNull());
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		query.where(where);

		query.limit(LongUtil.QUATRO);
		query.offset((pagina - 1) * 4);

		List<Expression<?>> projections = new ArrayList<>();
		projections.add(entrega.id.as(codigo));
		projections.add(entrega.titulo.as(titulo));
		projections.add(entrega.descricao.as(descricao));
		projections.add(entrega.volume.as(volume));
		projections.add(QueryDslUtil.dataString(entrega.dataColeta, DateUtil.FORMATO_DD_MM_YYYY).as(dataColeta));
		projections.add(QueryDslUtil.dataString(entrega.dataPrazoEntrega, DateUtil.FORMATO_DD_MM_YYYY).as(dataEntrega));
		projections.add(qUsuarioCliente.nome.as(nomeCliente));
		projections.add(qCidadeColeta.descricao.as(cidadeColeta));
		projections.add(qCidadeEntrega.descricao.as(cidadeEntrega));

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasUsuarioAbertas(Integer codigoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.eq(codigoUsuario));
		where.and(entrega.codigoEntregador.isNull());
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		return query().from(entrega).where(where).count();
	}

	@Override
	public List<EntregaDTO> buscarEntregasUsuarioAndamento(Integer codigoUsuario, Integer pagina) {
		NumberPath<Integer> codigo = new NumberPath<>(Integer.class, "codigo");
		StringPath titulo = new StringPath("titulo");
		StringPath descricao = new StringPath("descricao");
		StringPath nomeCliente = new StringPath("nomeCliente");
		StringPath nomeEntregador = new StringPath("nomeEntregador");
		StringPath cidadeEntrega = new StringPath("cidadeEntrega");
		StringPath cidadeColeta = new StringPath("cidadeColeta");
		StringPath volume = new StringPath("volume");
		StringPath dataColeta = new StringPath("dataColeta");
		StringPath dataEntrega = new StringPath("dataEntrega");

		JPAQuery query = query().from(entrega);

		QUsuario qUsuarioCliente = new QUsuario("qUsuarioCliente");
		QUsuario qUsuarioEntregador = new QUsuario("qUsuarioEntregador");
		QEndereco qEnderecoColeta = new QEndereco("qEnderecoColeta");
		QCidade qCidadeColeta = new QCidade("cidadeColeta");
		QEndereco qEnderecoEntrega = new QEndereco("qEnderecoEntrega");
		QCidade qCidadeEntrega = new QCidade("qCidadeEntrega");

		query.innerJoin(qUsuarioCliente).on(qUsuarioCliente.id.eq(entrega.codigoCliente));
		query.innerJoin(qUsuarioEntregador).on(qUsuarioEntregador.id.eq(entrega.codigoEntregador));
		query.innerJoin(qEnderecoColeta).on(entrega.codigoEnderecoColeta.eq(qEnderecoColeta.id));
		query.innerJoin(qCidadeColeta).on(qEnderecoColeta.codigoCidade.eq(qCidadeColeta.id));
		query.innerJoin(qEnderecoEntrega).on(entrega.codigoEnderecoColeta.eq(qEnderecoEntrega.id));
		query.innerJoin(qCidadeEntrega).on(qEnderecoColeta.codigoCidade.eq(qCidadeEntrega.id));

		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.eq(codigoUsuario));
		where.and(entrega.codigoEntregador.isNotNull());
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		query.where(where);

		query.limit(LongUtil.QUATRO);
		query.offset((pagina - 1) * 4);

		List<Expression<?>> projections = new ArrayList<>();
		projections.add(entrega.id.as(codigo));
		projections.add(entrega.titulo.as(titulo));
		projections.add(entrega.descricao.as(descricao));
		projections.add(entrega.volume.as(volume));
		projections.add(QueryDslUtil.dataString(entrega.dataColeta, DateUtil.FORMATO_DD_MM_YYYY).as(dataColeta));
		projections.add(QueryDslUtil.dataString(entrega.dataPrazoEntrega, DateUtil.FORMATO_DD_MM_YYYY).as(dataEntrega));
		projections.add(qUsuarioCliente.nome.as(nomeCliente));
		projections.add(qUsuarioEntregador.nome.as(nomeEntregador));
		projections.add(qCidadeColeta.descricao.as(cidadeColeta));
		projections.add(qCidadeEntrega.descricao.as(cidadeEntrega));

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasUsuarioAndamento(Integer codigoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.eq(codigoUsuario));
		where.and(entrega.codigoEntregador.isNotNull());
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		return query().from(entrega).where(where).count();
	}

	@Override
	public List<EntregaDTO> buscarEntregasAbertas(Integer codigoUsuario, Integer pagina) {
		NumberPath<Integer> codigo = new NumberPath<>(Integer.class, "codigo");
		StringPath titulo = new StringPath("titulo");
		StringPath descricao = new StringPath("descricao");
		StringPath nomeCliente = new StringPath("nomeCliente");
		StringPath cidadeEntrega = new StringPath("cidadeEntrega");
		StringPath cidadeColeta = new StringPath("cidadeColeta");
		StringPath volume = new StringPath("volume");
		StringPath dataColeta = new StringPath("dataColeta");
		StringPath dataEntrega = new StringPath("dataEntrega");

		JPAQuery query = query().from(entrega);

		QUsuario qUsuarioCliente = new QUsuario("qUsuarioCliente");
		QEndereco qEnderecoColeta = new QEndereco("qEnderecoColeta");
		QCidade qCidadeColeta = new QCidade("cidadeColeta");
		QEndereco qEnderecoEntrega = new QEndereco("qEnderecoEntrega");
		QCidade qCidadeEntrega = new QCidade("qCidadeEntrega");

		query.innerJoin(qUsuarioCliente).on(qUsuarioCliente.id.eq(entrega.codigoCliente));
		query.innerJoin(qEnderecoColeta).on(entrega.codigoEnderecoColeta.eq(qEnderecoColeta.id));
		query.innerJoin(qCidadeColeta).on(qEnderecoColeta.codigoCidade.eq(qCidadeColeta.id));
		query.innerJoin(qEnderecoEntrega).on(entrega.codigoEnderecoColeta.eq(qEnderecoEntrega.id));
		query.innerJoin(qCidadeEntrega).on(qEnderecoColeta.codigoCidade.eq(qCidadeEntrega.id));

		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.ne(codigoUsuario));
		where.and(entrega.codigoEntregador.isNull());
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		query.where(where);

		query.limit(LongUtil.QUATRO);
		query.offset((pagina - 1) * 4);

		List<Expression<?>> projections = new ArrayList<>();
		projections.add(entrega.id.as(codigo));
		projections.add(entrega.titulo.as(titulo));
		projections.add(entrega.descricao.as(descricao));
		projections.add(entrega.volume.as(volume));
		projections.add(QueryDslUtil.dataString(entrega.dataColeta, DateUtil.FORMATO_DD_MM_YYYY).as(dataColeta));
		projections.add(QueryDslUtil.dataString(entrega.dataPrazoEntrega, DateUtil.FORMATO_DD_MM_YYYY).as(dataEntrega));
		projections.add(qUsuarioCliente.nome.as(nomeCliente));
		projections.add(qCidadeColeta.descricao.as(cidadeColeta));
		projections.add(qCidadeEntrega.descricao.as(cidadeEntrega));

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasAbertas(Integer codigoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.ne(codigoUsuario));
		where.and(entrega.codigoEntregador.isNull());
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		return query().from(entrega).where(where).count();
	}

	@Override
	public List<EntregaDTO> buscarEntregasParaEntregar(Integer codigoUsuario, Integer pagina) {
		NumberPath<Integer> codigo = new NumberPath<>(Integer.class, "codigo");
		StringPath titulo = new StringPath("titulo");
		StringPath descricao = new StringPath("descricao");
		StringPath nomeCliente = new StringPath("nomeCliente");
		StringPath nomeEntregador = new StringPath("nomeEntregador");
		StringPath cidadeEntrega = new StringPath("cidadeEntrega");
		StringPath cidadeColeta = new StringPath("cidadeColeta");
		StringPath volume = new StringPath("volume");
		StringPath dataColeta = new StringPath("dataColeta");
		StringPath dataEntrega = new StringPath("dataEntrega");

		JPAQuery query = query().from(entrega);

		QUsuario qUsuarioCliente = new QUsuario("qUsuarioCliente");
		QUsuario qUsuarioEntregador = new QUsuario("qUsuarioEntregador");
		QEndereco qEnderecoColeta = new QEndereco("qEnderecoColeta");
		QCidade qCidadeColeta = new QCidade("cidadeColeta");
		QEndereco qEnderecoEntrega = new QEndereco("qEnderecoEntrega");
		QCidade qCidadeEntrega = new QCidade("qCidadeEntrega");

		query.innerJoin(qUsuarioCliente).on(qUsuarioCliente.id.eq(entrega.codigoCliente));
		query.innerJoin(qUsuarioEntregador).on(qUsuarioEntregador.id.eq(entrega.codigoEntregador));
		query.innerJoin(qEnderecoColeta).on(entrega.codigoEnderecoColeta.eq(qEnderecoColeta.id));
		query.innerJoin(qCidadeColeta).on(qEnderecoColeta.codigoCidade.eq(qCidadeColeta.id));
		query.innerJoin(qEnderecoEntrega).on(entrega.codigoEnderecoColeta.eq(qEnderecoEntrega.id));
		query.innerJoin(qCidadeEntrega).on(qEnderecoColeta.codigoCidade.eq(qCidadeEntrega.id));

		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.ne(codigoUsuario));
		where.and(entrega.codigoEntregador.eq(codigoUsuario));
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		query.where(where);

		query.limit(LongUtil.QUATRO);
		query.offset((pagina - 1) * 4);

		List<Expression<?>> projections = new ArrayList<>();
		projections.add(entrega.id.as(codigo));
		projections.add(entrega.titulo.as(titulo));
		projections.add(entrega.descricao.as(descricao));
		projections.add(entrega.volume.as(volume));
		projections.add(QueryDslUtil.dataString(entrega.dataColeta, DateUtil.FORMATO_DD_MM_YYYY).as(dataColeta));
		projections.add(QueryDslUtil.dataString(entrega.dataPrazoEntrega, DateUtil.FORMATO_DD_MM_YYYY).as(dataEntrega));
		projections.add(qUsuarioCliente.nome.as(nomeCliente));
		projections.add(qUsuarioEntregador.nome.as(nomeEntregador));
		projections.add(qCidadeColeta.descricao.as(cidadeColeta));
		projections.add(qCidadeEntrega.descricao.as(cidadeEntrega));

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasParaEntregar(Integer codigoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.ne(codigoUsuario));
		where.and(entrega.codigoEntregador.eq(codigoUsuario));
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		return query().from(entrega).where(where).count();
	}

	@Override
	public List<EntregaDTO> buscarEntregasEntregues(Integer codigoUsuario, Integer pagina) {
		NumberPath<Integer> codigo = new NumberPath<>(Integer.class, "codigo");
		StringPath titulo = new StringPath("titulo");
		StringPath descricao = new StringPath("descricao");
		StringPath nomeCliente = new StringPath("nomeCliente");
		StringPath nomeEntregador = new StringPath("nomeEntregador");
		StringPath cidadeEntrega = new StringPath("cidadeEntrega");
		StringPath cidadeColeta = new StringPath("cidadeColeta");
		StringPath volume = new StringPath("volume");
		StringPath dataColeta = new StringPath("dataColeta");
		StringPath dataEntrega = new StringPath("dataEntrega");

		JPAQuery query = query().from(entrega);

		QUsuario qUsuarioCliente = new QUsuario("qUsuarioCliente");
		QUsuario qUsuarioEntregador = new QUsuario("qUsuarioEntregador");
		QEndereco qEnderecoColeta = new QEndereco("qEnderecoColeta");
		QCidade qCidadeColeta = new QCidade("cidadeColeta");
		QEndereco qEnderecoEntrega = new QEndereco("qEnderecoEntrega");
		QCidade qCidadeEntrega = new QCidade("qCidadeEntrega");

		query.innerJoin(qUsuarioCliente).on(qUsuarioCliente.id.eq(entrega.codigoCliente));
		query.innerJoin(qUsuarioEntregador).on(qUsuarioEntregador.id.eq(entrega.codigoEntregador));
		query.innerJoin(qEnderecoColeta).on(entrega.codigoEnderecoColeta.eq(qEnderecoColeta.id));
		query.innerJoin(qCidadeColeta).on(qEnderecoColeta.codigoCidade.eq(qCidadeColeta.id));
		query.innerJoin(qEnderecoEntrega).on(entrega.codigoEnderecoColeta.eq(qEnderecoEntrega.id));
		query.innerJoin(qCidadeEntrega).on(qEnderecoColeta.codigoCidade.eq(qCidadeEntrega.id));

		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.ne(codigoUsuario));
		where.and(entrega.codigoEntregador.eq(codigoUsuario));
		where.and(entrega.flagFinalizada.eq(Boolean.TRUE));

		query.where(where);

		query.limit(LongUtil.QUATRO);
		query.offset((pagina - 1) * 4);

		List<Expression<?>> projections = new ArrayList<>();
		projections.add(entrega.id.as(codigo));
		projections.add(entrega.titulo.as(titulo));
		projections.add(entrega.descricao.as(descricao));
		projections.add(entrega.volume.as(volume));
		projections.add(QueryDslUtil.dataString(entrega.dataColeta, DateUtil.FORMATO_DD_MM_YYYY).as(dataColeta));
		projections.add(QueryDslUtil.dataString(entrega.dataPrazoEntrega, DateUtil.FORMATO_DD_MM_YYYY).as(dataEntrega));
		projections.add(qUsuarioCliente.nome.as(nomeCliente));
		projections.add(qUsuarioEntregador.nome.as(nomeEntregador));
		projections.add(qCidadeColeta.descricao.as(cidadeColeta));
		projections.add(qCidadeEntrega.descricao.as(cidadeEntrega));

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasEntregues(Integer codigoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.ne(codigoUsuario));
		where.and(entrega.codigoEntregador.eq(codigoUsuario));
		where.and(entrega.flagFinalizada.eq(Boolean.TRUE));

		return query().from(entrega).where(where).count();
	}

	@Override
	public List<EntregaDTO> buscarEntregasFinalizadas(Integer codigoUsuario, Integer pagina) {
		NumberPath<Integer> codigo = new NumberPath<>(Integer.class, "codigo");
		StringPath titulo = new StringPath("titulo");
		StringPath descricao = new StringPath("descricao");
		StringPath nomeCliente = new StringPath("nomeCliente");
		StringPath nomeEntregador = new StringPath("nomeEntregador");
		StringPath cidadeEntrega = new StringPath("cidadeEntrega");
		StringPath cidadeColeta = new StringPath("cidadeColeta");
		StringPath volume = new StringPath("volume");
		StringPath dataColeta = new StringPath("dataColeta");
		StringPath dataEntrega = new StringPath("dataEntrega");

		JPAQuery query = query().from(entrega);

		QUsuario qUsuarioCliente = new QUsuario("qUsuarioCliente");
		QUsuario qUsuarioEntregador = new QUsuario("qUsuarioEntregador");
		QEndereco qEnderecoColeta = new QEndereco("qEnderecoColeta");
		QCidade qCidadeColeta = new QCidade("cidadeColeta");
		QEndereco qEnderecoEntrega = new QEndereco("qEnderecoEntrega");
		QCidade qCidadeEntrega = new QCidade("qCidadeEntrega");

		query.innerJoin(qUsuarioCliente).on(qUsuarioCliente.id.eq(entrega.codigoCliente));
		query.innerJoin(qUsuarioEntregador).on(qUsuarioEntregador.id.eq(entrega.codigoEntregador));
		query.innerJoin(qEnderecoColeta).on(entrega.codigoEnderecoColeta.eq(qEnderecoColeta.id));
		query.innerJoin(qCidadeColeta).on(qEnderecoColeta.codigoCidade.eq(qCidadeColeta.id));
		query.innerJoin(qEnderecoEntrega).on(entrega.codigoEnderecoColeta.eq(qEnderecoEntrega.id));
		query.innerJoin(qCidadeEntrega).on(qEnderecoColeta.codigoCidade.eq(qCidadeEntrega.id));

		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.eq(codigoUsuario));
		where.and(entrega.codigoEntregador.ne(codigoUsuario));
		where.and(entrega.flagFinalizada.eq(Boolean.TRUE));

		query.where(where);

		query.limit(LongUtil.QUATRO);
		query.offset((pagina - 1) * 4);

		List<Expression<?>> projections = new ArrayList<>();
		projections.add(entrega.id.as(codigo));
		projections.add(entrega.titulo.as(titulo));
		projections.add(entrega.descricao.as(descricao));
		projections.add(entrega.volume.as(volume));
		projections.add(QueryDslUtil.dataString(entrega.dataColeta, DateUtil.FORMATO_DD_MM_YYYY).as(dataColeta));
		projections.add(QueryDslUtil.dataString(entrega.dataPrazoEntrega, DateUtil.FORMATO_DD_MM_YYYY).as(dataEntrega));
		projections.add(qUsuarioCliente.nome.as(nomeCliente));
		projections.add(qUsuarioEntregador.nome.as(nomeEntregador));
		projections.add(qCidadeColeta.descricao.as(cidadeColeta));
		projections.add(qCidadeEntrega.descricao.as(cidadeEntrega));

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasFinalizadas(Integer codigoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.eq(codigoUsuario));
		where.and(entrega.codigoEntregador.ne(codigoUsuario));
		where.and(entrega.flagFinalizada.eq(Boolean.TRUE));

		return query().from(entrega).where(where).count();
	}

	@Override
	public List<EntregaDTO> buscarEntregasFinalizadasAdmin(Integer codigoUsuario, Integer pagina) {
		NumberPath<Integer> codigo = new NumberPath<>(Integer.class, "codigo");
		StringPath titulo = new StringPath("titulo");
		StringPath descricao = new StringPath("descricao");
		StringPath nomeCliente = new StringPath("nomeCliente");
		StringPath nomeEntregador = new StringPath("nomeEntregador");
		StringPath cidadeEntrega = new StringPath("cidadeEntrega");
		StringPath cidadeColeta = new StringPath("cidadeColeta");
		StringPath volume = new StringPath("volume");
		StringPath dataColeta = new StringPath("dataColeta");
		StringPath dataEntrega = new StringPath("dataEntrega");

		JPAQuery query = query().from(entrega);

		QUsuario qUsuarioCliente = new QUsuario("qUsuarioCliente");
		QUsuario qUsuarioEntregador = new QUsuario("qUsuarioEntregador");
		QEndereco qEnderecoColeta = new QEndereco("qEnderecoColeta");
		QCidade qCidadeColeta = new QCidade("cidadeColeta");
		QEndereco qEnderecoEntrega = new QEndereco("qEnderecoEntrega");
		QCidade qCidadeEntrega = new QCidade("qCidadeEntrega");

		query.innerJoin(qUsuarioCliente).on(qUsuarioCliente.id.eq(entrega.codigoCliente));
		query.innerJoin(qUsuarioEntregador).on(qUsuarioEntregador.id.eq(entrega.codigoEntregador));
		query.innerJoin(qEnderecoColeta).on(entrega.codigoEnderecoColeta.eq(qEnderecoColeta.id));
		query.innerJoin(qCidadeColeta).on(qEnderecoColeta.codigoCidade.eq(qCidadeColeta.id));
		query.innerJoin(qEnderecoEntrega).on(entrega.codigoEnderecoColeta.eq(qEnderecoEntrega.id));
		query.innerJoin(qCidadeEntrega).on(qEnderecoColeta.codigoCidade.eq(qCidadeEntrega.id));

		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.eq(codigoUsuario).or(entrega.codigoEntregador.eq(codigoUsuario)));
		where.and(entrega.flagFinalizada.eq(Boolean.TRUE));

		query.where(where);

		query.limit(LongUtil.QUATRO);
		query.offset((pagina - 1) * 4);

		List<Expression<?>> projections = new ArrayList<>();
		projections.add(entrega.id.as(codigo));
		projections.add(entrega.titulo.as(titulo));
		projections.add(entrega.descricao.as(descricao));
		projections.add(entrega.volume.as(volume));
		projections.add(QueryDslUtil.dataString(entrega.dataColeta, DateUtil.FORMATO_DD_MM_YYYY).as(dataColeta));
		projections.add(QueryDslUtil.dataString(entrega.dataPrazoEntrega, DateUtil.FORMATO_DD_MM_YYYY).as(dataEntrega));
		projections.add(qUsuarioCliente.nome.as(nomeCliente));
		projections.add(qUsuarioEntregador.nome.as(nomeEntregador));
		projections.add(qCidadeColeta.descricao.as(cidadeColeta));
		projections.add(qCidadeEntrega.descricao.as(cidadeEntrega));

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasFinalizadasAdmin(Integer codigoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.eq(codigoUsuario).or(entrega.codigoEntregador.eq(codigoUsuario)));
		where.and(entrega.flagFinalizada.eq(Boolean.TRUE));

		return query().from(entrega).where(where).count();
	}

	@Override
	public List<EntregaDTO> buscarEntregasAdminAndamento(Integer codigoUsuario, Integer pagina) {
		NumberPath<Integer> codigo = new NumberPath<>(Integer.class, "codigo");
		StringPath titulo = new StringPath("titulo");
		StringPath descricao = new StringPath("descricao");
		StringPath nomeCliente = new StringPath("nomeCliente");
		StringPath nomeEntregador = new StringPath("nomeEntregador");
		StringPath cidadeEntrega = new StringPath("cidadeEntrega");
		StringPath cidadeColeta = new StringPath("cidadeColeta");
		StringPath volume = new StringPath("volume");
		StringPath dataColeta = new StringPath("dataColeta");
		StringPath dataEntrega = new StringPath("dataEntrega");

		JPAQuery query = query().from(entrega);

		QUsuario qUsuarioCliente = new QUsuario("qUsuarioCliente");
		QUsuario qUsuarioEntregador = new QUsuario("qUsuarioEntregador");
		QEndereco qEnderecoColeta = new QEndereco("qEnderecoColeta");
		QCidade qCidadeColeta = new QCidade("cidadeColeta");
		QEndereco qEnderecoEntrega = new QEndereco("qEnderecoEntrega");
		QCidade qCidadeEntrega = new QCidade("qCidadeEntrega");

		query.innerJoin(qUsuarioCliente).on(qUsuarioCliente.id.eq(entrega.codigoCliente));
		query.innerJoin(qUsuarioEntregador).on(qUsuarioEntregador.id.eq(entrega.codigoEntregador));
		query.innerJoin(qEnderecoColeta).on(entrega.codigoEnderecoColeta.eq(qEnderecoColeta.id));
		query.innerJoin(qCidadeColeta).on(qEnderecoColeta.codigoCidade.eq(qCidadeColeta.id));
		query.innerJoin(qEnderecoEntrega).on(entrega.codigoEnderecoColeta.eq(qEnderecoEntrega.id));
		query.innerJoin(qCidadeEntrega).on(qEnderecoColeta.codigoCidade.eq(qCidadeEntrega.id));

		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.eq(codigoUsuario).or(entrega.codigoEntregador.eq(codigoUsuario)));
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		query.where(where);

		query.limit(LongUtil.QUATRO);
		query.offset((pagina - 1) * 4);

		List<Expression<?>> projections = new ArrayList<>();
		projections.add(entrega.id.as(codigo));
		projections.add(entrega.titulo.as(titulo));
		projections.add(entrega.descricao.as(descricao));
		projections.add(entrega.volume.as(volume));
		projections.add(QueryDslUtil.dataString(entrega.dataColeta, DateUtil.FORMATO_DD_MM_YYYY).as(dataColeta));
		projections.add(QueryDslUtil.dataString(entrega.dataPrazoEntrega, DateUtil.FORMATO_DD_MM_YYYY).as(dataEntrega));
		projections.add(qUsuarioCliente.nome.as(nomeCliente));
		projections.add(qUsuarioEntregador.nome.as(nomeEntregador));
		projections.add(qCidadeColeta.descricao.as(cidadeColeta));
		projections.add(qCidadeEntrega.descricao.as(cidadeEntrega));

		return query.list(Projections.fields(EntregaDTO.class, ListUtil.from(projections).toArray(Expression.class)));
	}

	@Override
	public Long buscarTotalEntregasAdminAndamento(Integer codigoUsuario) {
		BooleanBuilder where = new BooleanBuilder();
		where.and(entrega.codigoCliente.eq(codigoUsuario).or(entrega.codigoEntregador.eq(codigoUsuario)));
		where.and(entrega.flagFinalizada.eq(Boolean.FALSE));

		return query().from(entrega).where(where).count();
	}

}
