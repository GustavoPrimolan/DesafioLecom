package br.com.desafio.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.desafio.daos.ServicoDao;
import br.com.desafio.enums.Plano;
import br.com.desafio.models.Cliente;
import br.com.desafio.models.Servico;

@Controller
@RequestMapping("/servico")
@Transactional
public class ServicoController {

	@Autowired
	private ServicoDao servicoDao;

	@GetMapping("/form")
	public ModelAndView form(Servico servico) {
		ModelAndView modelAndView = new ModelAndView("servico/form-add");

		return modelAndView;

	}

	@PostMapping
	public ModelAndView save(@Valid Servico servico, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return form(servico);
		}
		servicoDao.save(servico);
		return new ModelAndView("redirect:/servico");
	}

	@GetMapping("/{id}")
	public ModelAndView load(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("servico/form-update");
		modelAndView.addObject("servico", servicoDao.findById(id));
		return modelAndView;
	}

	@GetMapping
	public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page) {
		ModelAndView modelAndView = new ModelAndView("servico/listagem");
		modelAndView.addObject("paginatedList", servicoDao.paginated(page, 10));
		return modelAndView;
	}

	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		Servico servico = servicoDao.findById(id);
		servicoDao.remove(servico);
		return "redirect:/servico";
	}

	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable("id") Integer id, @Valid Servico servico, BindingResult bindingResult) {
		servico.setId(id);
		if (bindingResult.hasErrors()) {
			return new ModelAndView("servico/form-update");
		}
		servicoDao.update(servico);
		return new ModelAndView("redirect:/servico");
	}
}
