package br.com.eubusco.server.service;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eubusco.server.model.Entrega;

@RestController
@RequestMapping("entregaService")
public interface EntregaService {

	@CrossOrigin
	@RequestMapping(path = "salvar", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean salvar(Entrega entrega);

	@CrossOrigin
	@RequestMapping(path = "buscarAbertasCliente", method = RequestMethod.GET, produces = "application/json")
	public abstract List<Entrega> buscarAbertasCliente(Integer idUsuario);

}
