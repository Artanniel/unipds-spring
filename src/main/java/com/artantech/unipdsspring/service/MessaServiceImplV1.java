package com.artantech.unipdsspring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("v1")
public class MessaServiceImplV1 implements IMessageService {

    @Override
    public String sayCustomMessage(String original) {
        return "Saying: " + original.toUpperCase();
    }
}
