package br.com.eubusco.server.dao;

import java.rmi.ServerException;
import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;

import org.glassfish.jersey.internal.inject.AnnotationLiteral;
import org.hibernate.Session;

import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.jpa.sql.JPASQLQuery;
import com.mysema.query.sql.SQLSubQuery;

public interface GenericDAO<T> {

	public abstract T salvar(T entity);

	public abstract List<T> buscarTodos();

	public abstract void inicializar();

	public abstract void detach(Object entity);

	public abstract EntityManager getEntityManager();

	/**
	 * Executa salvar/atualizar de uma lista (quando existir) na classe informada
	 * 
	 * @throws ServerException
	 */
	public abstract List<T> salvar(List<T> entities);

	/**
	 * Executa salvar/atualizar (quando existir) na classe informada
	 */

	/**
	 * Executa excluir na classe informada
	 * 
	 * @throws BusinessServerException
	 */
	public abstract T excluir(T entity);

	/**
	 * Executa excluir nos itens da lista.
	 * 
	 * @throws BusinessServerException
	 */
	public abstract List<T> excluir(List<T> entities);

	/**
	 * Exclui todos os regitros da entidade vinculada ao DAO.
	 * 
	 * @throws BusinessServerException
	 */
	public void excluirTodos();

	/**
	 * Exclui regitros pela chave primária.
	 * 
	 * @param id
	 * @return
	 * @throws BusinessServerException
	 */
	public abstract T excluirPorId(Long id);

	/**
	 * Busca pelo atributo Id da classe informada
	 */
	public abstract T buscarPorId(Long id);

	/**
	 * Retorna a implementação padrão da classe JPASQLQuery.
	 * 
	 * Obs: Esta implementação não permite o uso de consulta JPA, os relacionamentos
	 * mapeados não irão funcionar. É obrigatorio definir a relação dos joins e suas
	 * colunas programaticamente.<br>
	 * 
	 * Ex:<br>
	 * 
	 * QTicketAcesso ticketAcesso = QTicketAcesso.ticketAcesso;<br>
	 * QUsuario usuario = new QUsuario("usuario");<br>
	 * QSistema sistema = new QSistema("sistema");<br>
	 * <br>
	 * 
	 * BooleanBuilder booleanBuilder = new BooleanBuilder();<br>
	 * booleanBuilder.and(usuario.id.eq(usuarioSistemaVO.getUsuario().getId()));
	 * <br>
	 * booleanBuilder.and(sistema.id.eq(usuarioSistemaVO.getSistema().getId()));
	 * <br>
	 * <br>
	 * 
	 * sqlQuery().from(ticketAcesso)<br>
	 * .innerJoin(usuario).on(ticketAcesso.codigoUsuario.eq(usuario.id))<br>
	 * .innerjoin(sistema).on(ticketAcesso.codigoSistema.eq(sistema.id))<br>
	 * .where(booleanBuilder). uniqueResult(ticketAcesso);<br>
	 * 
	 * 
	 * @return
	 */
	public JPASQLQuery sqlQuery();

	/**
	 * Retorna a implementação padrão da classe JPASQLQuery abrindo uma transação
	 * com o banco que corresponde ao parâmetro annotationLiteral.
	 * 
	 * @param annotationLiteral
	 * @return
	 * @throws BusinessServerException
	 */
	public JPASQLQuery sqlQuery(Class<? extends AnnotationLiteral<?>> annotationLiteral);

	/**
	 * Retorna a implementação padrão da interface JPQLQuery para JPA.<br>
	 * 
	 * @return
	 */
	public abstract JPAQuery query();

	/**
	 * Retorna a implementação padrão da classe JPAQuery que permite o uso de 'JPA
	 * Native Querys'.<br>
	 * Obs: Quando existir no modelo outras entidades mapeadas e estas necessitam
	 * constar na clausula where é obrigatório criar joins.<br>
	 * Ex:<br>
	 * QTicketAcesso tickeAcesso QTicketAcesso.ticketAcesso;<br>
	 * QUsuario usuario = new QUsuario("usuario");<br>
	 * QSistema sistema = new QSistema("sistema");<br>
	 * <br>
	 * BooleanBuilder booleanBuilder = new BooleanBuilder();<br>
	 * booleanBuilder.and(usuario.id.eq(usuarioSistemaVO.getUsuario().getId()));
	 * <br>
	 * booleanBuilder.and(sistema.id.eq(usuarioSistemaVO.getSistema().getId()));
	 * <br>
	 * <br>
	 * query().from(ticketAcesso, usuario,
	 * sistema).where(booleanBuilder).uniqueResult(ticketAcesso);<br>
	 * 
	 * @return
	 */
	public JPASQLQuery sqlFrom();

	/**
	 * Retorna a implementação padrão da interface JPQLQuery para JPA'.
	 * 
	 * @return
	 */
	public abstract JPAQuery from();

	/**
	 * Retorna a implementação padrão da interface DeleteClause para JPA..
	 * 
	 * @return
	 */
	public abstract JPADeleteClause deleteClause();

	/**
	 * Retorna a implementação padrão da interface UpdateClause para JPA.
	 * 
	 * @return
	 */
	public abstract JPAUpdateClause updateClause();

	/**
	 * Retorna SQLSubQuery.
	 * 
	 * @return
	 */
	public SQLSubQuery sqlSubQuery();

	/**
	 * Retorna JPASubQuery.
	 * 
	 * @return
	 * 
	 *         public JPASubQuery subQuery();
	 */
	/**
	 * Retorna uma Session do hibernate retirada do EntityManager do JPA
	 */
	public abstract Session getSession();

	/**
	 * Retorna a conexao JDBC com o Postgres
	 */
	public abstract Connection getConnection();

	public abstract void setSession(Session session);

	/**
	 * Retorna o nome padrão para classe Q.
	 */
	public abstract String getEntityPathName();

	public abstract Integer executeUpdate(String query);

	Class<T> getPersistentClass();

	JPASubQuery subQuery();

}
