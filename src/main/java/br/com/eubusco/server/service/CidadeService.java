package br.com.eubusco.server.service;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eubusco.server.model.Cidade;

@RestController
@RequestMapping("cidadeService")
public interface CidadeService {

	@CrossOrigin
	@RequestMapping(path = "adquirirTodos", method = RequestMethod.GET, produces = "application/json")
	public abstract List<Cidade> adquirirTodos();

	@CrossOrigin
	@RequestMapping(path = "salvar", method = RequestMethod.POST, produces = "application/json")
	public abstract Boolean salvar(Cidade cidade);

}
