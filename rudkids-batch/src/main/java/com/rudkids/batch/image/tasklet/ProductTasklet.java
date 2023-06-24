package com.rudkids.batch.image.tasklet;

import com.rudkids.core.image.service.S3ImageUploader;
import com.rudkids.core.product.domain.ProductBannerImageRepository;
import com.rudkids.core.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductTasklet implements Tasklet {
    private final ProductRepository productRepository;
    private final ProductBannerImageRepository productBannerImageRepository;
    private final S3ImageUploader s3ImageUploader;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<String> fileNames = Stream.concat(
                productRepository.getImageFileNames().stream(),
                productBannerImageRepository.getImageFileNames().stream())
            .toList();

        for (String fileName : fileNames) {
            s3ImageUploader.delete(fileName);
            log.info(">>>> 삭제햔 파일={}", fileName);
        }

        return RepeatStatus.FINISHED;
    }
}
