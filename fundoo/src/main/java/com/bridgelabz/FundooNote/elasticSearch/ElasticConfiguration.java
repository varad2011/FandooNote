/*
 * package com.bridgelabz.FundooNote.elasticSearch;
 * 
 * import org.elasticsearch.client.Client; import
 * org.elasticsearch.client.transport.TransportClient; import
 * org.elasticsearch.common.settings.Settings; import
 * org.elasticsearch.common.transport.TransportAddress; import
 * org.elasticsearch.transport.client.PreBuiltTransportClient; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.context.annotation.PropertySource; import
 * org.springframework.data.elasticsearch.core.ElasticsearchOperations; import
 * org.springframework.data.elasticsearch.core.ElasticsearchTemplate; import
 * org.springframework.data.elasticsearch.repository.config.
 * EnableElasticsearchRepositories; import java.net.InetAddress; import
 * java.net.UnknownHostException;
 * 
 * @PropertySource(value = "classpath:application.properties")
 * 
 * @Configuration
 * 
 * @EnableElasticsearchRepositories(basePackages =
 * "com.bridgelabz.FundooNote.elasticSearch.NoteElasticSearchRepository") public
 * class ElasticConfiguration {
 * 
 * 
 * @Value("${elasticsearch.host:localhost}") public String host;
 * 
 * @Value("${elasticsearch.port:9300}") public int port;
 * 
 * public String getHost() { return host; }
 * 
 * 
 * public int getPort() { return port; }
 * 
 * @SuppressWarnings("resource")
 * 
 * @Bean public Client client(){ TransportClient client = null; try{
 * System.out.println("host:"+ host+"port:"+port); client = new
 * PreBuiltTransportClient(Settings.EMPTY) .addTransportAddress(new
 * TransportAddress(InetAddress.getByName(host), port)); } catch
 * (UnknownHostException e) { e.printStackTrace(); } return client; } }
 */