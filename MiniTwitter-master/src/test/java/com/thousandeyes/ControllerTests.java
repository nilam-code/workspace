package com.thousandeyes;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTests {

  String plainCreds = "Emma:thousandeyes";
  byte[] plainCredsBytes = plainCreds.getBytes();
  byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
  String base64Creds = new String(base64CredsBytes);

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void Test1() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("Authorization", "Basic " + base64Creds);
  HttpEntity<String> request = new HttpEntity<String>(headers);
  ResponseEntity<String> response = restTemplate.exchange("/", HttpMethod.GET, request, String.class);
  String body = response.getBody();
  assertThat(body).contains("Welcome to Mini Twitter App");
  }

  @Test
  public void Test2() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("Authorization", "Basic " + base64Creds);
  HttpEntity<String> request = new HttpEntity<String>(headers);
  ResponseEntity<String> response = restTemplate.exchange("/follow?name=Emma", HttpMethod.PUT, request, String.class);
  String body = response.getBody();
  assertThat(body).contains("cannot follow yourself");
  }

  @Test
  public void Test3() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("Authorization", "Basic " + base64Creds);
  HttpEntity<String> request = new HttpEntity<String>(headers);
  ResponseEntity<String> response = restTemplate.exchange("/unfollow?name=Emma", HttpMethod.PUT, request, String.class);
  String body = response.getBody();
  assertThat(body).contains("cannot unfollow yourself");
  }

  @Test
  public void Test4() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("Authorization", "Basic " + base64Creds);
  HttpEntity<String> request = new HttpEntity<String>(headers);
  ResponseEntity<String> response = restTemplate.exchange("/follow?name=Xandra", HttpMethod.PUT, request, String.class);
  String body = response.getBody();
  assertThat(body).contains("Success");
  }

  @Test
  public void Test5() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("Authorization", "Basic " + base64Creds);
  HttpEntity<String> request = new HttpEntity<String>(headers);
  ResponseEntity<String> response = restTemplate.exchange("/follow?name=Xandra", HttpMethod.PUT, request, String.class);
  String body = response.getBody();
  assertThat(body).contains("already follow");
  }

  @Test
  public void Test6() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("Authorization", "Basic " + base64Creds);
  HttpEntity<String> request = new HttpEntity<String>(headers);
  ResponseEntity<String> response = restTemplate.exchange("/following", HttpMethod.GET, request, String.class);
  String body = response.getBody();
  assertThat(body).contains("Xandra");
  }

  @Test
  public void Test7() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("Authorization", "Basic " + base64Creds);
  HttpEntity<String> request = new HttpEntity<String>(headers);
  ResponseEntity<String> response = restTemplate.exchange("/unfollow?name=Xandra", HttpMethod.PUT, request, String.class);
  String body = response.getBody();
  assertThat(body).contains("Success");
  }        

  @Test
  public void Test8() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("Authorization", "Basic " + base64Creds);
  HttpEntity<String> request = new HttpEntity<String>(headers);
  ResponseEntity<String> response = restTemplate.exchange("/unfollow?name=Xandra", HttpMethod.PUT, request, String.class);
  String body = response.getBody();
  assertThat(body).contains("need to follow");
  }

  @Test
  public void Test9() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("Authorization", "Basic " + base64Creds);
  HttpEntity<String> request = new HttpEntity<String>(headers);
  ResponseEntity<String> response = restTemplate.exchange("/feed?search=lorem", HttpMethod.GET, request, String.class);
  String body = response.getBody();
  assertThat(body).contains("lorem");
  }

  @Test
  public void Test10() {
  HttpHeaders headers = new HttpHeaders();
  headers.add("Authorization", "Basic " + base64Creds);
  HttpEntity<String> request = new HttpEntity<String>(headers);
  ResponseEntity<String> response = restTemplate.exchange("/followers", HttpMethod.GET, request, String.class);
  String body = response.getBody();
  assertThat(body).contains("Eagan");
  }      
}