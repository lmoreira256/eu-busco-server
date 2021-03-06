package br.com.eubusco.server.service;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eubusco.server.model.Contato;

@RestController
@RequestMapping("contatoService")
public interface ContatoService {

	@CrossOrigin
	@RequestMapping(path = "adquirirPorUsuario", method = RequestMethod.GET, produces = "application/json")
	public abstract List<Contato> adquirirPorUsuario(Integer codigoUsuario);

	@CrossOrigin
	@RequestMapping(path = "salvarContato", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean salvarContato(Contato contato);

}
