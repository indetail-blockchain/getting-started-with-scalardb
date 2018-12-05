package com.example.qa.service.account;

import com.example.qa.dao.account.AccountDao;
import com.example.qa.dao.account.AccountRecord;
import com.example.qa.service.ServiceException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;

public abstract class AccountService {
  protected final AccountDao accountDao;
  protected final Logger log;

  public AccountService(AccountDao accountDao, Logger log) {
    this.accountDao = accountDao;
    this.log = log;
  }

  public abstract void put(String email, String password) throws ServiceException;

  public abstract AccountRecord get(String email) throws ServiceException;

  protected String getMD5(String str) {
    String md5;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(str.getBytes());
      byte[] hash = md.digest();

      md5 = DatatypeConverter.printHexBinary(hash);
    } catch (NoSuchAlgorithmException e) {
      log.error("MD5 Algorithm is not available", e);
      throw new RuntimeException("MD5 Algorithm is not available", e);
    }

    return md5;
  }
}
