package com.infy.customer.service;

import java.io.IOException;
import java.util.List;

import com.infy.customer.dto.CustomerDTO;
import com.infy.customer.dto.LoginDTO;
import com.infy.customer.dto.UpdateDTO;
import com.infy.customer.exception.CustomerException;

public interface CustomerService {
	
	String createCustomer(CustomerDTO customerDTO) throws CustomerException;
	
	CustomerDTO getSpecificCustomer(Long phoneNo) throws CustomerException;
		
	String updateBalanceAmount(Long phoneNo, UpdateDTO updateDTO) throws CustomerException;
	
	String deleteCustomer(Long phoneNo) throws CustomerException;
	
	String authenticateCustomer(LoginDTO loginDTO) throws CustomerException;
	
	List<CustomerDTO> getAllCustomers() throws CustomerException, IOException;
	
}
