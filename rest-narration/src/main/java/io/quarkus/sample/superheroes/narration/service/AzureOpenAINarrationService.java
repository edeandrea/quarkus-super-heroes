package io.quarkus.sample.superheroes.narration.service;

import java.time.temporal.ChronoUnit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.sample.superheroes.narration.Fight;
import io.quarkus.sample.superheroes.narration.config.NarrationConfig;

import com.azure.ai.openai.OpenAIAsyncClient;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import io.smallrye.faulttolerance.api.CircuitBreakerName;
import io.smallrye.mutiny.Uni;

@LookupIfProperty(name = "narration.azure-open-ai.enabled", stringValue = "true")
@ApplicationScoped
public final class AzureOpenAINarrationService extends OpenAINarrationServiceBase {
  private final NarrationConfig narrationConfig;
  private final OpenAIAsyncClient openAIAsyncClient;

  public AzureOpenAINarrationService(NarrationConfig narrationConfig, Instance<OpenAIAsyncClient> openAIAsyncClient) {
    super();
    this.narrationConfig = narrationConfig;
    this.openAIAsyncClient = openAIAsyncClient.get();
  }

  @Override
  @Timeout(value = 10, unit = ChronoUnit.SECONDS)
  @Fallback(fallbackMethod = "fallbackNarrate")
  @CircuitBreaker(requestVolumeThreshold = 8, failureRatio = 0.5, delay = 2, delayUnit = ChronoUnit.SECONDS)
  @CircuitBreakerName("azureOpenAINarrate")
  @Retry(maxRetries = 3, delay = 200, delayUnit = ChronoUnit.MILLIS)
  @WithSpan(kind = SpanKind.CLIENT, value="AzureOpenAINarrationService.narrate")
  public Uni<String> narrate(@SpanAttribute("arg.fight") Fight fight) {
    return narrateFight(fight);
  }

  @Override
  protected String getModelId() {
    return this.narrationConfig.azureOpenAi().deploymentName();
  }

  @Override
  protected NarrationConfig getNarrationConfig() {
    return this.narrationConfig;
  }

  @Override
  protected OpenAIAsyncClient getOpenAIClient() {
    return this.openAIAsyncClient;
  }
}
