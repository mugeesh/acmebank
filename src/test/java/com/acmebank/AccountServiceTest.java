package com.acmebank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import com.acmebank.exception.AccountNotExistException;
import com.acmebank.exception.CustomException;
import com.acmebank.model.Account;
import com.acmebank.model.AccountTransferRequest;
import com.acmebank.repository.AccountRepository;
import com.acmebank.service.impl.AccountServiceImpl;

//basic junit test
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	private Long initialAcBalance = 1000000L;

	@Mock
	AccountRepository accRepository;

	@InjectMocks
	AccountServiceImpl accService;

	@Test
	public void testRetrieveBalance() {

		when(accRepository.findByAccountId(initialAcBalance))
				.thenReturn(Optional.of(new Account(initialAcBalance, BigDecimal.valueOf(initialAcBalance))));

		assertEquals(BigDecimal.valueOf(initialAcBalance), accService.fetchBalances(initialAcBalance).getBalance());
	}

	@Test(expected = AccountNotExistException.class)
	public void testFetchBalanceFromInvalidAccount() {
		when(accRepository.findByAccountId(initialAcBalance)).thenReturn(Optional.empty());

		accService.fetchBalances(initialAcBalance);
	}

	@Test
	public void testTransferBalance() throws Exception, Exception, Exception {
		Long accountFromId = 12345678L;
		Long accountFromTo = 88888888L;
		BigDecimal amount = new BigDecimal(1000); // transfer amount

		AccountTransferRequest request = new AccountTransferRequest();
		request.setAccountFromId(accountFromId);
		request.setAccountToId(accountFromTo);
		request.setAmount(amount);

		Account accFrom = new Account(accountFromId, BigDecimal.valueOf(initialAcBalance));
		Account accTo = new Account(accountFromTo, BigDecimal.valueOf(initialAcBalance));

		when(accRepository.getAccountForUpdate(accountFromId)).thenReturn(Optional.of(accFrom));
		when(accRepository.getAccountForUpdate(accountFromTo)).thenReturn(Optional.of(accTo));

		accService.balanceTransfer(request);

		assertEquals(BigDecimal.valueOf(999000), accFrom.getBalance());
		assertEquals(BigDecimal.valueOf(1000).add(BigDecimal.valueOf(initialAcBalance)), accTo.getBalance());
	}

	@Test(expected = CustomException.class)
	public void testOverBalance() throws CustomException, AccountNotExistException {
		Long accountFromId = 12345678L;
		Long accountFromTo = 88888888L;
		BigDecimal amount = new BigDecimal(1000001);

		AccountTransferRequest request = new AccountTransferRequest();
		request.setAccountFromId(accountFromId);
		request.setAccountToId(accountFromTo);
		request.setAmount(amount);

		Account accFrom = new Account(accountFromId, BigDecimal.valueOf(initialAcBalance));
		Account accTo = new Account(accountFromTo, BigDecimal.valueOf(initialAcBalance));

		when(accRepository.getAccountForUpdate(accountFromId)).thenReturn(Optional.of(accFrom));
		when(accRepository.getAccountForUpdate(accountFromTo)).thenReturn(Optional.of(accTo));

		accService.balanceTransfer(request);
	}
}
