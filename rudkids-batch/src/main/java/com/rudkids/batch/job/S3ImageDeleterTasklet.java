package com.rudkids.batch.job;

import com.rudkids.core.community.domain.CommunityRepository;
import com.rudkids.core.image.service.S3ImageClient;
import com.rudkids.core.item.domain.ItemRepository;
import com.rudkids.core.product.domain.ProductRepository;
import com.rudkids.core.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3ImageDeleterTasklet implements Tasklet {
    private final S3ImageClient s3ImageClient;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<String> userImagePaths = userRepository.getImagePaths();
        List<String> communityImagePaths = communityRepository.getImagePaths();
        List<String> productImagePaths = productRepository.getImagePaths();
        List<String> itemImagePaths = itemRepository.getImagePaths();

        List<String> objectKeys = s3ImageClient.getObjectKeys();
        objectKeys.removeAll(userImagePaths);
        objectKeys.removeAll(communityImagePaths);
        objectKeys.removeAll(productImagePaths);
        objectKeys.removeAll(itemImagePaths);

        for(String key: objectKeys) {
            s3ImageClient.delete(key);
        }

        return RepeatStatus.FINISHED;
    }
}
