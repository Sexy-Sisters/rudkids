package com.rudkids.rudkids.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudkids.rudkids.domain.auth.service.AuthService;
import com.rudkids.rudkids.domain.auth.OAuthClient;
import com.rudkids.rudkids.domain.auth.OAuthUri;
import com.rudkids.rudkids.domain.cart.service.CartService;
import com.rudkids.rudkids.domain.item.service.ItemService;
import com.rudkids.rudkids.domain.magazine.service.MagazineService;
import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.domain.user.service.UserService;
import com.rudkids.rudkids.interfaces.auth.AuthController;
import com.rudkids.rudkids.interfaces.auth.dto.AuthDtoMapper;
import com.rudkids.rudkids.interfaces.cart.CartController;
import com.rudkids.rudkids.interfaces.cart.dto.CartDtoMapper;
import com.rudkids.rudkids.interfaces.item.ItemController;
import com.rudkids.rudkids.interfaces.item.dto.ItemDtoMapper;
import com.rudkids.rudkids.interfaces.magazine.MagazineController;
import com.rudkids.rudkids.interfaces.magazine.dto.MagazineDtoMapper;
import com.rudkids.rudkids.interfaces.product.ProductController;
import com.rudkids.rudkids.interfaces.product.dto.ProductDtoMapper;
import com.rudkids.rudkids.interfaces.user.UserController;
import com.rudkids.rudkids.interfaces.user.dto.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureRestDocs
@WebMvcTest({
    UserController.class,
    AuthController.class,
    ProductController.class,
    ItemController.class,
    CartController.class,
    MagazineController.class
})
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;


    // service

    @MockBean
    protected UserService userService;

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


    // dto mapper

    @MockBean
    protected UserDtoMapper userDtoMapper;

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

}
