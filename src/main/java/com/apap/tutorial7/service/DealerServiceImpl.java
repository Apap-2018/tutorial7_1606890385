package com.apap.tutorial7.service;

import java.util.List;

import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;



@Service
@Transactional

public class DealerServiceImpl implements DealerService {
	@Autowired
	private DealerDb dealerDb;

	@Override
	public Optional<DealerModel> getDealerDetailById(Long id) {
		// TODO Auto-generated method stub
		return dealerDb.findById(id);
	}

	@Override
	public DealerModel addDealer(DealerModel dealer) {
		return dealerDb.save(dealer);
		
	}
	
	public void deleteDealer(DealerModel dealer) {
		dealerDb.delete(dealer);
	}

	@Override
	public void updateDealer(DealerModel dealer, long id) {
		// TODO Auto-generated method stub
		DealerModel old = dealerDb.getOne(id);
		old.setAlamat(dealer.getAlamat());
		old.setNama(dealer.getNama());
		old.setNoTelp(dealer.getNoTelp());
		dealerDb.save(old);
		
	}

	@Override
	public List<DealerModel> viewAll() {
		// TODO Auto-generated method stub
		return dealerDb.findAll();
		
	}

	
	

}
