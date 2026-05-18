package com.artantech.unipdsspring.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artantech.unipdsspring.model.DocFiscal;
import com.artantech.unipdsspring.model.dto.ProtocoloDTO;
import com.artantech.unipdsspring.model.dto.RequisicaoDTO;
import com.artantech.unipdsspring.service.IDocFiscalService;

import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/autorizacao")
public class DocFiscalController {

    private final IDocFiscalService docFiscalService;

    public DocFiscalController(IDocFiscalService docFiscalService) {
        super();
        this.docFiscalService = docFiscalService;
    }

    @GetMapping("/consulta/{protocolo}")
    public ResponseEntity<DocFiscal> consultaPorProtocolo(@PathVariable("protocolo") String protocolo) {
        DocFiscal docFiscal = docFiscalService.consultaPorProtocolo(protocolo);
        if (docFiscal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(docFiscal);
    }

    @PostMapping("/solicitar")
    public Mono<ResponseEntity<ProtocoloDTO>> solicitar(@RequestBody RequisicaoDTO req) {
        String idProtocolo = UUID.randomUUID().toString();
        docFiscalService.realizarAutorizacaoApiExterna(req.idCliente(), req.idServico(), idProtocolo);
        return Mono.just(ResponseEntity.accepted().body(new ProtocoloDTO(idProtocolo)));
    }
}
