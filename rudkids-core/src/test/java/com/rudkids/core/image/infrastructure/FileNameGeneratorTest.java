package com.rudkids.core.image.infrastructure;

import com.rudkids.core.common.fixtures.image.FileNameGeneratorFixtures;
import com.rudkids.core.image.exception.UnsupportedFileExtensionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileNameGeneratorTest extends FileNameGeneratorFixtures {

    @Nested
    @DisplayName("파일이름을 생성한다")
    class generate {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            MultipartFile file = file();

            //when
            String filaName = fileNameGenerator.generate(file.getOriginalFilename());

            //then
            assertThat(filaName).isNotEmpty();
        }

        @Test
        @DisplayName("실패: 지원하지 않는 확장자")
        void fail() {
            //given
            final String invalidFileName = "23423581924hfsfauheadaf.php";

            //when, then
            assertThatThrownBy(() -> fileNameGenerator.generate(invalidFileName))
                .isInstanceOf(UnsupportedFileExtensionException.class);
        }
    }
}
