package media.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import media.soft.repository.ShippingFeesMartDao;
import media.soft.model.ShippingFeesMart;

@Service
public class ECommerceService {

	private ShippingFeesMartDao shippingFeesMartDao;

	public ECommerceService(@Autowired ShippingFeesMartDao shippingFeesMartDao) {
		this.shippingFeesMartDao = shippingFeesMartDao;
	}
	
	public List<ShippingFeesMart> getAllShippingFees(){
		return shippingFeesMartDao.getAllData();
	}
}
