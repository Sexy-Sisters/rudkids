package com.rudkids.core.admin.service;

import com.rudkids.core.admin.domain.OrderQuerydslRepository;
import com.rudkids.core.admin.dto.AdminRequest;
import com.rudkids.core.admin.dto.AdminResponse;
import com.rudkids.core.cart.domain.CartItemRepository;
import com.rudkids.core.collection.domain.Collection;
import com.rudkids.core.collection.domain.CollectionItem;
import com.rudkids.core.collection.domain.CollectionItemRepository;
import com.rudkids.core.collection.domain.CollectionRepository;
import com.rudkids.core.image.service.ImageDeletedEvent;
import com.rudkids.core.item.domain.Item;
import com.rudkids.core.item.domain.ItemImageRepository;
import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.item.domain.ItemStatus;
import com.rudkids.core.item.domain.option.ItemOptionRepository;
import com.rudkids.core.item.domain.optionGroup.ItemOptionGroupRepository;
import com.rudkids.core.order.domain.OrderRepository;
import com.rudkids.core.order.domain.OrderStatus;
import com.rudkids.core.order.service.DeliveryTracker;
import com.rudkids.core.product.domain.*;
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
    private final ItemImageRepository itemImageRepository;
    private final ItemOptionGroupRepository itemOptionGroupRepository;
    private final ItemOptionRepository itemOptionRepository;
    private final OrderRepository orderRepository;
    private final VideoRepository videoRepository;
    private final DeliveryTracker deliveryTracker;
    private final OrderQuerydslRepository orderQuerydslRepository;
    private final CollectionRepository collectionRepository;
    private final CollectionItemRepository collectionItemRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductFactory productFactory;
    private final MysteryProductFactory mysteryProductFactory;
    private final MysteryProductRepository mysteryProductRepository;
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

    public void updateProduct(UUID productId, AdminRequest.UpdateProduct request) {
        var product = productRepository.get(productId);
        deleteProductImage(
            product.getFrontImagePath(),
            product.getBackImagePath(),
            product.getBannerImagePath(),
            product.getMobileBannerImagePath()
        );
        productFactory.update(product, request);
    }

    public void deleteProduct(UUID productId) {
        var product = productRepository.get(productId);
        deleteProductImage(
            product.getFrontImagePath(),
            product.getBackImagePath(),
            product.getBannerImagePath(),
            product.getMobileBannerImagePath()
        );
        productRepository.delete(product);
    }

    public void createMysteryProduct(AdminRequest.CreateProduct request) {
        var mysteryProduct = mysteryProductFactory.create(request);
        mysteryProductRepository.save(mysteryProduct);
    }

    public void updateMysteryProduct(UUID mysteryProductId, AdminRequest.UpdateProduct request) {
        var mysteryProduct = mysteryProductRepository.get(mysteryProductId);
        deleteProductImage(
            mysteryProduct.getFrontImagePath(),
            mysteryProduct.getBackImagePath(),
            mysteryProduct.getBannerImagePath(),
            mysteryProduct.getMobileBannerImagePath()
        );
        mysteryProductFactory.update(mysteryProduct, request);
    }

    public void deleteMysteryProduct(UUID mysteryProductId) {
        var mysteryProduct = mysteryProductRepository.get(mysteryProductId);
        deleteProductImage(
            mysteryProduct.getFrontImagePath(),
            mysteryProduct.getBackImagePath(),
            mysteryProduct.getBannerImagePath(),
            mysteryProduct.getMobileBannerImagePath()
        );
        mysteryProductRepository.delete(mysteryProduct);
    }

    private void deleteProductImage(String frontImagePath,
                                    String backImagePath,
                                    String bannerImagePath,
                                    String mobileImagePath) {
        eventPublisher.publishEvent(new ImageDeletedEvent(frontImagePath));
        eventPublisher.publishEvent(new ImageDeletedEvent(backImagePath));
        eventPublisher.publishEvent(new ImageDeletedEvent(bannerImagePath));
        eventPublisher.publishEvent(new ImageDeletedEvent(mobileImagePath));
    }

    public String createItem(UUID productId, AdminRequest.CreateItem request) {
        var product = productRepository.get(productId);
        var item = itemFactory.create(request);
        item.setProduct(product);
        itemRepository.save(item);

        saveCollectionItem(item);
        return item.getEnName();
    }

    public void createMysteryItem(UUID mysteryProductId, AdminRequest.CreateItem request) {
        var mysteryProduct = mysteryProductRepository.get(mysteryProductId);
        var item = itemFactory.create(request);
        item.setMysteryProduct(mysteryProduct);
        itemRepository.save(item);

        saveCollectionItem(item);
    }

    private void saveCollectionItem(Item item) {
        var collections = collectionRepository.getAll();
        for(Collection collection: collections) {
            var collectionItem = CollectionItem.create(collection, item);
            collectionItemRepository.save(collectionItem);
        }
    }

    public void updateItem(String itemName, AdminRequest.UpdateItem request) {
        var item = itemRepository.getByEnNme(itemName);
        deleteItemImage(item);

        itemImageRepository.deletes(item.getImages());
        itemOptionRepository.deletes(item.getItemOptions());
        itemOptionGroupRepository.deletes(item.getItemOptionGroups());

        itemFactory.update(item, request);
    }

    public void changeItemStatus(String itemName, AdminRequest.ChangeItemStatus request) {
        var item = itemRepository.getByEnNme(itemName);
        var status = ItemStatus.toEnum(request.itemStatus());
        item.changeStatus(status);

        if(item.isSoldOut()) {
            cartItemRepository.deleteSoldOutCartItems();
        }
    }

    public void deleteItem(String name) {
        var item = itemRepository.getByEnNme(name);
        deleteItemImage(item);
        itemRepository.delete(item);
    }

    private void deleteItemImage(Item item) {
        eventPublisher.publishEvent(new ImageDeletedEvent(item.getGrayImagePath()));

        for (String path : item.getImagePaths()) {
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

    public Page<AdminResponse.OrderInfo> getAllOrder(String orderStatus, String deliveryTrackingNumber, String customerName, Pageable pageable) {
        return orderQuerydslRepository.getOrders(
                OrderStatus.toEnum(orderStatus),
                deliveryTrackingNumber,
                customerName, pageable)
            .map(AdminResponse.OrderInfo::new);
    }

    public AdminResponse.OrderDetail getOrder(UUID orderId) {
        var order = orderRepository.get(orderId);
        return new AdminResponse.OrderDetail(order);
    }

    public void registerDeliveryTrackingNumber(UUID orderId, AdminRequest.DeliveryTrackingNumber request) {
        var order = orderRepository.get(orderId);
        deliveryTracker.validateHasDeliveryTrackingNumber(request.trackingNumber());
        order.registerDeliveryTrackingNumber(request.trackingNumber());
    }
}
