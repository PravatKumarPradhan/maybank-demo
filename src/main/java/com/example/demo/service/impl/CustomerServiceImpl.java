package com.example.demo.service.impl;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.repopsitory.CustomerRepository;
import com.example.demo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author  Pravat
 */
@Service
@AllArgsConstructor
public class CustomerServiceImpl  implements CustomerService {

    final CustomerRepository customerRepository;

    @Override
    public String createCustomer(CustomerDTO customerDTO) {
        String message="";
        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setCustomerName(customerDTO.getCustomerFirstName() + customerDTO.getCustomerMiddleName() + customerDTO.getCustomerLastName());
        newCustomer.setAccountNumber(generateAccountNo(customerDTO.getAccountType(),customerDTO.getIdType(),customerDTO.getIdValue()));
        newCustomer.setDob(customerDTO.getDob());
        try{
            customerRepository.save(newCustomer);
           // message="Account Number Generated Successfully";
            message="0";
            return message;

        }catch (Exception e){
            e.getLocalizedMessage();
          //  message="Account Number Not";
            message="1";
        }
        return message;
    }


    /**
     * This method will help to create account number with combination of accountType ,idType and idValue
     * @param accountType
     * @param idType
     * @param idValue
     * @return
     */
    // TO DO this method needs to move util class with static method
    private  String generateAccountNo(String accountType,String idType,String idValue){
        String accountNoInitial;
        String accountNo;
        if(accountType.equalsIgnoreCase("Saving")){
            accountNoInitial="SB";
        }else {
            accountNoInitial="CR";
        }
        accountNo=accountNoInitial+"-"+idType+"-"+idValue;
        return accountNo;
    }

}

