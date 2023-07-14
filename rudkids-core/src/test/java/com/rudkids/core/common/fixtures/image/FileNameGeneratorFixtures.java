package com.rudkids.core.common.fixtures.image;

import com.rudkids.core.common.ServiceTest;
import com.rudkids.core.image.infrastructure.FileNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTest
public class FileNameGeneratorFixtures {

    @Autowired
    protected FileNameGenerator fileNameGenerator;
}
