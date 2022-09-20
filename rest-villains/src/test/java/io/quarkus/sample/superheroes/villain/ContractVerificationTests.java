package io.quarkus.sample.superheroes.villain;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.ExtendWith;

import io.quarkus.sample.superheroes.villain.service.VillainService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerConsumerVersionSelectors;
import au.com.dius.pact.provider.junitsupport.loader.SelectorBuilder;

@QuarkusTest
@Provider("rest-villains")
@PactBroker(url = "https://quarkus-super-heroes.pactflow.io")
@EnabledIfSystemProperty(named = "pactbroker.auth.token", matches = ".+", disabledReason = "pactbroker.auth.token system property not set")
//@TestTransaction
public class ContractVerificationTests {
  @ConfigProperty(name = "quarkus.http.test-port")
  int quarkusPort;

  @InjectSpy
  VillainService villainService;

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @BeforeEach
  void beforeEach(PactVerificationContext context) {
    context.setTarget(new HttpTestTarget("localhost", this.quarkusPort));
  }

  @PactBrokerConsumerVersionSelectors
  public static SelectorBuilder consumerVersionSelectors() {
    return new SelectorBuilder()
      .branch(System.getProperty("pactbroker.consumer.branch", "main"));
  }

  @State("No random villain found")
  public void clearData() {
    when(this.villainService.findRandomVillain())
      .thenReturn(Optional.empty());
  }
}
