package com.rudkids.core.admin.service;

import com.rudkids.core.admin.domain.OrderQuerydslRepository;
import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.dto.AdminResponse;
import com.rudkids.core.image.service.ImageDeletedEvent;
import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.order.domain.OrderRepository;
import com.rudkids.core.order.service.DeliveryTracker;
import com.rudkids.core.product.domain.Product;
import com.rudkids.core.product.domain.ProductRepository;
import com.rudkids.core.product.domain.ProductStatus;
import com.rudkids.core.user.domain.RoleType;
import com.rudkids.core.user.domain.UserRepository;
import com.rudkids.core.video.domain.VideoBio;
import com.rudkids.core.video.domain.VideoImage;
import com.rudkids.core.video.domain.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final VideoRepository videoRepository;
    private final DeliveryTracker deliveryTracker;
    private final OrderQuerydslRepository orderQuerydslRepository;
    private final ProductFactory productFactory;
    private final ItemFactory itemFactory;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public List<AdminResponse.UserSearchInfo> searchUsers(String email) {
        return userRepository.getUsersByEmail(email).stream()
            .map(AdminResponse.UserSearchInfo::new)
            .toList();
    }

    public void changeUserRole(UUID userId, AdminRequest.ChangeUserRole request) {
        var user = userRepository.getUser(userId);
        var roleType = RoleType.toEnum(request.roleType());
        user.changeRole(roleType);
    }

    public UUID createProduct(AdminRequest.CreateProduct request) {
        var product = productFactory.create(request);
        productRepository.save(product);
        return product.getId();
    }

    public void changeProductStatus(UUID productId, AdminRequest.ChangeProductStatus request) {
        var product = productRepository.get(productId);
        var productStatus = ProductStatus.toEnum(request.status());
        product.changeStatus(productStatus);
    }

    public void updateProduct(UUID productId, AdminRequest.UpdateProduct request) {
        var product = productRepository.get(productId);
        productFactory.update(product, request);
        deleteProductImage(product);
    }

    public void deleteProduct(UUID productId) {
        var product = productRepository.get(productId);
        deleteProductImage(product);
        productRepository.delete(product);
    }

    private void deleteProductImage(Product product) {
        eventPublisher.publishEvent(new ImageDeletedEvent(product.getFrontImagePath()));
        eventPublisher.publishEvent(new ImageDeletedEvent(product.getBackImagePath()));

        for(String path: product.getBannerPaths()) {
            eventPublisher.publishEvent(new ImageDeletedEvent(path));
        }
    }

    public String createItem(UUID productId, AdminRequest.CreateItem request) {
        var product = productRepository.get(productId);
        var item = itemFactory.create(product, request);
        itemRepository.save(item);
        return item.getEnName();
    }

    public void updateItem(String itemName, AdminRequest.UpdateItem request) {
        var item = itemRepository.getByEnNme(itemName);
        deleteItemImage(item);
        itemFactory.update(item, request);
    }

    public void changeItemStatus(String itemName, AdminRequest.ChangeItemStatus request) {
        var item = itemRepository.getByEnNme(itemName);
        var status = ItemStatus.toEnum(request.itemStatus());
        item.changeStatus(status);
    }

    public void deleteItem(String name) {
        var item = itemRepository.getByEnNme(name);
        deleteItemImage(item);
        itemRepository.delete(item);
    }

    private void deleteItemImage(Item item) {
        for(String path: item.getImagePaths()) {
            eventPublisher.publishEvent(new ImageDeletedEvent(path));
        }
    }

    public void createVideo(AdminRequest.CreateVideo request) {
        var video = request.toEntity();
        videoRepository.save(video);
    }

    public void updateVideo(UUID videoId, AdminRequest.UpdateVideo request) {
        var video = videoRepository.findVideoById(videoId);
        var image = VideoImage.create(request.image().path(), request.image().url());
        var bio = VideoBio.create(request.bio());
        video.update(image, bio, request.videoUrl(), request.itemName());
    }

    public void deleteVideo(UUID videoId) {
        var video = videoRepository.findVideoById(videoId);
        videoRepository.delete(video);
    }

    public Page<AdminResponse.OrderInfo> getAllOrder(String deliveryStatus, String orderStatus, String deliveryTrackingNumber, String customerName, Pageable pageable) {
        var orders = orderRepository.getOrders();
        deliveryTracker.changeCompletedState(orders);
        return orderQuerydslRepository.getOrders(deliveryStatus, orderStatus, deliveryTrackingNumber, customerName, pageable)
            .map(AdminResponse.OrderInfo::new);
    }
}
