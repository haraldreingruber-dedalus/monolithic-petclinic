package org.springframework.samples.petclinic;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import org.springframework.samples.petclinic.vets.VetDTO;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class VetsApiClientTest {

    private final WireMockServer wireMock = new WireMockServer(options().port(8089));

    @BeforeEach
    void startWireMock() {
        wireMock.start();
    }

    @AfterEach
    void stopWireMock() {
        wireMock.stop();
    }

    @Test
    void how_to_stub_a_server_with_wiremock() {
        wireMock.stubFor(get(urlEqualTo("/vets"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("[{\"specialties\":[],\"firstName\":\"James\",\"lastName\":\"Carter\",\"nrOfSpecialties\":0},{\"specialties\":[{\"name\":\"radiology\"}],\"firstName\":\"Helen\",\"lastName\":\"Leary\",\"nrOfSpecialties\":1},{\"specialties\":[{\"name\":\"dentistry\"},{\"name\":\"surgery\"}],\"firstName\":\"Linda\",\"lastName\":\"Douglas\",\"nrOfSpecialties\":2},{\"specialties\":[{\"name\":\"surgery\"}],\"firstName\":\"Rafael\",\"lastName\":\"Ortega\",\"nrOfSpecialties\":1},{\"specialties\":[{\"name\":\"radiology\"}],\"firstName\":\"Henry\",\"lastName\":\"Stevens\",\"nrOfSpecialties\":1},{\"specialties\":[],\"firstName\":\"Sharon\",\"lastName\":\"Jenkins\",\"nrOfSpecialties\":0}]")));

        VetDTO[] result = new RestTemplate().getForObject("http://localhost:8089/vets", VetDTO[].class);

        assertThat(result.length == 6);
    }
}