package com.artantech.unipdsspring.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artantech.unipdsspring.model.dto.RequisicaoDTO;
import com.artantech.unipdsspring.model.vo.DocAutorizacao;
import com.artantech.unipdsspring.service.IDocFiscalService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lazy-api/v1/autorizacao/{idCliente}")
public class DocAutorizacaoController {

    private final IDocFiscalService docFiscalService;

    public DocAutorizacaoController(IDocFiscalService docFiscalService) {
        super();
        this.docFiscalService = docFiscalService;
    }

    // Chamado via WebClient (GET com query params)
    @GetMapping
    public ResponseEntity<DocAutorizacao> autorizarViaGet(
            @PathVariable("idCliente") Long idCliente,
            @RequestParam("servico") Integer idServico) {
        DocAutorizacao doc = new DocAutorizacao();
        doc.setCodigoCliente(idCliente);
        doc.setCodigoServico(idServico);
        doc.setDataHora(LocalDateTime.now());
        doc.setChaveAutorizacao(generateKey());
        return ResponseEntity.ok(doc);
    }

    @PostMapping("/solicitar")
    public ResponseEntity<DocAutorizacao> solicitar(@RequestBody RequisicaoDTO req) {
        DocAutorizacao doc = new DocAutorizacao();
        doc.setCodigoCliente(req.idCliente());
        doc.setCodigoServico(req.idServico());
        doc.setDataHora(LocalDateTime.now());
        doc.setChaveAutorizacao(generateKey());

        return ResponseEntity.ok(doc);
    }

    private String generateKey() {
        return UUID.randomUUID().toString();
    }

}
