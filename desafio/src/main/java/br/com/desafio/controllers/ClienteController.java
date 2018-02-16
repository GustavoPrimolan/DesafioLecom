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

import br.com.desafio.daos.ClienteDao;
import br.com.desafio.enums.Plano;
import br.com.desafio.models.Cliente;

@Controller
@RequestMapping("/cliente")
@Transactional
public class ClienteController {


	   @Autowired
	   private ClienteDao clienteDao;

	   @GetMapping("/form")
	   public ModelAndView form(Cliente cliente)
	   {
	      ModelAndView modelAndView = new ModelAndView("cliente/form-add");
	      modelAndView.addObject("planos", Plano.values());
	      return modelAndView;

	   }

	   @PostMapping
	   public ModelAndView save(@Valid Cliente cliente, BindingResult bindingResult)
	   {
	      if (bindingResult.hasErrors())
	      {
	         return form(cliente);
	      }
	      clienteDao.save(cliente);
	      return new ModelAndView("redirect:/cliente");
	   }

	   @GetMapping("/{id}")
	   public ModelAndView load(@PathVariable("id") Integer id)
	   {
	      ModelAndView modelAndView = new ModelAndView("cliente/form-update");
	      modelAndView.addObject("cliente", clienteDao.findById(id));
	      modelAndView.addObject("planos", Plano.values());
	      return modelAndView;
	   }

	   @GetMapping
	   public ModelAndView list(@RequestParam(defaultValue = "0", required = false) int page)
	   {
	      ModelAndView modelAndView = new ModelAndView("cliente/listagem");
	      modelAndView.addObject("paginatedList", clienteDao.paginated(page, 10));
	      return modelAndView;
	   }

	   @GetMapping("/remove/{id}")
	   public String remove(@PathVariable("id") Integer id)
	   {
	      Cliente cliente = clienteDao.findById(id);
	      clienteDao.remove(cliente);
	      return "redirect:/cliente";
	   }

	   @PostMapping("/{id}")
	   public ModelAndView update(@PathVariable("id") Integer id, @Valid Cliente cliente, BindingResult bindingResult)
	   {
	      cliente.setId(id);
	      if (bindingResult.hasErrors())
	      {
	         return new ModelAndView("cliente/form-update");
	      }
	      clienteDao.update(cliente);
	      return new ModelAndView("redirect:/cliente");
	   }
	
}
