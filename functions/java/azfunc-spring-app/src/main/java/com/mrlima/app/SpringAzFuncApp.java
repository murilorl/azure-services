package com.mrlima.app;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mrlima.app.model.BasicPublishResponse;
import com.mrlima.app.model.vendor.Vendor;
import com.mrlima.app.service.VendorService;

@SpringBootApplication
public class SpringAzFuncApp {
	
	@Autowired
	private VendorService vendorService;

	@Bean
	public Function<Vendor, BasicPublishResponse> publishVendorRecord() {
		//return vendor -> new BasicPublishResponse("Response provided by the app " + vendor.getAccountNumber());
		return vendor -> vendorService.publishVendorRecord(vendor);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringAzFuncApp.class, args);

	}

}
