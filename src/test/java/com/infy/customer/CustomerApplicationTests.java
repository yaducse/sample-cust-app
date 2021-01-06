package com.infy.customer;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.infy.customer.dto.LoginDTO;
import com.infy.customer.entity.Customer;
import com.infy.customer.exception.CustomerException;
import com.infy.customer.repository.CustomerRepository;
import com.infy.customer.service.CustomerService;
import com.infy.customer.service.CustomerServiceImpl;
import com.infy.customer.utility.CustomerConstants;

@SpringBootTest
class CustomerApplicationTests {
	
	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	CustomerService customerService = new CustomerServiceImpl();
	
	@Mock
	Environment environment;
	
	@Test
	public void authenticateCustomerValidCredentials() throws CustomerException {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setPhoneNo(9876543210L);
		loginDTO.setPassword("testing");
		
		Customer customer = new Customer();
		customer.setPhoneNo(9876543210L);
		customer.setPassword("testing");
		
		Mockito.when(environment.getProperty(CustomerConstants.CUSTOMER_LOGIN_SUCCESS.toString())).thenReturn("Customer logged in successfully!");
		Mockito.when(customerRepository.findById(9876543210L)).thenReturn(Optional.of(customer));
		
		String result = customerService.authenticateCustomer(loginDTO);
		Assertions.assertEquals(environment.getProperty(CustomerConstants.CUSTOMER_LOGIN_SUCCESS.toString()), result);
		
	}
	
	@Test
	public void authenticateCustomerInValidCredentials() throws CustomerException {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setPhoneNo(9876543210L);
		loginDTO.setPassword("testing_invalid");
		
		Customer customer = new Customer();
		customer.setPhoneNo(9876543210L);
		customer.setPassword("testing");
		
		Mockito.when(customerRepository.findById(9876543210L)).thenReturn(Optional.of(customer));
		
		CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> customerService.authenticateCustomer(loginDTO));
		Assertions.assertEquals(CustomerConstants.CUSTOMER_LOGIN_FAILURE.toString(), customerException.getMessage());
		
	}

}
