package com.rudkids.rudkids.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudkids.rudkids.domain.admin.service.AdminService;
import com.rudkids.rudkids.domain.auth.service.AuthService;
import com.rudkids.rudkids.domain.auth.OAuthClient;
import com.rudkids.rudkids.domain.auth.OAuthUri;
import com.rudkids.rudkids.domain.cart.service.CartService;
import com.rudkids.rudkids.domain.image.service.ImageService;
import com.rudkids.rudkids.domain.item.service.ItemService;
import com.rudkids.rudkids.domain.community.service.CommunityService;
import com.rudkids.rudkids.domain.order.service.OrderService;
import com.rudkids.rudkids.domain.product.service.ProductService;
import com.rudkids.rudkids.domain.user.service.UserService;
import com.rudkids.rudkids.interfaces.admin.AdminController;
import com.rudkids.rudkids.interfaces.admin.dto.AdminDtoMapper;
import com.rudkids.rudkids.interfaces.auth.AuthController;
import com.rudkids.rudkids.interfaces.auth.dto.AuthDtoMapper;
import com.rudkids.rudkids.interfaces.cart.CartController;
import com.rudkids.rudkids.interfaces.cart.dto.CartDtoMapper;
import com.rudkids.rudkids.interfaces.image.ImageController;
import com.rudkids.rudkids.interfaces.item.ItemController;
import com.rudkids.rudkids.interfaces.item.dto.ItemDtoMapper;
import com.rudkids.rudkids.interfaces.community.CommunityController;
import com.rudkids.rudkids.interfaces.community.dto.CommunityDtoMapper;
import com.rudkids.rudkids.interfaces.order.OrderController;
import com.rudkids.rudkids.interfaces.order.dto.OrderDtoMapper;
import com.rudkids.rudkids.interfaces.product.ProductController;
import com.rudkids.rudkids.interfaces.product.dto.ProductDtoMapper;
import com.rudkids.rudkids.interfaces.user.UserController;
import com.rudkids.rudkids.interfaces.user.dto.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@AutoConfigureRestDocs
@WebMvcTest({
    AuthController.class,
    ProductController.class,
    ItemController.class,
    CartController.class,
    CommunityController.class,
    OrderController.class,
    AdminController.class,
    ImageController.class,
    UserController.class
})
@ActiveProfiles("test")
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
    protected CommunityService communityService;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected AdminService adminService;

    @MockBean
    protected ImageService imageService;

    @MockBean
    protected UserService userService;

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
    protected CommunityDtoMapper communityDtoMapper;

    @MockBean
    protected OrderDtoMapper orderDtoMapper;

    @MockBean
    protected AdminDtoMapper adminDtoMapper;

    @MockBean
    protected UserDtoMapper userDtoMapper;

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

    protected static FieldDescriptor Error_응답_필드() {
        return fieldWithPath("message")
            .type(JsonFieldType.STRING)
            .description("에러 메시지");
    }

    protected HeaderDescriptor JWT_ACCESS_TOKEN() {
        return headerWithName("Authorization")
            .description("JWT Access Token");
    }

    public static List<FieldDescriptor> pageResponseFieldsWith(List<FieldDescriptor> content) {
        return Stream.concat(
                Stream.of(
                    fieldWithPath("pageable")
                        .type(JsonFieldType.STRING)
                        .description("페이징 응답"),

                    fieldWithPath("last")
                        .type(JsonFieldType.BOOLEAN)
                        .description("마지막 페이지 인지 아닌지 여부"),

                    fieldWithPath("totalPages")
                        .type(JsonFieldType.NUMBER)
                        .description("총 페이지 수"),

                    fieldWithPath("totalElements")
                        .type(JsonFieldType.NUMBER)
                        .description("프로덕트 전체 데이터 갯수"),

                    fieldWithPath("first")
                        .type(JsonFieldType.BOOLEAN)
                        .description("첫번째 페이지 인지 여부"),

                    fieldWithPath("size")
                        .type(JsonFieldType.NUMBER)
                        .description("페이지 당 나타낼 수 있는 데이터의 갯수"),

                    fieldWithPath("number")
                        .type(JsonFieldType.NUMBER)
                        .description("현재 페이지의 번호"),

                    fieldWithPath("sort")
                        .type(JsonFieldType.OBJECT)
                        .description("정렬 값들"),

                    fieldWithPath("sort.empty")
                        .type(JsonFieldType.BOOLEAN)
                        .description("정렬 값이 비어있는지 여부"),

                    fieldWithPath("sort.sorted")
                        .type(JsonFieldType.BOOLEAN)
                        .description("정렬 했는지 여부"),

                    fieldWithPath("sort.unsorted")
                        .type(JsonFieldType.BOOLEAN)
                        .description("정렬 하지 않았는지 여부"),

                    fieldWithPath("numberOfElements")
                        .type(JsonFieldType.NUMBER)
                        .description("실제 데이터의 갯수"),

                    fieldWithPath("empty")
                        .type(JsonFieldType.BOOLEAN)
                        .description("리스트가 비어있는지 여부")

                ), content.stream())
            .toList();
    }
}
