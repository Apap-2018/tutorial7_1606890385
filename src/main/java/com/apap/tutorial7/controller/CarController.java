package com.apap.tutorial7.controller;
import com.apap.tutorial7.model.*;




import com.apap.tutorial7.service.*;
import com.sun.rowset.internal.Row;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	
	@PutMapping(value = "/{Carid}")
	private String updateCarSubmit(
			@PathVariable (value="Carid") long id,
			@RequestParam("brand") String brand,
			@RequestParam ("type") String type,
			@RequestParam ("price") long price) {
		CarModel car = (CarModel) carService.getCarDetailById(id).get();
		if(car.equals(null)) {
			return "Couldnt find your car";
		}
		car.setBrand(brand);
		car.setType(type);
		car.setPrice(price);
		carService.updateCar(id, car);
		
		return "car update success";
	}
	
	@PostMapping(value="/add")
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		return carService.addCar(car);
	}
	
	@GetMapping(value="/{carId}")
	private CarModel viewCar(@PathVariable ("carId") long carId, Model model) {
		CarModel car = carService.getCarDetailById(carId).get();
		
		return  car;
	}
	
	@GetMapping()
	private List<CarModel> viewAllCar(Model model){
		
		return carService.viewAll();
	}
	
	@DeleteMapping(value="/delete")
	private String carDealer(@RequestParam("carId") long id, Model model) {
		CarModel car = carService.getCarDetailById(id).get();
		carService.deleteCar(car);
		return "Success, car has been deleted";	
	}
	
	
	
	
	
	
	
	
	/*@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		ArrayList<CarModel> list = new ArrayList<CarModel>();
		list.add(new CarModel());
		dealer.setListCar(list);
		model.addAttribute("title", "Tambah Mobil");
		model.addAttribute("dealer", dealer);
		return "addCar";
	}

	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params= {"save"})
	private String addCarSubmit (@ModelAttribute DealerModel dealer) {
		DealerModel dealer_now = dealerService.getDealerDetailById(dealer.getId()).get();
		for(CarModel car : dealer.getListCar()) {
			car.setDealer(dealer_now);
			carService.addCar(car);
		}
		
		return "add";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", params= {"addRow"}, method = RequestMethod.POST)
	private String addRow (@ModelAttribute DealerModel dealer, Model model) {
		dealer.getListCar().add(new CarModel());
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"removeRow"})
	private String removeRow (@ModelAttribute DealerModel dealer, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
		final Integer row = Integer.valueOf(req.getParameter("removeRow"));
		dealer.getListCar().remove(row.intValue());
		model.addAttribute("dealer", dealer);
		return "addCar";
	

	}
	
	//Method untuk menghapus carbyId
	@RequestMapping(value="/car/delete/{id}", method = RequestMethod.GET)
	private String deleteCar(@PathVariable String id, Model model){
		long carId = Long.parseLong(id);
		CarModel car = carService.getCarDetailById(carId).get();
		carService.deleteCar(car);
		model.addAttribute("title", "Berhasil Menghapus");
		return "delete";
	}
	
	//Method untuk menghapus carbyId
		@RequestMapping(value="/car/delete", method = RequestMethod.POST)
		private String deleteCar(@ModelAttribute DealerModel dealer,  Model model){
			for(CarModel car : dealer.getListCar()) {
				carService.deleteCar(car);
			}
			model.addAttribute("title", "Berhasil Menghapus");
			return "delete";
		}
	
	
	@RequestMapping(value="/car/update/{id}", method= RequestMethod.GET)
	private String updateCar(@PathVariable String id, Model model) {
		long carId = Long.parseLong(id);
		CarModel car = carService.getCarDetailById(carId).get();
		model.addAttribute("car",car);
		model.addAttribute("title", "Update Mobil " + carId);
		return "updateCar";
	}
	
	@RequestMapping(value="/car/update/{id}", method=RequestMethod.POST)
	private String updateCar(@PathVariable (value="id") long id, @ModelAttribute CarModel car, Model model) {
		carService.updateCar(id, car);
		model.addAttribute("title", "Berhasil Mengupdate");
		return "update";
	}
	
	*/
	

}
