package vn.edu.fpt.mss.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.mss.dto.request.InvoiceRequest;
import vn.edu.fpt.mss.dto.response.InvoiceResponse;
import vn.edu.fpt.mss.service.InvoiceService;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
@Tag(name = "Invoices", description = "Invoice lifecycle: create + read-only. Immutability enforced — no update/delete on financial documents.")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    @Operation(summary = "Get all invoices, optionally filtered by customerId")
    public List<InvoiceResponse> findAll(@RequestParam(required = false) Integer customerId) {
        return invoiceService.findAll(customerId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get invoice by id with its lines. Served entirely from the stored snapshot — no calls to other services.")
    public InvoiceResponse findById(@PathVariable Integer id) {
        return invoiceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an invoice. Billing snapshot is fetched from customer-service via OpenFeign (503 if unreachable, 404 if customer not found). Unit prices and track names are resolved from catalog-service (404 if track not found). Total is computed server-side.")
    public InvoiceResponse create(@Valid @RequestBody InvoiceRequest request) {
        return invoiceService.create(request);
    }
}
