package com.ymmihw.spring.batch.chunks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ymmihw.spring.batch.config.ChunksConfig;
import com.ymmihw.spring.batch.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ChunksConfig.class, TestConfig.class})
public class ChunksIntegrationTest {

  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Test
  public void givenChunksJob_WhenJobEnds_ThenStatusCompleted() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    Assert.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
  }
}
