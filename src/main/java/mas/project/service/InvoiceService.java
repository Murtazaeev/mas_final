package mas.project.service;

import lombok.RequiredArgsConstructor;
import mas.project.model.Invoice;
import mas.project.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public Invoice getInvoiceByOrderId(UUID orderId){
        var invoice = invoiceRepository.findInvoiceByOrderId(orderId);
        if(invoice != null){
            if(invoice.getOrder().canGetInvoice(invoice.getOrder().getOrderState())){
                return invoice;
            }
        }
        return null;
    }

}
