package br.com.eubusco.server.service;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eubusco.server.dto.PaginacaoDTO;
import br.com.eubusco.server.dto.ParametroPegarEntregaDTO;
import br.com.eubusco.server.dto.RetornoBuscarEntregasDTO;
import br.com.eubusco.server.dto.RetornoEntregaAvaliacaoDTO;
import br.com.eubusco.server.model.Entrega;

@RestController
@RequestMapping("entregaService")
public interface EntregaService {

	@CrossOrigin
	@RequestMapping(path = "salvar", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean salvar(Entrega entrega);

	@CrossOrigin
	@RequestMapping(path = "pegarEntrega", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean pegarEntrega(ParametroPegarEntregaDTO parametroPegarEntregaDTO);

	@CrossOrigin
	@RequestMapping(path = "largarEntrega", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean largarEntrega(Integer codigoEntrega);

	@CrossOrigin
	@RequestMapping(path = "excluirEntrega", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean excluirEntrega(Integer codigoEntrega);

	@CrossOrigin
	@RequestMapping(path = "finalizarEntrega", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean finalizarEntrega(Integer codigoEntrega);

	@CrossOrigin
	@RequestMapping(path = "buscarEntregasAvaliacao", method = RequestMethod.GET, produces = "application/json")
	public abstract List<RetornoEntregaAvaliacaoDTO> buscarEntregasAvaliacao(Integer codigoUsuario);

	@CrossOrigin
	@RequestMapping(path = "buscarEntregasAbertas", method = RequestMethod.GET, produces = "application/json")
	public abstract PaginacaoDTO buscarEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina);

	@CrossOrigin
	@RequestMapping(path = "buscarEntregasAndamento", method = RequestMethod.GET, produces = "application/json")
	public abstract PaginacaoDTO buscarEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario, Integer pagina);

	@CrossOrigin
	@RequestMapping(path = "buscarEntregasFinalizadas", method = RequestMethod.GET, produces = "application/json")
	public abstract PaginacaoDTO buscarEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina);

	@CrossOrigin
	@RequestMapping(path = "buscarEntregasExcluidas", method = RequestMethod.GET, produces = "application/json")
	public abstract PaginacaoDTO buscarEntregasExcluidas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina);

	@CrossOrigin
	@RequestMapping(path = "buscarEntregas", method = RequestMethod.GET, produces = "application/json")
	public abstract RetornoBuscarEntregasDTO buscarEntregas(Integer codigoUsuario);

}
