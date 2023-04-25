package com.rudkids.rudkids.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudkids.rudkids.domain.auth.service.AuthService;
import com.rudkids.rudkids.domain.auth.OAuthClient;
import com.rudkids.rudkids.domain.auth.OAuthUri;
import com.rudkids.rudkids.domain.cart.service.CartService;
import com.rudkids.rudkids.domain.item.service.ItemService;
import com.rudkids.rudkids.domain.magazine.service.MagazineService;
import com.rudkids.rudkids.domain.order.service.OrderService;
import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.interfaces.auth.AuthController;
import com.rudkids.rudkids.interfaces.auth.dto.AuthDtoMapper;
import com.rudkids.rudkids.interfaces.cart.CartController;
import com.rudkids.rudkids.interfaces.cart.dto.CartDtoMapper;
import com.rudkids.rudkids.interfaces.item.ItemController;
import com.rudkids.rudkids.interfaces.item.dto.ItemDtoMapper;
import com.rudkids.rudkids.interfaces.magazine.MagazineController;
import com.rudkids.rudkids.interfaces.magazine.dto.MagazineDtoMapper;
import com.rudkids.rudkids.interfaces.order.OrderController;
import com.rudkids.rudkids.interfaces.order.dto.OrderDtoMapper;
import com.rudkids.rudkids.interfaces.product.ProductController;
import com.rudkids.rudkids.interfaces.product.dto.ProductDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureRestDocs
@WebMvcTest({
    AuthController.class,
    ProductController.class,
    ItemController.class,
    CartController.class,
    MagazineController.class,
    OrderController.class
})
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;


    // service

    @MockBean
    protected AuthService authService;

    @MockBean
    protected ProductService productService;

    @MockBean
    protected ItemService itemService;

    @MockBean
    protected CartService cartService;

    @MockBean
    protected MagazineService magazineService;

    @MockBean
    protected OrderService orderService;

    // dto mapper

    @MockBean
    protected AuthDtoMapper authDtoMapper;

    @MockBean
    protected ProductDtoMapper productDtoMapper;

    @MockBean
    protected ItemDtoMapper itemDtoMapper;

    @MockBean
    protected CartDtoMapper cartDtoMapper;

    @MockBean
    protected MagazineDtoMapper magazineDtoMapper;

    @MockBean
    protected OrderDtoMapper orderDtoMapper;

    // etc

    @MockBean
    protected RestTemplateBuilder restTemplateBuilder;

    @Autowired
    protected ObjectMapper objectMapper;


    // auth

    @MockBean
    protected OAuthUri oAuthUri;

    @MockBean
    protected OAuthClient oAuthClient;


    protected static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    protected static final String AUTHORIZATION_HEADER_VALUE = "Bearer aaaaaaaa.bbbbbbbb.cccccccc";
}
