package com.mrlima.app.service;

import org.springframework.stereotype.Service;

import com.mrlima.app.model.BasicPublishResponse;
import com.mrlima.app.model.vendor.Vendor;

@Service
public class VendorService {

	public BasicPublishResponse publishVendorRecord(Vendor vendor) {
		return new BasicPublishResponse("Response provided by the service " + vendor.getAccountNumber());

	}

}
