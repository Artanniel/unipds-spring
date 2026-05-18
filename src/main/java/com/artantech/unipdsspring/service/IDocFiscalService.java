package com.artantech.unipdsspring.service;

import com.artantech.unipdsspring.model.DocFiscal;

public interface IDocFiscalService {
    public void realizarAutorizacaoApiExterna(Long idCliente, Integer idServico, String protocolo);

    public DocFiscal consultaPorProtocolo(String protocolo);
}
