package com.rudkids.api.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudkids.api.admin.AdminController;
import com.rudkids.api.auth.AuthController;
import com.rudkids.api.cart.CartController;
import com.rudkids.api.community.CommunityController;
import com.rudkids.api.delivery.DeliveryController;
import com.rudkids.api.image.ImageController;
import com.rudkids.api.item.ItemController;
import com.rudkids.api.order.OrderController;
import com.rudkids.api.payment.PaymentController;
import com.rudkids.api.product.ProductController;
import com.rudkids.api.user.UserController;
import com.rudkids.core.admin.service.AdminService;
import com.rudkids.core.auth.service.AuthService;
import com.rudkids.core.auth.service.OAuthClient;
import com.rudkids.core.auth.service.OAuthUri;
import com.rudkids.core.cart.service.CartService;
import com.rudkids.core.community.service.CommunityService;
import com.rudkids.core.delivery.service.DeliveryService;
import com.rudkids.core.image.service.ImageService;
import com.rudkids.core.image.service.ImageClient;
import com.rudkids.core.item.service.ItemService;
import com.rudkids.core.order.service.OrderService;
import com.rudkids.core.payment.service.PaymentService;
import com.rudkids.core.product.service.ProductService;
import com.rudkids.core.user.service.UserService;
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
    UserController.class,
    DeliveryController.class,
    PaymentController.class
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

    @MockBean
    protected DeliveryService deliveryService;

    @MockBean
    protected PaymentService paymentService;

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

    // image
    @MockBean
    private ImageClient imageClient;


    protected static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    protected static final String AUTHORIZATION_HEADER_VALUE = "Bearer aaaaaaaa.bbbbbbbb.cccccccc";

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
