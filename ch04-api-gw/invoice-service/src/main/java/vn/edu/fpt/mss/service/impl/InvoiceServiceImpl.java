package vn.edu.fpt.mss.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.mss.client.CatalogClient;
import vn.edu.fpt.mss.client.CustomerClient;
import vn.edu.fpt.mss.dto.client.CustomerInfo;
import vn.edu.fpt.mss.dto.client.TrackInfo;
import vn.edu.fpt.mss.dto.request.InvoiceLineRequest;
import vn.edu.fpt.mss.dto.request.InvoiceRequest;
import vn.edu.fpt.mss.dto.response.InvoiceLineResponse;
import vn.edu.fpt.mss.dto.response.InvoiceResponse;
import vn.edu.fpt.mss.entity.Invoice;
import vn.edu.fpt.mss.entity.InvoiceLine;
import vn.edu.fpt.mss.exception.ResourceNotFoundException;
import vn.edu.fpt.mss.exception.ServiceUnavailableException;
import vn.edu.fpt.mss.repository.InvoiceRepository;
import vn.edu.fpt.mss.service.InvoiceService;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerClient customerClient;
    private final CatalogClient catalogClient;

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponse> findAll(Integer customerId) {
        List<Invoice> invoices = customerId == null
                ? invoiceRepository.findAll()
                : invoiceRepository.findByCustomerIdOrderByInvoiceDateDesc(customerId);
        return invoices.stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponse findById(Integer id) {
        Invoice invoice = invoiceRepository.findWithLinesById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice", id));
        return toDetailResponse(invoice);
    }

    @Override
    @Transactional
    public InvoiceResponse create(InvoiceRequest request) {
        Invoice invoice = new Invoice();
        applyRequest(invoice, request);
        return toResponse(invoiceRepository.save(invoice));
    }

    private void applyRequest(Invoice invoice, InvoiceRequest request) {
        invoice.setCustomerId(request.getCustomerId());
        CustomerInfo customer = requireCustomer(request.getCustomerId());
        applyCustomerSnapshot(invoice, customer);
        invoice.setInvoiceDate(request.getInvoiceDate() == null
                ? java.time.LocalDateTime.now()
                : request.getInvoiceDate());
        Map<Integer, TrackInfo> tracks = resolveRequiredTracks(request.getLines());
        List<InvoiceLine> lines = request.getLines().stream()
                .map(l -> toEntity(l, new InvoiceLine(), null, tracks))
                .toList();
        invoice.reconcileLines(lines);
        invoice.setTotal(recomputeTotal(invoice.getLines()));
    }

    private void applyCustomerSnapshot(Invoice invoice, CustomerInfo customer) {
        invoice.setCustomerFirstName(customer.getFirstName());
        invoice.setCustomerLastName(customer.getLastName());
        invoice.setBillingAddress(customer.getAddress());
        invoice.setBillingCity(customer.getCity());
        invoice.setBillingState(customer.getState());
        invoice.setBillingCountry(customer.getCountry());
        invoice.setBillingPostalCode(customer.getPostalCode());
    }

    private Map<Integer, TrackInfo> resolveRequiredTracks(List<InvoiceLineRequest> lines) {
        Map<Integer, TrackInfo> tracks = new HashMap<>();
        for (InvoiceLineRequest line : lines) {
            tracks.computeIfAbsent(line.getTrackId(), this::requireTrack);
        }
        return tracks;
    }

    private InvoiceLine toEntity(InvoiceLineRequest request, InvoiceLine line, Invoice invoice, Map<Integer, TrackInfo> tracks) {
        TrackInfo track = tracks.get(request.getTrackId());
        line.setInvoice(invoice);
        line.setTrackId(track.getTrackId());
        line.setTrackName(track.getName());
        line.setUnitPrice(track.getUnitPrice());
        line.setQuantity(request.getQuantity());
        return line;
    }

    private BigDecimal recomputeTotal(List<InvoiceLine> lines) {
        BigDecimal total = BigDecimal.ZERO;
        for (InvoiceLine line : lines) {
            BigDecimal lineTotal = line.getUnitPrice().multiply(BigDecimal.valueOf(line.getQuantity()));
            total = total.add(lineTotal);
        }
        return total;
    }

    private TrackInfo requireTrack(Integer trackId) {
        try {
            return catalogClient.getTrack(trackId);
        } catch (feign.FeignException.NotFound ex) {
            throw new ResourceNotFoundException("Track", trackId);
        } catch (Exception ex) {
            log.error("catalog-service unreachable for trackId={}", trackId);
            throw new ServiceUnavailableException("catalog-service", trackId);
        }
    }

    private CustomerInfo requireCustomer(Integer customerId) {
        try {
            return customerClient.getCustomer(customerId);
        } catch (feign.FeignException.NotFound ex) {
            throw new ResourceNotFoundException("Customer", customerId);
        } catch (Exception ex) {
            log.error("customer-service unreachable for customerId={}", customerId);
            throw new ServiceUnavailableException("customer-service", customerId);
        }
    }

    private InvoiceResponse toResponse(Invoice invoice) {
        return InvoiceResponse.builder()
                .invoiceId(invoice.getInvoiceId())
                .customerId(invoice.getCustomerId())
                .customerFirstName(invoice.getCustomerFirstName())
                .customerLastName(invoice.getCustomerLastName())
                .invoiceDate(invoice.getInvoiceDate())
                .billingAddress(invoice.getBillingAddress())
                .billingCity(invoice.getBillingCity())
                .billingState(invoice.getBillingState())
                .billingCountry(invoice.getBillingCountry())
                .billingPostalCode(invoice.getBillingPostalCode())
                .total(invoice.getTotal())
                .lines(invoice.getLines().stream().map(this::toLineResponse).toList())
                .build();
    }

    private InvoiceResponse toDetailResponse(Invoice invoice) {
        return InvoiceResponse.builder()
                .invoiceId(invoice.getInvoiceId())
                .invoiceDate(invoice.getInvoiceDate())
                .billingAddress(invoice.getBillingAddress())
                .billingCity(invoice.getBillingCity())
                .billingState(invoice.getBillingState())
                .billingCountry(invoice.getBillingCountry())
                .billingPostalCode(invoice.getBillingPostalCode())
                .total(invoice.getTotal())
                .lines(invoice.getLines().stream().map(this::toLineDetailResponse).toList())
                .customer(CustomerInfo.builder()
                        .customerId(invoice.getCustomerId())
                        .firstName(invoice.getCustomerFirstName())
                        .lastName(invoice.getCustomerLastName())
                        .build())
                .build();
    }

    private InvoiceLineResponse toLineResponse(InvoiceLine line) {
        return InvoiceLineResponse.builder()
                .invoiceLineId(line.getInvoiceLineId())
                .invoiceId(line.getInvoice().getInvoiceId())
                .trackId(line.getTrackId())
                .trackName(line.getTrackName())
                .unitPrice(line.getUnitPrice())
                .quantity(line.getQuantity())
                .lineTotal(line.getUnitPrice().multiply(BigDecimal.valueOf(line.getQuantity())))
                .build();
    }

    private InvoiceLineResponse toLineDetailResponse(InvoiceLine line) {
        return InvoiceLineResponse.builder()
                .invoiceLineId(line.getInvoiceLineId())
                .invoiceId(line.getInvoice().getInvoiceId())
                .unitPrice(line.getUnitPrice())
                .quantity(line.getQuantity())
                .lineTotal(line.getUnitPrice().multiply(BigDecimal.valueOf(line.getQuantity())))
                .track(TrackInfo.builder()
                        .trackId(line.getTrackId())
                        .name(line.getTrackName())
                        .build())
                .build();
    }
}