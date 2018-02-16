package br.com.desafio.controllers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.desafio.daos.ClienteDao;
import br.com.desafio.daos.ClienteXServicoDao;
import br.com.desafio.daos.ServicoDao;
import br.com.desafio.enums.Pago;
import br.com.desafio.enums.Plano;
import br.com.desafio.misc.DateManipulation;
import br.com.desafio.misc.Descontos;
import br.com.desafio.models.Cliente;
import br.com.desafio.models.ClienteXServico;
import br.com.desafio.models.Servico;

@Controller
@RequestMapping("/clientexservico")
@Transactional
public class ClienteXServicoController {

	@Autowired
	private ClienteXServicoDao clienteXServicoDao;

	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private ServicoDao servicoDao;

	@GetMapping("/form")
	public ModelAndView form(ClienteXServico clienteXServico) {
		ModelAndView modelAndView = new ModelAndView("clientexservico/form-add");

		List<Cliente> clientes = clienteDao.all();
		List<Servico> servicos = servicoDao.all();

		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("servicos", servicos);

		return modelAndView;

	}

	@PostMapping
	public ModelAndView save(@Valid ClienteXServico clienteXServico, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return form(clienteXServico);
		}
		clienteXServicoDao.save(clienteXServico);
		return new ModelAndView("redirect:/clientexservico");
	}

	@GetMapping("/{id}")
	public ModelAndView load(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("/clientexservico/form-update");
		List<Cliente> clientes = clienteDao.all();
		List<Servico> servicos = servicoDao.all();

		modelAndView.addObject("clientes", clientes);
		modelAndView.addObject("servicos", servicos);

		return modelAndView;
	}

	@GetMapping
	public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page) {
		ModelAndView modelAndView = new ModelAndView("clientexservico/listagem");
		modelAndView.addObject("paginatedList", clienteXServicoDao.paginated(page, 10));
		return modelAndView;
	}

	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		ClienteXServico clienteXServico = clienteXServicoDao.findById(id);
		clienteXServicoDao.remove(clienteXServico);
		return "redirect:/clientexservico";
	}

	@GetMapping("/efetuapag/{id}")
	public ModelAndView efetuaPagamento(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("clientexservico/form-efetpag");
		
		ClienteXServico clienteXServico = clienteXServicoDao.findById(id);
		BigDecimal valorDescontado = clienteXServico.getServico().getPreco();
		
		int difDiasPag =  DateManipulation.diferencaEmDias(Calendar.getInstance(), clienteXServico.getDataEntrega());
		
		double porcentagem = Descontos.calculoDesconto(clienteXServico.getCliente().getPlano(), difDiasPag);
		
		valorDescontado = valorDescontado.subtract(valorDescontado.multiply(new BigDecimal(porcentagem)));

		String descontoFormatado = String.format("%.2f", valorDescontado);

		//SALVA COMO PAGO
		clienteXServico.setPago(Pago.SIM);
		clienteXServicoDao.update(clienteXServico);
		
		modelAndView.addObject("clienteXServico", clienteXServico);
		modelAndView.addObject("valorDescontado", descontoFormatado);
		
		return modelAndView;
	}

	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable("id") Integer id, @Valid ClienteXServico clienteXServico,
			BindingResult bindingResult) {
		clienteXServico.setId(id);
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("clientexservico/form-update");
		}
		clienteXServicoDao.update(clienteXServico);
		return new ModelAndView("redirect:/clientexservico");
	}

}
