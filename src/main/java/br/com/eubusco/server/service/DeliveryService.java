package br.com.eubusco.server.service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eubusco.server.dto.PaginationDTO;

@RestController
@RequestMapping("deliveryService")
public interface DeliveryService {

	@CrossOrigin
	@RequestMapping(path = "getUserDeliveries", method = RequestMethod.GET, produces = "application/json")
	public abstract PaginationDTO getUserDeliveries(Integer userCode, Integer page);

}
