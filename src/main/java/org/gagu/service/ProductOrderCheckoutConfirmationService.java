package org.gagu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gagu.dto.ComponentInventoryItemDTO;
import org.gagu.dto.ProductInventoryItemDTO;
import org.gagu.entity.ProductInventory;
import org.gagu.repository.InventoryItemRepository;
import org.gagu.repository.ProductInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductOrderCheckoutConfirmationService {
    private final InventoryItemRepository inventoryItemRepository;
    private final ProductInventoryRepository productInventoryRepository;

    public List<ProductInventoryItemDTO> getProductInventoryItemList(Integer productOrderId) {
        return inventoryItemRepository.findProductNecessaryInventoryByOProductOrderId(productOrderId);
    }
    public List<ComponentInventoryItemDTO> getComponentInventoryItemList(String productId) {
        return inventoryItemRepository.findComponentNecessaryInventoryByProductId(productId);
    }

    public ProductInventoryItemDTO getProductInventoryItem(String productId, Integer productOrderId) {
        return inventoryItemRepository.findProductInventoryByProductId(productId, productOrderId);
    }
}
