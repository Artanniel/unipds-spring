package com.artantech.unipdsspring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("v2")
public class MessaServiceImplV2 implements IMessageService {

    @Override
    public String sayCustomMessage(String original) {
        return "Saying: " + original.replaceAll(" ", "_");
    }
}
