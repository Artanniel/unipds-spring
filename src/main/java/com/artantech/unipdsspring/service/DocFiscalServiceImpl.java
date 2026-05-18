package com.artantech.unipdsspring.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.artantech.unipdsspring.model.DocFiscal;
import com.artantech.unipdsspring.repository.DocFiscalRepo;

@Service
public class DocFiscalServiceImpl implements IDocFiscalService {

    private DocFiscalRepo docFiscalRepo;
    private WebClient webClient;

    public DocFiscalServiceImpl(DocFiscalRepo docFiscalRepo, WebClient webClient) {
        super();
        this.docFiscalRepo = docFiscalRepo;
        this.webClient = webClient;
    }

    @Override
    public void realizarAutorizacaoApiExterna(Long idCliente, Integer idServico, String protocolo) {
        webClient.get()
                .uri("http://localhost:8080/lazy-api/v1/autorizacao/" + idCliente + "?servico=" + idServico)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(documento -> {
                    System.out.println("DEBUG - Solicitação atendida pela API externa:" + documento);
                    DocFiscal docFiscal = new DocFiscal();
                    docFiscal.setProtocolo(protocolo);
                    docFiscal.setDocumento(documento);
                    docFiscalRepo.save(docFiscal);
                })
                .doOnError(error -> {
                    System.out.println(error);
                })
                .subscribe();
    }

    @Override
    public DocFiscal consultaPorProtocolo(String protocolo) {
        return docFiscalRepo.findByProtocolo(protocolo).orElse(null);
    }

}
