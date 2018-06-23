package com.ymmihw.spring.batch.tasklets;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import com.ymmihw.spring.batch.model.Line;
import com.ymmihw.spring.batch.utils.FileUtils;

public class LinesWriter implements Tasklet, StepExecutionListener {

  private final Logger logger = LoggerFactory.getLogger(LinesWriter.class);

  private List<Line> lines;
  private FileUtils fu;

  @Override
  public void beforeStep(StepExecution stepExecution) {
    ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
    this.lines = (List<Line>) executionContext.get("lines");
    fu = new FileUtils("output.csv");
    logger.debug("Lines Writer initialized.");
  }

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
      throws Exception {
    for (Line line : lines) {
      fu.writeLine(line);
      logger.debug("Wrote line " + line.toString());
    }
    return RepeatStatus.FINISHED;
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    fu.closeWriter();
    logger.debug("Lines Writer ended.");
    return ExitStatus.COMPLETED;
  }
}