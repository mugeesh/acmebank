//package com.htisec.repository;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.htisec.model.Account;
//
//@Transactional(readOnly = true)
//public interface AccountRepository extends JpaRepository<Account, Long>{
//
//	Optional<Account> findByAccountId(Long id);
//	
//	@Transactional
//	@Query("SELECT a FROM Account a WHERE a.accountId = ?1")
//	Optional<Account> getAccountForUpdate(Long id);
//	
//}
