package br.com.eubusco.server.bo.impl;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import br.com.eubusco.server.bo.AvaliacaoBO;
import br.com.eubusco.server.bo.EntregaBO;
import br.com.eubusco.server.bo.UsuarioBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.ContatoDAO;
import br.com.eubusco.server.dao.DocumentoDAO;
import br.com.eubusco.server.dao.EnderecoDAO;
import br.com.eubusco.server.dao.UsuarioDAO;
import br.com.eubusco.server.dto.LoginDTO;
import br.com.eubusco.server.dto.NovoUsuarioDTO;
import br.com.eubusco.server.dto.RetornoEfetuarLoginDTO;
import br.com.eubusco.server.model.Contato;
import br.com.eubusco.server.model.Documento;
import br.com.eubusco.server.model.Endereco;
import br.com.eubusco.server.model.Usuario;
import br.com.eubusco.server.resources.Resource;
import br.com.eubusco.server.resources.S3Service;

@ManagedBean
public class UsuarioBOImpl implements UsuarioBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private AvaliacaoBO avaliacaoBO;

	@Autowired
	private EntregaBO entregaBO;

	@Autowired
	private DocumentoDAO documentoDAO;

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Autowired
	private ContatoDAO contatoDAO;

	@Autowired
	private S3Service s3Service;

	@Override
	public RetornoEfetuarLoginDTO efetuarLogin(LoginDTO loginDTO) {
		logger.info("*** Rodando o método efetuarLogin ***");

		if (loginDTO == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Usuario usuario = usuarioDAO.buscarPorLogin(loginDTO.getLogin());

		if (usuario == null) {
			throw Resource.getServerException(MensagemService.USUARIO_NAO_CADASTRADO);
		}

		if (!usuario.getSenha().equals(loginDTO.getSenha())) {
			throw Resource.getServerException(MensagemService.SENHA_INVALIDA);
		}

		RetornoEfetuarLoginDTO retornoEfetuarLoginDTO = new RetornoEfetuarLoginDTO();
		retornoEfetuarLoginDTO.setCodigoUsuario(usuario.getId());
		retornoEfetuarLoginDTO.setNomeUsuario(usuario.getNome());
		retornoEfetuarLoginDTO.setTipoUsuario(usuario.getCodigoTipoUsuario());
		retornoEfetuarLoginDTO.setNota(avaliacaoBO.buscarNotaUsuario(usuario.getId(), usuario.getCodigoTipoUsuario()));

		switch (usuario.getCodigoTipoUsuario()) {
		case 2:
			retornoEfetuarLoginDTO
					.setEntregasUsuarioAbertas(entregaBO.buscarEntregasUsuarioAbertas(usuario.getId(), 1));
			retornoEfetuarLoginDTO
					.setEntregasUsuarioAndamento(entregaBO.buscarEntregasUsuarioAndamento(usuario.getId(), 1));
			retornoEfetuarLoginDTO.setEntregasFinalizadas(entregaBO.buscarEntregasFinalizadas(usuario.getId(), 1));
			break;

		case 3:
			retornoEfetuarLoginDTO.setEntregasAbertas(entregaBO.buscarEntregasAbertas(usuario.getId(), 1));
			retornoEfetuarLoginDTO
					.setEntregasUsuarioAndamento(entregaBO.buscarEntregasParaEntregar(usuario.getId(), 1));
			retornoEfetuarLoginDTO.setEntregasFinalizadas(entregaBO.buscarEntregasEntregues(usuario.getId(), 1));
			break;

		default:
			retornoEfetuarLoginDTO
					.setEntregasUsuarioAbertas(entregaBO.buscarEntregasUsuarioAbertas(usuario.getId(), 1));
			retornoEfetuarLoginDTO
					.setEntregasUsuarioAndamento(entregaBO.buscarEntregasAdminAndamento(usuario.getId(), 1));
			retornoEfetuarLoginDTO.setEntregasAbertas(entregaBO.buscarEntregasAbertas(usuario.getId(), 1));
			retornoEfetuarLoginDTO.setEntregasFinalizadas(entregaBO.buscarEntregasFinalizadasAdmin(usuario.getId(), 1));
			break;
		}

		return retornoEfetuarLoginDTO;
	}

	@Override
	public String buscarNomeUsuario(Integer codigoUsuario) {
		logger.info("*** Rodando o método buscarNomeUsuario ***");

		return usuarioDAO.buscarNomeUsuario(codigoUsuario);
	}

	@Override
	public Usuario buscarPorCodigo(Integer codigoUsuario) {
		logger.info("*** Rodando o método buscarPorCodigo ***");

		return usuarioDAO.buscarPorId(codigoUsuario);
	}

	@Override
	public Integer novoUsuario(NovoUsuarioDTO novoUsuarioDTO) {
		logger.info("==> Executando o método novoUsuario.");

		if (novoUsuarioDTO == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Usuario usuario = usuarioDAO.buscarPorLogin(novoUsuarioDTO.getLoginUsuario());

		if (usuario != null) {
			throw Resource.getServerException(MensagemService.USUARIO_CADASTRADO);
		}

		Usuario novoUsuario = new Usuario(novoUsuarioDTO.getTipoUsuario(), novoUsuarioDTO.getLoginUsuario(),
				novoUsuarioDTO.getNomeUsuario(), novoUsuarioDTO.getSenhaUsuario(), null,
				novoUsuarioDTO.getDataNascimento(), new Date(), new Date(), null);

		usuario = usuarioDAO.salvar(novoUsuario);

		if (novoUsuarioDTO.getDocumentosUsuario() != null) {
			this.salvarDocumentosUsuario(usuario.getId(), novoUsuarioDTO.getDocumentosUsuario());
		}

		if (novoUsuarioDTO.getEnderecosUsuario() != null) {
			this.salvarEnderecosUsuario(usuario.getId(), novoUsuarioDTO.getEnderecosUsuario());
		}

		if (novoUsuarioDTO.getContatosUsuario() != null) {
			this.salvarContatosUsuario(usuario.getId(), novoUsuarioDTO.getContatosUsuario());
		}

		return usuario.getId();
	}

	private void salvarDocumentosUsuario(Integer idUsuario, List<Documento> documentos) {
		logger.info("==> Executando o método salvarDocumentosUsuario.");

		documentos.stream().forEach(x -> {
			x.setCodigoUsuario(idUsuario);
			documentoDAO.salvar(x);
		});

	}

	private void salvarEnderecosUsuario(Integer idUsuario, List<Endereco> enderecos) {
		logger.info("==> Executando o método salvarEnderecosUsuario.");

		enderecos.stream().forEach(x -> {
			x.setCodigoUsuario(idUsuario);
			enderecoDAO.salvar(x);
		});
	}

	private void salvarContatosUsuario(Integer idUsuario, List<Contato> contatos) {
		logger.info("==> Executando o método salvarContatosUsuario.");

		contatos.stream().forEach(x -> {
			x.setCodigoUsuario(idUsuario);
			contatoDAO.salvar(x);
		});
	}

	public URI salvarFotoUsuario(MultipartFile multipartFile) {
		return s3Service.uploadFile(multipartFile);
	}

	@Override
	public List<Usuario> buscarTodosUsuarios() {
		logger.info("==> Executando o método buscarTodosUsuarios.");

		return usuarioDAO.buscarTodos();
	}

}
