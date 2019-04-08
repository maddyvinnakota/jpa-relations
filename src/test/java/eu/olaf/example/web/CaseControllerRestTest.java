package eu.olaf.example.web;

import eu.olaf.example.SpringBootCrudRestApplication;
import eu.olaf.example.model.test.Case;
import eu.olaf.example.model.test.Seizure;
import eu.olaf.example.util.RestResponsePage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCrudRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaseControllerRestTest {

    @Autowired
    private TestRestTemplate restTmpl;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void test_get() {
        Long id = 11L;

        Case res = restTmpl.getForObject(getRootUrl() + "/cases" + "/" + id.toString(), Case.class);
        assertNotNull(res);
    }

    @Test
    public void test_save() {
        Case cas = new Case().withName("case1").withSeizure(new Seizure().withDesc("seizure1"));
//        cas.setRefNumber("test");
        ResponseEntity<Case> res = restTmpl.postForEntity(getRootUrl() + "/cases", (Object) cas, Case.class);
        assertNotNull(res);
        assertEquals(200, res.getStatusCode().value());
    }


    @Test
    public void test_scenario_entity_issue() {

        //create  case1
            Case cas1 = new Case().withName("case1").withSeizure(new Seizure().withDesc("seizure1"));
            ResponseEntity<Case> res1 = restTmpl.postForEntity(getRootUrl() + "/cases", cas1, Case.class);
            Case cas1WithId = res1.getBody();
            assertNotNull(res1);
            assertEquals(200, res1.getStatusCode().value());

//create  case2
            Case cas2 = new Case().withName("case2").withSeizure(new Seizure().withDesc("seizure2"));
            ResponseEntity<Case> res2 = restTmpl.postForEntity(getRootUrl() + "/cases", cas2, Case.class);
            Case cas2WithId = res2.getBody();
            assertNotNull(res2);
            assertEquals(200, res2.getStatusCode().value());



        //list all cases
            ParameterizedTypeReference<RestResponsePage<Case>> ptr = new ParameterizedTypeReference<RestResponsePage<Case>>() { };

            ResponseEntity<RestResponsePage<Case>> resList = restTmpl.exchange(getRootUrl() + "/cases/", GET, null, ptr);
            assertNotNull(resList);
            assertEquals(200, resList.getStatusCode().value());
            assertEquals(2, resList.getBody().getContent().size());

            System.out.println(resList.getBody().getContent());

        //update case2 with seizure1

            cas2WithId.setSeizure(cas1WithId.getSeizure());
            HttpEntity<Case> requestEntity = new HttpEntity<>(cas2WithId);
            //String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType

            ResponseEntity<Case> resUpdateCas2 = restTmpl.exchange(getRootUrl() + "/cases/" + cas2WithId.getId().toString(), PUT, requestEntity, Case.class);
            Case cas2WithIdUpdated = resUpdateCas2.getBody();
            assertNotNull(resUpdateCas2);
            assertEquals(200, resUpdateCas2.getStatusCode().value());
//            assertNotNull(cas2WithIdUpdated);


        ParameterizedTypeReference<RestResponsePage<Case>> ptr1 = new ParameterizedTypeReference<RestResponsePage<Case>>() { };

        ResponseEntity<RestResponsePage<Case>> resList1 = restTmpl.exchange(getRootUrl() + "/cases/", GET, null, ptr1);
        assertNotNull(resList1);
        assertEquals(200, resList1.getStatusCode().value());
        assertEquals(2, resList1.getBody().getContent().size());

        System.out.println(resList1.getBody().getContent());


//

    }
}
