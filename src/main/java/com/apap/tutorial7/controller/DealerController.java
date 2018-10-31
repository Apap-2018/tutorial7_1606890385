package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.*;
import com.apap.tutorial7.rest.DealerDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/dealer")
public class DealerController {
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	//terakhir ditambahkan
	@GetMapping(value="/status/{dealerId}")
	private String getStatus(@PathVariable ("dealerId") long dealerId) throws Exception{
		String path = Setting.dealerUrl + "/dealer?id=" + dealerId;
		return restTemplate.getForEntity(path, String.class).getBody();
	}
	
	@GetMapping(value = "/full/{dealerId}")
	private DealerDetail postStatus(@PathVariable ("dealerId") long dealerId) throws Exception{
		String path = Setting.dealerUrl + "/dealer";
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		DealerDetail detail = restTemplate.postForObject(path, dealer, DealerDetail.class);
		return detail;
	}
	@PostMapping(value="/add")
	private DealerModel addDealerSubmit(@RequestBody DealerModel dealer) {
		return dealerService.addDealer(dealer);
	}
	
	@GetMapping(value="/{dealerId}")
	private DealerModel viewDealer(@PathVariable ("dealerId") long dealerId, Model model) {
		
		return  dealerService.getDealerDetailById(dealerId).get();
	}
	
	@DeleteMapping(value="/delete")
	private String deleteDealer(@RequestParam("dealerId") long id, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		dealerService.deleteDealer(dealer);
		return "Success";
		
	}
	
	@PutMapping(value = "/{id}")
	private String updateDealerSubmit(
			@PathVariable (value="id") long id,
			@RequestParam("nama") String nama,
			@RequestParam ("alamat") String alamat,
			@RequestParam ("telp") String telp) {
		DealerModel dealer = (DealerModel) dealerService.getDealerDetailById(id).get();
		if(dealer.equals(null)) {
			return "Couldnt find your dealer";
		}
		dealer.setNama(nama);
		dealer.setAlamat(alamat);
		dealer.setNoTelp(telp);
		dealerService.updateDealer(dealer, id);
		return "update success";
	}
	@GetMapping()
	private List<DealerModel> viewAllDealer(Model model){
		return dealerService.viewAll();
	}
	
	
	
	/*@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("title","Home");
		return "home";
	}
	
	@RequestMapping(value="/dealer/add", method= RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		model.addAttribute("title", "Tambah Dealer");
		return "addDealer";
	}
	
	@RequestMapping(value="/dealer/add", method=RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute DealerModel dealer, Model model) {
		dealerService.addDealer(dealer);
		model.addAttribute("title", "Berhasil Menambahkan");
		return "add";
	}
	
	@RequestMapping(value="/dealer/view", method=RequestMethod.GET)
	private String viewDealer(@RequestParam ("dealerId") long id, Model model) {
		if(dealerService.getDealerDetailById(id).isPresent()) {
			
			DealerModel dealer = dealerService.getDealerDetailById(id).get();
			model.addAttribute("dealer", dealer);
			List<CarModel> car = dealer.getListCar();
			model.addAttribute("car", car);
			model.addAttribute("title", "Dealer "+ id);
			return "viewDealer";
		}
		else {
			model.addAttribute("exception", "Data tidak ditemukan");
			model.addAttribute("title", "Error");
			return "exception";
		}
		
	}
	
	
	//Method untuk menghapus dealer
	@RequestMapping(value="/dealer/delete", method=RequestMethod.GET)
	private String deleteDealer(@RequestParam ("dealerId") long id, Model model){
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		dealerService.deleteDealer(dealer);
		model.addAttribute("title", "Berhasil Menghapus");
		return "delete";
		
	}
	
	@RequestMapping(value="/dealer/update/{id}", method= RequestMethod.GET)
	private String updateDealer(@PathVariable String id, Model model) {
		long dealerId = Long.parseLong(id);
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		model.addAttribute("dealer", dealer);
		model.addAttribute("title", "Update Dealer "+ id);
		return "updateDealer";
	}
	
	@RequestMapping(value="/dealer/update/{id}", method=RequestMethod.POST)
	private String updateDealer(@PathVariable (value="id") long id, @ModelAttribute DealerModel dealer, Model model) {
		dealerService.updateDealer(dealer, id);
		model.addAttribute("title", "Berhasil Update");
		
		return "update";
	}
	
	@RequestMapping("/viewAll")
	private String viewAll(Model model) {
		List<DealerModel> dealerAll =  dealerService.viewAll();
		model.addAttribute("listDealer", dealerAll);
		model.addAttribute("title", "View All");
		return "viewAll";
	}*/
	
	
	
	
}


