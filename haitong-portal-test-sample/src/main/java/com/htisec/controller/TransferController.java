//package com.htisec.controller;
//
//import javax.validation.Valid;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.htisec.dto.AccountTransferResult;
//import com.htisec.exception.AccountNotExistException;
//import com.htisec.exception.CheckBalanceException;
//import com.htisec.exception.CustomException;
//import com.htisec.model.AccountTransferRequest;
//import com.htisec.service.AccountService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@RestController
//@RequestMapping("/acc/transaction")
//@Api(tags = { "Account Transfer" }, description = "API for acount transfer")
//public class TransferController {
//
//	private static final Logger log = LoggerFactory.getLogger(TransferController.class);
//
//	@Autowired
//	@Qualifier("accountService")
//	private AccountService accountService;
//
//	@PostMapping(consumes = { "application/json" })
//	@ApiOperation(value = "API to account transfer", response = AccountTransferResult.class, produces = "application/json")
//	public ResponseEntity<AccountTransferResult> transferAmount(@RequestBody @Valid AccountTransferRequest request) throws Exception {
//
//		try {
//			accountService.balanceTransfer(request);
//
//			AccountTransferResult result = new AccountTransferResult();
//			result.setAccountFromId(request.getAccountFromId());
//			result.setBalanceAfterTransfer(accountService.balanceCheck(request.getAccountFromId()));
//
//			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
//		} catch (CheckBalanceException cbEx) {
//			log.error("Fail to check balances after transfer, please check with IT Support Team.");
//			throw cbEx;
//		} catch (AccountNotExistException | CustomException e) {
//			log.error("Fail to transfer balances, , please check with IT Support Team.");
//			throw e;
//		}
//	}
//}
