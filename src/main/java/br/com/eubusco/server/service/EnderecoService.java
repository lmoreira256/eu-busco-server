package br.com.eubusco.server.service;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eubusco.server.model.Endereco;

@RestController
@RequestMapping("enderecoService")
public interface EnderecoService {

	@CrossOrigin
	@RequestMapping(path = "salvar", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean salvar(Endereco endereco);

	@CrossOrigin
	@RequestMapping(path = "adquirirTodosUsuario", method = RequestMethod.GET, produces = "application/json")
	public abstract List<Endereco> adquirirTodosUsuario(Integer idUsuario);

}
