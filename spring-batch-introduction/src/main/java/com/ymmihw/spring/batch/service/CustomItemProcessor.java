package com.ymmihw.spring.batch.service;

import org.springframework.batch.item.ItemProcessor;
import com.ymmihw.spring.batch.model.Transaction;

public class CustomItemProcessor implements ItemProcessor<Transaction, Transaction> {

  @Override
  public Transaction process(Transaction item) {
    System.out.println("Processing..." + item);
    return item;
  }
}
