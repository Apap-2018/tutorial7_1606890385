package com.apap.tutorial7.service;
import java.util.List;
import java.util.Optional;


import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.CarDb;

public interface CarService {
	CarModel addCar(CarModel car);

	Optional<CarModel> getCarDetailById(Long id);

	void deleteCar(CarModel car);

	void updateCar(long id, CarModel car);

	List<CarModel> viewAll();
	

}

