package br.com.eubusco.server.bo.impl;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import br.com.eubusco.server.bo.DeliveryBO;
import br.com.eubusco.server.bo.UserBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.AvaliacaoDAO;
import br.com.eubusco.server.dao.ContatoDAO;
import br.com.eubusco.server.dao.DocumentoDAO;
import br.com.eubusco.server.dao.EnderecoDAO;
import br.com.eubusco.server.dao.EntregaDAO;
import br.com.eubusco.server.dao.UserDAO;
import br.com.eubusco.server.dto.DadosUsuarioDTO;
import br.com.eubusco.server.dto.LoginDTO;
import br.com.eubusco.server.dto.NovoUsuarioDTO;
import br.com.eubusco.server.dto.ReturnLoginDTO;
import br.com.eubusco.server.enumerator.UserTypeEnum;
import br.com.eubusco.server.model.Contato;
import br.com.eubusco.server.model.Documento;
import br.com.eubusco.server.model.Endereco;
import br.com.eubusco.server.model.Usuario;
import br.com.eubusco.server.resources.Resource;
import br.com.eubusco.server.resources.S3Service;

@ManagedBean
public class UserBOImpl implements UserBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private DocumentoDAO documentoDAO;

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Autowired
	private ContatoDAO contatoDAO;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private AvaliacaoDAO avaliacaoDAO;

	@Autowired
	private EntregaDAO entregaDAO;

	@Autowired
	private DeliveryBO deliveryBO;

	@Override
	public ReturnLoginDTO login(LoginDTO loginDTO) {

		if (loginDTO == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Usuario user = userDAO.buscarPorLogin(loginDTO.getLogin());

		if (user == null) {
			throw Resource.getServerException(MensagemService.USUARIO_NAO_CADASTRADO);
		}

		if (!user.getSenha().equals(loginDTO.getSenha())) {
			throw Resource.getServerException(MensagemService.SENHA_INVALIDA);
		}

		ReturnLoginDTO returnLoginDTO = new ReturnLoginDTO();
		returnLoginDTO.setUserCode(user.getId());
		returnLoginDTO.setUserName(user.getNome());
		returnLoginDTO.setUserType(user.getCodigoTipoUsuario());

		if (user.getCodigoTipoUsuario() == UserTypeEnum.CLIENTE.toInteger()
				|| user.getCodigoTipoUsuario() == UserTypeEnum.ADMIN.toInteger()) {
			returnLoginDTO.setDeliveriesToUser(deliveryBO.getUserDeliveries(user.getId(), 1));
		}

		return returnLoginDTO;
	}

	@Override
	public Integer novoUsuario(NovoUsuarioDTO novoUsuarioDTO) {
		logger.info("==> Executando o método novoUsuario.");

		if (novoUsuarioDTO == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Usuario usuario = userDAO.buscarPorLogin(novoUsuarioDTO.getLoginUsuario());

		if (usuario != null) {
			throw Resource.getServerException(MensagemService.USUARIO_CADASTRADO);
		}

		Usuario novoUsuario = new Usuario(novoUsuarioDTO.getTipoUsuario(), novoUsuarioDTO.getLoginUsuario(),
				novoUsuarioDTO.getNomeUsuario(), novoUsuarioDTO.getSenhaUsuario(), null,
				novoUsuarioDTO.getDataNascimento(), new Date(), new Date(), null);

		usuario = userDAO.salvar(novoUsuario);

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
	public DadosUsuarioDTO buscarDadosUsuario(Integer codigoUsuario) {
		logger.info("==> Executando o método buscarDadosUsuario.");

		if (codigoUsuario == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Usuario usuario = userDAO.buscarPorId(codigoUsuario);

		DadosUsuarioDTO dadosUsuarioDTO = new DadosUsuarioDTO();
		dadosUsuarioDTO.setNota(avaliacaoDAO.adiquirirNotaUsuario(codigoUsuario, usuario.getCodigoTipoUsuario()));
		dadosUsuarioDTO.setEntregasAbertas(entregaDAO.adquirirEntregasAbertasUsuario(codigoUsuario));
		dadosUsuarioDTO.setTotalEntregas(entregaDAO.adquirirTotalEntregasUsuario(codigoUsuario));

		return dadosUsuarioDTO;
	}

	@Override
	public List<Usuario> buscarTodosUsuarios() {
		logger.info("==> Executando o método buscarTodosUsuarios.");

		return userDAO.buscarTodos();
	}

	@Override
	public String getUserName(Integer userCode) {
		return userDAO.getNameFromUser(userCode);
	}

}
