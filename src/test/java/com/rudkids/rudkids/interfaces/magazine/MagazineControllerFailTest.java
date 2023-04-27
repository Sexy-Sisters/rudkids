package com.rudkids.rudkids.interfaces.magazine;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
import com.rudkids.rudkids.domain.user.exception.NotAdminRoleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.magazine.MagazineControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MagazineControllerFailTest extends ControllerTest {

    @DisplayName("[매거진-상세조회-404-에러]")
    @Test
    void 존재하지_않는_매거진을_상세조회할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new MagazineNotFoundException())
                .when(magazineService)
                .find(any());

        mockMvc.perform(get(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID))
                .andDo(print())
                .andDo(document("magazine/find/failByNotFoundError",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id")
                                        .description("존재하지 않는 매거진 id")
                        )
                ))
                .andExpect(status().isNotFound());
    }
}
