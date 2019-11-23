package com.bank.rest.web;

import com.bank.rest.data.Account;
import com.bank.rest.data.Customer;
import com.bank.rest.data.Transaction;
import com.bank.rest.services.IAccountReadServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.math.BigDecimal;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadController {

    private static final Logger LOG = LoggerFactory.getLogger(ReadController.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Init binder.
     *
     * @param webDataBinder the web data binder
     */
    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {

//        webDataBinder.registerCustomEditor(TypeOfCoverageIndex.class, new TypeOfCoverageIndexBinder());
//        webDataBinder.registerCustomEditor(ComputeCoverageIndex.class, new ComputeCoverageIndexBinder());
    }

    @Autowired
    IAccountReadServices iAccountReadServices;

    @GetMapping(path = "/account-balance")
    @ResponseBody
    public ResponseEntity getAccountBalance(@RequestParam(value = "accountNumber") String accountNumber) {

        BigDecimal bigDecimal = iAccountReadServices.getAccountBalance(accountNumber);

        if (bigDecimal != null) {
            return new ResponseEntity(bigDecimal, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/all-accounts-for-customer")
    @ResponseBody
    public ResponseEntity getAllAccountsForCustomer(@RequestParam(value = "accountID") Long accountID)
        throws JsonProcessingException {

        Collection<Account> collection = iAccountReadServices.getAllAccountsOfCustomer(accountID);

        if (collection != null) {

            return new ResponseEntity(objectMapper.writeValueAsString(collection), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/customer")
    @ResponseBody
    public ResponseEntity getAnAccountHolder(@RequestParam(value = "username") String username)
        throws JsonProcessingException {

        Customer customer = iAccountReadServices.getAccountHolder(username);

        if (customer != null) {

            return new ResponseEntity(objectMapper.writeValueAsString(customer), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/customer-transactions")
    @ResponseBody
    public ResponseEntity getCustomerTransactions(@RequestParam(value = "username") String username)
        throws JsonProcessingException {

        Collection<Transaction> transactions = iAccountReadServices.getAllTransactionsOfCustomer(username);

        if (transactions != null) {

            return new ResponseEntity(objectMapper.writeValueAsString(transactions), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
//        MethodArgumentNotValidException.class})
//    private @ResponseBody
//    SearchError handleInvalidPayload(Exception e) {
//
//        LOG.error("Data Integrity Violation: ", e);
//
//        SearchError error = new SearchError();
//
//        error.setCode("0001");
//        error.setMessage("Data Integrity Violation! Check Log for exception information");
//
//        return error;
//    }
}
