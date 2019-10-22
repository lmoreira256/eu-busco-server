package br.com.eubusco.server.service;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eubusco.server.dto.ParametroPegarEntregaDTO;
import br.com.eubusco.server.dto.RetornoEntregasDisponiveisDTO;
import br.com.eubusco.server.model.Entrega;

@RestController
@RequestMapping("entregaService")
public interface EntregaService {

	@CrossOrigin
	@RequestMapping(path = "salvar", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean salvar(Entrega entrega);

	@CrossOrigin
	@RequestMapping(path = "buscarAbertasCliente", method = RequestMethod.GET, produces = "application/json")
	public abstract List<RetornoEntregasDisponiveisDTO> buscarAbertasCliente(Integer idUsuario);

	@CrossOrigin
	@RequestMapping(path = "buscarAbertasEntregador", method = RequestMethod.GET, produces = "application/json")
	public abstract List<RetornoEntregasDisponiveisDTO> buscarAbertasEntregador(Integer idUsuario);

	@CrossOrigin
	@RequestMapping(path = "buscarDisponiveis", method = RequestMethod.GET, produces = "application/json")
	public abstract List<RetornoEntregasDisponiveisDTO> buscarDisponiveis();

	@CrossOrigin
	@RequestMapping(path = "pegarEntrega", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean pegarEntrega(ParametroPegarEntregaDTO parametroPegarEntregaDTO);

}
